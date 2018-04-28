package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
//        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("D:/MyDownloads"));
        Path passwords = downloadFile("https://retina.news.mail.ru/pic/d5/c3/image537526_e188dcda6492103a45b161b42258e4fc.jpg", Paths.get("C:\\Users\\roman.holodkov\\Documents\\TEST"));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        // implement this method

        URL url = new URL(urlString);
        String fileName = urlString.split("/")[urlString.split("/").length - 1];

        InputStream inputStream = url.openStream();

        Path tempFile = Files.createTempFile("temp",".tmp");
        Files.copy(inputStream, tempFile, StandardCopyOption.REPLACE_EXISTING);

        Path downloadPath = Paths.get(downloadDirectory + "/" + fileName);

        Files.move(tempFile, downloadPath, StandardCopyOption.REPLACE_EXISTING);
        return downloadPath;
    }
}
