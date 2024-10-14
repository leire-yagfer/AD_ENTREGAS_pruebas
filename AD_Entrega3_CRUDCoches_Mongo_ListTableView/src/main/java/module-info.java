module org.example.ad_entrega3_crudcoches_mongo_listtableview {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens org.example.ad_entrega3_crudcoches_mongo_listtableview to javafx.fxml;
    exports org.example.ad_entrega3_crudcoches_mongo_listtableview;
}