package com.waka_coco_lego.enigmaticlegacy.registries;

import com.waka_coco_lego.enigmaticlegacy.items.*;
import com.waka_coco_lego.etherium.block.EtheriumBlock;
import com.waka_coco_lego.etherium.items.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.component.type.FoodComponent;
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

    public static final Item IRON_RING = registerItem("iron_ring", new IronRing(new Item.Settings().maxCount(1)));
    public static final Item MAGNET_RING = registerItem("magnet_ring", new MagnetRing(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item SUPER_MAGNET_RING = registerItem("super_magnet_ring", new SuperMagnetRing(new Item.Settings().maxCount(1).rarity(Rarity.RARE)));
    public static final Item ASTRAL_DUST = registerItem("astral_dust", new AstralDust(new Item.Settings().rarity(Rarity.EPIC)));
    public static final Item THICC_SCROLL = registerItem("thicc_scroll", new ThiccScroll(new Item.Settings().maxCount(16)));
    public static final Item EVIL_ESSENCE = registerItem("evil_essence", new EvilEssence(new Item.Settings().rarity(Rarity.EPIC).maxCount(8).fireproof()));
    public static final Item EVIL_INGOT = registerItem("evil_ingot", new EvilIngot(new Item.Settings().rarity(Rarity.EPIC).maxCount(8).fireproof()));
    public static final Item ENDER_RING = registerItem("ender_ring", new EnderRing(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item MINING_CHARM = registerItem("mining_charm", new MiningCharm(new Item.Settings().rarity(Rarity.RARE).maxCount(1)));
    public static final Item CURSED_RING = registerItem("cursed_ring", new CursedRing(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item EARTH_HEART = registerItem("earth_heart", new EarthHeart(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item ABYSSAL_HEART = registerItem("abyssal_heart", new AbyssalHeart(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item DARKEST_SCROLL = registerItem("darkest_scroll", new DarkestScroll(new Item.Settings().rarity(Rarity.UNCOMMON).maxCount(1)));
    public static final Item TWISTED_HEART = registerItem("twisted_heart", new TwistedHeart(new Item.Settings().rarity(Rarity.EPIC).maxCount(1)));
    public static final Item ASTRAL_POTATO = registerItem("astral_potato", new AstralPotato(new Item.Settings().rarity(Rarity.EPIC).fireproof()
            .food(new FoodComponent.Builder().nutrition(8).saturationModifier(1.4F).alwaysEdible().build())));
    public static final Item AVARICE_SCROLL = null;
    public static final Item ANIMAL_GUIDEBOOK = null;

    public static final Item ENDER_ROD = registerItem("ender_rod", new EnderRod(new Item.Settings()));
    public static final Item ETHERIUM_ORE = registerItem("etherium_ore", new EtheriumOre(new Item.Settings().fireproof()));
    public static final Item ETHERIUM_INGOT = registerItem("etherium_ingot", new EtheriumIngot(new Item.Settings().fireproof()));
    public static final Item ETHERIUM_NUGGET = registerItem("etherium_nugget", new EtheriumNugget(new Item.Settings().fireproof()));
    public static final Item ETHERIUM_SCRAPS = registerItem("etherium_scraps", new EtheriumScraps(new Item.Settings().fireproof())); // These aren't actually fireproof in the orig Mod, but it just makes more sense this way

    // TODO add Spellstones to this List here
    // EnigmaticItems.SPELLSTONES.add(EnigmaticItems.ANGEL_BLESSING);
    // EnigmaticItems.SPELLSTONES.add(EnigmaticItems.GOLEM_HEART);
    // EnigmaticItems.SPELLSTONES.add(EnigmaticItems.OCEAN_STONE);
    // EnigmaticItems.SPELLSTONES.add(EnigmaticItems.BLAZING_CORE);
    // EnigmaticItems.SPELLSTONES.add(EnigmaticItems.EYE_OF_NEBULA);
    // EnigmaticItems.SPELLSTONES.add(EnigmaticItems.VOID_PEARL);
    // EnigmaticItems.SPELLSTONES.add(EnigmaticItems.THE_CUBE);
    // EnigmaticItems.SPELLSTONES.add(EnigmaticItems.ENIGMATIC_ITEM);

    public static final ItemGroup ENIGMATICLEGACY_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(MODID, "enigmatic_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.enigmaticCreativeTab"))
                    .icon(() -> new ItemStack(IRON_RING)).entries((displayContext, entries) -> {
                        entries.add(IRON_RING);
                        entries.add(MAGNET_RING);
                        entries.add(SUPER_MAGNET_RING);
                        entries.add(ASTRAL_DUST);
                        entries.add(THICC_SCROLL);
                        entries.add(EVIL_ESSENCE);
                        entries.add(EVIL_INGOT);
                        entries.add(ENDER_RING);
                        entries.add(MINING_CHARM);
                        entries.add(CURSED_RING);
                        entries.add(EARTH_HEART);
                        entries.add(ABYSSAL_HEART);
                        entries.add(DARKEST_SCROLL);
                        entries.add(TWISTED_HEART);
                        entries.add(ASTRAL_POTATO);

                        entries.add(ENDER_ROD);
                        entries.add(ETHERIUM_INGOT);
                        entries.add(ETHERIUM_NUGGET);
                        entries.add(ETHERIUM_ORE);
                        entries.add(ETHERIUM_SCRAPS);

                        entries.add(EnigmaticBlocks.ETHERIUM_BLOCK);
                        entries.add(EnigmaticBlocks.ASTRAL_BLOCK);
                    }).build());


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(MODID, name), item);
    }

    public static void registerModItems() {
        LOGGER.info("Registering Mod Items for " + NAME);
    }

}
