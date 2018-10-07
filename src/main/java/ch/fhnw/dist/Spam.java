package main.java.ch.fhnw.dist;

import java.util.List;

public class Spam extends Mail {
    private static final String PATH = "C:\\Users\\marco\\Dropbox\\FHNW\\dist\\Uebungen\\Bayes-SpamFilter\\spam-anlern";

    public Spam() {
        List<String> files = initiateFiles(PATH);
        words = initiateWords(files);
        frequencies = initiateWordFrequency(words);
    }

}

