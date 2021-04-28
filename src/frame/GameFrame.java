/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import bloky.Blok;
import hra.Pole;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import main.Hra;
import veci.KrompacDrevo;
import veci.KrompacKamen;
import veci.KrompacZlato;
/**
 *
 * @author Tom
 */
public class GameFrame extends JFrame {
    /**
     * menu bar.
     */
    private final JMenuBar mb;
    /**
     * zobrazuje, ci je stavanie zapnute alebo nie.
     */
    private JMenu mnStavanie;
    /**
     * pole.
     */
    private Pole pole;
    /**
     * hra.
     */
    private Hra hra;
    /**
     * rozsirujeJFrame, pouzite v triede Platno.
     * @param hraa instancia hry s ktorou pracujeme (na zaciatku nova hra)
     */
    public GameFrame(final Hra hraa) {
        //JOptionPane.showMessageDialog( null,"HRA ZACINA");
        this.hra = hraa;
        this.pole = hra.getPole();
        //this.pole.zobraz();
        //gameeeeeeeeeee
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing(final WindowEvent e) {
                akciaKoniec();
            }
        }
        );

        //rozmiestnenie prvkov
        this.setLayout(new BorderLayout(2, 2));
        //instancia platna do center plochy
        this.add(BorderLayout.CENTER, new JComponent() { });

        //prihlasenie klavesnice
        Klavesnica klav = new Klavesnica();
        this.addKeyListener(klav);

        //menu
        this.mb = new JMenuBar();
        this.setJMenuBar(this.mb);
        //pridame polozky na menu
        JMenu mnMenu = new JMenu("Menu");
        this.mb.add(mnMenu);
        JMenu mnInfo = new JMenu("Info");
        this.mb.add(mnInfo);
        this.mnStavanie = new JMenu("Stavanie - VYPNUTE");

        //pridanie zvislej volby do poloziek menu subor
        JMenuItem mniNova = new JMenuItem("Zacni novu hru");
        mniNova.addActionListener(new ActionListener() {
            @Override public void actionPerformed(final ActionEvent e) {
                hra.setNazovSveta(JOptionPane.showInputDialog("Zadajte názov"
                        + " nového sveta"));
                pole.skry();
                final int patdesiat = 50;
                final int styridsatdva = 42;
                pole.setHracXY(patdesiat, styridsatdva);
                pole.vytvorSvet();
                pole.zobraz();
            }
        }
        );
        // vloz do menu
        mnMenu.add(mniNova);
        //nacitanie ulozenej hry
        JMenuItem mniNacitaj = new JMenuItem("Nacitaj ulozenu hru");
        mniNacitaj.addActionListener(new ActionListener() {
            @Override public void actionPerformed(final ActionEvent e) {
                try {
                    pole.skry();
                    read();
                    pole.zobraz();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "problem so suborom");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Nespravna hodnota");
                }
            }
        }
        );
        // vloz do menu
        mnMenu.add(mniNacitaj);
        //ulozenie hry do suboru
        JMenuItem mniUloz = new JMenuItem("Uloz hru");
        mniUloz.addActionListener(new ActionListener() {
            @Override public void actionPerformed(final ActionEvent e) {
                try {
                    write(hra.stringCelejHry());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "problem so suborom");
                }
            }
        }
        );
        // vloz do menu
        mnMenu.add(mniUloz);
        JMenuItem mniKoniec = new JMenuItem("Koniec hry");
        mniKoniec.addActionListener(new ActionListener() {
            @Override public void actionPerformed(final ActionEvent e) {
                akciaKoniec();
            }
        }
        );
        // vloz do menu
        mnMenu.add(mniKoniec);

        JMenuItem mniInfo = new JMenuItem("Ciel hry");
        mniInfo.addActionListener(new ActionListener() {
            @Override public void actionPerformed(final ActionEvent e) {
                infoCiel();
            }
        }
        );
        // vloz do menu
        mnInfo.add(mniInfo);
        JMenuItem mniHTP = new JMenuItem("Ako hrat");
        mniHTP.addActionListener(new ActionListener() {
            @Override public void actionPerformed(final ActionEvent e) {
                infoHowtoplay();
            }
        }
        );
        // vloz do menu
        mnInfo.add(mniHTP);
        this.setVisible(true);
        this.toFront();
    }
    /**
     * ukoncuje hru.
     */
    public final void akciaKoniec() {
        if (JOptionPane.showConfirmDialog(null, "Vasa hra nebude ulozena."
                + " Chcete naozaj ukoncit?", "Zvolte moznost",
                JOptionPane.YES_NO_OPTION, JOptionPane
                        .QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
        this.repaint();
    }
    /**
     * meni iba metodu keyPressed().
     */
    private class Klavesnica extends KeyAdapter {
        /**
         * boolean ci je stavanie zapnute.
         */
        private boolean stavanieOn = false;
        @Override public void keyPressed(final KeyEvent ks) {
            try {
                if (this.stavanieOn) {
                    pole.skry();
                    switch (ks.getKeyCode()) {
                        case KeyEvent.VK_ESCAPE:
                            akciaKoniec();
                            break;
                        case KeyEvent.VK_LEFT:
                            akciaPosun("vlavo");
                            break;
                        case KeyEvent.VK_RIGHT:
                            akciaPosun("vpravo");
                            break;
                        case KeyEvent.VK_ADD:
                            akciaVytvor();
                            break;
                        case KeyEvent.VK_NUMPAD1:
                            pole.postav((Blok) pole.getBlok(pole.getHracX() - 1,
                                    pole.getHracY() + 1), pole.getHracX() - 1,
                                    pole.getHracY() + 1);
                            break;
                        case KeyEvent.VK_NUMPAD2:
                            pole.postav((Blok) pole.getBlok(pole.getHracX(),
                                    pole.getHracY() + 1), pole.getHracX(),
                                    pole.getHracY() + 1);
                            break;
                        case KeyEvent.VK_NUMPAD3:
                            pole.postav((Blok) pole.getBlok(pole.getHracX() + 1,
                                    pole.getHracY() + 1), pole.getHracX() + 1,
                                    pole.getHracY() + 1);
                            break;
                        case KeyEvent.VK_NUMPAD4:
                            pole.postav((Blok) pole.getBlok(pole.getHracX() - 1,
                                    pole.getHracY()), pole.getHracX() - 1,
                                    pole.getHracY());
                            break;
                        case KeyEvent.VK_NUMPAD5:
                            this.stavanieOn = false;
                            ukazStavanie(false);
                            pole.setStavany(null);
                            break;
                        case KeyEvent.VK_NUMPAD6:
                            pole.postav((Blok) pole.getBlok(pole.getHracX() + 1,
                                    pole.getHracY()), pole.getHracX() + 1,
                                    pole.getHracY());
                            break;
                        case KeyEvent.VK_NUMPAD7:
                            pole.postav((Blok) pole.getBlok(pole.getHracX() - 1,
                                    pole.getHracY() - 1), pole.getHracX() - 1,
                                    pole.getHracY() - 1);
                            break;
                        case KeyEvent.VK_NUMPAD8:
                            pole.postav((Blok) pole.getBlok(pole.getHracX(),
                                    pole.getHracY() - 1), pole.getHracX(),
                                    pole.getHracY() - 1);
                            break;
                        case KeyEvent.VK_NUMPAD9:
                            pole.postav((Blok) pole.getBlok(pole.getHracX() + 1,
                                    pole.getHracY() - 1), pole.getHracX() + 1,
                                    pole.getHracY() - 1);
                            break;
                        case KeyEvent.VK_I:
                            JOptionPane.showMessageDialog(null,
                                    pole.vypisInventar(),
                                    "obsah vasho inventara", 1);
                            break;
                        default:
                            break;
                    }
                    pole.zobraz();
                } else {                                //stavanie off
                    pole.skry();
                    switch (ks.getKeyCode()) {
                        case KeyEvent.VK_ESCAPE:
                            akciaKoniec();
                            break;
                        case KeyEvent.VK_LEFT:
                            akciaPosun("vlavo");
                            break;
                        case KeyEvent.VK_RIGHT:
                            akciaPosun("vpravo");
                            break;
                        case KeyEvent.VK_ADD:
                            akciaVytvor();
                            break;
                        case KeyEvent.VK_NUMPAD1:
                            pole.vykop((Blok) pole.getBlok(pole.getHracX() - 1,
                                    pole.getHracY() + 1));
                            break;
                        case KeyEvent.VK_NUMPAD2:
                            pole.vykop((Blok) pole.getBlok(pole.getHracX(),
                                    pole.getHracY() + 1));
                            break;
                        case KeyEvent.VK_NUMPAD3:
                            pole.vykop((Blok) pole.getBlok(pole.getHracX() + 1,
                                    pole.getHracY() + 1));
                            break;
                        case KeyEvent.VK_NUMPAD4:
                            pole.vykop((Blok) pole.getBlok(pole.getHracX() - 1,
                                    pole.getHracY()));
                            break;
                        case KeyEvent.VK_NUMPAD5:
                            this.stavanieOn = true;
                            ukazStavanie(true);
                            break;
                        case KeyEvent.VK_NUMPAD6:
                            pole.vykop((Blok) pole.getBlok(pole.getHracX() + 1,
                                    pole.getHracY()));
                            break;
                        case KeyEvent.VK_NUMPAD7:
                            pole.vykop((Blok) pole.getBlok(pole.getHracX() - 1,
                                    pole.getHracY() - 1));
                            break;
                        case KeyEvent.VK_NUMPAD8:
                            pole.vykop((Blok) pole.getBlok(pole.getHracX(),
                                    pole.getHracY() - 1));
                            break;
                        case KeyEvent.VK_NUMPAD9:
                            pole.vykop((Blok) pole.getBlok(pole.getHracX() + 1,
                                    pole.getHracY() - 1));
                            break;
                        case KeyEvent.VK_I:
                            JOptionPane.showMessageDialog(null,
                                    pole.vypisInventar(),
                                    "obsah vasho inventara", 1);
                            break;
                        default:
                            break;
                    }
                    pole.zobraz();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Nespravna hodnota");
            } catch (IndexOutOfBoundsException ex) {
                JOptionPane.showMessageDialog(null, "Nerozbitny blok");
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "V ceste ti zavadzia blok");
            } catch (NullPointerException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            } catch (UnsupportedOperationException ex) {
                JOptionPane.showMessageDialog(null,
                        "Na tuto akciu potrebujes aspon " + ex.getMessage());
            }
            //pole.vypisPole();
            //System.out.println(pole.vypisInventar());
        }
    }
    /**
     * posunie hraca specifikovanym smerom.
     * @param smer string ktorym smerom sa posuvame
     */
    public final void akciaPosun(final String smer) {
        try {
            pole.skry();
            this.pole.posun(smer);
            pole.zobraz();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "V ceste ti zavadzia blok");
        }
    }
    /**
     * daju sa tu vytvarat nastroje.
     */
    public final void akciaVytvor() {
        int vyber = Integer.parseInt(JOptionPane.showInputDialog(null, "1. "
                + new KrompacDrevo().toString() + "\n2. "
                + new KrompacKamen().toString() + "\n3. "
                + new KrompacZlato().toString(), "Zvolte moznost", 1));
        switch (vyber) {
            case 1:
                this.pole.vytvorNastroj(new KrompacDrevo().getPopis());
                break;
            case 2:
                this.pole.vytvorNastroj(new KrompacKamen().getPopis());
                break;
            case 2 + 1:
                this.pole.vytvorNastroj(new KrompacZlato().getPopis());
                break;
            default:
                break;
        }
    }
    /**
     * zobrazuje ci je stavanie zapnute alebo nie.
     * @param stavanieOn true-stavanie zapnute, false-stavanie vypnute
     */
    public final void ukazStavanie(final boolean stavanieOn) {
        if (stavanieOn) {
            this.mb.remove(mnStavanie);
            this.mnStavanie = new JMenu("Stavanie - ZAPNUTE");
            this.mnStavanie.setForeground(Color.blue);
            this.mb.add(mnStavanie);
        } else {
            this.mb.remove(mnStavanie);
            this.mnStavanie = new JMenu("Stavanie - VYPNUTE");
            this.mnStavanie.setForeground(Color.red);
            this.mb.add(mnStavanie);
        }
    }
    /**
     * uklada celu hru do suboru.
     * @param celaHra string celej hry ktory chceme ulozit
     * @throws IOException idk
     */
    public final void write(final String celaHra) throws IOException {
        Scanner reader = new Scanner(new File("Ulozene hry.dat"));
        int i = 0;
        boolean rovnake = false;
        String vypis = "";
        String aktualne = "";
        try {
            do {
                aktualne = reader.next();
                if (this.hra.getNazovSveta().equals(aktualne)) {
                    rovnake = true;
                    reader.nextLine();
                } else {
                    vypis += aktualne + " " + reader.nextLine() + " \n";
                }
                i++;
            } while (reader.hasNextLine());
        } catch (NoSuchElementException ex) {
            //nope
        }
        reader.close();
        try {
            if (!rovnake) {
                Writer writer = new FileWriter("Ulozene hry.dat", true);
                writer.write(celaHra);
                writer.close();
                JOptionPane.showMessageDialog(null, "Hra bola uspesne ulozena");
            } else {
                Writer writer = new FileWriter("Ulozene hry.dat", false);
                writer.write(vypis + celaHra + " \n");
                writer.close();
                JOptionPane.showMessageDialog(null, "Hra bola uspesne ulozena");
            }
        } catch (IOException e) {
            System.out.println("vyskytol sa problem pri zapisovani do suboru:"
                    + " skore.txt");
        }
    }
    /**
     * nacita vybranu hru zo suboru.
     * @throws IOException idk
     */
    public final void read() throws IOException {
        String vypis = "";
        int i = 1;
        Scanner reader = new Scanner(new File("Ulozene hry.dat"));
        try {
            do {
                vypis += i + ". " + reader.next() + "\n";
                reader.nextLine();
                i++;
            } while (reader.hasNextLine());
            reader.close();
            int vybe = Integer.parseInt(JOptionPane.showInputDialog(null, vypis,
                    "Zvolte moznost", 1));
            i = 1;
            reader = new Scanner(new File("Ulozene hry.dat"));
            do {
                if (i == vybe) {
                    this.hra.setNazovSveta(reader.next());
                    int pocetVeciVInventari = reader.nextInt();
                    this.pole.vymazInventar();
                    if (pocetVeciVInventari != 0) {
                        do {
                            int pocetVInventari = reader.nextInt();
                            String vec = reader.next();
                            if (vec.contains("krompac")) {
                                this.pole.vytvorNastroj(vec, pocetVInventari);
                                pocetVeciVInventari--;
                            } else {
                                do {
                                    this.pole.pridajBlokDoInventara(vec);
                                    pocetVInventari--;
                                } while (pocetVInventari != 0);
                                pocetVeciVInventari--;
                            }
                        } while (pocetVeciVInventari != 0);
                    }
                    this.pole.vycistiPole();
                    for (int j = 0; j < this.pole.getRozmer(); j++) {
                        for (int k = 0; k < this.pole.getRozmer(); k++) {
                            String blok = reader.next();
                            if (blok == null) {
                                this.pole.setBlok(j, k, null);
                            } else if (blok.equals("hrac")) {
                                this.pole.setHracXY(j, k);
                                this.pole.setBlok(j, k, this.pole.getHrac());
                            } else {
                                this.pole.setBlok(j, k,
                                        this.pole.getBlokZoStringu(blok));
                            }
                        }
                    }
                    this.pole.reloadPole();
                } else {
                    reader.nextLine();
                }
                i++;
            } while (reader.hasNextLine());
        } catch (NoSuchElementException ex) {
            //nope :D
        }
        reader.close();
    }
    /**
     * info o cieli hry.
     */
    public final void infoCiel() {
        JOptionPane.showMessageDialog(null, "Hra 2D MienCroft\n\n"
                + "hra nema jasne stanoveny ciel, ale ako challenge postaci"
                + " napriklad vykopat 3 diamanty\n\n");
    }
    /**
     * info o ovladani hry.
     */
    public final void infoHowtoplay() {
        JOptionPane.showMessageDialog(null, "Hra 2D MienCroft\n\n"
                + "ovladanie:\n<- pohyb vlavo\n-> pohyb vpravo\n+ vyroba "
                + "nastroja\ni vypis inventara\n5 prepinanie medzi stavanim a "
                + "kopanim\n1,2,3,4,6,7,8,9 kopanie/stavanie konkretnych "
                + "blokov okolo hraca(5)");
    }
}
