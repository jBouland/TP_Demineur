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

    protected int score = 99;
    protected boolean isDead = false;
    Timer timer;
    protected int nombreMines;

    public abstract ArrayList<Case> getVoisins(Case c);

    public abstract void generateLevel(int nombreMine);

    public abstract void revealMap();

    public Plateau() {
        timer = new Timer();
        final TimerTask tmt = new TimerTask() {
            @Override
            public void run() {
                if (!isDead && !isFinished()) {
                    score--;

                } else if (!isFinished()) {
                    score = 0;
                }
                setChanged();
                notifyObservers();
            }
        };
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                timer.scheduleAtFixedRate(tmt, 1000, 1000);

            }

        });
        t.run();
    }

    public boolean isDead() {
        return isDead;
    }

    public abstract boolean isFinished();

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
