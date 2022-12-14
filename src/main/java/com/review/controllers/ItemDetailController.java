package com.review.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemDetailController implements Initializable{
    @FXML
    private AnchorPane info_button;

    @FXML
    private AnchorPane rating_button;

    @FXML
    private AnchorPane rating_list;
    private static PrimaryController primaryController;
    private Pane rating_pane;
    @FXML
    void info_button_press(MouseEvent event) throws IOException{
        Pane newPane = FXMLLoader.load(getClass().getResource("/com/review/info_detail.fxml"));
        this.rating_list.getChildren().clear();
        this.rating_list.getChildren().addAll(newPane);
        this.rating_button.getStyleClass().remove("action");
        this.info_button.getStyleClass().add("action");

    }

    @FXML
    void rating_button_press(MouseEvent event) throws IOException{
        if(rating_pane != null){
            this.rating_list.getChildren().clear();
            this.rating_list.getChildren().addAll(rating_pane);
            this.info_button.getStyleClass().remove("action");
            this.rating_button.getStyleClass().add("action");
        }
        else{
            this.rating_pane = FXMLLoader.load(getClass().getResource("/com/review/rating_list.fxml"));
            this.rating_list.getChildren().clear();
            this.rating_list.getChildren().addAll(rating_pane);
            this.info_button.getStyleClass().remove("action");
            this.rating_button.getStyleClass().add("action");
        }

    }
    @FXML
    void returnItemList(MouseEvent event) {
        primaryController.swapItemList();
    }
    public void openItemDetail(PrimaryController primaryController1){
        primaryController = primaryController1;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/com/review/item_detail.fxml"));
            Pane newPane = fxmlLoader.load();
            ItemDetailController itemDetailController = fxmlLoader.getController();
            primaryController.setContainer(newPane);

            newPane = FXMLLoader.load(getClass().getResource("/com/review/info_detail.fxml"));
            itemDetailController.rating_list.getChildren().addAll(newPane);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
