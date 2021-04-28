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
public class BDrevo extends Blok {
    /**
     * blok dreva.
     * @param xPozicia X-ova pozicia bloku
     * @param yPozicia Y-ova pozicia bloku
     */
    public BDrevo(final int xPozicia, final int yPozicia) {
        super();
        super.setFarba("brown");
        super.setPopis("drevo");
        super.setXPozicia(xPozicia);
        super.setYPozicia(yPozicia);
    }
}
