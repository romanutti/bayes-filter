package main.java.ch.fhnw.dist;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public abstract class Mail {
    protected List<String> words = new ArrayList<>();
    protected Map<String, Integer> frequencies;

    public List<String> initiateFiles(String path) {
        List<String> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.filter(Files::isRegularFile).forEach(f -> files.add(f.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return files;
    }

    public List<String> initiateWords(List<String> files) {
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

    public Map<String, Integer> initiateWordFrequency(List<String> words) {
        Map<String, Integer> wordFrequency = words.stream()
                .collect(toMap(w -> w,
                        w -> 1,
                        Integer::sum));

        return wordFrequency;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public Map<String, Integer> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(Map<String, Integer> wordFrequency) {
        this.frequencies = wordFrequency;
    }

    public int getFrencency(String word){
        return frequencies.get(word);
    }
}