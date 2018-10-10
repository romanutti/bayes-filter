package main.java.ch.fhnw.dist;

/**
 * Class to process a single complete bayes-filter iteration
 *
 * Module dist, assignment 1
 * @author M. Romanutti
 */
public class BayesApp {

    /********************************************************************************
     * App configuration                                                            *
     ********************************************************************************/
    public static final double DEFAULT_FREQUENCY = 0.02;        // α, dt. Alpha
    public static final double THRESHOLD = 0.9;                 // dt. Schwellenwert

    public enum Phase {
        TRAINING("anlern"), CALIBRATION("kallibrierung"), TEST("test");

        private String infix;

        Phase(String infix) {
            this.infix = infix;
        }

        public String getInfix() {
            return infix;
        }

        public void setInfix(String infix) {
            this.infix = infix;
        }

    }

    public static void main(String[] args) {

        // Generate handler object to process different phases
        BayesHandler handler = new BayesHandler();

        /********************************************************************************
         * Train phase                                                                  *
         ********************************************************************************/
        BayesHandler trainingHandler = handler;
        trainingHandler.train();
        trainingHandler.printSummary(Phase.TRAINING);

        /********************************************************************************
         * Calibration phase                                                                  *
         ********************************************************************************/
        BayesHandler calibrationHandler = new BayesHandler(trainingHandler.getHam(), trainingHandler.getSpam());
        calibrationHandler.calibrate();
        calibrationHandler.printSummary(Phase.CALIBRATION);

        /********************************************************************************
         * Test phase                                                                  *
         ********************************************************************************/
        BayesHandler testHandler = new BayesHandler(trainingHandler.getHam(), trainingHandler.getSpam());
        testHandler.test();
        testHandler.printSummary(Phase.TEST);

    }

}
