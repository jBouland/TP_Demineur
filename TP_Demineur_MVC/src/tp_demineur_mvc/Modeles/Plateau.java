/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc.Modeles;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;

/**
 *
 * @author Epulapp
 */
public abstract class Plateau extends Observable {

    protected int score = 100;
    protected boolean isDead = false;
    Timer timer;

    public abstract ArrayList<Case> getVoisins(Case c);

    public abstract void generateLevel(int nombreMine);

    public abstract void revealMap();
    
    public Plateau() {
        timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        if (!isDead) {
                            score--;
                            setChanged();
                            notifyObservers();
                        }else{
                            score = 0;
                        }

                    }
                });
            }
        }, 1000, 1000);
    }

    public boolean isDead() {
        return isDead;
    }

    public void die() {
        isDead = true;
        this.revealMap();
        this.setChanged();
        this.notifyObservers();
    }

    public int getScore() {
        return score;
    }

}
