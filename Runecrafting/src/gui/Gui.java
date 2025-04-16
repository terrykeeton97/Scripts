package gui;

import com.osmb.api.ScriptCore;
import com.osmb.api.javafx.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class Gui extends BorderPane {

    private final ComboBox<runecraftingaltar.Altar> altarComboBox;
    private final CheckBox useShortcutsCheckBox = new CheckBox("Use shortcuts");

    public Gui(ScriptCore core) {
        setPadding(new Insets(10));

        Label label = new Label("Select Altar:");

        altarComboBox = new ComboBox<>(FXCollections.observableArrayList(getAltars()));
        altarComboBox.setPrefWidth(250);

        useShortcutsCheckBox.setVisible(false);
        altarComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            useShortcutsCheckBox.setVisible(newVal instanceof runecraftingaltar.blood.Altar);
        });

        VBox mainVBox = new VBox(label, altarComboBox, useShortcutsCheckBox);
        mainVBox.setStyle("-fx-spacing: 5; -fx-padding: 10; -fx-background-color: #636E72");
        mainVBox.setPadding(new Insets(10));
        mainVBox.getStyleClass().add("script-manager-titled-pane");
        mainVBox.setMaxHeight(Double.MAX_VALUE);
        setCenter(mainVBox);
        VBox.setVgrow(mainVBox, Priority.ALWAYS);

        altarComboBox.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(runecraftingaltar.Altar altar, boolean empty) {
                super.updateItem(altar, empty);
                if (empty || altar == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(altar.getAltarName());
                    ImageView imageView = JavaFXUtils.getItemImageView(core, altar.getRuneItemId());
                    imageView.setFitHeight(35);
                    imageView.setFitWidth(35);
                    setGraphic(imageView);
                }
            }
        });

        altarComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(runecraftingaltar.Altar altar, boolean empty) {
                super.updateItem(altar, empty);
                if (empty || altar == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(altar.getAltarName());
                    ImageView imageView = JavaFXUtils.getItemImageView(core, altar.getRuneItemId());
                    imageView.setFitHeight(16);
                    imageView.setFitWidth(16);
                    setGraphic(imageView);
                }
            }
        });

        altarComboBox.setPrefWidth(250);

        var startButton = new Button("Start");
        startButton.setOnAction(e -> {
            runecraftingaltar.Altar selected = altarComboBox.getValue();
            if (selected != null) {
                core.log("Starting " + selected.getAltarName());
            }
            ((Stage) startButton.getScene().getWindow()).close();
        });

        HBox bottomHBox = new HBox(startButton);
        bottomHBox.setPadding(new Insets(10));
        bottomHBox.setStyle("-fx-alignment: center-right; -fx-background-color: #3C3C3C;");
        setBottom(bottomHBox);
    }

    private List<runecraftingaltar.Altar> getAltars () {
        var airAltar = new runecraftingaltar.air.Altar();
        var waterAltar = new runecraftingaltar.water.Altar();
        var earthAltar = new runecraftingaltar.earth.Altar();
        var fireAltar = new runecraftingaltar.fire.Altar();
        var bloodAltar = new runecraftingaltar.blood.Altar();
        var soulAltar = new runecraftingaltar.soul.Altar();
        var lavaAltar = new runecraftingaltar.lava.Altar();
        var zmiAltar = new runecraftingaltar.zmi.Altar();

        return List.of(
                airAltar,
                waterAltar,
                earthAltar,
                fireAltar,
                bloodAltar,
                soulAltar,
                lavaAltar,
                zmiAltar
        );
    }

    public boolean useShortcuts() {
        return useShortcutsCheckBox.isSelected();
    }

    public runecraftingaltar.Altar getSelectedAltar() {
        return altarComboBox.getValue();
    }
}
