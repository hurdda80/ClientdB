module dhurd.c195.clientdb {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens dhurd.c195.clientdb to javafx.fxml;
    exports dhurd.c195.clientdb;
    exports dhurd.c195.clientdb.controllers;
    opens dhurd.c195.clientdb.controllers to javafx.fxml;
    opens dhurd.c195.clientdb.models to javafx.base;
}