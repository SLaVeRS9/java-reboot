package ru.sberbank.edu;

import org.apache.commons.lang3.StringUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Class to get info from any text-format file (line count, space count and find the longest line)
 * and save this info into the new creating file near to the original one
 * @author Viacheslav Lopusov
 * @version 1.0
 */
public class FileToFileStatistic implements Statistic {
    private static final int INCORRECT_RESULT = 1;
    private static final String FILE_NOT_FOUND_EXCEPTION_MESSAGE = "File in this path (%s) not found\n";
    private static final String SOME_PROBLEMS_WITH_FILE_MESSAGE = "Some problems with the file placed on path: %s\n";
    private static final String A_LOT_OF_LINES_IN_THE_FILE = "There are a lot of lines in this file (more than %s). Will be return %s";
    private static final String FILE_EXIST = "File already exists. Delete it and try again";
    private Path filePath;
    FileToFileStatistic(Path filePath){
        this.filePath = filePath;
    }

    /**
     * Method to calculate the number of lines in the transferred file
     * @return lines count in file and -1 if something went wrong
     */
    @Override
    public int getLineCount() {
        try(Stream<String> lines = Files.lines(filePath)) {
            long linesCount = lines.count();
            if (linesCount > Integer.MAX_VALUE) {
                System.out.printf(A_LOT_OF_LINES_IN_THE_FILE, Integer.MAX_VALUE, Integer.MAX_VALUE);
                return Integer.MAX_VALUE;
            } else if (linesCount == 0){
                return 0;
            } else {
                return (int) linesCount;
            }
        } catch (FileNotFoundException e) {
            System.out.printf(FILE_NOT_FOUND_EXCEPTION_MESSAGE, filePath);
            e.printStackTrace();
            return INCORRECT_RESULT;
        } catch (IOException e) {
            System.out.printf(SOME_PROBLEMS_WITH_FILE_MESSAGE, filePath);
            e.printStackTrace();
            return INCORRECT_RESULT;
        }
    }

    /**
     * Method to calculate the number of spaces in the transferred file
     * @return spaces count in file and -1 if something went wrong
     */
    @Override
    public int getSpaceCount() {
        try {
            List<String> lines = Files.readAllLines(filePath);
            int spaceCount = 0;
            for (String line: lines) {
                spaceCount = spaceCount + StringUtils.countMatches(line, ' ');
            }
            return spaceCount;
        } catch (FileNotFoundException e) {
            System.out.printf(FILE_NOT_FOUND_EXCEPTION_MESSAGE, filePath);
            e.printStackTrace();
            return INCORRECT_RESULT;
        } catch (IOException e) {
            System.out.printf(SOME_PROBLEMS_WITH_FILE_MESSAGE, filePath);
            e.printStackTrace();
            return INCORRECT_RESULT;
        }
    }

    /**
     * Method to find the longest line in the transferred file
     * @return longest line or -1 if something went wrong
     */
    @Override
    public String getLongestLine() {
        try {
            List<String> lines = Files.readAllLines(filePath);
            int stringLength = 0;
            String longestLine = "";
            for (String line: lines) {
                if (stringLength > line.length())
                    continue;
                stringLength = line.length();
                longestLine = line;
            }
            return longestLine;
        } catch (FileNotFoundException e) {
            System.out.printf(FILE_NOT_FOUND_EXCEPTION_MESSAGE, filePath);
            e.printStackTrace();
            return String.valueOf(INCORRECT_RESULT);
        } catch (IOException e) {
            System.out.printf(SOME_PROBLEMS_WITH_FILE_MESSAGE, filePath);
            e.printStackTrace();
            return String.valueOf(INCORRECT_RESULT);
        }
    }

    /**
     * Method to save info about the lines count, the space count and the longest line
     * from transferred file in new file
     */
    @Override
    public void saveStatistic() {
        int lineCount = getLineCount();
        int spaceCount = getSpaceCount();
        String longestLine = getLongestLine();

        String newFileName = "Statistic for " + filePath.getFileName().toString();
        Path newFilePathName = Path.of(filePath.getParent() + "/" + newFileName);
        try {
            Path path = Files.createFile(newFilePathName);
            String lineToWriting = "lineCount = " + lineCount + "\n" +
                    "spaceCount = " + spaceCount + "\n" +
                    "longest line = " + longestLine + "\n";
            Files.writeString(path, lineToWriting);
        } catch (FileAlreadyExistsException e) {
            System.out.println(FILE_EXIST);
            e.printStackTrace();
        } catch (IOException e) {
            System.out.printf(SOME_PROBLEMS_WITH_FILE_MESSAGE, newFilePathName);
            e.printStackTrace();
        }
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }
}
