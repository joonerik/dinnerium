module gr2038 {
 
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires javafx.controls;

    //requires fx.map.control;


    exports dinnerium.ui;

    opens dinnerium to javafx.fxml;
}
