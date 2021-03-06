/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tp_demineur_mvc.Modeles.Plateau2D;
import tp_demineur_mvc.Vue.CaseVue;
import java.util.Timer;
import javafx.stage.WindowEvent;
import tp_demineur_mvc.Vue.Plateau2DVue;

/**
 *
 * @author Epulapp
 */
public class TP_Demineur_MVC extends Application {

    int hauteur = 10;
    int largeur = 10;
    int nombreMine = 10;
    final Plateau2D board = new Plateau2D(hauteur, largeur);
    final CaseVue[][] listCases = new CaseVue[hauteur][largeur];
    final static Image imgEntete = new Image("Assets/Code.PNG");
    final static Image imgIcon = new Image("Assets/iconeTee.png");
    Button buttonRestart;
    Label labelScore;
    Timer timer;

    @Override
    public void start(Stage stage) {

        board.generateLevel(nombreMine);
        stage.getIcons().add(imgIcon);
        Plateau2DVue plateau = new Plateau2DVue(board, listCases);

        Scene scene = new Scene(plateau, 500, 600);
        // root.getChildren().addAll(selectedImage);
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


}
