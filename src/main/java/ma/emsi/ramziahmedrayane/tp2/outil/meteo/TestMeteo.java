package ma.emsi.ramziahmedrayane.tp2.outil.meteo;

import ma.emsi.ramziahmedrayane.tp2.outil.meteo.MeteoTool;

public class TestMeteo {
    public static void main(String[] args) {
        MeteoTool outil = new MeteoTool();
        boolean pluiePrevue = outil.isPluiePrevue(48.85, 2.35, 0, 3);
        System.out.println("Pluie prévue : " + pluiePrevue);
    }
}
