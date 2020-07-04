package me.simon.chestgui.API;

import me.simon.chestgui.API.parts.button.ChestGuiButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.HashMap;

public class ChestGuiBuilder {
    private NamedScreenHandlerFactory SNCF;
    private HashMap<Integer, ChestGuiButton> buttonList;
    private int rowSize;
    private Text containerName;
    private ItemStack background;

    public ChestGuiBuilder() {
        this.rowSize = 3;
        this.containerName = new LiteralText("Unnamed GUI");
        this.buttonList = new HashMap<>();

        ItemStack backgroundStack = new ItemStack(Items.BRICK);
        backgroundStack.getOrCreateTag().putInt("CustomModelData", 1);
        backgroundStack.setCustomName(new LiteralText(""));
        this.background = backgroundStack;
    }

    public ChestGuiBuilder addButton(ChestGuiButton button){
        this.buttonList.put(button.settings.slotId, button);
        return this;
    }

    public ChestGuiBuilder setRowSize(int rowSize){
        if(rowSize > 6 || rowSize < 1) return null;
        else{
            this.rowSize = rowSize;
            return this;
        }
    }

    public ChestGuiBuilder setName(Text name){
        this.containerName = name;
        return this;
    }

    public ChestGuiBuilder setBackground(ItemStack item){
        this.background = item;
        return this;
    }

    public NamedScreenHandlerFactory build(){
        this.SNCF = new NamedScreenHandlerFactory() {
            @Override
            public Text getDisplayName() {
                return containerName;
            }

            @Override
            public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
                return getContainer(syncId, inv, buttonList);
            }
        };
        return SNCF;
    }

    private GUIContainer getContainer(int syncId, PlayerInventory inventory, HashMap<Integer, ChestGuiButton> buttonList){
        GUIContainer output;
        switch (rowSize){
            case 1:
                output = new GUIContainer(ScreenHandlerType.GENERIC_9X1, syncId, inventory, new SimpleInventory(9 * rowSize), rowSize, buttonList);
                break;
            case 2:
                output = new GUIContainer(ScreenHandlerType.GENERIC_9X2, syncId, inventory, new SimpleInventory(9 * rowSize), rowSize, buttonList);
                break;
            case 3:
                output = new GUIContainer(ScreenHandlerType.GENERIC_9X3, syncId, inventory, new SimpleInventory(9 * rowSize), rowSize, buttonList);
                break;
            case 4:
                output = new GUIContainer(ScreenHandlerType.GENERIC_9X4, syncId, inventory, new SimpleInventory(9 * rowSize), rowSize, buttonList);
                break;
            case 5:
                output = new GUIContainer(ScreenHandlerType.GENERIC_9X5, syncId, inventory, new SimpleInventory(9 * rowSize), rowSize, buttonList);
                break;
            case 6:
                output = new GUIContainer(ScreenHandlerType.GENERIC_9X6, syncId, inventory, new SimpleInventory(9 * rowSize), rowSize, buttonList);
                break;
            default:
                output = new GUIContainer(ScreenHandlerType.GENERIC_9X3, syncId, inventory, new SimpleInventory(9 * rowSize), rowSize, buttonList);
            break;
        }
        buildInventory(output);
        return output;
    }

    private void buildInventory(ScreenHandler container){
        for(int i = 0; i < container.slots.size() - 36; i++){
            container.setStackInSlot(i, this.background);
        }
        this.buttonList.forEach((integer, button) -> container.setStackInSlot(button.settings.slotId, button.settings.displayItem));
    }
}
