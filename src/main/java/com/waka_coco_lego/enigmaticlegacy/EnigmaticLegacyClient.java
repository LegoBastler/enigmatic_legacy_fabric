package com.waka_coco_lego.enigmaticlegacy;

import com.waka_coco_lego.enigmaticlegacy.helpers.ItemHelper;
import com.waka_coco_lego.enigmaticlegacy.networking.payloads.EnderRingPayload;
import com.waka_coco_lego.enigmaticlegacy.networking.payloads.KeyBindResetPayload;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticItems;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.option.KeyBinding;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class EnigmaticLegacyClient implements ClientModInitializer {

	private boolean isEnderRingBindPressable = true;

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
			while (EnderRingBind.isPressed() && isEnderRingBindPressable
					&& client.player.currentScreenHandler == client.player.playerScreenHandler
					&& ItemHelper.hasItemEquipped(client.player, EnigmaticItems.ENDER_RING)) {
				ClientPlayNetworking.send(new EnderRingPayload(true));
				isEnderRingBindPressable = false;
            }
		});

		ClientPlayNetworking.registerGlobalReceiver(KeyBindResetPayload.ID, (payload, context) -> {
			if (payload.bind().equals("i")) isEnderRingBindPressable = true;
		});
	}

}