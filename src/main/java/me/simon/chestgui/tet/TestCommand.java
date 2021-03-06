package me.simon.chestgui.tet;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import me.simon.chestgui.API.ChestGuiBuilder;
import me.simon.chestgui.API.parts.button.ButtonBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.slot.SlotActionType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Formatting;

public class TestCommand {
        public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
            dispatcher.register(CommandManager.literal("testGui")
                    .requires(src -> {
                        try {
                            return src.getPlayer().hasPermissionLevel(5);
                        } catch (CommandSyntaxException e) {
                            e.printStackTrace();
                        }
                        return false;
                    })
                    .executes(TestCommand::testGui)
            );
        }

        private static int testGui(CommandContext<ServerCommandSource> ctx) throws CommandSyntaxException {
            ServerPlayerEntity player = ctx.getSource().getPlayer();
            player.openHandledScreen(openGui(player));
            return 1;
        }
        private static NamedScreenHandlerFactory openGui(ServerPlayerEntity playerEntity) {
            NamedScreenHandlerFactory factory =
                    new ChestGuiBuilder()
                            .setName(new LiteralText("Cool GUI yee haw").formatted(Formatting.RED))
                            .setRowSize(2)
                            .addButton(new ButtonBuilder(8, new ItemStack(Items.GREEN_WOOL))
                                    .setName(
                                            new LiteralText("Cool nice green wool").formatted(Formatting.GREEN))
                                    .setAction(SlotActionType.PICKUP,
                                            ()-> playerEntity.sendMessage(new LiteralText("bruh"), false))
                                    .build())
                            .addButton(
                                    new ButtonBuilder( 0, new ItemStack(Items.RED_WOOL))
                                            .setAction(SlotActionType.QUICK_MOVE, playerEntity::kill)
                                            .setName(new LiteralText("RED MEANS DEATH").formatted(Formatting.RED))
                                            .build())
                            .build();
            return factory;
        }
}
