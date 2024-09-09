package com.waka_coco_lego.enigmaticlegacy.items;

import com.waka_coco_lego.enigmaticlegacy.api.items.ICursed;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.List;

public class EvilEssence extends Item implements ICursed {
    public EvilEssence(Settings settings) {
        super(settings);
    }

    // Dunno if we really need this part
    // @Override
    // public boolean canBeHurtBy(DamageSource source) {
    //     return !SuperpositionHandler.isExplosion(source);
    // }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()) {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.evilEssence1");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.evilEssence2");
        } else {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.holdShift");
        }

        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
        ItemLoreHelper.indicateCursedOnesOnly(tooltip);
    }
}
