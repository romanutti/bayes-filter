package main.java.ch.fhnw.dist;

import main.java.ch.fhnw.dist.util.FileUtil;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Dataset {


    private List<String> words;
    private Map<String, Double> frequencies;
    private int fileCount;
    private int wrongClassification;

    public Dataset(String path) {
        List<String> files = FileUtil.getFiles(path);
        words = FileUtil.getWords(files);
        frequencies = calculateWordFrecuency(words);
        fileCount = FileUtil.countFiles(files);
    }

    ;

    private Map<String, Double> calculateWordFrecuency(List<String> words) {
        return words.stream().collect(toMap(entry -> entry, entry -> 1.0, Double::sum));
    }

    public double getFrequecency(String word) {
        // set default value if word not existing (2b)
        double frequency = frequencies.containsKey(word) ? frequencies.get(word) : BayesApp.DEFAULT_FREQUENCY;

        return frequency;
    }

    public List<String> getWords() {
        return words;
    }

    public void setWords(List<String> words) {
        this.words = words;
    }

    public Map<String, Double> getFrequencies() {
        return frequencies;
    }

    public void setFrequencies(Map<String, Double> wordFrequency) {
        this.frequencies = wordFrequency;
    }

    public int getFileCount() {
        return fileCount;
    }

    public void setFileCount(int fileCount) {
        this.fileCount = fileCount;
    }

    public int getWrongClassification() {
        return wrongClassification;
    }

    public void setWrongClassification(int wrongClassification) {
        this.wrongClassification = wrongClassification;
    }
}
