module com.seaclub.seaclub {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.seaclub.client to javafx.fxml;
    exports com.seaclub.client;
}