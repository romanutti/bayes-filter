package main.java.ch.fhnw.dist;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BayesHandler {
    private Dataset ham;
    private Dataset spam;
    //private Map<String, Term> knowledge;

    public BayesHandler() {
        this.ham = new Dataset(DataUtil.PATH_HAM_TRAINING);
        this.spam = new Dataset(DataUtil.PATH_SPAM_TRAINING);
        //this.knowledge = train(this.ham, this.spam);
    }

    public Map<String, Term> train(Dataset ham, Dataset spam) {
        return Stream.concat(ham.getFrequencies().keySet().stream(), spam.getFrequencies().keySet().stream()).distinct()
                .collect(Collectors.toMap(entry -> entry,
                        entry -> new Term(entry, ham.getFrequecency(entry), spam.getFrequecency(entry),
                                MathUtil.getPWordWhenS(entry, spam), MathUtil.getPWordWhenH(entry, ham))));

    }

    public void calibrate() {
        Dataset mails = new Dataset(DataUtil.PATH_HAM_CALIBRATION);

        List<String> files = DataUtil.getFiles(DataUtil.PATH_HAM_CALIBRATION);
        for (String file : files) {
            List<String> words = DataUtil.getWords(file);
            double p = MathUtil.getProbability(words, spam, ham);
            System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println(String.format("%.12f", p));
        }
    }

    public Dataset getHam() {
        return ham;
    }

    public void setHam(Dataset ham) {
        this.ham = ham;
    }

    public Dataset getSpam() {
        return spam;
    }

    public void setSpam(Dataset spam) {
        this.spam = spam;
    }

    /*public Map<String, Term> getKnowledge() {
        return knowledge;
    }

    public void setKnowledge(Map<String, Term> wordCount) {
        this.knowledge = wordCount;
    }*/
}
