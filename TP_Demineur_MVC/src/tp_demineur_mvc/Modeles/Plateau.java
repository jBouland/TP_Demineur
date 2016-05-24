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
public abstract class Plateau {
    
    public abstract ArrayList<Case> getVoisins(Case c);
    public abstract void generateLevel(int nombreMine);
    public abstract void addObserver(Observer o);
    
    
    
    
    
}
