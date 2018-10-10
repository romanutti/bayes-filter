package main.java.ch.fhnw.dist.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public abstract class FileUtil {

    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.filter(Files::isRegularFile).forEach(f -> files.add(f.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return files;
    }

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

    public static int countFiles(List<String> files){
        return files.size();
    }

}
