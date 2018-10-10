package main.java.ch.fhnw.dist.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Utility class for file operations
 * <p>
 * Module dist, assignment 1
 *
 * @author M. Romanutti
 */
public abstract class FileUtil {

    /**
     * Get filenames from directory
     *
     * @param path directory to files
     * @return list of fully qualified paths
     */
    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.filter(Files::isRegularFile).forEach(f -> files.add(f.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return files;
    }

    /**
     * Get words from several files
     *
     * @param files fully qualified filenames
     * @return complete list of all words
     */
    public static List<String> getWords(List<String> files) {
        List<String> words = new ArrayList<>();
        for (String file : files) {
            try {
                Scanner in = new Scanner(new File(file));

                while (in.hasNext()) {
                    words.add(in.next());
                }
                in.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return words;
    }

    /**
     * Get words from single file
     *
     * @param mail fully qualified filename
     * @return list of all words
     */
    public static List<String> getWords(String mail) {
        List<String> words = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File(mail));

            while (in.hasNext()) {
                words.add(in.next());
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return words;
    }

    /**
     * Count number of files
     *
     * @param files list of files
     * @return number of files
     */
    public static int countFiles(List<String> files) {
        return files.size();
    }

}
