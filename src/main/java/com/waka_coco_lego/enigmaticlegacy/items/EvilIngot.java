package com.waka_coco_lego.enigmaticlegacy.items;

import com.waka_coco_lego.enigmaticlegacy.api.items.ICursed;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class EvilIngot extends Item implements ICursed {
    public EvilIngot(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ItemLoreHelper.indicateCursedOnesOnly(tooltip);
    }
}
