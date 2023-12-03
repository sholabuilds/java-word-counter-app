package com.adaptavist.javawordcounter;

import java.util.*;

public class WordCounter {

    public Map<String, Integer> countWords(String text) {
        try {
            Map<String, Integer> wordCountMap = new HashMap<>();
            String[] processedWords = processWords(text);

            for (String processedWord : processedWords) {
                processedWord = processedWord.trim();

                if (!processedWord.isEmpty()) {
                    Integer count = wordCountMap.getOrDefault(processedWord, 0);
                    wordCountMap.put(processedWord, count + 1);
                }
            }

            return wordCountMap;
        } catch (Exception e) {
            throw new RuntimeException("There is an error with the text. Please try again");
        }
    };

    public int getCountForAGivenWord(String word, Map<String, Integer> wordCountMap) {
        String lowerCaseWord = word.toLowerCase();
        return wordCountMap.getOrDefault(lowerCaseWord, 0);
    }

    private String[] processWords(String text) {
        return text.toLowerCase()
                .replaceAll("[^a-zA-Z\\s]", "")
                .split("\\s+");
    }
}
