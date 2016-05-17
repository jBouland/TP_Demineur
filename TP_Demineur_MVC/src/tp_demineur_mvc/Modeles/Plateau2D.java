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
public class Plateau2D extends Plateau {

    public Case[][] grille;
    private int longueur;
    private int hauteur;

    @Override
    public ArrayList<Case> getVoisins(Case c) {
        ArrayList<Case> cases = new ArrayList<>();
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < longueur; j++) {
                if (grille[i][j] == c) {
                    for (int a = i - 1; a < i + 1; a++) {
                        for (int b = j - 1; b < j + 1; b++) {
                            if(a > 0 && a < hauteur && b > 0 && b < longueur){
                                if(!(a == i && b == j)){
                                    cases.add(grille[i][j]);
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

}
