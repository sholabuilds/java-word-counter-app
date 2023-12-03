package com.adaptavist.textfileprocessor;

import com.adaptavist.javawordcounter.WordCounter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class TextFileProcessor {
    WordCounter wordCounter = new WordCounter();
    public void processTextFile(String filePath) {
        // Check if filepath exists
        Path path = Paths.get(filePath);

        if (!Files.exists(path) || !Files.isReadable(path)) {
            throw new IllegalArgumentException("File does not exist or is not accessible. Please check again.");
        }

        try {
            String data = Files.readString(path);

            // Check if file empty
            if (isTextFileEmpty(data)) {
                System.out.println("Text file is empty");
                return;
            }

            Map<String, Integer> wordCountMap = wordCounter.countWords(data);
            Map<String, Integer> wordCountMapSortedByFreq = sortValuesByFrequency(wordCountMap);

            wordCountMapSortedByFreq.forEach((word, count)
                    -> System.out.println(word + ": " + count));
        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
        }
    }

    // Helper fn to check if text file is empty
    private boolean isTextFileEmpty(String data) {
        return data.trim().isEmpty();
    }

    // Sort map by values (frequency of words in desc order)
    private Map<String, Integer> sortValuesByFrequency(Map<String, Integer> wordCountMap) {
        return wordCountMap
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // desc order
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> oldValue, // keep existing val (if keys collide)
                                LinkedHashMap::new));
    }
}
