package me.simon.chestgui.API.parts;

import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.text.Text;


public class ChestGuiButton {
    public boolean isDummy;
    public int slotId;
    public ItemStack displayItem;
    public Runnable onPICKUP = ()->{};
    public Runnable onQUICK_MOVE = ()->{};
    public Runnable onSWAP = ()->{};
    public Runnable onCLONE = ()->{};
    public Runnable onTHROW = ()->{};
    public Runnable onPICKUP_ALL = ()->{};
    public Text buttonName;

    public ChestGuiButton(int slotId, ItemStack displayItem){
        this.slotId = slotId;
        this.displayItem = displayItem;
        this.isDummy = false;
    }

    public ChestGuiButton setName(Text name){
        this.buttonName = name;
        this.displayItem.setCustomName(name);
        return this;
    }

    public ChestGuiButton setAction(SlotActionType actionType, Runnable runnable){
        switch (actionType){
            case PICKUP:
                this.onPICKUP = runnable;
                break;
            case THROW:
                this.onTHROW = runnable;
                break;
            case QUICK_MOVE:
                this.onQUICK_MOVE = runnable;
                break;
            case SWAP:
                this.onSWAP = runnable;
                break;
            case CLONE:
                this.onCLONE = runnable;
                break;
            case PICKUP_ALL:
                this.onPICKUP_ALL = runnable;
                break;
            default:
                break;
        }
        return this;
    }

    public void runAction(SlotActionType actionType){
        switch (actionType) {
            case PICKUP:
                this.onPICKUP.run();
                break;
            case THROW:
                this.onTHROW.run();
                break;
            case QUICK_MOVE:
                this.onQUICK_MOVE.run();
                break;
            case SWAP:
                this.onSWAP.run();
                break;
            case CLONE:
                this.onCLONE.run();
                break;
            case PICKUP_ALL:
                this.onPICKUP_ALL.run();
                break;
            default:
                break;
        }
    }
}
