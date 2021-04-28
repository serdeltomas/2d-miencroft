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
public class BNerozbitny extends Blok {
    /**
     * nerozbitny blok.
     * @param xPozicia X-ova pozicia bloku
     * @param yPozicia Y-ova pozicia bloku
     */
    public BNerozbitny(final int xPozicia, final int yPozicia) {
        super();
        super.setFarba("red");
        super.setPopis("nerozbitny");
        super.setXPozicia(xPozicia);
        super.setYPozicia(yPozicia);
    }
}