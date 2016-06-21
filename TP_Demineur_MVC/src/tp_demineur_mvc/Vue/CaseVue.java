/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc.Vue;

import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import tp_demineur_mvc.Modeles.Case;

/**
 *
 * @author Epulapp
 */
public class CaseVue extends ImageView implements Observer {
    
    final static Image caseClose = new Image("Assets/CaseClose.png");
    final static Image caseFlag = new Image("Assets/CaseFlag.png");
    final static Image case0 = new Image("Assets/Case0.png");
    final static Image case1 = new Image("Assets/Case1.png");
    final static Image case2 = new Image("Assets/Case2.png");
    final static Image case3 = new Image("Assets/Case3.png");
    final static Image case4 = new Image("Assets/Case4.png");
    final static Image case5 = new Image("Assets/Case5.png");
    final static Image case6 = new Image("Assets/Case6.png");
    final static Image case7 = new Image("Assets/Case7.png");
    final static Image case8 = new Image("Assets/Case8.png");
    final static Image caseMine = new Image("Assets/CaseMine.png");
    
    public CaseVue() {
        super();
        this.setImage(caseClose);
    }
    
    public static Image getImageInit() {
        return caseClose;
    }
    
    @Override
    public void update(Observable o, Object o1) {
        Case c = (Case) o;
        if(!c.getVisite() && !c.isFlag()){
            this.setImage(caseClose);
            return;
        }
        if (c.isFlag()) {
            this.setImage(caseFlag);
        } else if (c.isMine()) {
            this.setImage(caseMine);
        } else {
            switch (c.getNombreMinesAutour()) {
                case 0:
                    this.setImage(case0);
                    break;
                case 1:
                    this.setImage(case1);
                    break;
                case 2:
                    this.setImage(case2);
                    break;
                case 3:
                    this.setImage(case3);
                    break;
                case 4:
                    this.setImage(case4);
                    break;
                case 5:
                    this.setImage(case5);
                    break;
                case 6:
                    this.setImage(case6);
                    break;
                case 7:
                    this.setImage(case7);
                    break;
                case 8:
                    this.setImage(case8);
                    break;
                
            }
        }
        
    }
    
}
