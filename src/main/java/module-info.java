module tech.harbinger.tempest {
    requires java.prefs;

    requires javafx.fxml;
    requires javafx.controls;

    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;

    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.core;

    requires org.slf4j;
    requires fontawesomefx;

    opens tech.harbinger.springfx to
            com.fasterxml.jackson.databind,
            javafx.fxml,
            javafx.controls,
            javafx.graphics,
            spring.beans,
            spring.context,
            spring.core;

    opens tech.harbinger.springfx.controllers to
            com.fasterxml.jackson.databind,
            javafx.controls,
            javafx.fxml,
            javafx.graphics,
            spring.beans,
            spring.context,
            spring.core;

    opens tech.harbinger.springfx.fx to
            com.fasterxml.jackson.databind,
            javafx.controls,
            javafx.fxml,
            javafx.graphics,
            spring.beans,
            spring.context,
            spring.core;
}