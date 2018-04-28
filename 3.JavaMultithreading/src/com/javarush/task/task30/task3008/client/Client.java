package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.ConsoleHelper;
import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.net.Socket;

public class Client {
    /*
    Создай поле Connection connection в классе Client. Используй модификатор доступа,
    который позволит обращаться к этому полю из класса потомков, но запретит обращение
     из других классов вне пакета.
     */
    protected Connection connection;
    /*
     Добавь приватное поле-флаг boolean clientConnected в класс Client. Проинициализируй
     его значением false. В дальнейшем оно будет устанавливаться в true, если клиент
     подсоединен к серверу или в false в противном случае. При объявлении этого поля
     используй ключевое слово, которое позволит гарантировать что каждый поток,
     использующий поле clientConnected, работает с актуальным, а не кэшированным его значением.
     */
    private volatile boolean clientConnected = false;

    public class SocketThread extends Thread {
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
        }

        protected void informAboutAddingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " присоединился к чату");
        }

        protected void informAboutDeletingNewUser(String userName) {
            ConsoleHelper.writeMessage(userName + " покинул чат");
        }

        protected void notifyConnectionStatusChanged(boolean clientConnected) {
            Client.this.clientConnected = clientConnected;
            synchronized (Client.this) {
                Client.this.notify();
            }
        }
//        Этот метод будет представлять клиента серверу.

        protected void clientHandshake() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.NAME_REQUEST) {
                    String userName = getUserName();
                    Message result = new Message(MessageType.USER_NAME, userName);
                    connection.send(result);
                } else if (message.getType() == MessageType.NAME_ACCEPTED) {
                    notifyConnectionStatusChanged(true);
                    return;
                } else throw new IOException("Unexpected MessageType");

            }
        }


        //        Этот метод будет реализовывать главный цикл обработки сообщений сервера.
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    processIncomingMessage(message.getData());
                } else if (message.getType() == MessageType.USER_ADDED) {
                    informAboutAddingNewUser(message.getData());
                } else if (message.getType() == MessageType.USER_REMOVED) {
                    informAboutDeletingNewUser(message.getData());
                } else {
                    throw new IOException("Unexpected MessageType");
                }
            }
        }

        public void run() {
            try {
                String serverAddress = getServerAddress();
                int serverPort = getServerPort();
                Socket socket = new Socket(serverAddress, serverPort);
                connection = new Connection(socket);
                clientHandshake();
                clientMainLoop();
            } catch (IOException e) {
                notifyConnectionStatusChanged(false);
            } catch (ClassNotFoundException e) {
                notifyConnectionStatusChanged(false);
            }
        }
    }

    /*
    String getServerAddress() - должен запросить ввод адреса сервера у пользователя и вернуть
    введенное значение. Адрес может быть строкой, содержащей ip, если клиент и сервер запущен
    на разных машинах или 'localhost', если клиент и сервер работают на одной машине.
     */
    protected String getServerAddress() {
        ConsoleHelper.writeMessage("Введите адрес сервера");
        return ConsoleHelper.readString();
    }

    //    int getServerPort() - должен запрашивать ввод порта сервера и возвращать его.
    protected int getServerPort() {
        ConsoleHelper.writeMessage("Введите порт сервера");
        return ConsoleHelper.readInt();
    }

    //    String getUserName() - должен запрашивать и возвращать имя пользователя.
    protected String getUserName() {
        ConsoleHelper.writeMessage("Введите ваше имя");
        return ConsoleHelper.readString();
    }

    /*
    4. boolean shouldSendTextFromConsole() - в данной реализации клиента всегда должен
    возвращать true (мы всегда отправляем текст введенный в консоль). Этот метод может
    быть переопределен, если мы будем писать какой-нибудь другой клиент, унаследованный
    от нашего, который не должен отправлять введенный в консоль текст.
     */
    protected boolean shouldSendTextFromConsole() {
        return true;
    }

    /*
    SocketThread getSocketThread() - должен создавать и возвращать новый объект класса SocketThread.
     */
    protected SocketThread getSocketThread() {
        return new SocketThread();
    }

    /*
    void sendTextMessage(String text) - создает новое текстовое сообщение, используя
    переданный текст и отправляет его серверу через соединение connection.
Если во время отправки произошло исключение IOException, то необходимо вывести
информацию об этом пользователю и присвоить false полю clientConnected.
     */
    protected void sendTextMessage(String text) {
        try {
            Message message = new Message(MessageType.TEXT, text);
            connection.send(message);
        } catch (IOException e) {
            ConsoleHelper.writeMessage("Сообщение не удалось отправить");
            clientConnected = false;
        }
    }

    public void run() {
//        новый сокетный поток
        SocketThread socketThread = this.getSocketThread();
//        Помечать созданный поток как daemon, это нужно для того, чтобы при выходе
// из программы вспомогательный поток прервался автоматически.
        socketThread.setDaemon(true);
//        Запустить вспомогательный поток.
        socketThread.start();
//        Заставить текущий поток ожидать, пока он не получит нотификацию из другого потока
        try {
            synchronized (this) {
                this.wait();
            }
        } catch (InterruptedException e) {
            ConsoleHelper.writeMessage("Возникла ошибка");
//           ////////// socketThread.interrupt(); // было так, return не было
            return;
        }

        if (clientConnected) {
            ConsoleHelper.writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
        } else {
            ConsoleHelper.writeMessage("Произошла ошибка во время работы клиента.");
        }
//        Считывай сообщения с консоли пока клиент подключен. Если будет введена команда 'exit', то выйди из цикла.
        while (clientConnected) {
//            После каждого считывания, если метод shouldSendTextFromConsole() возвращает true, отправь
// считанный текст с помощью метода sendTextMessage().
            String text = ConsoleHelper.readString();
            if (text.equals("exit")) {
                break;
            } else {
                if (shouldSendTextFromConsole()) {
                    this.sendTextMessage(text);
                }
            }
        }
    }

    //должен создавать новый объект типа Client и вызывать у него метод run().
    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

}
