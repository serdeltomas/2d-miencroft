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
public abstract class Nastroj implements IVec {
    /**
     * zakladna pouzitelnost.
     */
    private int basePouzitelnost;
    /**
     * aktualna pouzitelnost.
     */
    private int pouzitelnost;
    /**
     * pocet veci urciteho druhu.
     */
    private int pocet;
    /**
     * vytvori instanciu.
     */
    public Nastroj() {
        this.basePouzitelnost = 0;
        this.pouzitelnost = 0;
        this.pocet = 0;
    }
    @Override
    public abstract String toString();
    /**
     * @return zostavajuci pocet pouziti
     */
    public final int getPouzitelnost() {
        return pouzitelnost;
    }
    /**
     * @param pouzitie pocet pouziti
     */
    public final void setPouzitelnost(final int pouzitie) {
        this.pouzitelnost = pouzitie;
    }
    /**
     * uberie 1 pouzitie.
     */
    public final void pouzi() {
        if (this.pouzitelnost > 0) {
            this.setPouzitelnost(this.pouzitelnost - 1);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public abstract String getPopis();
    @Override
    public final int getPocet() {
        return this.pocet;
    }
    @Override
    public final void setPocet(final int poceet) {
        this.pocet = poceet;
    }
    /**
     * @return zakladna pouzitelnost
     */
    public final int getBasePouzitelnost() {
        return basePouzitelnost;
    }
    /**
     * @param basePouzitie nastavovana pouzitelnost
     */
    public final void setBasePouzitelnost(final int basePouzitie) {
        this.basePouzitelnost = basePouzitie;
    }
}