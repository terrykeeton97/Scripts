package Gui;

import RunecraftingAltar.Altar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Settings {
    private static Settings instance;
    private static List<Altar> altarList = new ArrayList<>();
    private boolean enableFeature;
    private String selectedOption;

    private Settings() {
        this.enableFeature = false;
        this.selectedOption = "Default";
    }
    
    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    public static List<String> getBasicAltarNames() {
        return Optional.ofNullable(altarList)
                .orElse(Collections.emptyList()) // Use an empty list if null
                .stream()
                .map(Altar::name)
                .toList();
    }
    
    public boolean isEnableFeature() {
        return enableFeature;
    }

    public void setEnableFeature(boolean enableFeature) {
        this.enableFeature = enableFeature;
    }

    public String getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(String selectedOption) {
        this.selectedOption = selectedOption;
    }
}
