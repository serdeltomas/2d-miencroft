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
public class Hrac implements IBlok {
    /**
     * nastavi x-ovu poziciu hraca.
     */
    private int xPozicia;
    /**
     * nastavi y-ovu poziciu hraca.
     */
    private int yPozicia;
    /**
     * farba hraca.
     */
    private final String farba = "blue";
    /**
     * vytvori hraca.
     */
    public Hrac() {
        final int patdesiat = 50;
        final int styridsatdva = 42;
        this.xPozicia = patdesiat;
        this.yPozicia = styridsatdva;
    }

    @Override
    public final int getYPozicia() {
        return this.yPozicia;
    }

    @Override
    public final int getXPozicia() {
        return this.xPozicia;
    }

    @Override
    public final void setXPozicia(final int paXPozicia) {
        this.xPozicia = paXPozicia;
    }

    @Override
    public final void setYPozicia(final int paYPozicia) {
        this.yPozicia = paYPozicia;
    }

    @Override
    public final String getFarba() {
        return this.farba;
    }

    @Override
    public final String toString() {
        return "Na tomto bloku sa nachadza hrac";
    }
}
