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
public class KrompacKamen extends Nastroj {
    /**
     * vytvori kamenny krompac.
     */
    public KrompacKamen() {
        super();
        final int sto = 100;
        super.setBasePouzitelnost(sto);
        super.setPouzitelnost(super.getBasePouzitelnost());
    }
    /**
     * vytvori krompac z kamena.
     * @param pouz ostavajuca pouzitelnost
     */
    public KrompacKamen(final int pouz) {
        super();
        final int sto = 100;
        super.setBasePouzitelnost(sto);
        super.setPouzitelnost(pouz);
    }
    @Override
    public final String toString() {
        return "Krompac kamenny";
    }
    @Override
    public final String getPopis() {
        return "krompacK";
    }
}
