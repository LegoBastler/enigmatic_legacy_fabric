package com.waka_coco_lego.enigmaticlegacy.api.items;

import com.waka_coco_lego.enigmaticlegacy.helpers.SuperpositionHelper;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticComponents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface ITaintable {
    default void handleTaintable(ItemStack stack, PlayerEntity player) {
        if (SuperpositionHelper.isTheCursedOne(player)) {
            if (Boolean.FALSE.equals(stack.get(EnigmaticComponents.IS_TAINTED_COMPONENT))) {
                stack.set(EnigmaticComponents.IS_TAINTED_COMPONENT, true);
                System.out.println(stack.getName() + " was tainted");
            }
        } else {
            if (Boolean.TRUE.equals(stack.get(EnigmaticComponents.IS_TAINTED_COMPONENT))) {
                stack.set(EnigmaticComponents.IS_TAINTED_COMPONENT, false);
                System.out.println(stack.getName() + " was untainted");
            }
        }
    }

    default boolean isTainted(ItemStack stack) {
        return Boolean.TRUE.equals(stack.get(EnigmaticComponents.IS_TAINTED_COMPONENT));
    }
}
