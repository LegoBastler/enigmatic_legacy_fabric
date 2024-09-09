package com.waka_coco_lego.enigmaticlegacy.registries;

import com.waka_coco_lego.enigmaticlegacy.blocks.BlockAstralDust;
import com.waka_coco_lego.etherium.block.EtheriumBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import static com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy.*;

public class EnigmaticBlocks {

    public static final Block ASTRAL_BLOCK = registerBlock("astral_block", new BlockAstralDust(AbstractBlock.Settings.create().mapColor(MapColor.LIGHT_BLUE)
            .strength(1F, 10F).luminance(luminance -> 10).nonOpaque().sounds(BlockSoundGroup.WOOL)));

    public static final Block ETHERIUM_BLOCK = registerBlock("etherium_block", new EtheriumBlock(AbstractBlock.Settings.create()));

    private static Block registerBlock(String name, Block block) {
        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        Registry.register(Registries.ITEM, Identifier.of(MODID, name), blockItem);
        return Registry.register(Registries.BLOCK, Identifier.of(MODID, name), block);
    }

    public static void registerModBlocks() {
        LOGGER.info("Registering Mod Blocks for " + NAME);
    }

}
