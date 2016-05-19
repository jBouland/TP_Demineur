/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc.Modeles;

import java.util.ArrayList;

/**
 *
 * @author Epulapp
 */
public class Case {

    public enum etat {

        vide,
        drapeau,
        interrogation,
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
    
    

    public void majClick() {
        if (mine) {
            //perdre
        }
        if (!visite && this.getNombreMinesAutour() == 0) {
            visite = true;
            ArrayList<Case> casesVoisines = plateau.getVoisins(this);
            for (Case c : casesVoisines) {

                c.majClick();
            }

        }
    }

    public void majClickdroit() {
        if (etatcase == etat.vide) {
            etatcase = etat.drapeau;
        }
        if (etatcase == etat.drapeau) {
            etatcase = etat.interrogation;
        }
        if (etatcase == etat.interrogation) {
            etatcase = etat.vide;
        }
    }

}
