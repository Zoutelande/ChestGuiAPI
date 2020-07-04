package me.simon.chestgui.API.parts.button;

import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class ButtonSettings {
    public boolean isDummy;
    public int slotId;
    public ItemStack displayItem;
    Runnable onPICKUP = ()->{};
    Runnable onQUICK_MOVE = ()->{};
    Runnable onSWAP = ()->{};
    Runnable onCLONE = ()->{};
    Runnable onTHROW = ()->{};
    Runnable onPICKUP_ALL = ()->{};
    public Text buttonName;
}
