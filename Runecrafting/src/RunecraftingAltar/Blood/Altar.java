package RunecraftingAltar.Blood;

import Runecrafting.Runecraft;
import com.osmb.api.ScriptCore;
import com.osmb.api.item.ItemID;
import com.osmb.api.location.area.Area;
import com.osmb.api.location.area.impl.RectangleArea;
import com.osmb.api.location.position.types.WorldPosition;
import com.osmb.api.scene.RSObject;
import com.osmb.api.visual.drawing.Canvas;

public class Altar implements RunecraftingAltar.Altar {

    private static final Area START_AREA = new RectangleArea(1757, 3842, 14, 21, 0);
    private static final Area DARK_ALTAR = new RectangleArea(1708, 3878, 14, 11, 0);
    private static final Area BLOOD_RUNE_ALTAR = new RectangleArea(1713, 3825, 8, 8, 0);
    private static final Area CLIMB_ROCKS_DOWN = new RectangleArea(1735, 3851, 7, 5, 0);

    private static final WorldPosition CLIMB_ROCKS_DOWN_END_POS = new WorldPosition(1752, 3854, 0);
    private static final WorldPosition CLIMB_ROCKS_UP_START_POS = new WorldPosition(81, 88, 0);
    @Override
    public int poll(Runecraft core) {
        var worldPosition = core.getWorldPosition();
        if (START_AREA.contains(worldPosition)) {
            Runecraft.handleAltar(this);
        }

        return 0;
    }

    @Override
    public Area getArea() {
        return null;
    }

    @Override
    public RSObject getAltar(ScriptCore core) {
        return null;
    }

    @Override
    public int[] regions() {
        return new int[6715];
    }

    @Override
    public void onPaint(Canvas gc) {

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
