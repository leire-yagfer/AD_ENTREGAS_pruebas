module org.example.ad_entrega6_crudcoches_hibernate {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.ad_entrega6_crudcoches_hibernate to javafx.fxml;
    exports org.example.ad_entrega6_crudcoches_hibernate;
}