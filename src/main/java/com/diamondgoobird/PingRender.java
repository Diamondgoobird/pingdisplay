package com.diamondgoobird;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.awt.*;
import java.util.ArrayList;

public class PingRender implements HudRenderCallback {

    private static int ping = -1;
    private static TextRenderer renderer = MinecraftClient.getInstance().textRenderer;

    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        if (renderer == null) {
            renderer = MinecraftClient.getInstance().textRenderer;
            return;
        }
        if (ping != -1 && PingDisplayMod.getToggle()) {
            String s = String.valueOf(ping);
            drawContext.drawText(renderer, s, drawContext.getScaledWindowWidth() - renderer.getWidth(s), 0, Color.WHITE.getRGB(), true);
        }
    }

    static void setPing(int ping) {
        PingRender.ping = ping;
    }
}
