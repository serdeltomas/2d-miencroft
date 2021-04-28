package hra;

import frame.Platno;
import java.awt.Rectangle;
import main.Hra;

/**
 * ObdÄşĹľnik, s ktorĂ˝m moĹľno pohybovaĹĄ a nakreslĂ­ sa na plĂˇtno.
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0  (15 July 2000)
 */

public class Stvorec {
    private int stranaA;
    private int stranaB;
    private int lavyHornyX;
    private int lavyHornyY;
    private String farba;
    private boolean jeViditelny;
    private Hra hra;

    /**
     * Vytvor novĂ˝ obdÄşĹľnik preddefinovanej farby na preddefinovanej pozĂ­cii.
     * @param strana --
     * @param hraa --
     */
    public Stvorec(int strana, Hra hraa) {
        this.stranaA = strana;
        this.stranaB = strana;
        this.lavyHornyX = 0;
        this.lavyHornyY = 0;
        this.farba = "white";
        this.jeViditelny = false;
        this.hra = hraa;
    }

    /**
     * (ObdÄşĹľnik) Zobraz sa.
     */
    public void zobraz() {
        this.jeViditelny = true;
        this.nakresli();
    }
    
    /**
     * (ObdÄşĹľnik) Skry sa.
     */
    public void skry() {
        this.zmaz();
        this.jeViditelny = false;
    }
    
    /**
     * (ObdÄşĹľnik) PosuĹ sa vpravo o pevnĂş dÄşĹľku.
     */
    public void posunVpravo() {
        this.posunVodorovne(20);
    }

    /**
     * (ObdÄşĹľnik) PosuĹ sa vÄľavo o pevnĂş dÄşĹľku.
     */
    public void posunVlavo() {
        this.posunVodorovne(-20);
    }

    /**
     * (ObdÄşĹľnik) PosuĹ sa hore o pevnĂş dÄşĹľku.
     */
    public void posunHore() {
        this.posunZvisle(-20);
    }

    /**
     * (ObdÄşĹľnik) PosuĹ sa dole o pevnĂş dÄşĹľku.
     */
    public void posunDole() {
        this.posunZvisle(20);
    }

    /**
     * (ObdÄşĹľnik) PosuĹ sa vodorovne o dÄşĹľku danĂş parametrom.
     * @param vzdialenost --
     */
    public void posunVodorovne(int vzdialenost) {
        this.zmaz();
        this.lavyHornyX += vzdialenost;
        this.nakresli();
    }

    /**
     * (ObdÄşĹľnik) PosuĹ sa zvisle o dÄşĹľku danĂş parametrom.
     * @param vzdialenost --
     */
    public void posunZvisle(int vzdialenost) {
        this.zmaz();
        this.lavyHornyY += vzdialenost;
        this.nakresli();
    }

    /**
     * (ObdÄşĹľnik) PosuĹ sa pomaly vodorovne o dÄşĹľku danĂş parametrom.
     * @param vzdialenost --
     */
    public void pomalyPosunVodorovne(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else  {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.lavyHornyX += delta;
            this.nakresli();
        }
    }

    /**
     * (ObdÄşĹľnik) PosuĹ sa pomaly vodorovne o dÄşĹľku danĂş parametrom.
     * @param vzdialenost --
     */
    public void pomalyPosunZvisle(int vzdialenost) {
        int delta;

        if (vzdialenost < 0) {
            delta = -1;
            vzdialenost = -vzdialenost;
        } else {
            delta = 1;
        }

        for (int i = 0; i < vzdialenost; i++) {
            this.lavyHornyY += delta;
            this.nakresli();
        }
    }

    /**
     * (ObdÄşĹľnik) ZmeĹ dÄşĹľky strĂˇn na hodnoty danĂ© parametrami.
     * DÄşĹľka strany musĂ­ byĹĄ nezĂˇpornĂ© celĂ© ÄŤĂ­slo. 
     * @param stranaA --
     * @param stranaB --
     */
    public void zmenStrany(int stranaA, int stranaB) {
        this.zmaz();
        this.stranaA = stranaA;
        this.stranaB = stranaB;
        this.nakresli();
    }
    
    /**
     *
     * @param lavyHornyX --
     * @param lavyHornyY --
     */
    public void zmenPolohu(int lavyHornyX, int lavyHornyY) {
        boolean nakresleny = this.jeViditelny;
        this.zmaz();
        this.lavyHornyX = lavyHornyX;
        this.lavyHornyY = lavyHornyY;
        if (nakresleny) {
            this.nakresli();
        }
    }

    /**
     * (ObdÄşĹľnik) ZmeĹ farbu na hodnotu danĂş parametrom.
     * Nazov farby musĂ­ byĹĄ po anglicky.
     * MoĹľnĂ© farby sĂş tieto:
     * ÄŤervenĂˇ - "red"
     * ĹľltĂˇ    - "yellow"
     * modrĂˇ   - "blue"
     * zelenĂˇ  - "green"
     * fialovĂˇ - "magenta"
     * ÄŤierna  - "black"
     * biela   - "white"
     * hnedĂˇ   - "brown"
     * @param farba --
     */
    public void zmenFarbu(String farba) {
        this.farba = farba;
        this.nakresli();
    }

    /*
     * Draw the square with current specifications on screen.
     */
    private void nakresli() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno(this.hra);
            canvas.draw(this, this.farba,
                        new Rectangle(this.lavyHornyX, this.lavyHornyY, this.stranaA, this.stranaB));
            //canvas.wait(20);
        }
    }

    /*
     * Erase the square on screen.
     */
    private void zmaz() {
        if (this.jeViditelny) {
            Platno canvas = Platno.dajPlatno(this.hra);
            canvas.erase();
        }
    }
}