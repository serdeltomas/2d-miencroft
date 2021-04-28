/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;

import frame.Platno;
import main.Hra;

/**
 *
 * @author Tom
 */

public class Okno {
    /**
     * x rozmer pola.
     */
    private final int xRozmer = 15; //15
    /**
     * y rozmer pola.
     */
    private final int yRozmer = 10; //10
    /**
     * hra.
     */
    private Hra hra;
    /**
     * pole stvorcov ktore sa zobrazia.
     */
    private Stvorec[][] okno = new Stvorec[this.xRozmer][this.yRozmer];
    /**
     * okno v ktorom sa zobrazuje pole.
     * @param hraa cela hra
     */
    public Okno(final Hra hraa) {
        this.noveOkno();
        this.hra = hraa;
    }
    /**
     * nastavi kazdemu stvorcu v okne farbu.
     * @param pole pole IBlokov
     */
    public final void nastavZobrazenie(final Pole pole) {
        this.noveOkno();
        final int sedem = 7;
        final int styri = 4;
        final int patdesiat = 50;
        int pomocnai = 0;
        int pomocnaj;
        for (int i = pole.getHracX() - sedem; i < pole.getHracX()
                + (sedem + 1); i++) {
            pomocnaj = 0;
            for (int j = pole.getHracY() - styri; j < pole.getHracY()
                    + (styri + 2); j++) {
                try {
                    if (pole.getBlok(i, j) != null) {
                        this.okno[pomocnai][pomocnaj].zmenFarbu(pole
                                .getBlok(i, j).getFarba());
                    } else {
                        this.okno[pomocnai][pomocnaj].zmenFarbu("white");
                    }
                } catch (IndexOutOfBoundsException ex) {
                    this.okno[pomocnai][pomocnaj].zmenFarbu("red");
                }
                this.okno[pomocnai][pomocnaj]
                        .posunVodorovne(pomocnai * patdesiat);
                this.okno[pomocnai][pomocnaj].posunZvisle(pomocnaj * patdesiat);
                pomocnaj++;
            }
            pomocnai++;
        }
    }
    /**
     * zobrazi vsetky stvorce v okne.
     * @param pole pole IBlokov
     */
    public final void zobraz(final Pole pole) {
        this.nastavZobrazenie(pole);
        for (int i = 0; i < this.xRozmer; i++) {
            for (int j = 0; j < this.yRozmer; j++) {
                this.okno[i][j].zobraz();
            }
        }
    }
    /**
     * skryje vsetky stvorce v okne.
     */
    public final void skry() {
        for (int i = 0; i < this.xRozmer; i++) {
            for (int j = 0; j < this.yRozmer; j++) {
                this.okno[i][j].skry();
            }
        }
        Platno canvas = Platno.dajPlatno(this.hra);
        //canvas.erase();
    }
    /**
     * nastavi kazdemu stvorceku v poli jeho poziciu v okne.
     * pouziva sa na vykreslenie celeho pola
     * @param pole nastavovane pole
     */
    public final void nastavZobrazenieCele(final Pole pole) {
        this.noveOkno();
        final int posun = 5;
        for (int i = 0; i < this.xRozmer; i++) {
            for (int j = 0; j < this.yRozmer; j++) {
                if (pole.getBlok(i, j) != null) {
                    this.okno[i][j].zmenFarbu(pole.getBlok(i, j).getFarba());
                } else {
                    this.okno[i][j].zmenFarbu("white");
                }
                this.okno[i][j].posunVodorovne(i * posun);
                this.okno[i][j].posunZvisle(j * posun);
            }
        }
    }
    /**
     * zmeni vsetky stvorce na nove.
     */
    private void noveOkno() {
        final int patdesiat = 50;
        for (int i = 0; i < this.xRozmer; i++) {
            for (int j = 0; j < this.yRozmer; j++) {
                this.okno[i][j] = new Stvorec(patdesiat, this.hra);
            }
        }
    }
}
