package runecrafting;

import gui.Gui;
import runecraftingaltar.Altar;
import com.osmb.api.scene.RSObject;
import com.osmb.api.script.Script;
import com.osmb.api.script.ScriptDefinition;
import com.osmb.api.script.SkillCategory;
import com.osmb.api.trackers.experiencetracker.Skill;
import com.osmb.api.trackers.experiencetracker.XPTracker;
import com.osmb.api.ui.GameState;
import com.osmb.api.visual.drawing.Canvas;
import com.osmb.api.visual.image.ImageImport;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@ScriptDefinition(name = "TLK AIO Runecrafter", author = "tezkidd", version = 1.0, description = "AIO Runecrafter", skillCategory = SkillCategory.RUNECRAFTING)
public class Runecraft extends Script {
   
    private Altar selectedAltar;
    private boolean guiInitialized = false;
    
    public Runecraft(Object scriptCore) {
        super(scriptCore);
    }

    @Override
    public int poll() {
        // Only start polling the altar after the GUI has been initialized
        if (guiInitialized && selectedAltar != null) {
            return selectedAltar.poll(this);
        }
        return 1000;
    }

    @Override
    public void onGameStateChanged(GameState newGameState) {
        super.onGameStateChanged(newGameState);
    }

    @Override
    public void onPaint(Canvas c) {
        super.onPaint(c);
    }

    @Override
    public boolean canBreak() {
        return super.canBreak();
    }

    @Override
    public boolean canHopWorlds() {
        return super.canHopWorlds();
    }

    @Override
    public boolean promptBankTabDialogue() {
        return super.promptBankTabDialogue();
    }

    @Override
    public int onRelog() {
        return super.onRelog();
    }

    @Override
    public void onStart() {
        // Create the GUI
        var gui = new Gui(this);
        var scene = new Scene(gui);
        scene.getStylesheets().add("style.css");
        
        // Show the GUI and wait for it to close
        getStageController().show(scene, "Settings", true);  // Use blocking mode to wait for GUI to close
        
        // Get the selected altar after the GUI is closed
        selectedAltar = gui.getSelectedAltar();
        if (selectedAltar != null) {
            log("Starting " + selectedAltar.getAltarName());
        }
        
        // Mark the GUI as initialized so polling can begin
        guiInitialized = true;
        
        super.onStart();
    }

    @Override
    public void importImages(Set<ImageImport> imageImportSet) {
        super.importImages(imageImportSet);
    }

    @Override
    public void skillsToTrack(Map<Skill, XPTracker> skillsToTrack) {
        super.skillsToTrack(skillsToTrack);
    }

    @Override
    public int[] regionsToPrioritise() {
        // If we have a selected altar, prioritize its regions
        if (selectedAltar != null) {
            return selectedAltar.regions();
        }
        return super.regionsToPrioritise();
    }

    /**
     * Handles mining dense runestone for blood runecrafting
     */
    public void handleRunestone() {
        if (getObjectManager() == null) {
            log("Error: ObjectManager is null");
            return;
        }
        
        RSObject denseEssenceBlock = getObjectManager().getClosestObject("Dense runestone");
        if (denseEssenceBlock != null && denseEssenceBlock.isInteractableOnScreen()) {
            getFinger().tap(denseEssenceBlock.getConvexHull(), "Mine");
            log("Mining dense essence");
            sleep(3000);
        } else {
            log("Dense runestone not found or not interactable");
        }
    }
}
