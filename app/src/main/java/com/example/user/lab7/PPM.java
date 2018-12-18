package com.example.user.lab7;

public class PPM {

    //klasa pozwalajaca na obliczenie podstawowego zapotrzebowania kalorycznego

    public PPM() {
    }

    //metoda obliczająca podstawową przemianę materii metodą Benedicta-Harrisa.
    public double PPM_BH(String sex, double weight, double height, double age, String units) {
        double height2 = 0;
        //ustawienie zeby wzrost był w centymetrach
        if (units.equals("m")) {
            height2 = height * 100;
        } else {
            height2 = height;
        }

        //obliczanie w zależności od płci
        if (sex.equals("kobieta")) {
            return (655.1 + (9.563 * weight) + (1.85 * height2) - (4.676 *
                    age));
        } else {
            return (66.5 + (13.75 * weight) + (5.003 * height2) - (6.775 *
                    age));

        }

    }

    //metoda obliczająca podstawową przemianę materii metodą Mifflina
    public double PPM_M(String sex, double weight, double height, double age, String units) {
        double height2 = 0;
        //ustawienie zeby wzrost był w centymetrach
        if (units.equals("m")) {
            height2 = height * 100;
        } else {
            height2 = height;
        }
        //obliczanie w zależności od płci
        if (sex.equals("kobieta")) {
            return (10 * weight) + (6.25 * height2) - (5 * age) - 161;
        } else {
            return (10 * weight) + (6.25 * height2) - (5 * age) + 5;

        }

    }
}
