/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp_demineur_mvc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tp_demineur_mvc.Modeles.Plateau2D;

/**
 *
 * @author Epulapp
 */
public class TP_Demineur_MVC extends Application {

    @Override
    public void start(Stage stage) {

        Plateau2D board = new Plateau2D();

        BorderPane border = new BorderPane();
        GridPane grid = new GridPane();
//        grid.setHgap(10);
//        grid.setVgap(10);
        ImageView[][] listCases = new ImageView[10][10];

        HBox hbox = new HBox();
        border.setTop(hbox);
        border.setCenter(grid);

        ImageView selectedImage = new ImageView();
        final Image caseClose = new Image("Assets/CaseClose.png");
        final Image CaseFlag = new Image("Assets/CaseFlag.png");
        selectedImage.setImage(caseClose);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                listCases[i][j] = new ImageView();
                listCases[i][j].setImage(caseClose);
                grid.add(listCases[i][j], i, j);
                listCases[i][j].setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent t) {
                        if(t.getButton() == MouseButton.SECONDARY){
                            ImageView ImageSource = (ImageView)t.getSource();
                            ImageSource.setImage(CaseFlag);
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
