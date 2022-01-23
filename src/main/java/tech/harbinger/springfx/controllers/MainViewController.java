package tech.harbinger.springfx.controllers;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.IOException;
import java.io.InputStream;

public class MainViewController {

    @FXML TreeView<MenuItem> sidebarMenu;
    @FXML Button btnOverview;
    @FXML Button btnAnalytics;
    @FXML Button btnLogs;

    @FXML
    public void initialize() {

        final InputStream menuJson = this.getClass().getResourceAsStream("/menu.json");
        final JsonFactory jsonFactory = new JsonFactory();
        final ObjectMapper mapper = new ObjectMapper();
        try (final JsonParser jsonParser = jsonFactory.createParser(menuJson)) {
            final JsonNode node = mapper.readTree(jsonParser);
            final MenuItem[] root = mapper.treeToValue(node.at("/links"), MenuItem[].class);
            System.out.println(root);

            final MenuItem rootItem = new MenuItem();
            rootItem.text = "Menu";
            final TreeItem<MenuItem> rootMenu = new TreeItem<>(rootItem);
            this.sidebarMenu.setRoot(rootMenu);
            for (final MenuItem mi : root) {
                final TreeItem<MenuItem> item = new TreeItem<>(mi);
                rootMenu.getChildren().add(item);
                if (mi.children != null) {
                    for (final MenuItem subMi : mi.children) {
                        item.getChildren().add(new TreeItem<>(subMi));
                    }
                }
            }

        } catch (final IOException ex) {
            ex.printStackTrace();
        }

        this.sidebarMenu.getFocusModel().focusedItemProperty().addListener(new ChangeListener<TreeItem<MenuItem>>() {
            @Override
            public void changed(final ObservableValue<? extends TreeItem<MenuItem>> observable,
                                final TreeItem<MenuItem> oldValue,
                                final TreeItem<MenuItem> newValue) {
                System.out.println("old: " + oldValue);
                System.out.println("new: " + newValue);
            }
        });
    }

    @FXML
    private void onTitleClick() {
        System.out.println("Title Click");
    }

    @FXML
    private void onOverviewClick() {
        System.out.println("Overview clicked");
        this.handleNewActiveMenu(this.btnOverview);
    }

    @FXML
    private void onAnalyticsClick() {
        System.out.println("Analytics Click");
        this.handleNewActiveMenu(this.btnAnalytics);
    }

    @FXML
    private void onLogsClick() {
        System.out.println("Logs Click");
        this.handleNewActiveMenu(this.btnLogs);
    }

    private void handleNewActiveMenu(final Button menuOption) {
        clearActivateMenuOptions();
        this.setActiveMenuOption(menuOption.getStyleClass());
    }

    private void setActiveMenuOption(final ObservableList<String> styleClass) {
        styleClass.add("active");
    }

    private void clearActivateMenuOptions() {
        this.btnOverview.getStyleClass().removeAll("active");
        this.btnAnalytics.getStyleClass().removeAll("active");
        this.btnLogs.getStyleClass().removeAll("active");
    }

    private static class MenuItem {

        public String text;
        public String icon;
        public String fxml;
        public MenuItem[] children;

        public String toString() {
            return this.text;
        }
    }

}
