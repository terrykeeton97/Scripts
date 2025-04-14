package RunecraftingAltar.Zmi;

import Runecrafting.Runecraft;
import com.osmb.api.location.area.Area;
import com.osmb.api.visual.drawing.Canvas;

public class Altar implements RunecraftingAltar.Altar {
    @Override
    public int poll(Runecraft core) {
        return 0;
    }

    @Override
    public Area getBankArea() {
        return null;
    }

    @Override
    public int[] regions() {
        return new int[0];
    }

    @Override
    public String name() {
        return "";
    }

    @Override
    public void onPaint(Canvas gc) {

    }

    @Override
    public int getRuneItemId() {
        return 9767;
    }

    @Override
    public String getAltarName() {
        return "Zmi Altar";
    }
}
