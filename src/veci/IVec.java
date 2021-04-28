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
public interface IVec {
    /**
     * popis pouzity v inventari na identifikaciu.
     * @return popis
     */
    String getPopis();
    /**
     * popis pouzity v inventari na vypisanie inventara.
     * @return popis
     */
    @Override
    String toString();
    /**
     * ak je to nastroj tak vrati pouzitelnost.
     * @return pocet itemov toho druhu v inventari
     */
    int getPocet();
    /**
     * nastavi pocet kusov konkretnej veci v inventari.
     * @param poceet novy pocet kusov
     */
    void setPocet(int poceet);
}
