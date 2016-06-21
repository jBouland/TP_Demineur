/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc.Modeles;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Epulapp
 */
public abstract class Plateau extends Observable{
    
    private boolean isDead=false;
    public abstract ArrayList<Case> getVoisins(Case c);
    public abstract void generateLevel(int nombreMine);
    public abstract void revealMap();
    
    public boolean isDead(){
        return isDead;
    }
    
    public void die(){
        isDead = true;
        this.revealMap();
        this.setChanged();
        this.notifyObservers();
    }
    
    
}
