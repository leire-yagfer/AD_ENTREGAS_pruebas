module org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas to javafx.fxml;
    exports org.example.ad_entrega7_crudcocheshibernate1n_javafx_cambiovistas;
}