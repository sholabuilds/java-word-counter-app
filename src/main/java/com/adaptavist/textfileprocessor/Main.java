package com.adaptavist.textfileprocessor;

import com.adaptavist.textfileprocessor.TextFileProcessor;

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java com.adaptavist.textfileprocessor.Main <file_path>");
            System.exit(1);
        }

        String filePath = args[0];
        TextFileProcessor textFileProcessor = new TextFileProcessor();
        textFileProcessor.processTextFile(filePath);
    }
}
