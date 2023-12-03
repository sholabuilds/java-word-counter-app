package com.adaptavist.javawordcounter;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterTest {

    private static WordCounter wordCounter;

    @BeforeAll
    static void setUp() {
        wordCounter = new WordCounter();
    }

    @Test
    void countsWordsInASentence() {
        String text = "This is a sample sentence for testing purposes.";
        Map<String, Integer> expectedWordCount = new HashMap<>();
        expectedWordCount.put("this", 1);
        expectedWordCount.put("is", 1);
        expectedWordCount.put("a", 1);
        expectedWordCount.put("sample", 1);
        expectedWordCount.put("sentence", 1);
        expectedWordCount.put("for", 1);
        expectedWordCount.put("testing", 1);
        expectedWordCount.put("purposes", 1);

        Map<String, Integer> result = wordCounter.countWords(text);
        assertEquals(expectedWordCount, result);
    }

    @Test
    void countsRepeatedWordsInASentence() {
        String text = "Scriptrunner scriptrunner scriptrunner slack zoom slack scriptrunner";
        Map<String, Integer> expectedWordCount = new HashMap<>();
        expectedWordCount.put("scriptrunner", 4);
        expectedWordCount.put("slack", 2);
        expectedWordCount.put("zoom", 1);

        Map<String, Integer> result = wordCounter.countWords(text);
        assertEquals(expectedWordCount, result);
    }

    @Test
    void handlesTextWithNonAlphaChars() {
        String text = "word1! word2 bitbucket3 word1 jira5 word2 jira!*-";
        Map<String, Integer> expectedWordCount = new HashMap<>();
        expectedWordCount.put("word", 4);
        expectedWordCount.put("jira", 2);
        expectedWordCount.put("bitbucket", 1);

        Map<String, Integer> result = wordCounter.countWords(text);
        assertEquals(expectedWordCount, result);
    }

    @Test
    void returnsEmptyMapWhenEmptyTextIsGiven() {
        String text = "";
        Map<String, Integer> expectedWordCount = new HashMap<>();

        Map<String, Integer> result = wordCounter.countWords(text);
        assertEquals(expectedWordCount, result);
    }
}
