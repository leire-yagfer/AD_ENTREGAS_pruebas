module org.example.pruebajx {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.pruebajx to javafx.fxml;
    exports org.example.pruebajx;
}