package com.waka_coco_lego.enigmaticlegacy;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class EnigmaticLegacyClient implements ClientModInitializer {

	public static KeyBinding EnderRingBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.enderRing", InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_I, "key.categories.enigmaticLegacy"));
	public static KeyBinding XPScrollBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.xpScroll", InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_J, "key.categories.enigmaticLegacy"));
	public static KeyBinding SpellstoneBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.spellstoneAbility", InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_K, "key.categories.enigmaticLegacy"));


	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (EnderRingBind.isPressed()) {
				client.player.sendMessage(Text.literal("ooga booga"));
			}
		});
	}

}