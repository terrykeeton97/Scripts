package Runecrafting;

import Gui.Gui;
import com.osmb.api.script.Script;
import com.osmb.api.script.ScriptDefinition;
import com.osmb.api.script.SkillCategory;
import com.osmb.api.trackers.experiencetracker.Skill;
import com.osmb.api.trackers.experiencetracker.XPTracker;
import com.osmb.api.ui.GameState;
import com.osmb.api.visual.drawing.Canvas;
import com.osmb.api.visual.image.ImageImport;

import java.util.Map;
import java.util.Set;

@ScriptDefinition(name = "TLK AIO Runecrafter", author = "tezkidd", version = 1.0, description = "AIO Runecrafter", skillCategory = SkillCategory.RUNECRAFTING)
public class Runecraft extends Script {
   
    public Runecraft(Object scriptCore) {
        super(scriptCore);
    }

    @Override
    public int poll() {
        return 0;
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
        
        var gui = new Gui();
        gui.buildScene(this);
        
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
        return super.regionsToPrioritise();
    }
}
