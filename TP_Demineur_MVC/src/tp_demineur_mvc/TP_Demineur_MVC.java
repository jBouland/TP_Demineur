/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc;

import java.util.Observable;
import java.util.Observer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import tp_demineur_mvc.Modeles.Plateau2D;
import tp_demineur_mvc.Modeles.Case;
import tp_demineur_mvc.Vue.CaseVue;
import java.util.Timer;
import javafx.stage.WindowEvent;
import tp_demineur_mvc.Modeles.Plateau;
import tp_demineur_mvc.Vue.Plateau2DVue;

/**
 *
 * @author Epulapp
 */
public class TP_Demineur_MVC extends Application implements Observer {

    int hauteur = 10;
    int largeur = 10;
    int nombreMine = 10;
    final Plateau2D board = new Plateau2D(hauteur, largeur);
    final CaseVue[][] listCases = new CaseVue[hauteur][largeur];
    final static Image imgIcon = new Image("Assets/iconeTee.png");
    Button buttonRestart;
    Label labelScore;

    @Override
    public void start(Stage stage) {

        stage.getIcons().add(imgIcon);
        Plateau2DVue plateau = new Plateau2DVue(board, listCases);
        Scene scene = new Scene(plateau, 500, 600);
        scene.setRoot(plateau);
        stage.setTitle("Démineur");
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                System.out.println("Stage is closing");
                System.exit(0);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void update(Observable o, Object o1) {

    }

}
