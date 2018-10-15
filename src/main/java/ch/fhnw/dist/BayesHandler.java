package main.java.ch.fhnw.dist;

import main.java.ch.fhnw.dist.util.FileUtil;
import main.java.ch.fhnw.dist.util.MathUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Class to handle the different phases
 * <p>
 * Module dist, assignment 1
 *
 * @author M. Romanutti
 */
public class BayesHandler {

    // Attributes
    private Dataset ham;        // Ham dataset
    private Dataset spam;       // Spam dataset

    /**
     * Initial constructor
     */
    public BayesHandler() {
    }

    /**
     * Constructor for phases calibration and test
     *
     * @param ham  ham training dataset
     * @param spam spam training dataset
     */
    public BayesHandler(Dataset ham, Dataset spam) {
        this.ham = ham;
        this.spam = spam;
    }

    /**
     * Process train phase. This phase consist of reading ham and spam files and
     * calculate frequencies per word.
     */
    public void train() {
        this.ham = new Dataset(
                "src/main/resources/ham-" + BayesApp.Phase.TRAINING
                        .getInfix());
        this.spam = new Dataset(
                "src/main/resources/spam-" + BayesApp.Phase.TRAINING
                        .getInfix());
    }

    /**
     * Process calibration phase. This phase consist of reading ham and spam files,
     * calculate probability per mail and count false classified mails.
     */
    public void calibrate() {
        ham.setWrongClassification(0);
        spam.setWrongClassification(0);

        // Get ham mails
        List<String> files = FileUtil.getFiles(
                "src/main/resources/ham-" + BayesApp.Phase.CALIBRATION
                        .getInfix());

        for (String file : files) {
            // Get words per mail
            List<String> words = FileUtil.getWords(file);
            // Calculate probability per mail based on training data
            double p = MathUtil.getProbability(words, spam, ham);

            if (p > BayesApp.THRESHOLD) {
                // Increment wrongClassification if probability is higher than threshold
                // even though the mail was originally classified as ham
                ham.setWrongClassification(ham.getWrongClassification() + 1);
            }
        }

        // Get spam mails
        files = FileUtil.getFiles(
                "src/main/resources/spam-" + BayesApp.Phase.CALIBRATION
                        .getInfix());
        for (String file : files) {
            // get words per mail
            List<String> words = FileUtil.getWords(file);
            // Calculate probability per mail based on trainig data
            double p = MathUtil.getProbability(words, spam, ham);

            if (p < BayesApp.THRESHOLD || Double.isNaN(p)) {
                // Increment wrongClassification if probability is lower than threshold
                // even though the mail was originally classified as spam
                spam.setWrongClassification(spam.getWrongClassification() + 1);
            }
        }
    }

    /**
     * Process test phase. This phase consist of reading ham and spam files,
     * calculate probability per mail and count false classified mails.
     */
    public void test() {
        ham.setWrongClassification(0);
        spam.setWrongClassification(0);

        // Get ham mails
        List<String> files = FileUtil.getFiles(
                "src/main/resources/ham-" + BayesApp.Phase.TEST
                        .getInfix());

        for (String file : files) {
            // Get words per mail
            List<String> words = FileUtil.getWords(file);
            // Calculate probability per mail based on training data
            double p = MathUtil.getProbability(words, spam, ham);

            if (p > BayesApp.THRESHOLD) {
                // Increment wrongClassification if probability is higher than threshold
                // even though the mail was originally classified as ham
                ham.setWrongClassification(ham.getWrongClassification() + 1);
            }
        }

        // Get spam mails
        files = FileUtil.getFiles(
                "src/main/resources/spam-" + BayesApp.Phase.TEST
                        .getInfix());
        for (String file : files) {
            // get words per mail
            List<String> words = FileUtil.getWords(file);
            // Calculate probability per mail based on trainig data
            double p = MathUtil.getProbability(words, spam, ham);

            if (p < BayesApp.THRESHOLD || Double.isNaN(p)) {
                // Increment wrongClassification if probability is lower than threshold
                // even though the mail was originally classified as spam
                spam.setWrongClassification(spam.getWrongClassification() + 1);
            }
        }
    }

    /**
     * Print a summary per phase.
     *
     * @param phase phase that should be summarized
     */
    public void printSummary(BayesApp.Phase phase) {
        int fileCount;
        int wrongClassificationCount;

        // Output header
        System.out.println("####################################");
        System.out.println("#");
        System.out.println("# " + phase.name());

        switch (phase) {
        case TRAINING:
            // Number of mails
            System.out.println("# Ham count: " + ham.getFileCount());                       // Number of ham mails
            System.out.println("# Spam count: " + spam.getFileCount());                     // Number of spam mails
            break;
        case CALIBRATION:
            // Number of mails
            // TODO Count size of calibration datasets
            System.out.println("# Ham count: " + ham.getFileCount());                        // Number of ham mails
            System.out.println("# Spam count: " + spam.getFileCount());                      // Number of spam mails
            // Number of false classifications
            System.out.println("# False positives: " + ham
                    .getWrongClassification());        // Classified as spam, but was originally ham
            System.out.println("# False negatives: " + spam
                    .getWrongClassification());       // Classified as ham, but was originally spam

            // Count totals
            fileCount = ham.getFileCount() + spam.getFileCount();
            wrongClassificationCount = ham.getWrongClassification() + spam.getWrongClassification();
            // Detection rate
            System.out.println("# Detection rate (in %): " + (100.00 - ((100 * wrongClassificationCount) / fileCount)));
            break;
        case TEST:
            // Number of mails
            // TODO Count size of calibration datasets
            System.out.println("# Ham count: " + ham.getFileCount());                        // Number of ham mails
            System.out.println("# Spam count: " + spam.getFileCount());                      // Number of spam mails
            // Number of false classifications
            System.out.println("# False positives: " + ham
                    .getWrongClassification());        // Classified as spam, but was originally ham
            System.out.println("# False negatives: " + spam
                    .getWrongClassification());       // Classified as ham, but was originally spam

            // Count totals
            fileCount = ham.getFileCount() + spam.getFileCount();
            wrongClassificationCount = ham.getWrongClassification() + spam.getWrongClassification();
            // Detection rate
            System.out.println("# Detection rate (in %): " + (100.00 - ((100 * wrongClassificationCount) / fileCount)));
        }

        // Output footer
        System.out.println("#");
        System.out.println("# Alpha = " + BayesApp.DEFAULT_FREQUENCY + ", Schwellenwert = " + BayesApp.THRESHOLD);
        System.out.println("####################################");
        System.out.println();

    }

    // Getters and setters
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

}
