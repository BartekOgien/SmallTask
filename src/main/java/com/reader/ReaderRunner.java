package com.reader;

public class ReaderRunner {

    public static void main(String[] args) {

        String filePath = "file/users.txt";
        FileReader fileReader = new FileReader();
            fileReader.readFile(filePath);
    }
}
