package main.java.ch.fhnw.dist;

import main.java.ch.fhnw.dist.util.FileUtil;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * Container class for different datasets
 * <p>
 * Module dist, assignment 1
 *
 * @author M. Romanutti
 */
public class Dataset {

    // Attributes
    /**
     * List of all words
     */
    private List<String> words;
    /**
     * Map of wordcount
     */
    private Map<String, Double> frequencies;
    /**
     * Number of files
     */
    private int fileCount;
    /**
     * Number of wrong classifications
     */
    private int wrongClassification;

    /**
     * Constructor for a dataset
     *
     * @param path datasource from where the files are imported
     */
    public Dataset(String path) {
        List<String> files = FileUtil.getFiles(path);
        words = FileUtil.getWords(files);
        frequencies = calculateWordFrecuency(words);
        fileCount = FileUtil.countFiles(files);
    }

    ;

    /**
     * Accumulate frequency per word
     *
     * @param words list of words
     * @return Map of wordcount
     */
    private Map<String, Double> calculateWordFrecuency(List<String> words) {
        return words.stream().collect(toMap(entry -> entry, entry -> 1.0, Double::sum));
    }

    /**
     * Get frequency for specific word
     *
     * @param word word, to which frequency should be returned
     * @return frequency of word in dataset
     */
    public double getFrequecency(String word) {
        // set default value if word not existing (2b)
        double frequency = frequencies.containsKey(word) ? frequencies.get(word) : BayesApp.DEFAULT_FREQUENCY;

        return frequency;
    }

    /**
     * Get list of words
     *
     * @return list of words in dataset
     */
    public List<String> getWords() {
        return words;
    }

    // Setter and getter
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
