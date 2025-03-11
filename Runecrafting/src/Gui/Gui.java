package Gui;

import RunecraftingAltar.Earth.Altar;
import com.osmb.api.ScriptCore;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;

public class Gui {

    public void buildScene(ScriptCore core) {
        var vBox = new VBox();
        var selectAltar = new HBox();
        var selectAltarLabel = new Label("Select Altar");
        var selectAltarComboBox = new ComboBox<RunecraftingAltar.Altar>();
        
        vBox.setStyle("-fx-background-color: #636E72; -fx-padding: 10");
        selectAltar.setSpacing(10);
        selectAltar.setAlignment(Pos.CENTER_LEFT);
        selectAltarComboBox.getItems().addAll(new RunecraftingAltar.Air.Altar(), new RunecraftingAltar.Water.Altar(), new Altar(), new RunecraftingAltar.Fire.Altar());
        selectAltarComboBox.setConverter(new StringConverter<>() {
            @Override
            public String toString(RunecraftingAltar.Altar altar) {
                return altar != null ? altar.name() : "";
            }

            @Override
            public RunecraftingAltar.Altar fromString(String string) {
                return selectAltarComboBox.getItems().stream().filter(course -> course.name().equals(string)).findFirst().orElse(null);
            }
        });

        selectAltar.getChildren().addAll(selectAltarLabel, selectAltarComboBox);
        vBox.getChildren().add(selectAltarComboBox);

        new Scene(vBox);
    }
    
}