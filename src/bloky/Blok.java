/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloky;

/**
 *
 * @author Tom
 */
public class Blok implements IBlok {
    /**
     * farba.
     */
    private String farba;
    /**
     * popis.
     */
    private String popis;
    /**
     * x-ova pozicia.
     */
    private int xPozicia;
    /**
     * y-ova pozicia.
     */
    private int yPozicia;
    /**
     * vytvorí zaklad pre blok.
     */
    public Blok() {
        this.farba = "white";
        this.popis = "Blok - ";
        this.xPozicia = -1;
        this.yPozicia = -1;
    }

    @Override
    public final int getYPozicia() {
        return this.yPozicia;
    }

    @Override
    public final int getXPozicia() {
        return this.xPozicia;
    }

    /**
     * @param paFarba nova farba bloku
     */
    public final void setFarba(final String paFarba) {
        this.farba = paFarba;
    }

    @Override
    public final void setXPozicia(final int paXPozicia) {
        this.xPozicia = paXPozicia;
    }

    @Override
    public final void setYPozicia(final int paYPozicia) {
        this.yPozicia = paYPozicia;
    }
    /**
     * @param paPopis popis bloku
     */
    public final void setPopis(final String paPopis) {
        this.popis = paPopis;
    }

    @Override
    public final String getFarba() {
        return farba;
    }
    /**
     * @return popis bloku
     */
    public final String getPopis() {
        return popis;
    }

    @Override
    public final String toString() {
        return "Blok - " + this.getPopis();
    }
}
