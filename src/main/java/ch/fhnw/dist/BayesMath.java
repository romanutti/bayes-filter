package main.java.ch.fhnw.dist;

public class BayesMath {

    // hide constructor
    private BayesMath(){};
    private static double pH = 0.5;
    private static double pS = 0.5;

    public static double getPSwhenAB(String a, String b, Spam s, Ham h){
        double pAwhenS = BayesMath.getPAwhenS(a, s);
        double pAwhenH = BayesMath.getPAwhenH(a, h);

        double pBwhenS = BayesMath.getPAwhenS(b, s);
        double pBwhenH = BayesMath.getPAwhenH(b, h);

        double pSwhenAB = (pAwhenS * pBwhenS) / ((pAwhenS * pBwhenS) + (pAwhenH * pBwhenH));

        return pSwhenAB;
    }

    public static double getPAwhenS(String a, Spam s){
        return s.getFrencency(a) / s.getFrequencies().size();
    }

    public static double getPAwhenH(String a, Ham h){
        return h.getFrencency(a) / h.getFrequencies().size();
    }

    public static double getPH() {
        return pH;
    }

    public static double getPS() {
        return pS;
    }
}
