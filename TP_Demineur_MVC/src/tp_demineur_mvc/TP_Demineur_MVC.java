/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc;

import java.util.Observable;
import java.util.Observer;
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
import tp_demineur_mvc.Vue.CaseVue;

/**
 *
 * @author Epulapp
 */
public class TP_Demineur_MVC extends Application{

    int hauteur = 10;
    int largeur = 10;
    final Plateau2D board = new Plateau2D(hauteur, largeur);
    final CaseVue[][] listCases = new CaseVue[hauteur][largeur];

    @Override
    public void start(Stage stage) {

        board.generateLevel(10);
        
        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);

        HBox hbox = new HBox();
        border.setTop(hbox);
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

        Scene scene = new Scene(border, 500, 500);
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
