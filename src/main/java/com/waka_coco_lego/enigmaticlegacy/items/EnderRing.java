package com.waka_coco_lego.enigmaticlegacy.items;

import com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy;
import com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacyClient;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

public class EnderRing extends TrinketItem {
    public EnderRing(Settings settings) {
        super(settings);
    }

    @Override
    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");

        if (Screen.hasShiftDown()) {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.enderRing1");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.enderRing2");
        } else {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.holdShift");
        }

        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.currentKeybind", Formatting.LIGHT_PURPLE, EnigmaticLegacyClient.EnderRingBind.getBoundKeyLocalizedText());
    }

    @Override
    public boolean canEquipFromUse(ItemStack stack, LivingEntity entity) {
        return true;
    }

}
