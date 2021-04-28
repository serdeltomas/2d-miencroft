/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hra;

import bloky.Blok;
import bloky.BDiamant;
import bloky.BDrevo;
import bloky.BNerozbitny;
import bloky.BKamen;
import bloky.BTrava;
import bloky.BUhlie;
import bloky.BZlato;
import bloky.Hrac;
import bloky.IBlok;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;
import main.Hra;
import veci.KrompacDrevo;
import veci.KrompacKamen;
import veci.KrompacZlato;
import veci.Vec;

/**
 *
 * @author Tom
 */
public class Pole {
    /**
     * rozmer pola.
     */
    private final int rozmer = 100;
    /**
     * pole IBlokov.
     */
    private IBlok[][] poleBlokov = new IBlok[this.rozmer][this.rozmer];
    /**
     * figurka hraca.
     */
    private Hrac hrac = new Hrac();
    /**
     * okno na zobrazovanie.
     */
    private Okno okno;
    /**
     * vytvori inventar.
     */
    private Inventar invent = new Inventar();
    /**
     * figurka hraca.
     */
    private Blok stavany = null;
    /**
     * vytvori pole.
     * @param hra hra
     */
    public Pole(final Hra hra) {
        for (int i = 0; i < this.rozmer; i++) {
            for (int j = 0; j < this.rozmer; j++) {
                this.poleBlokov[i][j] = null;
            }
        }
        this.vytvorSvet();
        this.okno = new Okno(hra);
    }
    /**
     * vytvori plochu sveta a pouklada na nu bloky podla urcitych pravidiel.
     */
    public final void vytvorSvet() {
        final int dvadsatsedem = 27;
        final int patdesiatsedem = 57;
        final int styridsatdva = 42;
        final int desat = 10;
        final int rozmerMinusTri = this.rozmer - 3;
        this.vycistiPole();
        for (int i = 0; i < this.rozmer; i++) {
            for (int j = 0; j < this.rozmer; j++) {
                if (j == 0 || i == 0 || j == this.rozmer - 1
                        || i == this.rozmer - 1) {
                    this.poleBlokov[i][j] = new BNerozbitny(i, j);
                } else if (j < (this.rozmer - dvadsatsedem) && j > (this.rozmer
                        - (patdesiatsedem - 2)) && i < rozmerMinusTri
                        && ThreadLocalRandom.current().nextInt(0,
                                styridsatdva + desat - 1) == 0  //0-50
                        && this.poleBlokov[i][j] == null) {
                    this.poleBlokov[i][j] = new BUhlie(i, j);
                    this.poleBlokov[i + 1][j] = new BUhlie(i + 1, j);
                    this.poleBlokov[i + 1][j - 1] = new BUhlie(i + 1, j - 1);
                    this.poleBlokov[i + 2][j - 1] = new BUhlie(i + 2, j - 1);
                } else if (j < this.rozmer - desat + 2 && j > this.rozmer
                        - styridsatdva && i < rozmerMinusTri + 1
                        && ThreadLocalRandom.current()
                                .nextInt(0, rozmerMinusTri + 2 + 2) == 0 //0-100
                        && this.poleBlokov[i][j] == null) {
                    this.poleBlokov[i][j] = new BZlato(i, j);
                    this.poleBlokov[i + 1][j] = new BZlato(i + 1, j);
                    this.poleBlokov[i][j + 1] = new BZlato(i, j + 1);
                    this.poleBlokov[i + 1][j + 1] = new BZlato(i + 1, j + 1);
                } else if (j < this.rozmer && j > this.rozmer - dvadsatsedem
                        && i < rozmerMinusTri + 1
                        && ThreadLocalRandom.current().nextInt(0,
                                (rozmerMinusTri + 2 + 1) * 2 + 1) == 0 //0-200
                        && this.poleBlokov[i][j] == null) {
                    this.poleBlokov[i][j] = new BDiamant(i, j);
                    this.poleBlokov[i + 1][j] = new BDiamant(i + 1, j);
                    this.poleBlokov[i + 1][j + 1] = new BDiamant(i + 1, j + 1);
                } else if (j > this.rozmer - patdesiatsedem
                        && this.poleBlokov[i][j] == null) {
                    this.poleBlokov[i][j] = new BKamen(i, j);
                }
            }
        }
        int j = this.rozmer - patdesiatsedem;
        for (int i = 1; i < this.rozmer - 1; i++) {
            for (int pocet = ThreadLocalRandom.current()
                    .nextInt(1, 2 + 2); pocet > 0; pocet--) { //1-3
                this.poleBlokov[i][j + pocet - 1] = new BTrava(i,
                        j + pocet - 1);
            }
            if (ThreadLocalRandom.current().nextInt(0, desat / 2) == 0 //0-7
                    && !(this.poleBlokov[i - 1][j - 1] instanceof BDrevo)
                    && this.hrac.getXPozicia() != i) {
                for (int pocet = 0; pocet < ThreadLocalRandom.current()
                        .nextInt(2, 2 + 2); pocet++) { //2-3
                    this.poleBlokov[i][j - pocet - 1] = new BDrevo(i,
                            j - pocet - 1);
                }
            }
        }
        this.poleBlokov[this.hrac.getXPozicia()][this.hrac.getYPozicia()]
                = this.hrac;
        this.reloadPole();
    }
    /**
     * vracia konkretny vyziadany IBlok.
     * @param i x-ova suradnica
     * @param j y-ova suradnica
     * @return IBlok so suradnicami [i,j]
     */
    public final IBlok getBlok(final int i, final int j) {
        if (i < this.rozmer && j < this.rozmer && i > -1 && j > -1) {
            return this.poleBlokov[i][j];
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    /**
     * nastavuje konkretny blok so suradnicami [i, j] na novy.
     * @param i x-ova suradnica
     * @param j y-ova suradnica
     * @param blok nastavovany blok
     */
    public final void setBlok(final int i, final int j, final IBlok blok) {
        if (i < this.rozmer && j < this.rozmer && i > -1 && j > -1) {
            this.poleBlokov[i][j] = blok;
        } else {
            throw new IndexOutOfBoundsException();
        }
    }
    /**
     * zobrazi vsetky stvorce v okne.
     */
    public final void zobraz() {
        this.okno.zobraz(this);
    }
    /**
     * skryje vsetky stvorce v okne.
     */
    public final void skry() {
        this.okno.skry();
    }
    /**
     * getter.
     * @return x-ova pozicia hraca
     */
    public final int getHracX() {
        return this.hrac.getXPozicia();
    }
    /**
     * getter.
     * @return y-ova pozicia hraca
     */
    public final int getHracY() {
        return this.hrac.getYPozicia();
    }
    /**
     * nastavi hracovi novu poziciu.
     * @param x nova x-ova suradnica hraca
     * @param y nova y-ova suradnica hraca
     */
    public final void setHracXY(final int x, final int y) {
        this.hrac.setXPozicia(x);
        this.hrac.setYPozicia(y);
    }
    /**
     * metoda posuva hraca bud smerom vlavo alebo vpravo.
     * @param smer vlavo alebo vpravo
     */
    public final void posun(final String smer) {
        if ("vlavo".equals(smer) && this.getBlok(this.getHracX() - 1,
                this.getHracY()) == null) {
            this.setBlok(this.getHracX(), this.getHracY(), null);
            this.hrac.setXPozicia(this.getHracX() - 1);
            this.hrac.setYPozicia(this.gravitacia(this.getHracX(),
                    this.getHracY()));
            this.setBlok(this.getHracX(), this.getHracY(), this.hrac);
        } else if ("vlavo".equals(smer) && this.getBlok(this.getHracX(),
                this.getHracY() - 1) == null && this.getBlok(this.getHracX()
                        - 1, this.getHracY() - 1) == null) {
            this.setBlok(this.getHracX(), this.getHracY(), null);
            this.hrac.setXPozicia(this.getHracX() - 1);
            this.hrac.setYPozicia(this.getHracY() - 1);
            this.setBlok(this.getHracX(), this.getHracY(), this.hrac);
        } else if ("vpravo".equals(smer) && this.getBlok(this.getHracX() + 1,
                this.getHracY()) == null) {
            this.setBlok(this.getHracX(), this.getHracY(), null);
            this.hrac.setXPozicia(this.getHracX() + 1);
            this.hrac.setYPozicia(this.gravitacia(this.getHracX(),
                    this.getHracY()));
            this.setBlok(this.getHracX(), this.getHracY(), this.hrac);
        } else if ("vpravo".equals(smer) && this.getBlok(this.getHracX() + 1,
                this.getHracY() - 1) == null && this.getBlok(this.getHracX(),
                        this.getHracY() - 1) == null) {
            this.setBlok(this.getHracX(), this.getHracY(), null);
            this.hrac.setXPozicia(this.getHracX() + 1);
            this.hrac.setYPozicia(this.getHracY() - 1);
            this.setBlok(this.getHracX(), this.getHracY(), this.hrac);
        } else {
            throw new IllegalArgumentException();
        }
    }
    /**
     * metoda sluzi na zistenie ci sa pod hracom nachadza vzduch alebo nie
     * a ak sa nachadza, tak hraca polozi na najnizsi mozny blok.
     * @param x x-ova suradnica bloku na ktory sa hrac snazi stupit
     * @param y y-ova suradnica bloku na ktory sa hrac snazi stupit
     * @return suradnica y na ktoru sa ma hrac posunut aby bola dodrzana gravity
     */
    public final int gravitacia(final int x, final int y) {
        int pomoc = y;
        while (this.getBlok(x, pomoc) == null) {
            if (this.getBlok(x, pomoc + 1) == null) {
                pomoc++;
            } else {
                return pomoc;
            }
        }
        return pomoc;
    }
    /**
     * odstrani z pola pozadovany blok.
     * @param blok blok na vykopanie
     */
    public final void vykop(final Blok blok) {
        //this.reloadPole();
        BNerozbitny bn = new BNerozbitny(0, 0);
        BKamen bk = new BKamen(0, 0);
        BUhlie bu = new BUhlie(0, 0);
        BZlato bz = new BZlato(0, 0);
        BDiamant bd = new BDiamant(0, 0);
        if (blok == null) {
            throw new NullPointerException("Vzduch sa neda vytazit");
        } else if (bn.getPopis().equals(blok.getPopis())) {
            throw new IndexOutOfBoundsException();
        } else if (bk.getPopis().equals(blok.getPopis())
                && this.invent.obsahuje(new KrompacDrevo().getPopis())) {
            this.pridajBlokDoInventaraAOdstranHoZPola(blok);
            this.invent.odoberKusZInventara(new KrompacDrevo());
        } else if (bk.getPopis().equals(blok.getPopis())
                && this.invent.obsahuje(new KrompacKamen().getPopis())) {
            this.pridajBlokDoInventaraAOdstranHoZPola(blok);
            this.invent.odoberKusZInventara(new KrompacKamen());
        } else if (bk.getPopis().equals(blok.getPopis())
                && this.invent.obsahuje(new KrompacZlato().getPopis())) {
            this.pridajBlokDoInventaraAOdstranHoZPola(blok);
            this.invent.odoberKusZInventara(new KrompacZlato());
        } else if (bk.getPopis().equals(blok.getPopis())) {
            throw new UnsupportedOperationException(new KrompacDrevo()
                    .toString());
        } else if (bu.getPopis().equals(blok.getPopis())
                && this.invent.obsahuje(new KrompacKamen().getPopis())) {
            this.pridajBlokDoInventaraAOdstranHoZPola(blok);
            this.invent.odoberKusZInventara(new KrompacKamen());
        } else if (bu.getPopis().equals(blok.getPopis())
                && this.invent.obsahuje(new KrompacZlato().getPopis())) {
            this.pridajBlokDoInventaraAOdstranHoZPola(blok);
            this.invent.odoberKusZInventara(new KrompacZlato());
        } else if (bu.getPopis().equals(blok.getPopis())) {
            throw new UnsupportedOperationException(new KrompacKamen()
                    .toString());
        } else if (bz.getPopis().equals(blok.getPopis())
                && this.invent.obsahuje(new KrompacKamen().getPopis())) {
            this.pridajBlokDoInventaraAOdstranHoZPola(blok);
            this.invent.odoberKusZInventara(new KrompacKamen());
        } else if (bz.getPopis().equals(blok.getPopis())
                && this.invent.obsahuje(new KrompacZlato().getPopis())) {
            this.pridajBlokDoInventaraAOdstranHoZPola(blok);
            this.invent.odoberKusZInventara(new KrompacZlato());
        } else if (bz.getPopis().equals(blok.getPopis())) {
            throw new UnsupportedOperationException(new KrompacKamen()
                    .toString());
        } else if (bd.getPopis().equals(blok.getPopis())
                && this.invent.obsahuje(new KrompacZlato().getPopis())) {
            this.pridajBlokDoInventaraAOdstranHoZPola(blok);
            this.invent.odoberKusZInventara(new KrompacZlato());
        } else if (bd.getPopis().equals(blok.getPopis())) {
            throw new UnsupportedOperationException(new KrompacZlato()
                    .toString());
        } else {
            this.pridajBlokDoInventaraAOdstranHoZPola(blok);
        }
    }
    /**
     *
     * @return string vypisu obsahu celeho inventara
     */
    public final String vypisInventar() {
        return this.invent.vypisInventar();
    }
    /**
     *
     * @return string vypisu obsahu celeho inventara
     */
    public final String vypisInventarString() {
        return this.invent.vypisInventarString();
    }
    /**
     * vypise cele zobrazovane pole do konzoly.
     */
    public final void vypisPole() {
        final int sedem = 7;
        final int styri = 4;
        for (int j = this.getHracY() - styri; j < this.getHracY()
                + (styri + 2); j++) {
            for (int i = this.getHracX() - sedem; i < this.getHracX()
                    + (sedem + 1); i++) {
                if (this.getBlok(i, j) == null) {
                    System.out.print("white");
                } else {
                    System.out.print(this.getBlok(i, j).getFarba());
                }
                System.out.print("\t");
            }
            System.out.println();
        }
    }
    /**
     * nastavi kazdemu bloku v poli jeho suradnice.
     */
    public final void reloadPole() {
        for (int x = 0; x < this.rozmer; x++) {
            for (int y = 0; y < this.rozmer; y++) {
                if (this.poleBlokov[x][y] != null) {
                    this.poleBlokov[x][y].setXPozicia(x);
                    this.poleBlokov[x][y].setYPozicia(y);
                }
            }
        }
    }
    /**
     * samopopisne.
     * @param blok pridavany blok
     */
    public final void pridajBlokDoInventaraAOdstranHoZPola(final Blok blok) {
        this.invent.pridajKusDoInventara(new Vec(blok.getPopis()));
        this.setBlok(blok.getXPozicia(), blok.getYPozicia(), null);
        if (this.getHracX() == blok.getXPozicia() && this.getHracY()
                + 1 == blok.getYPozicia()) {
            this.setBlok(this.getHracX(), this.getHracY(), null);
            this.hrac.setYPozicia(this.getHracY() + 1);
            this.hrac.setYPozicia(this.gravitacia(this.getHracX(),
                    this.getHracY()));
            this.setBlok(this.getHracX(), this.getHracY(), this.hrac);
        } else {
            this.setBlok(blok.getXPozicia(), blok.getYPozicia(), null);
        }
    }
    /**
     * samopopisne.
     */
    public final void pridajBlokDoPolaAOdstranHoZInventara() {
        try {
            this.invent.odoberKusZInventara(new Vec(this.stavany.getPopis()));
            this.setBlok(this.stavany.getXPozicia(),
                    this.stavany.getYPozicia(), this.stavany);
            if (this.getHracX() == this.stavany.getXPozicia()
                    && this.getHracY() == this.stavany.getYPozicia()) {
                this.setBlok(this.getHracX(), this.getHracY(),
                        this.stavany); //tu sa nechcem dostat
                this.hrac.setYPozicia(this.getHracY() - 1);
                this.setBlok(this.getHracX(), this.getHracY(), this.hrac);
            } else {
                this.setBlok(this.stavany.getXPozicia(),
                        this.stavany.getYPozicia(), this.stavany);
            }
        } catch (IllegalArgumentException ex) {
            throw ex;
        }
    }
    /**
     * metoda vlozi do pola stavany blok.
     * @param blok stavany blok
     * @param surX suradnica x miesta na ktore sa ma stavany blok umiestnit
     * @param surY suradnica y miesta na ktore sa ma stavany blok umiestnit
     */
    public final void postav(final Blok blok, final int surX, final int surY) {
        //this.reloadPole();
        this.getStavanyBlok();
        this.stavany.setXPozicia(surX);
        this.stavany.setYPozicia(surY);
        if (blok != null) {
            if (surX == this.getHracX() && surY == this.getHracY() + 1
                    && null == this.getBlok(this.getHracX(),
                            this.getHracY() - 1)) {
                this.stavany.setXPozicia(this.getHracX());
                this.stavany.setYPozicia(this.getHracY());
                try {
                    this.pridajBlokDoPolaAOdstranHoZInventara();
                } catch (IllegalArgumentException ex) {
                    this.stavany = null;
                    //this.postav(blok, surX, surY);
                    System.out.println("if null");
                    throw new NullPointerException("Dosli ti bloky");
                }
            } else if (surX == this.getHracX() && surY == this.getHracY()) {
                this.stavany = null;
                //throw new NullPointerException("Dosli ti bloky");
            } else {
                throw new NullPointerException("Nemozes postavit blok na"
                        + " miesto, kde uz je iny blok");
            }
        } else {
            this.stavany.setXPozicia(surX);
            this.stavany.setYPozicia(surY);
            try {
                this.pridajBlokDoPolaAOdstranHoZInventara();
            } catch (IllegalArgumentException ex) {
                this.stavany = null;
                //this.postav(blok, surX, surY);
                System.out.println("else null");
                throw new NullPointerException("Dosli ti bloky");
            }
        }
    }
    /**
     * ziska od hraca alebo z atributu typ bloku ktorym sa stavia.
     */
    public final void getStavanyBlok() {
        if (this.stavany == null) {
            if (this.invent.getPocetVeci() == 0) {
                throw new NullPointerException("Nemas ziadne bloky");
            }
            int vyber = Integer.parseInt(JOptionPane.showInputDialog(null,
                    this.invent.vypisInventarSOcislovanim(),
                    "Zvolte moznost", 1));
            if (this.invent.getItuVec(vyber) != null) {
                this.stavany = this.getBlokZoStringu(this.invent
                        .getItuVec(vyber));
            } else {
                throw new NumberFormatException();
            }
        } else {
            this.stavany = this.getBlokZoStringu(stavany.getPopis());
        }
    }
    /**
     * samopopisna.
     * @param popisBloku string
     * @return Blok vytvoreny zo stringu
     */
    public final Blok getBlokZoStringu(final String popisBloku) {
        if (popisBloku.equals(new BDiamant(0, 0).getPopis())) {
            return new BDiamant(0, 0);
        } else if (popisBloku.equals(new BDrevo(0, 0).getPopis())) {
            return new BDrevo(0, 0);
        } else if (popisBloku.equals(new BKamen(0, 0).getPopis())) {
            return new BKamen(0, 0);
        } else if (popisBloku.equals(new BTrava(0, 0).getPopis())) {
            return new BTrava(0, 0);
        } else if (popisBloku.equals(new BUhlie(0, 0).getPopis())) {
            return new BUhlie(0, 0);
        } else if (popisBloku.equals(new BZlato(0, 0).getPopis())) {
            return new BZlato(0, 0);
        } else if (popisBloku.equals(new BNerozbitny(0, 0).getPopis())) {
            return new BNerozbitny(0, 0);
        } else {
            return null;
        }
    }
    /**
     * vytvara nastroj.
     * @param nastroj string aky nastroj sa ma vytvorit
     */
    public final void vytvorNastroj(final String nastroj) {
        this.invent.vytvorNastroj(nastroj);
    }
    /**
     * vytvara nastroj.
     * @param nastroj string aky nastroj sa ma vytvorit
     * @param pouzit zostavajuca pouzitelnost nastroja
     */
    public final void vytvorNastroj(final String nastroj, final int pouzit) {
        this.invent.vytvorNastroj(nastroj, pouzit);
    }
    /**
     * nastavi stavany blok na pozadovany typ.
     * @param stavanyy blok ktorym sa ma stavat
     */
    public final void setStavany(final Blok stavanyy) {
        this.stavany = stavanyy;
    }
    /**
     * getter.
     * @return rozmer
     */
    public final int getRozmer() {
       return this.rozmer;
    }
    /**
     * vymaze cely inventar.
     */
    public final void vymazInventar() {
        this.invent.vymazCelyInventar();
    }
    /**
     * prida blok do inventara.
     * @param blok pridavany blok
     */
    public final void pridajBlokDoInventara(final String blok) {
        this.invent.pridajKusDoInventara(new Vec(blok));
    }
    /**
     * vymaze vsetky bloky pola.
     */
    public final void vycistiPole() {
        for (int i = 0; i < this.rozmer; i++) {
            for (int j = 0; j < this.rozmer; j++) {
                this.poleBlokov[i][j] = null;
            }
        }
    }
    /**
     * getter.
     * @return hrac
     */
    public final Hrac getHrac() {
        return hrac;
    }
}
