package runecraftingaltar.blood;

import runecrafting.Runecraft;
import com.osmb.api.ScriptCore;
import com.osmb.api.item.ItemID;
import com.osmb.api.item.ItemSearchResult;
import com.osmb.api.location.area.Area;
import com.osmb.api.location.area.impl.RectangleArea;
import com.osmb.api.location.position.types.WorldPosition;
import com.osmb.api.scene.RSObject;
import com.osmb.api.utils.UIResultList;
import com.osmb.api.visual.drawing.Canvas;
import java.awt.Color;

public class Altar implements runecraftingaltar.Altar {

    private static final Area START_AREA = new RectangleArea(1757, 3842, 14, 21, 0);
    private static final Area DARK_ALTAR = new RectangleArea(1708, 3878, 14, 11, 0);
    private static final Area BLOOD_RUNE_ALTAR = new RectangleArea(1713, 3825, 8, 8, 0);
    private static final Area CLIMB_ROCKS_DOWN = new RectangleArea(1735, 3851, 7, 5, 0);
    private static final Area CLIMB_ROCKS_UP = new RectangleArea(1745, 3854, 7, 5, 0);
    private static final Area DENSE_ESSENCE_AREA = new RectangleArea(1760, 3852, 12, 12, 0);

    private enum State {
        MINE_ESSENCE,
        TRAVEL_TO_DARK_ALTAR,
        VENERATE_DARK_ALTAR,
        TRAVEL_TO_BLOOD_ALTAR,
        CRAFT_BLOOD_RUNES,
        RETURN_TO_START
    }
    
    private State currentState = State.MINE_ESSENCE;
    private long lastStateChange = 0;
    private boolean inventoryWasFull = false;
    private int essenceCount = 0;

    @Override
    public int poll(Runecraft script) {
        // Get player position
        WorldPosition playerPos = script.getWorldPosition();
        
        // State machine for blood runecrafting
        switch (currentState) {
            case MINE_ESSENCE:
                script.log("Blood Runecrafting: Mining essence");
                
                // Check if inventory is full (using a counter instead of findAllOfItem)
                if (essenceCount >= 26) {  // Leave room for pickaxe and maybe another item
                    setState(State.TRAVEL_TO_DARK_ALTAR);
                    inventoryWasFull = true;
                    return 300;
                }
                
                // Use the handleRunestone method we added to Runecraft
                script.handleRunestone();
                essenceCount++; // Increment counter when mining
                return 1000;
                
            case TRAVEL_TO_DARK_ALTAR:
                script.log("Blood Runecrafting: Traveling to dark altar");
                if (DARK_ALTAR.contains(playerPos)) {
                    setState(State.VENERATE_DARK_ALTAR);
                    return 300;
                }
                
                // Check if we need to climb down rocks
                if (CLIMB_ROCKS_DOWN.contains(playerPos)) {
                    RSObject rocks = script.getObjectManager().getClosestObject("Rocks");
                    if (rocks != null && rocks.isInteractableOnScreen()) {
                        script.getFinger().tap(rocks.getConvexHull(), "Climb-down");
                        script.sleep(5000);
                    }
                    return 1000;
                }
                
                // Move to dark altar - use walkTo method with WorldPosition
                script.log("Walking to dark altar");
                WorldPosition darkAltarPos = new WorldPosition(DARK_ALTAR.getCenter().getX(), DARK_ALTAR.getCenter().getY(), 0);
                script.getWalker().walkTo(darkAltarPos);
                return 1000;
                
            case VENERATE_DARK_ALTAR:
                script.log("Blood Runecrafting: Venerating dark altar");
                
                // Check if we've converted all dense essence (using counter)
                if (essenceCount <= 0) {
                    setState(State.TRAVEL_TO_BLOOD_ALTAR);
                    return 300;
                }
                
                RSObject darkAltar = script.getObjectManager().getClosestObject("Dark Altar");
                if (darkAltar != null && darkAltar.isInteractableOnScreen()) {
                    script.getFinger().tap(darkAltar.getConvexHull(), "Venerate");
                    script.sleep(5000);
                    script.log("Converted dense essence to dark essence");
                    essenceCount = 0; // Reset counter after converting all essence
                }
                return 1000;
                
            case TRAVEL_TO_BLOOD_ALTAR:
                script.log("Blood Runecrafting: Traveling to blood altar");
                if (BLOOD_RUNE_ALTAR.contains(playerPos)) {
                    setState(State.CRAFT_BLOOD_RUNES);
                    return 300;
                }
                
                // Check if we need to climb up rocks
                if (CLIMB_ROCKS_UP.contains(playerPos)) {
                    RSObject rocks = script.getObjectManager().getClosestObject("Rocks");
                    if (rocks != null && rocks.isInteractableOnScreen()) {
                        script.getFinger().tap(rocks.getConvexHull(), "Climb-up");
                        script.sleep(5000);
                    }
                    return 1000;
                }
                
                // Move to blood altar - use walkTo method with WorldPosition
                script.log("Walking to blood altar");
                WorldPosition bloodAltarPos = new WorldPosition(BLOOD_RUNE_ALTAR.getCenter().getX(), BLOOD_RUNE_ALTAR.getCenter().getY(), 0);
                script.getWalker().walkTo(bloodAltarPos);
                return 1000;
                
            case CRAFT_BLOOD_RUNES:
                script.log("Blood Runecrafting: Crafting blood runes");
                
                // Check if we've crafted all dark essence (using a flag)
                if (inventoryWasFull) {
                    setState(State.RETURN_TO_START);
                    return 300;
                }
                
                RSObject bloodAltar = getAltar(script);
                if (bloodAltar != null && bloodAltar.isInteractableOnScreen()) {
                    script.getFinger().tap(bloodAltar.getConvexHull(), "Bind Blood rune");
                    script.sleep(5000);
                    script.log("Crafted blood runes");
                    inventoryWasFull = false; // Reset flag after crafting
                }
                return 1000;
                
            case RETURN_TO_START:
                script.log("Blood Runecrafting: Returning to start");
                if (START_AREA.contains(playerPos)) {
                    setState(State.MINE_ESSENCE);
                    return 300;
                }
                
                // Move back to start area - use walkTo method with WorldPosition
                script.log("Walking back to start area");
                WorldPosition startAreaPos = new WorldPosition(START_AREA.getCenter().getX(), START_AREA.getCenter().getY(), 0);
                script.getWalker().walkTo(startAreaPos);
                return 1000;
                
            default:
                return 1000;
        }
    }
    
    private void setState(State newState) {
        if (currentState != newState) {
            currentState = newState;
            lastStateChange = System.currentTimeMillis();
        }
    }

    @Override
    public Area getArea() {
        return START_AREA;
    }

    @Override
    public RSObject getAltar(ScriptCore core) {
        return core.getObjectManager().getClosestObject("Blood Altar");
    }

    @Override
    public int[] regions() {
        return new int[]{6715, 6714, 6970, 6971};
    }

    @Override
    public void onPaint(Canvas canvas) {
        // Simple paint implementation that doesn't use specific Canvas methods
        // The parent script will handle the actual painting
    }

    @Override
    public int getRuneItemId() {
        return ItemID.BLOOD_RUNE;
    }

    @Override
    public String getAltarName() {
        return "Blood Altar";
    }
}
