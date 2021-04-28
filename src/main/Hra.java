/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import bloky.Blok;
import bloky.Hrac;
import hra.Pole;
import javax.swing.JOptionPane;

/**
 *
 * @author Tom
 */
public class Hra {
    /**
     * nazov Sveta.
     */
    private String nazovSveta;
    /**
     * pole blokov sveta.
     */
    private final Pole pole;
    /**
     * konstruktor.
     */
    public Hra() {
        JOptionPane.showMessageDialog(null,
                "Toto je novy svet... ak chcete pokracovat v ulozemej hre"
                        + " vyberte polozku Nacitaj Hru v menu");
        this.nazovSveta = JOptionPane
                .showInputDialog("Zadajte názov nového sveta");
        this.pole = new Pole(this);
    }
    /**
     * zacne hru.
     */
    public final void hraj() {
        this.pole.zobraz();
    }
    /**
     *
     * @return pole blokov
     */
    public final Pole getPole() {
        return this.pole;
    }
    /**
     *
     * @param nazov nastavovany novy nazzov
     */
    public final void setNazovSveta(final String nazov) {
        this.nazovSveta = nazov;
    }
    /**
     *
     * @return nazov sveta
     */
    public final String getNazovSveta() {
        return nazovSveta;
    }
    /**
     *
     * @return string, v ktorom su vsetky informacie o hre
     */
    public final String stringCelejHry() {
        String cely = this.nazovSveta + " " + this.pole.vypisInventarString();
        for (int i = 0; i < this.pole.getRozmer(); i++) { //100
            for (int j = 0; j < this.pole.getRozmer(); j++) { //100
                //cely += " " + i + " " + j;
                if (this.pole.getBlok(i, j) == null) {
                    cely = cely + " null";
                } else if (this.pole.getBlok(i, j) instanceof Hrac) {
                     cely = cely + " hrac";
                } else {
                    cely = cely + " " + ((Blok) this.pole.getBlok(i, j))
                            .getPopis();
                }
            }
        }
        return cely;
    }
}
