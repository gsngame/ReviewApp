package com.review.controllers;

import com.review.MyListener;
import com.review.models.Product;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ItemListController implements Initializable {
    @FXML
    private AnchorPane amazon_button;
    @FXML
    private AnchorPane ebay_button;
    @FXML
    private AnchorPane lazada_button;
    @FXML
    private AnchorPane sendo_button;
    @FXML
    private AnchorPane tiki_button;
    @FXML
    private ScrollPane product_list_scroll;
    @FXML
    private GridPane product_list_grid;
    @FXML
    private HBox pagination_list;
    public List<Product> productList = new ArrayList<>();
    private MyListener myListener;
    private Pane pane;
    private int stepPagination = 0;
    private int pageNumDefault = 5;
    private PrimaryController primaryController;
    @FXML
    void amazon_button_presss(MouseEvent event) {
        this.ebay_button.getStyleClass().remove("action");
        this.lazada_button.getStyleClass().remove("action");
        this.sendo_button.getStyleClass().remove("action");
        this.tiki_button.getStyleClass().remove("action");
        this.amazon_button.getStyleClass().add("action");
    }

    @FXML
    void ebay_button_press(MouseEvent event) {
        this.amazon_button.getStyleClass().remove("action");
        this.lazada_button.getStyleClass().remove("action");
        this.sendo_button.getStyleClass().remove("action");
        this.tiki_button.getStyleClass().remove("action");
        this.ebay_button.getStyleClass().add("action");
    }

    @FXML
    void lazada_button_press(MouseEvent event) {
        this.amazon_button.getStyleClass().remove("action");
        this.ebay_button.getStyleClass().remove("action");
        this.sendo_button.getStyleClass().remove("action");
        this.tiki_button.getStyleClass().remove("action");
        this.lazada_button.getStyleClass().add("action");
    }

    @FXML
    void sendo_button_press(MouseEvent event) {
        this.amazon_button.getStyleClass().remove("action");
        this.ebay_button.getStyleClass().remove("action");
        this.lazada_button.getStyleClass().remove("action");
        this.tiki_button.getStyleClass().remove("action");
        this.sendo_button.getStyleClass().add("action");
    }

    @FXML
    void tiki_button_press(MouseEvent event) {
        this.amazon_button.getStyleClass().remove("action");
        this.ebay_button.getStyleClass().remove("action");
        this.lazada_button.getStyleClass().remove("action");
        this.sendo_button.getStyleClass().remove("action");
        this.tiki_button.getStyleClass().add("action");
    }
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
    public List<Product> getData() {
        List<Product> products = new ArrayList<>();
        Product product;

        product = new Product();
        product.setProductName("Iphone 1");
        product.setPrice(30000);
        product.setPrice_sale(20000);
        product.setImageUrl("https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-tim-thumb-600x600.jpg");
        products.add(product);

        product = new Product();
        product.setProductName("[11.11_SALE L???N NH???T N??M ] iPhone 14 - H??ng Ch??nh H??ng VN/A - H??ng C?? S???n Giao Nhanh Trong Ng??y");
        product.setPrice(30000);
        product.setPrice_sale(20000);
        product.setImageUrl("https://lzd-img-global.slatic.net/g/p/8d96d9407c232c862422a4ed255c05ed.png");
        products.add(product);

        product = new Product();
        product.setProductName("S???n ph???m 3");
        product.setPrice(30000);
        product.setPrice_sale(20000);
        product.setImageUrl("https://vn-live-01.slatic.net/p/b72b0a0bdddf15b29419975efd47d0f6.png");
        products.add(product);

        product = new Product();
        product.setProductName("S???n ph???m 4");
        product.setPrice(30000);
        product.setPrice_sale(20000);
        product.setImageUrl("https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-tim-thumb-600x600.jpg");
        products.add(product);

        product = new Product();
        product.setProductName("S???n ph???m 5");
        product.setPrice(30000);
        product.setPrice_sale(20000);
        product.setImageUrl("https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-tim-thumb-600x600.jpg");
        products.add(product);

        product = new Product();
        product.setProductName("S???n ph???m 6");
        product.setPrice(30000);
        product.setPrice_sale(20000);
        product.setImageUrl("https://cdn.tgdd.vn/Products/Images/42/251192/iphone-14-pro-max-tim-thumb-600x600.jpg");
        products.add(product);

        return products;
    }
    public void setStepPagination(int stepPagination) {
        this.stepPagination = stepPagination;
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
    public void openItemList(PrimaryController primaryController1){
        if(pane != null){
            primaryController.setContainer(pane);
        }
        else{
            this.primaryController = primaryController1;
            //productList.addAll(getData());

            if(productList.size()>0){
                myListener = new MyListener() {
                    @Override
                    public void onClickListener(Product product) {
                        primaryController.swapItemDetail();
                    }
                };
            }

            int col = 0;
            int row = 1;
            try {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/com/review/item_list.fxml"));
                pane = fxmlLoader.load();
                ItemListController itemListController = fxmlLoader.getController();
                primaryController.setContainer(pane);

                /*--------------------View Product List-------------*/
                for (int i = 0; i < productList.size(); i++){
                    fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource("/com/review/item.fxml"));
                    AnchorPane anchorPane = fxmlLoader.load();

                    ItemController itemController = fxmlLoader.getController();
                    itemController.setData(productList.get(i), myListener);

                    if(col == 5){
                        col = 0;
                        row++;
                    }

                    itemListController.product_list_grid.add(anchorPane, col++, row);
                    GridPane.setMargin(anchorPane, new Insets(10));
                    itemListController.product_list_scroll.setPadding(new Insets(0, 0, 0, 30));

                }

                /*---------------Set Pagination-------------------*/
                itemListController.pagination_list.getChildren().clear();
                Button button;
                int startPage = (pageNumDefault * stepPagination) + 1;
                for(int i = 0; i < pageNumDefault; i++){
                    button = new Button();
                    button.getStyleClass().add("button-pagination");
                    button.addEventHandler(MouseEvent.MOUSE_PRESSED, event ->{
                        Node node;
                        for(int j = 0; j < pageNumDefault; j++){
                            node = itemListController.pagination_list.getChildren().get(j);
                            node.getStyleClass().remove("button-pagination-action");
                        }

                        ((Button)event.getSource()).getStyleClass().add("button-pagination-action");
                    });
                    button.setText(String.valueOf(startPage++));
                    itemListController.pagination_list.getChildren().add(button);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
