package tech.harbinger.springfx.controllers;

import de.jensd.fx.glyphs.GlyphsDude;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @FXML private TextField usernameField;

    @FXML private PasswordField passwordField;

    @FXML private Button btnLogin;

    @FXML
    public void initialize() {
        GlyphsDude.setIcon(btnLogin, FontAwesomeIcon.SIGN_IN);
    }

    @FXML
    private void onLoginClick() throws IOException {
        logger.info("Logging in " + usernameField.getText());
        final Stage currentStage = (Stage) this.usernameField.getScene().getWindow();
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("/tech/harbinger/springfx/fxml/MainView.fxml"));
        currentStage.setScene(new Scene(loader.load()));
    }

}
