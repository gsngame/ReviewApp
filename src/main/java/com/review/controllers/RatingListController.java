package com.review.controllers;

import com.review.models.Rate;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RatingListController implements Initializable {
    @FXML
    private GridPane rating_grid;

    @FXML
    private ScrollPane rating_scroll;
    @FXML
    private HBox pagination_list;
    private int stepPagination = 0;
    private int pageNumDefault = 5;

    private List<Rate> rateList = new ArrayList<>();
    @FXML
    void pagination_left_press(MouseEvent event) {
        if(this.stepPagination != 0){
            setStepPagination(this.stepPagination-1);
            setPagination();
        }
    }

    @FXML
    void pagination_right_press(MouseEvent event) {
        setStepPagination(this.stepPagination+1);
        setPagination();
    }
    public void setStepPagination(int stepPagination) {
        this.stepPagination = stepPagination;
    }
    public void setPagination(){
        pagination_list.getChildren().clear();
        Button button;
        int startPage = (pageNumDefault * stepPagination) + 1;
        for(int i = 0; i < pageNumDefault; i++){
            button = new Button();
            button.getStyleClass().add("button-pagination");
            button.addEventHandler(MouseEvent.MOUSE_PRESSED, clickPagination);
            button.setText(String.valueOf(startPage++));
            pagination_list.getChildren().add(button);
        }
    }
    private EventHandler clickPagination = new EventHandler() {

        @Override
        public void handle(Event event) {
            Node node;
            for(int i = 0; i < pageNumDefault; i++){
                node = pagination_list.getChildren().get(i);
                node.getStyleClass().remove("button-pagination-action");
            }


            ((Button)event.getSource()).getStyleClass().add("button-pagination-action");
        }
    };
    private List<Rate> getData() {
        List<Rate> rates = new ArrayList<>();
        Rate rate;

        for (int i = 0; i < 10; i++){
            rate = new Rate();
            rate.setComment("Xin chao nhe");
            rate.setImageUrl("/images/product.png");
            rate.setDate("7/11/2022");
            rate.setUsername("Phan Minh Phat");
            rate.setUserImageUrl("/images/user.png");
            rates.add(rate);
        }
        return rates;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rateList.addAll(getData());

        int row = 1;
        try {
            for (int i = 0; i < rateList.size(); i++){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/review/rating.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                RatingController ratingController = fxmlLoader.getController();
                ratingController.setData(rateList.get(i));

                rating_grid.add(anchorPane, 0, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
                rating_scroll.setPadding(new Insets(0, 0, 0, 0));

                //set grid height
                rating_grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                rating_grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                rating_grid.setMaxHeight(Region.USE_PREF_SIZE);
                rating_grid.setAlignment(Pos.TOP_CENTER);

                pagination_list.getChildren().clear();
                setPagination();


            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
