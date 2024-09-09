package com.waka_coco_lego.enigmaticlegacy.items;

import com.waka_coco_lego.enigmaticlegacy.api.items.ITaintable;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class EarthHeart extends Item implements ITaintable {
    public EarthHeart(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (entity instanceof PlayerEntity player) {
            this.handleTaintable(stack, player);
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (this.isTainted(stack)) {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.tainted1");
        }
    }
}
