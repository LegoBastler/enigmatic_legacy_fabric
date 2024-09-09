package com.waka_coco_lego.enigmaticlegacy.items;

import com.waka_coco_lego.enigmaticlegacy.api.items.IEldritch;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class AbyssalHeart extends Item implements IEldritch {
    public AbyssalHeart(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()) {
            ItemLoreHelper.indicateWorthyOnesOnly(tooltip);
        } else {
            //if (SuperpositionHelper.isTheWorthyOne(Minecraft.getInstance().player)) {
            tooltip.add(Text.translatable("tooltip.enigmaticlegacy.abyssalHeart1"));
            tooltip.add(Text.translatable("tooltip.enigmaticlegacy.abyssalHeart2"));
            tooltip.add(Text.translatable("tooltip.enigmaticlegacy.abyssalHeart3"));
            tooltip.add(Text.translatable("tooltip.enigmaticlegacy.abyssalHeart4"));
            //} else {
            //	tooltip.add(Component.translatable("tooltip.enigmaticlegacy.abyssalHeart1_obf"));
            //	tooltip.add(Component.translatable("tooltip.enigmaticlegacy.abyssalHeart2_obf"));
            //	tooltip.add(Component.translatable("tooltip.enigmaticlegacy.abyssalHeart3_obf"));
            //	tooltip.add(Component.translatable("tooltip.enigmaticlegacy.abyssalHeart4_obf"));
            //}

            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.holdShift");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
            ItemLoreHelper.indicateCursedOnesOnly(tooltip);
        }
    }
}
