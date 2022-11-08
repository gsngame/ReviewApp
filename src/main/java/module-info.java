module com.review.reviewapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.review to javafx.fxml;
    exports com.review;
    exports com.review.controllers;
    opens com.review.controllers to javafx.fxml;
}