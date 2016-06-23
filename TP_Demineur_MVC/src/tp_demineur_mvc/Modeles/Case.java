/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc.Modeles;

import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Epulapp
 */
public class Case extends Observable {

    public enum etat {

        vide,
        drapeau,
    }
    private boolean visite;
    private boolean mine;
    private Plateau plateau;
    private etat etatcase;

    public Case(Plateau p) {
        visite = false;
        plateau = p;
        etatcase = etat.vide;
    }

    public void reset() {
        visite = false;
        etatcase = etat.vide;
        mine = false;
        this.setChanged();
        this.notifyObservers();
    }

    public boolean getVisite() {
        return visite;
    }

    public int getNombreMinesAutour() {
        ArrayList<Case> casesVoisines = plateau.getVoisins(this);
        int nbmine = 0;
        for (Case c : casesVoisines) {
            if (c.mine == true) {
                nbmine++;
            }
        }
        return nbmine;
    }

    public boolean isMine() {
        return mine;
    }

    public void addMine() {
        this.mine = true;
    }

    public boolean isFlag() {
        return etatcase == etat.drapeau;
    }

    public void setVisite() {
        visite = true;
        setChanged();
        this.notifyObservers();
    }

    public void majClick() {
        if (mine) {
            plateau.die();
            setChanged();
            this.notifyObservers();
        }
        if (!visite) {
            visite = true;
            if (this.getNombreMinesAutour() == 0) {
                ArrayList<Case> casesVoisines = plateau.getVoisins(this);
                for (Case c : casesVoisines) {
                    c.majClick();
                }
            }
        }
        setChanged();
        this.notifyObservers();
    }

    public void majClickdroit() {
        if (etatcase == etat.vide && !visite) {
            etatcase = etat.drapeau;
        } else {
            etatcase = etat.vide;
        }
        setChanged();
        this.notifyObservers();
    }
}
