module gr2038 {
 
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;

    //requires fx.map.control;


    exports dinnerium.ui;
    exports dinnerium.core;
    exports dinnerium.json;

    opens dinnerium.ui to javafx.fxml;
}
