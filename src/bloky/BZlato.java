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
public class BZlato extends Blok {
    /**
     * blok zlata.
     * @param xPozicia X-ova pozicia bloku
     * @param yPozicia Y-ova pozicia bloku
     */
    public BZlato(final int xPozicia, final int yPozicia) {
        super();
        super.setFarba("gold");
        super.setPopis("zlato");
        super.setXPozicia(xPozicia);
        super.setYPozicia(yPozicia);
    }
}