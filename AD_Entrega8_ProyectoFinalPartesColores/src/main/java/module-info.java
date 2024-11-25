module org.example.ad_entrega8_proyectofinalpartescolores {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.ad_entrega8_proyectofinalpartescolores to javafx.fxml;
    exports org.example.ad_entrega8_proyectofinalpartescolores;
}