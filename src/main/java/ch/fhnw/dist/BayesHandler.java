package main.java.ch.fhnw.dist;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BayesHandler {
    private Ham ham;
    private Spam spam;
    private Map<String, Term> anlernMap;

    public class Term {
        private String term;
        private double hamCount;
        private double spamCount;
        private double pAwhenS;
        private double pAwhenH;

        public Term(String term, double hamCount, double spamCount, double pAwhenS, double pAwhenH) {
            this.term = term;
            this.hamCount = hamCount;
            this.spamCount = spamCount;
            this.pAwhenS = pAwhenS;
            this.pAwhenH = pAwhenH;
        }

        public String getTerm() {
            return term;
        }

        public void setTerm(String term) {
            this.term = term;
        }

        public double getHamCount() {
            return hamCount;
        }

        public void setHamCount(double hamCount) {
            this.hamCount = hamCount;
        }

        public double getSpamCount() {
            return spamCount;
        }

        public void setSpamCount(double spamCount) {
            this.spamCount = spamCount;
        }

        public double getpAwhenS() {
            return pAwhenS;
        }

        public void setpAwhenS(double pAwhenS) {
            this.pAwhenS = pAwhenS;
        }

        public double getpAwhenH() {
            return pAwhenH;
        }

        public void setpAwhenH(double pAwhenH) {
            this.pAwhenH = pAwhenH;
        }
    }

    public BayesHandler() {
        this.ham = new Ham();
        this.spam = new Spam();
        this.anlernMap = mergeMaps(this.ham, this.spam);
    }

    public Map<String, Term> mergeMaps(Ham ham, Spam spam) {
        return Stream.concat(ham.getFrequencies().keySet().stream(), spam.getFrequencies().keySet().stream()).distinct()
                .collect(Collectors.toMap(entry -> entry,// key object
                        entry -> new Term(entry, ham.getFrencency(entry), spam.getFrencency(entry),
                                BayesMath.getPAwhenS(entry, spam),
                                BayesMath.getPAwhenH(entry, ham)))); // value object

    }

    public Ham getHam() {
        return ham;
    }

    public void setHam(Ham ham) {
        this.ham = ham;
    }

    public Spam getSpam() {
        return spam;
    }

    public void setSpam(Spam spam) {
        this.spam = spam;
    }

    public Map<String, Term> getWordCount() {
        return anlernMap;
    }

    public void setWordCount(Map<String, Term> wordCount) {
        this.anlernMap = wordCount;
    }
}
