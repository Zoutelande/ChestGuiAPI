package me.simon.chestgui.API.parts.button;

import net.minecraft.screen.slot.SlotActionType;

public class ChestGuiButton {
    public final ButtonSettings settings;

    public ChestGuiButton(ButtonSettings settings){
        this.settings = settings;
    }

    public void runAction(SlotActionType actionType){
        switch (actionType) {
            case PICKUP:
                settings.onPICKUP.run();
                break;
            case THROW:
                settings.onTHROW.run();
                break;
            case QUICK_MOVE:
                settings.onQUICK_MOVE.run();
                break;
            case SWAP:
                settings.onSWAP.run();
                break;
            case CLONE:
                settings.onCLONE.run();
                break;
            case PICKUP_ALL:
                settings.onPICKUP_ALL.run();
                break;
            default:
                break;
        }
    }
}
