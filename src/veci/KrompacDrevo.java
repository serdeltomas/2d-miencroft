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
public class KrompacDrevo extends Nastroj {
    /**
     * vytvori dreveny krompac.
     */
    public KrompacDrevo() {
        super();
        final int patdesiat = 50;
        super.setBasePouzitelnost(patdesiat);
        super.setPouzitelnost(super.getBasePouzitelnost());
    }
    /**
     * vytvori dreveny krompac.
     * @param pouz ostavajuca pouzitelnost
     */
    public KrompacDrevo(final int pouz) {
        super();
        final int patdesiat = 50;
        super.setBasePouzitelnost(patdesiat);
        super.setPouzitelnost(pouz);
    }
    @Override
    public final String toString() {
        return "Krompac dreveny";
    }
    @Override
    public final String getPopis() {
        return "krompacD";
    }
}