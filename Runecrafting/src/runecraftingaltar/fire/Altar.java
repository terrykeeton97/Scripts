package runecraftingaltar.fire;

import runecrafting.Runecraft;
import com.osmb.api.ScriptCore;
import com.osmb.api.item.ItemID;
import com.osmb.api.location.area.Area;
import com.osmb.api.scene.RSObject;
import com.osmb.api.visual.drawing.Canvas;

public class Altar implements runecraftingaltar.Altar {
    @Override
    public int poll(Runecraft core) {
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
        return new int[0];
    }

    @Override
    public void onPaint(Canvas gc) {

    }

    @Override
    public int getRuneItemId() {
        return ItemID.FIRE_RUNE;
    }

    @Override
    public String getAltarName() {
        return "Fire Altar";
    }
}
