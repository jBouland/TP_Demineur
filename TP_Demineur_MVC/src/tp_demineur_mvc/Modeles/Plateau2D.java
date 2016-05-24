/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc.Modeles;

import java.util.ArrayList;
import java.util.Observer;

/**
 *
 * @author Epulapp
 */
public class Plateau2D extends Plateau {

    public Case[][] grille;
    private int longueur;
    private int hauteur;

    public Plateau2D(int hauteur, int longueur) {
        this.hauteur = hauteur;
        this.longueur = longueur;

        grille = new Case[hauteur][longueur];
        for (int i = 0; i < hauteur; i++) {
            grille[i] = new Case[longueur];
            for (int j = 0; j < longueur; j++) {
                grille[i][j] = new Case(this);
            }
        }
    }

    public Case getCase(int i, int j) {
        if (i >= 0 && i < hauteur && j >= 0 && j < longueur) {
            return grille[i][j];
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Case> getVoisins(Case c) {
        ArrayList<Case> cases = new ArrayList<>();
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < longueur; j++) {
                if (grille[i][j] == c) {
                    for (int a = i - 1; a <= i + 1; a++) {
                        for (int b = j - 1; b <= j + 1; b++) {
                            if (a >= 0 && a < hauteur && b >= 0 && b < longueur) {
                                if (!(a == i && b == j)) {
                                    cases.add(grille[a][b]);
                                }
                            }
                        }
                    }
                    return cases;
                }
            }
        }
        return null;
    }

    @Override
    public void generateLevel(int nombreMines) {
        if (nombreMines < longueur * hauteur) {
            while (nombreMines > 0) {
                double randlong = Math.random() * (longueur - 1);
                double randhaut = Math.random() * (hauteur - 1);
                if (!grille[(int) randhaut][(int) randlong].isMine()) {
                    grille[(int) randhaut][(int) randlong].addMine();
                    nombreMines--;
                }
            }
        }
    }

    @Override
    public void addObserver(Observer o, int i, int j) {
        grille[i][j].addObserver(o);
    }
}
