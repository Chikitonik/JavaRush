package com.javarush.task.task27.task2710;

/* 
Расставьте wait-notify
*/
public class Solution {
    public static void main(String[] args) {
        Mail mail = new Mail();
        Thread server = new Thread(new MailServer(mail));
        Thread amigo = new Thread(new Person(mail));
//        Thread amigo1 = new Thread(new Person(mail));

        server.start();
        amigo.start();
//        amigo1.start();
    }
}
