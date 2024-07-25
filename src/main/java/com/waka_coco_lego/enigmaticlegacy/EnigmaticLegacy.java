package com.waka_coco_lego.enigmaticlegacy;

import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnigmaticLegacy implements ModInitializer {
	public static final String MODID ="enigmaticlegacy";
	public static final String NAME = "Enigmatic Legacy";

	public static final Logger LOGGER = LoggerFactory.getLogger(NAME);

	@Override
	public void onInitialize() {
		EnigmaticItems.registerModItems();
	}
}