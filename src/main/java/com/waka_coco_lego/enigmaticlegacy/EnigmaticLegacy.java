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

		onLoadComplete();
	}

	public void onLoadComplete() {
		LOGGER.info("Initializing load completion phase...");

		// TODO add Spellstones to this List here
		// EnigmaticItems.SPELLSTONES.add(EnigmaticItems.ANGEL_BLESSING);
		// EnigmaticItems.SPELLSTONES.add(EnigmaticItems.GOLEM_HEART);
		// EnigmaticItems.SPELLSTONES.add(EnigmaticItems.OCEAN_STONE);
		// EnigmaticItems.SPELLSTONES.add(EnigmaticItems.BLAZING_CORE);
		// EnigmaticItems.SPELLSTONES.add(EnigmaticItems.EYE_OF_NEBULA);
		// EnigmaticItems.SPELLSTONES.add(EnigmaticItems.VOID_PEARL);
		// EnigmaticItems.SPELLSTONES.add(EnigmaticItems.THE_CUBE);
		// EnigmaticItems.SPELLSTONES.add(EnigmaticItems.ENIGMATIC_ITEM);
	}
}