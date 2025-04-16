package runecraftingaltar;

import runecrafting.Runecraft;
import com.osmb.api.ScriptCore;
import com.osmb.api.location.area.Area;
import com.osmb.api.scene.RSObject;
import com.osmb.api.visual.drawing.Canvas;

public interface Altar {
    int poll(Runecraft core);

    Area getArea();

    RSObject getAltar(ScriptCore core);
    int[] regions();

    void onPaint(Canvas gc);

    int getRuneItemId();

    String getAltarName();
}
