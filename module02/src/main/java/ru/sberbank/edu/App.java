package ru.sberbank.edu;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Hello world!
 *
 */
public class App {
    private static final String testFilePath = "module02/src/main/resources/TestData.txt";
    public static void main( String[] args ) {
        Path filePath = Path.of(testFilePath);
        FileToFileStatistic fileToFileStatistic = new FileToFileStatistic(filePath);
        System.out.println("fileToFileStatistic.getLineCount() = " + fileToFileStatistic.getLineCount());
        System.out.println("fileToFileStatistic.getSpaceCount = " + fileToFileStatistic.getSpaceCount());
        System.out.println("LongestLine = " + fileToFileStatistic.getLongestLine());

        fileToFileStatistic.saveStatistic();
    }
}
