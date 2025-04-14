package RunecraftingAltar;

import Runecrafting.Runecraft;
import com.osmb.api.location.area.Area;
import com.osmb.api.visual.drawing.Canvas;

public interface Altar {
    int poll(Runecraft core);

    Area getBankArea();

    int[] regions();

    void onPaint(Canvas gc);

    int getRuneItemId();

    String getAltarName();
}
