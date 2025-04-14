package Gui;

import com.osmb.api.ScriptCore;
import com.osmb.api.item.ItemID;
import com.osmb.api.javafx.JavaFXUtils;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.List;

public class Gui extends BorderPane {

    private final ComboBox<RunecraftingAltar.Altar> altarComboBox;
    private final CheckBox useShortcutsCheckBox = new CheckBox("Use shortcuts");

    public Gui(ScriptCore core) {
        setPadding(new Insets(10));

        Label label = new Label("Select Altar:");

        altarComboBox = new ComboBox<>(FXCollections.observableArrayList(getAltars()));
        altarComboBox.setPrefWidth(250);

        useShortcutsCheckBox.setVisible(false);
        altarComboBox.valueProperty().addListener((obs, oldVal, newVal) -> {
            useShortcutsCheckBox.setVisible(newVal instanceof RunecraftingAltar.Blood.Altar);
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
            protected void updateItem(RunecraftingAltar.Altar altar, boolean empty) {
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

        // Set custom rendering for the selected item
        altarComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(RunecraftingAltar.Altar altar, boolean empty) {
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
            RunecraftingAltar.Altar selected = altarComboBox.getValue();
            if (selected != null) {
                System.out.println("Starting script with altar: " + selected.getAltarName());
                // Your script logic here
            }
            ((Stage) startButton.getScene().getWindow()).close();
        });

        HBox bottomHBox = new HBox(startButton);
        bottomHBox.setPadding(new Insets(10));
        bottomHBox.setStyle("-fx-alignment: center-right; -fx-background-color: #3C3C3C;");
        setBottom(bottomHBox);
    }

    private List<RunecraftingAltar.Altar> getAltars () {
        var airAltar = new RunecraftingAltar.Air.Altar();
        var waterAltar = new RunecraftingAltar.Water.Altar();
        var earthAltar = new RunecraftingAltar.Earth.Altar();
        var fireAltar = new RunecraftingAltar.Fire.Altar();
        var bloodAltar = new RunecraftingAltar.Blood.Altar();
        var soulAltar = new RunecraftingAltar.Soul.Altar();
        var lavaAltar = new RunecraftingAltar.Lava.Altar();
        var zmiAltar = new RunecraftingAltar.Zmi.Altar();

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

    public RunecraftingAltar.Altar getSelectedAltar() {
        return altarComboBox.getValue();
    }
}
