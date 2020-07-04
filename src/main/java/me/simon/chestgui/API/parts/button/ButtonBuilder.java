package me.simon.chestgui.API.parts.button;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;

public class ButtonBuilder {
    private final ButtonSettings settings = new ButtonSettings();

    public ButtonBuilder(int slotId, ItemStack displayItem){
        settings.slotId = slotId;
        settings.displayItem = displayItem;
        settings.isDummy = false;
    }

    public ButtonBuilder setName(Text name){
        settings.buttonName = name;
        settings.displayItem.setCustomName(name);
        return this;
    }

    public ButtonBuilder setAction(SlotActionType actionType, Runnable runnable){
        switch (actionType){
            case PICKUP:
                settings.onPICKUP = runnable;
                break;
            case THROW:
                settings.onTHROW = runnable;
                break;
            case QUICK_MOVE:
                settings.onQUICK_MOVE = runnable;
                break;
            case SWAP:
                settings.onSWAP = runnable;
                break;
            case CLONE:
                settings.onCLONE = runnable;
                break;
            case PICKUP_ALL:
                settings.onPICKUP_ALL = runnable;
                break;
            default:
                break;
        }
        return this;
    }

    public ChestGuiButton build(){
        return new ChestGuiButton(this.settings);
    }
}
