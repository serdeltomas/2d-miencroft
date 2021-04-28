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
public class BTrava extends Blok {
    /**
     * blok travy.
     * @param xPozicia X-ova pozicia bloku
     * @param yPozicia Y-ova pozicia bloku
     */
    public BTrava(final int xPozicia, final int yPozicia) {
        super();
        super.setFarba("green");
        super.setPopis("trava");
        super.setXPozicia(xPozicia);
        super.setYPozicia(yPozicia);
    }
}
