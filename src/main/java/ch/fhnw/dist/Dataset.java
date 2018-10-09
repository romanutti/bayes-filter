package main.java.ch.fhnw.dist;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Dataset {

    public static final double DEFAULT_FREQUENCY = 1;

    private List<String> words;
    private Map<String, Double> frequencies;

/*    public class Mail extends Dataset {

        private boolean isSpam;

        public Mail(String path){
            super(path);
        }

        public void classify(){
        }

        public boolean isSpam() {
            return isSpam;
        }

        public void setSpam(boolean spam) {
            isSpam = spam;
        }
    }*/

    public Dataset(String path) {
        List<String> files = DataUtil.getFiles(path);
        words = DataUtil.getWords(files);
        frequencies = calculateWordFrecuency(words);
    }

    ;

    private Map<String, Double> calculateWordFrecuency(List<String> words) {
        return words.stream().collect(toMap(entry -> entry, entry -> 1.0, Double::sum));
    }

    public double getFrequecency(String word) {
        // set default value if word not existing (2b)
        double frequency = frequencies.containsKey(word) ? frequencies.get(word) : DEFAULT_FREQUENCY;

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

}
