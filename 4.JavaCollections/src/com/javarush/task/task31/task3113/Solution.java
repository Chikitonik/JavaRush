package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Path file = Paths.get(bufferedReader.readLine());
        bufferedReader.close();
        if (!Files.isDirectory(file)) {
            System.out.println(file.toAbsolutePath() + " - не папка");
            return;
        }
        VisitorFiles visitorFiles = new VisitorFiles();
        Files.walkFileTree(file, options, Integer.MAX_VALUE, visitorFiles);
        System.out.println("Всего папок - " + visitorFiles.countFolders);
        System.out.println("Всего файлов - " + visitorFiles.countFiles);
        System.out.println("Общий размер - " + visitorFiles.countBytes);

//                System.out.println("Всего папок - " + Files.walk(file).filter(Files::isDirectory).count());


    }

    public static class VisitorFiles extends SimpleFileVisitor<Path> {
        public long countFolders = -1;
        public long countFiles = 0;
        public long countBytes = 0;

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

            if (Files.isRegularFile(file)) {
                countFiles++;
                countBytes += attrs.size();
            }
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            if (Files.isDirectory(dir)) {
                countFolders++;
            }
            return FileVisitResult.CONTINUE;
        }

    }

}
