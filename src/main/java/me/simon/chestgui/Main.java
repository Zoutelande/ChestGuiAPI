package me.simon.chestgui;

import me.simon.chestgui.tet.TestCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.CommandRegistry;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        CommandRegistry.INSTANCE.register(false, TestCommand::register);

    }
}