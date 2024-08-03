package com.waka_coco_lego.enigmaticlegacy;

import com.waka_coco_lego.enigmaticlegacy.networking.payloads.EnderRingPayload;
import com.waka_coco_lego.enigmaticlegacy.networking.payloads.KeyBindResetPayload;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnigmaticLegacy implements ModInitializer {
	public static final String MODID ="enigmaticlegacy";
	public static final String NAME = "Enigmatic Legacy";

	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	@Override
	public void onInitialize() {
		EnigmaticItems.registerModItems();

		registerS2C();
		registerC2S();
		registerGlobalServerReceiver();
	}

	private void registerS2C() {
		PayloadTypeRegistry.playS2C().register(KeyBindResetPayload.ID, KeyBindResetPayload.CODEC);
	}

	private void registerC2S() {
		PayloadTypeRegistry.playC2S().register(EnderRingPayload.ID, EnderRingPayload.CODEC);
	}

	private void registerGlobalServerReceiver() {
		ServerPlayNetworking.registerGlobalReceiver(EnderRingPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				ServerPlayerEntity player = context.player();
				player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
					return GenericContainerScreenHandler.createGeneric9x3(i, playerInventory, player.getEnderChestInventory());
				}, Text.translatable("container.enderchest")));
				ServerPlayNetworking.send(player, new KeyBindResetPayload("i"));
			});
		});
	}
}