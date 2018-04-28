package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
- Сервер создает серверное сокетное соединение.
- В цикле ожидает, когда какой-то клиент подключится к сокету.
- Создает новый поток обработчик Handler, в котором будет происходить обмен
сообщениями с клиентом.
- Ожидает следующее соединение.
 */
public class Server {
    /*
    Метод main класса Server, должен:
    а) Запрашивать порт сервера, используя ConsoleHelper.
    б) Создавать серверный сокет java.net.ServerSocket, используя порт из предыдущего пункта.
    в) Выводить сообщение, что сервер запущен.
    г) В бесконечном цикле слушать и принимать входящие сокетные соединения только что созданного
    серверного сокета.
    д) Создавать и запускать новый поток Handler, передавая в конструктор сокет из предыдущего пункта.
    е) После создания потока обработчика Handler переходить на новый шаг цикла.
    ж) Предусмотреть закрытие серверного сокета в случае возникновения исключения.
    з) Если исключение Exception все же произошло, поймать его и вывести сообщение
    об ошибке.
     */
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static Handler handler;
    private static int serverPort;
    /*
    Статическое поле Map<String, Connection> connectionMap, где ключом будет имя
    клиента, а значением - соединение с ним.
    2. Инициализацию поля из п.7.1 с помощью подходящего Map из библиотеки
    java.util.concurrent, т.к. работа с этим полем будет происходить из разных потоков и
    нужно обеспечить потокобезопасность.
     */
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        //ДОПИСАЛ САМ//////////////////////////////////////////////////////////
        ConsoleHelper.writeMessage("ВВедите порт сервера");

        serverPort = ConsoleHelper.readInt();

        try {
            serverSocket = new ServerSocket(serverPort);
            System.out.println("Сервер запущен.");

        } catch (IOException e) {
            e.printStackTrace();
            try {
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        while (true) {
            try {
                socket = serverSocket.accept();
                handler = new Handler(socket);
                handler.start();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                    serverSocket.close();
                    break;
                } catch (IOException e1) {
                    System.out.println(e1);
                    break;
                }
            }
        }


    }

    /*
    Статический метод void sendBroadcastMessage(Message message), который должен
    отправлять сообщение message всем соединениям из connectionMap. Если при
    отправке сообщение произойдет исключение IOException, нужно отловить его и
    сообщить пользователю, что не смогли отправить сообщение.
     */
    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> map : connectionMap.entrySet()) {
            try {
                map.getValue().send(message);
            } catch (IOException e) {
                System.out.println("Отправить сообщение не получилось.");
            }
        }
    }

    /*
    Класс Handler должен реализовывать протокол общения с клиентом.
     */
    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        /*
        Метод в качестве параметра принимает соединение connection, а возвращает имя нового клиента.
        Реализация метода должна:
1) Сформировать и отправить команду запроса имени пользователя
2) Получить ответ клиента
3) Проверить, что получена команда с именем пользователя
4) Достать из ответа имя, проверить, что оно не пустое и пользователь с таким именем еще не подключен (используй connectionMap)
5) Добавить нового пользователя и соединение с ним в connectionMap
6) Отправить клиенту команду информирующую, что его имя принято
7) Если какая-то проверка не прошла, заново запросить имя клиента
8) Вернуть принятое имя в качестве возвращаемого значения
         */
        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {

            String userName;
            while (true) {
//                Сформировать и отправить команду запроса имени пользователя
                connection.send(new Message(MessageType.NAME_REQUEST));
//                Получить ответ клиента
                Message message = connection.receive();
//                Проверить, что получена команда с именем пользователя
                if (message.getType() == MessageType.USER_NAME) {
//                    Достать из ответа имя
                    userName = message.getData();
//                    проверить, что оно не пустое и пользователь с таким именем еще не подключен (используй connectionMap)
                    if (userName != "" && !connectionMap.containsKey(userName)) {
//                        Добавить нового пользователя и соединение с ним в connectionMap
                        connectionMap.put(userName, connection);
//                Отправить клиенту команду информирующую, что его имя принято
                        connection.send(new Message(MessageType.NAME_ACCEPTED));
                        return userName;
//                    } else {
//                        continue;
                    }
//                } else {
//                    continue;
                }

            }

        }

        /*
        отправка клиенту (новому участнику) информации об
остальных клиентах (участниках) чата.
         */
        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (Map.Entry<String, Connection> map :
                    connectionMap.entrySet()) {
                if (!map.getKey().equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, map.getKey()));
                }
            }
        }

        //        главный цикл обработки сообщений сервером
        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    Server.sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                } else {
                    ConsoleHelper.writeMessage("Произошла ошибка при отправке сообщения, проверьте, что был введен текст.");
                }
            }
        }

        /*
        главный метод класса Handler, который будет вызывать все
        вспомогательные методы, написанные ранее
         */
        @Override
        public void run() {
//            Выводить сообщение, что установлено новое соединение с удаленным адресом
            ConsoleHelper.writeMessage("Установлено новое соединение с удаленным адресом: " + socket.getRemoteSocketAddress());
            try {

//                Создавать Connection, используя поле socket.
                Connection connection = new Connection(socket);
//                Вызывать метод, реализующий рукопожатие с клиентом, сохраняя имя нового клиента.
                ///////////можно без this?
                String userName = serverHandshake(connection);
//                Рассылать всем участникам чата информацию об имени присоединившегося участника
                ///////////можно без Server.?
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
//                Сообщать новому участнику о существующих участниках.
                sendListOfUsers(connection, userName);
//                Запускать главный цикл обработки сообщений сервером.
                serverMainLoop(connection, userName);
//                Обеспечить закрытие соединения при возникновении исключения.
            /*
            После того как все исключения обработаны, если п.11.3 отработал и возвратил нам имя, мы должны
             удалить запись для этого имени из connectionMap и разослать всем остальным участникам сообщение
              с типом USER_REMOVED и сохраненным именем.
             */
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
//            Последнее, что нужно сделать в методе run() - вывести сообщение, информирующее что соединение с
//            удаленным адресом закрыто.
                ConsoleHelper.writeMessage("Соединение с удаленным адресом закрыто.");
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными");
            } catch (ClassNotFoundException c) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными");
            }
        }
    }
}
