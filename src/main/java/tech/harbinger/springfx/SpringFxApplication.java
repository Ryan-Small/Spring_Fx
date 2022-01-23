package tech.harbinger.springfx;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import tech.harbinger.springfx.fx.EnhancedApplication;

import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class SpringFxApplication extends EnhancedApplication {

    private static final Properties APP_PROPERTIES = loadApplicationProperties();
    private static final String APP_NAME = APP_PROPERTIES.getProperty("application.name", "unknown");
    private static final String APP_VERSION = APP_PROPERTIES.getProperty("application.version", "prototype");

    private static final int APP_MIN_WIDTH = Integer.parseInt(APP_PROPERTIES.getProperty("application.minWidth", "460"));
    private static final int APP_MIN_HEIGHT = Integer.parseInt(APP_PROPERTIES.getProperty("application.minHeight", "320"));

    private static Properties loadApplicationProperties() {
        final Properties properties = new Properties();
        try {
            properties.load(SpringFxApplication.class.getResourceAsStream("/application.properties"));
        } catch (final IOException ex) {
            throw new RuntimeException("unable to find application properties");
        }
        return properties;
    }

    private ConfigurableApplicationContext applicationContext;

    @Override
    public void init() throws Exception {
        this.applicationContext = new SpringApplicationBuilder(SpringFxApplication.class).run();
    }

    @Override
    public void start(final Stage primaryStage) throws IOException {
        this.setPersistentSettings(primaryStage);
        final Parent parent = this.loadFxml("/tech/harbinger/springfx/fxml/login.fxml");
        primaryStage.setTitle(APP_NAME + " - v" + APP_VERSION);
        primaryStage.setMinHeight(APP_MIN_HEIGHT);
        primaryStage.setMinWidth(APP_MIN_WIDTH);
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        this.applicationContext.close();
        Platform.exit();
    }
}
