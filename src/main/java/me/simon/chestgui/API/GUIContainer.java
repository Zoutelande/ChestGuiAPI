package me.simon.chestgui.API;

import me.simon.chestgui.API.parts.ChestGuiButton;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.s2c.play.InventoryS2CPacket;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.HashMap;

public class GUIContainer extends ScreenHandler {
    private final Inventory inventory;
    private final int rows;
    private final HashMap<Integer, ChestGuiButton> buttonList;

    protected GUIContainer(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, Inventory inventory, int rows, HashMap<Integer, ChestGuiButton> buttonList) {
        super(type, syncId);
        checkSize(inventory, rows * 9);
        this.inventory = inventory;
        this.rows = rows;
        this.buttonList = buttonList;
        inventory.onOpen(playerInventory.player);
        int i = (this.rows - 4) * 18;
        int n;
        int m;
        for(n = 0; n < this.rows; ++n) {
            for(m = 0; m < 9; ++m) {
                this.addSlot(new Slot(inventory, m + n * 9, 8 + m * 18, 18 + n * 18));
            }
        }
        for(n = 0; n < 3; ++n) {
            for(m = 0; m < 9; ++m) {
                this.addSlot(new Slot(playerInventory, m + n * 9 + 9, 8 + m * 18, 103 + n * 18 + i));
            }
        }
        for(n = 0; n < 9; ++n) {
            this.addSlot(new Slot(playerInventory, n, 8 + n * 18, 161 + i));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ItemStack onSlotClick(int slotId, int clickData, SlotActionType actionType, PlayerEntity playerEntity) {
        if(buttonList.containsKey(slotId)) {
            ChestGuiButton button = buttonList.get(slotId);
            button.runAction(actionType);
        }
        this.inventory.markDirty();
        ((ServerPlayerEntity) playerEntity).server.getPlayerManager().sendToAll(new InventoryS2CPacket(syncId, playerEntity.currentScreenHandler.getStacks()));
        this.sendContentUpdates();
        return ItemStack.EMPTY;
    }
}
