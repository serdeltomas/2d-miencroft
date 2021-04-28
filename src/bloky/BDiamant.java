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
public class BDiamant extends Blok {
    /**
     * blok diamantu.
     * @param xPozicia X-ova pozicia bloku
     * @param yPozicia Y-ova pozicia bloku
     */
    public BDiamant(final int xPozicia, final int yPozicia) {
        super();
        super.setFarba("diamond");
        super.setPopis("diamant");
        super.setXPozicia(xPozicia);
        super.setYPozicia(yPozicia);
    }
}
