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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import tp_demineur_mvc.Modeles.Plateau2D;
import tp_demineur_mvc.Modeles.Case;
import tp_demineur_mvc.Vue.CaseVue;
import java.util.Timer;
import java.util.TimerTask;
import javafx.application.Platform;
import javafx.stage.WindowEvent;

/**
 *
 * @author Epulapp
 */
public class TP_Demineur_MVC extends Application {

    int hauteur = 10;
    int largeur = 10;
    int nombreMine = 10;
    int score = 100;
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
        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();
        ImageView imageHaut = new ImageView(imgEntete);
        labelScore = new Label("Score - " + Integer.toString(score));
        labelScore.setStyle("-fx-text-fill: white;\n"
                + "   -fx-font-size: 18;");
        buttonRestart = new Button("Restart");
        buttonRestart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                board.generateLevel(10);
                score = 100;
                labelScore.setText("Score - " + Integer.toString(score));
            }
        });
        BorderPane bbox = new BorderPane();
        border.setTop(bbox);
        bbox.setPrefHeight(100);
        bbox.setStyle("-fx-background-color: #000000;");
        bbox.setCenter(imageHaut);
        bbox.setRight(buttonRestart);
        bbox.setLeft(labelScore);
        border.setCenter(grid);

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                listCases[i][j] = new CaseVue();
                board.addObserver(listCases[i][j], i, j);
                grid.add(listCases[i][j], i, j);
                listCases[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (t.getButton() == MouseButton.SECONDARY) {
                            ImageView imageSource = (ImageView) t.getSource();
                            for (int i = 0; i < hauteur; i++) {
                                for (int j = 0; j < largeur; j++) {
                                    if (imageSource.equals(listCases[i][j])) {
                                        Case c = board.getCase(i, j);
                                        c.majClickdroit();
                                    }
                                }
                            }
                        }
                        if (t.getButton() == MouseButton.PRIMARY) {
                            ImageView imageSource = (ImageView) t.getSource();
                            for (int i = 0; i < hauteur; i++) {
                                for (int j = 0; j < largeur; j++) {
                                    if (imageSource.equals(listCases[i][j])) {
                                        Case c = board.getCase(i, j);
                                        c.majClick();
                                        break;
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
        timer = new Timer();

        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        score--;
                        labelScore.setText("Score - " + Integer.toString(score));
                    }
                });
            }
        }, 1000 , 1000);

        Scene scene = new Scene(border, 500, 600);
        // root.getChildren().addAll(selectedImage);
        scene.setRoot(border);
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
