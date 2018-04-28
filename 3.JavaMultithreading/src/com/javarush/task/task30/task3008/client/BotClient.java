package com.javarush.task.task30.task3008.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BotClient extends Client {

    public static void main(String[] args) {
        BotClient botClient = new BotClient();
        botClient.run();

        System.out.println(botClient.getUserName());
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int) (Math.random() * 100);
    }

    public class BotSocketThread extends SocketThread {
        public BotSocketThread() {
        }

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);
            if (message.contains(":")) {
                String userName, textMessage;
                String[] strings = message.split(": ");
                userName = strings[0];
                textMessage = strings[1];

                HashMap<String, SimpleDateFormat> dates = new HashMap<String, SimpleDateFormat>() {
                    {
                        put("дата", new SimpleDateFormat("d.MM.YYYY"));
                        put("день", new SimpleDateFormat("d"));
                        put("месяц", new SimpleDateFormat("MMMM"));
                        put("год", new SimpleDateFormat("YYYY"));
                        put("время", new SimpleDateFormat("H:mm:ss"));
                        put("час", new SimpleDateFormat("H"));
                        put("минуты", new SimpleDateFormat("m"));
                        put("секунды", new SimpleDateFormat("s"));
                    }
                };

                for (Map.Entry<String, SimpleDateFormat> map : dates.entrySet()) {
                    if (textMessage.equals(map.getKey())) {
                        sendTextMessage("Информация для " + userName + ": "
                                + map.getValue().format(Calendar.getInstance().getTime()));
                    }

                }
            }
        }
    }

}