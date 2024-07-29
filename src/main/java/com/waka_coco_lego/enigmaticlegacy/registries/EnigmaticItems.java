package com.waka_coco_lego.enigmaticlegacy.registries;

import com.waka_coco_lego.enigmaticlegacy.items.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.ArrayList;
import java.util.List;

import static com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy.*;

public class EnigmaticItems {
    public static final List<Item> SPELLSTONES = new ArrayList<>();

    public static final Item IRONRING = registerItem("iron_ring", new IronRing(new Item.Settings().maxCount(1)));
    public static final Item MAGNET_RING = registerItem("magnet_ring", new MagnetRing(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item SUPER_MAGNET_RING = registerItem("super_magnet_ring", new SuperMagnetRing(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item ASTRAL_DUST = registerItem("astral_dust", new AstralDust(new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item THICC_SCROLL = registerItem("thicc_scroll", new ThiccScroll(new Item.Settings().maxCount(16)));
    public static final Item EVIL_ESSENCE = registerItem("evil_essence", new EvilEssence(new Item.Settings().rarity(Rarity.EPIC).maxCount(8).fireproof()));
    public static final Item EVIL_INGOT = registerItem("evil_ingot", new EvilIngot(new Item.Settings().rarity(Rarity.EPIC).maxCount(8).fireproof()));
    public static final Item ENDER_ROD = registerItem("ender_rod", new EnderRod(new Item.Settings()));



    public static final ItemGroup ENIGMATICGROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.tryParse(MODID, "enigmatic_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.enigmaticCreativeTab"))
                    .icon(() -> new ItemStack(IRONRING)).entries((displayContext, entries) -> {
                        entries.add(IRONRING);
                        entries.add(MAGNET_RING);
                        entries.add(SUPER_MAGNET_RING);
                        entries.add(ASTRAL_DUST);
                        entries.add(THICC_SCROLL);
                        entries.add(EVIL_ESSENCE);
                        entries.add(EVIL_INGOT);
                        entries.add(ENDER_ROD);
                    }).build());


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.tryParse(MODID, name), item);
    }

    public static void registerModItems() {
        LOGGER.info("Registering Mod Items for " + NAME);
    }

}
