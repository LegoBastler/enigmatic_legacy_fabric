package com.waka_coco_lego.enigmaticlegacy;

import com.waka_coco_lego.enigmaticlegacy.api.PlayerData;
import com.waka_coco_lego.enigmaticlegacy.api.StateSaverAndLoader;
import com.waka_coco_lego.enigmaticlegacy.helpers.SuperpositionHelper;
import com.waka_coco_lego.enigmaticlegacy.networking.payloads.EnderRingPayload;
import com.waka_coco_lego.enigmaticlegacy.networking.payloads.KeyBindResetPayload;
import com.waka_coco_lego.enigmaticlegacy.networking.payloads.MagnetTogglePayload;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticBlocks;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticComponents;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticItems;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class EnigmaticLegacy implements ModInitializer {
	public static final String MODID ="enigmaticlegacy";
	public static final String NAME = "Enigmatic Legacy";

	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);
	public static final UUID SOUL_OF_THE_ARCHITECT = UUID.fromString("bfa45411-874a-4ee0-b3bd-00c716059d95");

	@Override
	public void onInitialize() {
		EnigmaticComponents.registerComponents();
		EnigmaticBlocks.registerModBlocks();
		EnigmaticItems.registerModItems();

		registerS2C();
		registerC2S();
		registerGlobalServerReceiver();
		registerCursedCounter();
	}

	private void registerS2C() {
		PayloadTypeRegistry.playS2C().register(KeyBindResetPayload.ID, KeyBindResetPayload.CODEC);
	}

	private void registerC2S() {
		PayloadTypeRegistry.playC2S().register(EnderRingPayload.ID, EnderRingPayload.CODEC);
		PayloadTypeRegistry.playC2S().register(MagnetTogglePayload.ID, MagnetTogglePayload.CODEC);
	}

	private void registerGlobalServerReceiver() {
		ServerPlayNetworking.registerGlobalReceiver(EnderRingPayload.ID, (payload, context) -> {
			context.server().execute(() -> {
				ServerPlayerEntity player = context.player();
				player.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity)
						-> GenericContainerScreenHandler.createGeneric9x3(i, playerInventory, player.getEnderChestInventory()),
							Text.translatable("container.enderchest")));
				player.getWorld().playSound(player, player.getX() + 0.5, player.getY() + 0.5, player.getZ() + 0.5,
						SoundEvents.BLOCK_ENDER_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, player.getWorld().random.nextFloat() * 0.1F + 0.9F);
				ServerPlayNetworking.send(player, new KeyBindResetPayload("i"));
			});
		});
		ServerPlayNetworking.registerGlobalReceiver(MagnetTogglePayload.ID, (payload, context) -> {
			PlayerData playerData = StateSaverAndLoader.getPlayerState(context.player());
			playerData.hasMagnetEffectsDisabled = !playerData.hasMagnetEffectsDisabled;
		});
	}

	private void registerCursedCounter() {
		ServerTickEvents.END_WORLD_TICK.register(world -> {
			for (PlayerEntity player : world.getPlayers()) {
				PlayerData playerData = StateSaverAndLoader.getPlayerState(player);
				if (SuperpositionHelper.hasItemEquipped(player, EnigmaticItems.CURSED_RING)) playerData.timeWithRing++;
				playerData.totalTime++;
			}
		});
	}
}