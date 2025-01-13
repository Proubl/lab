module com.example.shapechasegame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.example.shapechasegame to javafx.fxml;
    opens com.example.shapechasegame.controller to javafx.fxml;
    opens com.example.shapechasegame.model to javafx.fxml;

    exports com.example.shapechasegame;
    exports com.example.shapechasegame.controller;
    exports com.example.shapechasegame.model;
}
