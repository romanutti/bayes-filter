package main.java.ch.fhnw.dist;

public class BayesApp {

    public static void main(String[] args) {

        BayesHandler handler = new BayesHandler();
        //for (BayesHandler.Term term: handler.getWordCount().values()){
        //    System.out.println(term.getTerm() + " " + term.getHamCount() + " " + term.getSpamCount() + " " + String.format("%.12f", term.getpAwhenS()) + " " + String.format("%.12f", term.getpAwhenH()));
        //}
        handler.calibrate();

    }

}
