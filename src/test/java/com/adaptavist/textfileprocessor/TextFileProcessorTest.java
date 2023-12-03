package com.adaptavist.textfileprocessor;

import com.adaptavist.javawordcounter.WordCounter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TextFileProcessorTest {

    private TextFileProcessor textFileProcessor;

    @Mock
    private WordCounter wordCounter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        textFileProcessor = new TextFileProcessor();
        textFileProcessor.wordCounter = wordCounter;
    }

    @Test
    void printsWordCountsInCountFreqOrder() throws IOException {
        Path tempFile = Files.createTempFile("temp_file", ".txt");
        String fileContent = "Made up sample text filled with made up cake CAKE cake";

        when(wordCounter.countWords(anyString())).thenReturn(
                Map.of("Made", 2, "up", 2, "sample", 1, "text", 1, "filled", 1, "with", 1, "cake", 3)
        );

        Files.write(tempFile, fileContent.getBytes());

        textFileProcessor.processTextFile(tempFile.toString());

        verify(wordCounter).countWords(fileContent);
    }

    @Test
    void throwsExceptionForInvalidFilePath() {
        String invalidFilePath = "invalid_file.txt";

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            textFileProcessor.processTextFile(invalidFilePath);
        });

        String expectedErrorMessage = "File does not exist or is not accessible. Please check again.";

        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void handlesEmptyFileCorrectly() throws IOException {
        Path tempFile = Files.createTempFile("temp_file", ".txt");

        // simulate console print
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalOut = System.out;

        System.setOut(printStream);

        textFileProcessor.processTextFile(tempFile.toString());
        System.out.flush();

        System.setOut(originalOut); // Reset System.out

        String expectedOutput = "Text file is empty\n";

        assertEquals(expectedOutput, outputStream.toString());
    }


}
