/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
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

/**
 *
 * @author Epulapp
 */
public class TP_Demineur_MVC extends Application {

    @Override
    public void start(Stage stage) {

        final int hauteur = 10;
        final int largeur = 10;
        final Plateau2D board = new Plateau2D(hauteur, largeur);

        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
        final ImageView[][] listCases = new ImageView[hauteur][largeur];

        HBox hbox = new HBox();
        border.setTop(hbox);
        border.setCenter(grid);

        ImageView selectedImage = new ImageView();

        //Création des images de la grille :
        final Image caseClose = new Image("Assets/CaseClose.png");
        final Image caseFlag = new Image("Assets/CaseFlag.png");
        final Image case0 = new Image("Assets/Case0.png");
        final Image case1 = new Image("Assets/Case1.png");
        final Image case2 = new Image("Assets/Case2.png");
        final Image case3 = new Image("Assets/Case3.png");
        final Image case4 = new Image("Assets/Case4.png");

        selectedImage.setImage(caseClose);

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                listCases[i][j] = new ImageView();
                listCases[i][j].setImage(caseClose);
                grid.add(listCases[i][j], i, j);
                listCases[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if (t.getButton() == MouseButton.SECONDARY) {
                            ImageView imageSource = (ImageView) t.getSource();
                            imageSource.setImage(caseFlag);
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
                            imageSource.setImage(caseFlag);
                            for (int i = 0; i < hauteur; i++) {
                                for (int j = 0; j < largeur; j++) {
                                    if (imageSource.equals(listCases[i][j])) {
                                        Case c = board.getCase(i, j);
                                        c.majClick();
                                        break;
                                    }
                                }
                            }

                            for (int i = 0; i < hauteur; i++) {
                                for (int j = 0; j < largeur; j++) {
                                    Case c = board.getCase(i, j);
                                    if (c.getVisite()) {
                                        switch (c.getNombreMinesAutour()) {
                                            case 0:
                                                listCases[i][j].setImage(case0);
                                                break;
                                            case 1:
                                                listCases[i][j].setImage(case1);
                                                break;
                                            case 2:
                                                listCases[i][j].setImage(case2);
                                                break;
                                            case 3:
                                                listCases[i][j].setImage(case3);
                                                break;
                                            case 4:
                                                listCases[i][j].setImage(case4);
                                                break;
                                        }
                                    }
                                }
                            }

                        }

                    }
                });
            }
        }

        Scene scene = new Scene(border, 600, 600);
        // root.getChildren().addAll(selectedImage);
        scene.setRoot(border);
        stage.setTitle("Démineur");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
