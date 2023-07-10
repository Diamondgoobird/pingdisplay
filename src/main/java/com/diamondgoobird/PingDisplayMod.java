package com.diamondgoobird;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingDisplayMod implements ModInitializer {
	private static boolean toggle = true;

    public static final Logger LOGGER = LoggerFactory.getLogger("ping-display");

	public static boolean getToggle() {
		return toggle;
	}

	@Override
	public void onInitialize() {
		HudRenderCallback.EVENT.register(new PingRender());
		ClientTickEvents.END_CLIENT_TICK.register(new PingUpdater());
		CommandRegistrationCallback.EVENT.register(
				((dispatcher, registryAccess, environment) -> {
					dispatcher.register(CommandManager.literal("pingdisplay").executes(
							(context) -> {
								toggle = !toggle;
								context.getSource().sendMessage(Text.of("Ping Display Mod " + (toggle ? "Enabled" : "Disabled")));
								return 1;
							}
					));
				})
		);
	}
}