package main.java.ch.fhnw.dist;

public class BayesMath {

    // hide constructor
    private BayesMath(){};
    private static double pH = 0.5;
    private static double pS = 0.5;

    public static double getPSwhenAB(String a, String b, Spam s, Ham h){
        double P_A_when_S = BayesMath.getPAwhenS(a, s);
        double P_A_when_H = BayesMath.getPAwhenH(a, h);

        double P_B_when_S = BayesMath.getPAwhenS(b, s);
        double P_B_when_H = BayesMath.getPAwhenH(b, h);

        double P_S_when_AB = (P_A_when_S * P_B_when_S) / ((P_A_when_S * P_B_when_S) + (P_A_when_H * P_B_when_H));

        return P_S_when_AB;
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
