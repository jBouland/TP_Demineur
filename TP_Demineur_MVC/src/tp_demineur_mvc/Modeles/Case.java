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
    private boolean visite;
    private int nombreMinesAutour;
    private boolean mine;
    private Plateau plateau;

    public Case(Plateau p) {
        visite = false;
        plateau = p;
    }

    public boolean getVisite() {
        return visite;
    }

    public int getNombreMinesAutour() {
        return nombreMinesAutour;
    }

    public boolean isMine() {
        return mine;
    }
    
    
    public void majClick(){
        if(!visite && nombreMinesAutour ==0){
            ArrayList<Case> casesVoisines = plateau.getVoisins();
            for(Case c : casesVoisines){
                c.visite = true;
                c.majClick();
            }
        }
    }
    
    
    
    
}
