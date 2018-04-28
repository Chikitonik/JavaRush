package com.javarush.task.task31.task3101;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Stream;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);
        ArrayDeque<File> fileArrayDeque = new ArrayDeque();

        ArrayDeque<File> arrayDeque = new ArrayDeque();
        arrayDeque.add(path);

        while (!arrayDeque.isEmpty()) {
            File file = arrayDeque.poll();
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null) {
                    for (int i = 0; i < files.length; i++) {

                        if (files[i].isDirectory()) {
                            arrayDeque.add(files[i]);
                        } else if(files[i].isFile()){
                            if (files[i].length() < 50) {
                                fileArrayDeque.add(files[i]);
                            }
                        }
                    }
                }
            } else {
                if (file.isFile() & file.length()<50) {
                    fileArrayDeque.add(file);
                }
            }
        }

        ArrayList<File> fileArrayList = new ArrayList<>(fileArrayDeque);
        Collections.sort(fileArrayList, (o1, o2) -> o1.getName().compareTo(o2.getName()));

        File destinationFile = new File(resultFileAbsolutePath.getParent() + "\\allFilesContent.txt");
        if (FileUtils.isExist(resultFileAbsolutePath)) {
            FileUtils.renameFile(resultFileAbsolutePath, destinationFile);
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(destinationFile)){
            for (File file: fileArrayList) {
                fileOutputStream.write(file.toString().replaceAll("\\\\","/").getBytes());
                fileOutputStream.write("\n".getBytes());
            }
        } catch (IOException e) {

        }






    }
}
