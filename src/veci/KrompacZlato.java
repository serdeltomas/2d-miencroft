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
public class KrompacZlato extends Nastroj {
    /**
     * vytvori zlaty krompac.
     */
    public KrompacZlato() {
        super();
        final int dvesto = 200;
        super.setBasePouzitelnost(dvesto);
        super.setPouzitelnost(super.getBasePouzitelnost());
    }
    /**
     * vytvori zlaty krompac.
     * @param pouz ostavajuca pouzitelnost
     */
    public KrompacZlato(final int pouz) {
        super();
        final int dvesto = 200;
        super.setBasePouzitelnost(dvesto);
        super.setPouzitelnost(pouz);
    }
    @Override
    public final String toString() {
        return "Krompac zlaty";
    }
    @Override
    public final String getPopis() {
        return "krompacZ";
    }
}
