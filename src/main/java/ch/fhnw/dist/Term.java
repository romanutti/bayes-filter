package main.java.ch.fhnw.dist;

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
