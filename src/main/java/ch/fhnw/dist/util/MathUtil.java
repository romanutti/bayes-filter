package main.java.ch.fhnw.dist.util;

import main.java.ch.fhnw.dist.Dataset;

import java.util.List;

/**
 * Utility class for math operations
 *
 * Module dist, assignment 1
 * @author M. Romanutti
 */
public abstract class MathUtil {

    /**
     * Calculate the cumulated probability of a list of word, based on spam and ham training datasets
     * @param words list of words in email
     * @param spam spam training dataset
     * @param ham ham training dataset
     * @return probability of spam, P(S|words)
     */
    public static double getProbability(List<String> words, Dataset spam, Dataset ham) {
        double pWordWhenS = 0d;             // P(word|S)
        double pWordWhenH = 0d;             // P(word|H)
        double pWordsWhenS = 1d;            // P(words|S)
        double pWordsWhenH = 1d;            // P(words|H)
        double pSWhenWords = 0d;            // P(S|words)

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
     * Calculate how likely a word appears in spam, based on spam training dataset
     * @param word word to check
     * @param spam spam dataset
     * @return probability of word in spam dataset, P(word|S)
     */
    public static double getPWordWhenS(String word, Dataset spam) {
        return spam.getFrequecency(word) / spam.getFileCount(); // P(word|S)
    }

    /**
     * Calculate how likely a word appears in ham, based on ham training dataset
     * @param word word to check
     * @param ham ham dataset
     * @return probability of word in ham dataset, P(word|H)
     */
    public static double getPWordWhenH(String word, Dataset ham) {
        return ham.getFrequecency(word) / ham.getFileCount(); // P(word|H)
    }

}
