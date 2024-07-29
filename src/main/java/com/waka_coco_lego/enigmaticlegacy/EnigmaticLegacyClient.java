package com.waka_coco_lego.enigmaticlegacy;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.option.KeyBinding;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class EnigmaticLegacyClient implements ClientModInitializer {

	public static KeyBinding EnderRingBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.enderRing", InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_I, "key.categories.enigmaticLegacy"));

	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
	}

}