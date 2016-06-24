/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc.Vue;

import java.util.Observable;
import java.util.Observer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import tp_demineur_mvc.Modeles.Case;
import tp_demineur_mvc.Modeles.Plateau;
import tp_demineur_mvc.Modeles.Plateau2D;

/**
 *
 * @author Epulapp
 */
public class Plateau2DVue extends PlateauVue implements Observer {

    final static Image imgEntete = new Image("Assets/Code.PNG");
    private final Button buttonRestart;
    private Label labelScore;
    int hauteur = 10;
    int largeur = 10;
    int nombreMine = 10;
    final Plateau2D board;

    public Plateau2DVue(final Plateau2D board2D, final CaseVue[][] listCases) {

        super();
        this.board = board2D;
        board.addObserver(this);
        GridPane grid = new GridPane();
        ImageView imageHaut = new ImageView(imgEntete);
        labelScore = new Label("Score - " + Integer.toString(board.getScore()));
        labelScore.setStyle("-fx-text-fill: white;\n"
                + "   -fx-font-size: 18;");
        buttonRestart = new Button("Restart");
        buttonRestart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                board.generateLevel(10);
                labelScore.setText("Score - " + Integer.toString(board.getScore()));
            }
        });
        BorderPane bbox = new BorderPane();
        this.setTop(bbox);
        bbox.setPrefHeight(100);
        bbox.setStyle("-fx-background-color: #000000;");
        bbox.setCenter(imageHaut);
        bbox.setRight(buttonRestart);
        bbox.setLeft(labelScore);
        this.setCenter(grid);

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                final int fi = i;
                final int fj = j;
                listCases[i][j] = new CaseVue();
                board.addObserver(listCases[i][j], i, j);
                grid.add(listCases[i][j], i, j);
                listCases[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (t.getButton() == MouseButton.SECONDARY) {
                            ImageView imageSource = (ImageView) t.getSource();
                            Case c = board.getCase(fi, fj);
                            c.majClickdroit();
                        }
                        if (t.getButton() == MouseButton.PRIMARY) {
                            ImageView imageSource = (ImageView) t.getSource();
                            Case c = board.getCase(fi, fj);
                            c.majClick();
                        }
                    }
                });
            }
        }
    }

    @Override
    public void update(final Observable o, Object o1) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if (o instanceof Plateau) {
                    if (board.isDead()) {
                        labelScore.setText("DEFEAT");
                    } else if (board.isFinished()) {
                        labelScore.setText("Victory ! Score : " + Integer.toString(board.getScore()));
                    } else {
                        labelScore.setText("Score - " + Integer.toString(board.getScore()));
                    }

                }
            }

        });

    }

}
