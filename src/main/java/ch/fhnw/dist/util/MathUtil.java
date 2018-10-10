package main.java.ch.fhnw.dist.util;

import main.java.ch.fhnw.dist.Dataset;

import java.util.List;

public abstract class MathUtil {

    //private static double pH = 0.5;
    //private static double pS = 0.5;

    public static double getProbability(List<String> words, Dataset spam, Dataset ham) {
        double pWordWhenS = 0d;             // P(word|S)
        double pWordWhenH = 0d;             // P(word|H)
        double pWordsWhenS = 1d;            // P(words|S)
        double pWordsWhenH = 1d;            // P(words|H)
        double pSWhenWords = 0d;            // P(s|words)

        for (String word : words) {
            pWordWhenS = getPWordWhenS(word, spam);
            pWordsWhenS = pWordsWhenS * pWordWhenS; // P(word[1]|S) * P(word[2]|S) * ... * P(word[n]|S)

            pWordWhenH = getPWordWhenH(word, ham);
            pWordsWhenH = pWordsWhenH * pWordWhenH; // P(word[1]|H) * P(word[2]|H) * ... * P(word[n]|H)
        }
        ;

        pSWhenWords = pWordsWhenS / (pWordsWhenS + pWordsWhenH); // P(words|S) / ( P(words|S) * P(words|H) )

        return pSWhenWords;
    }

    /**
     * @param word
     * @param s
     * @return P(word | S)
     */
    public static double getPWordWhenS(String word, Dataset s) {
        return s.getFrequecency(word) / s.getFileCount();
    }

    /**
     * @param word
     * @param h
     * @return P(word | H)
     */
    public static double getPWordWhenH(String word, Dataset h) {
        return h.getFrequecency(word) / h.getFileCount();
    }

    /*public static double getPH() {
        return pH;
    }

    public static double getPS() {
        return pS;
    }*/
}
