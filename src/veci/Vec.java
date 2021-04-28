/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package veci;

/**
 *
 * @author Tom
 */
public class Vec implements IVec {
    private String popis;
    private int pocet;
    /**
     * vytvori vec s popisom
     * @param popis popis veci
     */
    public Vec(String popis) {
        this.popis = popis;
    }
    @Override
    public String toString() {
        return "Vec - " + this.popis;
    }
    public String getPopis() {
        return popis;
    }

    @Override
    public int getPocet() {
        return this.pocet;
    }
    @Override
    public void setPocet(int poceet) {
        this.pocet = poceet;
    }
}