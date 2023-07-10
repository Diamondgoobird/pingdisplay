package com.diamondgoobird;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class PingUpdater implements ClientTickEvents.EndTick {
    @Override
    public void onEndTick(MinecraftClient client) {
        if (PingDisplayMod.getToggle()) {
            try {
                client.getNetworkHandler().getListedPlayerListEntries().forEach((player) -> {
                    if (player.getProfile().getName().equals(client.player.getGameProfile().getName())) {
                        PingRender.setPing(player.getLatency());
                    }
                });
            } catch (Exception ignored) {

            }
        }
    }
}
