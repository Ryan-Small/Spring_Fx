package tech.harbinger.springfx.fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.prefs.Preferences;


// TODO: Move this entire package into a separate project that can be reused.
public abstract class EnhancedApplication extends Application {

    private static final Logger logger = LoggerFactory.getLogger(EnhancedApplication.class);

    private static final String WINDOW_POSITION_X = "Window_Position_X";
    private static final String WINDOW_POSITION_Y = "Window_Position_Y";
    private static final String WINDOW_WIDTH = "Window_Width";
    private static final String WINDOW_HEIGHT = "Window_Height";
    private static final double DEFAULT_X = 10;
    private static final double DEFAULT_Y = 10;

    private Stage stage;

    private double defaultWidth = 800;
    private double defaultHeight = 600;

    protected void setDefaultDimensions(final double defaultWidth, final double defaultHeight) {
        this.defaultWidth = defaultWidth;
        this.defaultHeight = defaultHeight;
    }

    protected void setPersistentSettings(final Stage stage) {
        // TODO: Handle values that are no longer valid due to changing monitor/resolution.
        final String nodeName = this.getClass().getName();
        final Preferences pref = Preferences.userRoot().node(nodeName);
        stage.setX(pref.getDouble(WINDOW_POSITION_X, DEFAULT_X));
        stage.setY(pref.getDouble(WINDOW_POSITION_Y, DEFAULT_Y));
        stage.setWidth(pref.getDouble(WINDOW_WIDTH, defaultWidth));
        stage.setHeight(pref.getDouble(WINDOW_HEIGHT, defaultHeight));
        stage.setOnCloseRequest((final WindowEvent event) -> {
            final Preferences preferences = Preferences.userRoot().node(nodeName);
            preferences.putDouble(WINDOW_POSITION_X, stage.getX());
            preferences.putDouble(WINDOW_POSITION_Y, stage.getY());
            preferences.putDouble(WINDOW_WIDTH, stage.getWidth());
            preferences.putDouble(WINDOW_HEIGHT, stage.getHeight());
        });
    }

    protected Parent loadFxml(final String scenePath) {
        final URL sceneUrl = this.getClass().getResource(scenePath);
        if (sceneUrl != null) {
            try {
                return FXMLLoader.load(sceneUrl);
            } catch (IOException ex) {
                final String msg = "unable to load fxml: " + scenePath;
                logger.error(msg, ex);
                throw new IllegalArgumentException(msg, ex);
            }
        }
        return null;
    }
}
