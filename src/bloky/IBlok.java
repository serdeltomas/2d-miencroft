/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bloky;

/**
 * @author Tom
 */
public interface IBlok {
    /**
     * @return X-ova pozicia bloku
     */
    int getXPozicia();
    /**
     * @return Y-ova pozicia bloku
     */
    int getYPozicia();
    /**
     * @return farba bloku
     */
    String getFarba();
    /**
     * nastavi X-ovu poziciu bloku.
     * @param xPozicia X-ova pozicia bloku
     */
    void setXPozicia(int xPozicia);
    /**
     * nastavi Y-ovu poziciu bloku.
     * @param yPozicia Y-ova pozicia bloku
     */
    void setYPozicia(int yPozicia);
    /**
     * @return string info o bloku
     */
    @Override
    String toString();
}
