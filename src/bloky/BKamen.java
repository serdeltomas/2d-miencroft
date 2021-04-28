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
public class BKamen extends Blok {
    /**
     * blok skaly.
     * @param xPozicia X-ova pozicia bloku
     * @param yPozicia Y-ova pozicia bloku
     */
    public BKamen(final int xPozicia, final int yPozicia) {
        super();
        super.setFarba("gray");
        super.setPopis("kamen");
        super.setXPozicia(xPozicia);
        super.setYPozicia(yPozicia);
    }
}
