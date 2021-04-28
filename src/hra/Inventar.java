/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;

import bloky.BDrevo;
import bloky.BKamen;
import bloky.BZlato;
import java.util.ArrayList;
import veci.IVec;
import veci.KrompacDrevo;
import veci.KrompacKamen;
import veci.KrompacZlato;
import veci.Nastroj;
import veci.Vec;

/**
 *
 * @author Tom
 */
public class Inventar {
    /**
     * inventar.
     */
    private ArrayList<IVec> invent = new ArrayList();
    /**
     * vytvori inventar.
     */
    public Inventar() {
    }
    /**
     * prida kus do inventara.
     * @param vec vec pridavana do inventara
     */
    public final void pridajKusDoInventara(final IVec vec) {
        boolean jeVInventari = false;
        for (IVec item : this.invent) {
            if (vec instanceof Vec && vec.getPopis().equals(item.getPopis())) {
                item.setPocet(item.getPocet() + 1);
                jeVInventari = true;
            } else if (vec instanceof Nastroj && vec.getPopis().equals(item
                    .getPopis())) {
                ((Nastroj) item).setPouzitelnost(((Nastroj) item)
                        .getPouzitelnost() + ((Nastroj) item)
                                .getBasePouzitelnost());
                jeVInventari = true;
            }
        }
        if (!jeVInventari) {
            vec.setPocet(vec.getPocet() + 1);
            this.invent.add(vec);
        }
    }
    /**
     * odoberie kus z inventara.
     * @param vec odoberana vec
     */
    public final void odoberKusZInventara(final IVec vec) {
        boolean jeVInventari = false;
        boolean nulovyPocet = false;
        IVec odlozeny = null;
        for (IVec item : this.invent) {
            if (vec instanceof Vec && vec.getPopis().equals(item.getPopis())) {
                item.setPocet(item.getPocet() - 1);
                if (item.getPocet() == 0) {
                    nulovyPocet = true;
                    odlozeny = item;
                }
                jeVInventari = true;
            } else if (vec instanceof Nastroj && vec.getPopis()
                    .equals(item.getPopis())) {
                ((Nastroj) item).setPouzitelnost(((Nastroj) item)
                        .getPouzitelnost() - 1);
                if (((Nastroj) item).getPouzitelnost() == 0) {
                    odlozeny = item;
                    nulovyPocet = true;
                }
                jeVInventari = true;
            }
        }
        if (nulovyPocet) {
            this.invent.remove(odlozeny);
        }
        if (!jeVInventari) {
            throw new IllegalArgumentException();
        }
    }
    /**
     * vypise zoznam.
     * @return string so vsetkymi vecami v inventari
     */
    public final String vypisInventar() {
        String vypis = "";
        for (IVec item : this.invent) {
            if (item instanceof Vec) {
                vypis += item.getPocet() + "x - " + item.getPopis() + "\n";
            } else {
                vypis += item.toString() + " (pouzitelnost " + ((Nastroj) item)
                        .getPouzitelnost() + ")\n";
            }
        }
        return vypis;
    }
    /**
     * vracia hodnotu true ak sa dany predmet nachádza v inventari.
     * @param veec hladana vec
     * @return true ak sa vec nachádza v inventári
     */
    public final boolean obsahuje(final String veec) {
        for (IVec item : this.invent) {
            if (veec.equals(item.getPopis())) {
                return true;
            }
        }
        return false;
    }
    /**
     *
     * @param veec string popis veci ktorej pocet chcem vediet
     * @return pocet veci so specifikovanym nazvom
     */
    public final int obsahujePocet(final String veec) {
        for (IVec item : this.invent) {
            if (veec.equals(item.getPopis())) {
                return item.getPocet();
            }
        }
        return 0;
    }
    /**
     *
     * @param popisNastroja string nastroja ktory chceme vytvorit
     */
    public final void vytvorNastroj(final String popisNastroja) {
        if (popisNastroja.equals(new KrompacDrevo().getPopis())) {
            if (this.obsahujePocet(new BDrevo(0, 0).getPopis()) >= 2 + 2 + 1) {
                this.pridajKusDoInventara(new KrompacDrevo());
                this.odoberKusZInventara(new Vec(new BDrevo(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BDrevo(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BDrevo(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BDrevo(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BDrevo(0, 0).getPopis()));
                throw new NullPointerException("Nastroj bol uspesne pridany"
                        + " do tvojho inventara");
            } else {
                throw new NullPointerException("Na vytvorenie potrebujes"
                        + " 5x drevo");
            }
        } else if (popisNastroja.equals(new KrompacKamen().getPopis())) {
            if (this.obsahujePocet(new BDrevo(0, 0).getPopis()) >= 2 && this
                    .obsahujePocet(new BKamen(0, 0).getPopis()) >= 2 + 1) {
                this.pridajKusDoInventara(new KrompacKamen());
                this.odoberKusZInventara(new Vec(new BDrevo(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BDrevo(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BKamen(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BKamen(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BKamen(0, 0).getPopis()));
                throw new NullPointerException("Nastroj bol uspesne pridany"
                        + " do tvojho inventara");
            } else {
                throw new NullPointerException("Na vytvorenie potrebujes 2x"
                        + " drevo a 3x kamen");
            }
        } else if (popisNastroja.equals(new KrompacZlato().getPopis())) {
            if (this.obsahujePocet(new BDrevo(0, 0).getPopis()) >= 2 && this
                    .obsahujePocet(new BZlato(0, 0).getPopis()) >= 2 + 1) {
                this.pridajKusDoInventara(new KrompacZlato());
                this.odoberKusZInventara(new Vec(new BDrevo(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BDrevo(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BZlato(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BZlato(0, 0).getPopis()));
                this.odoberKusZInventara(new Vec(new BZlato(0, 0).getPopis()));
                throw new NullPointerException("Nastroj bol uspesne pridany"
                        + " do tvojho inventara");
            } else {
                throw new NullPointerException("Na vytvorenie potrebujes 2x"
                        + " drevo a 3x zlato");
            }
        }
    }
    /**
     *
     * @param popisNastroja string nastroja ktory chceme vytvorit
     * @param ostavajucaPouzitelnost ostavajuca pouzitelnost
     */
    public final void vytvorNastroj(final String popisNastroja,
            final int ostavajucaPouzitelnost) {
        if (popisNastroja.equals(new KrompacDrevo().getPopis())) {
            this.pridajKusDoInventara(new KrompacDrevo(ostavajucaPouzitelnost));
        } else if (popisNastroja.equals(new KrompacKamen().getPopis())) {
            this.pridajKusDoInventara(new KrompacKamen(ostavajucaPouzitelnost));
        } else if (popisNastroja.equals(new KrompacZlato().getPopis())) {
            this.pridajKusDoInventara(new KrompacZlato(ostavajucaPouzitelnost));
        }
    }
    /**
     * pouziva sa na vypis inventara.
     * @return retazec veci s ocislovanim
     */
    public final String vypisInventarSOcislovanim() {
        String vypis = "";
        int i = 1;
        for (IVec item : this.invent) {
            if (item instanceof Vec) {
                vypis += i + ".  " + item.getPocet() + "x - " + item.toString()
                        + "\n";
                i++;
            }
        }
        return vypis;
    }
    /**
     * vrati i-tu vec v inventari.
     * @param cisloI cislo v zozname
     * @return popis hladanej veci
     */
    public final String getItuVec(final int cisloI) {
        int i = 0;
        for (IVec item : this.invent) {
            if (item instanceof Vec) {
                i++;
                if (i == cisloI) {
                    return item.getPopis();
                }
            }
        }
        return null;
    }
    /**
     *
     * @return pocet veci v inventari bez nastrojov
     */
    public final int getPocetVeci() {
        int i = 0;
        for (IVec item : this.invent) {
            if (item instanceof Vec) {
                i++;
            }
        }
        return i;
    }
    /**
     *
     * @return pocet vsetkych veci vratane nasstrojov
     */
    public final int getPocetVsetkych() {
        int i = 0;
        for (IVec item : this.invent) {
            i++;
        }
        return i;
    }
    /**
     * vymaze cely inventar.
     */
    public final void vymazCelyInventar() {
        this.invent = new ArrayList();
    }
    /**
     * vypise zoznam.
     * @return string so vsetkymi vecami v inventari
     */
    public final String vypisInventarString() {
        String vypis = "" + this.getPocetVsetkych();
        for (IVec item : this.invent) {
            if (item instanceof Vec) {
                vypis += " " + item.getPocet() + " " + item.getPopis();
            } else {
                vypis += " " + ((Nastroj) item).getPouzitelnost() + " "
                        + item.getPopis();
            }
        }
        return vypis;
    }
}
