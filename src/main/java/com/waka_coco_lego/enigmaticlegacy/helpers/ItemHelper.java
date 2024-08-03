package com.waka_coco_lego.enigmaticlegacy.helpers;

import dev.emi.trinkets.api.SlotGroup;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.*;

public class ItemHelper {

    public static boolean hasItemEquipped(LivingEntity entity, Item checkedItem) {
        Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(entity);
        // return optional.isPresent() ? optional.get().isEquipped(checkedItem) : false;
        // I really don't know how the code below is simpler than the code above
        return optional.map(trinketComponent -> trinketComponent.isEquipped(checkedItem)).orElse(false);
    }

    public static boolean canPickStack(PlayerEntity player, ItemStack stack) {
        if (player.getInventory().getEmptySlot() >= 0)
            return true;
        else {
            List<ItemStack> allInventories = new ArrayList<ItemStack>();

            // allInventories.addAll(player.getInventory().armorInventory);
            allInventories.addAll(player.getInventory().main);
            allInventories.addAll(player.getInventory().offHand);

            for (ItemStack invStack : allInventories) {
                if (canMergeStacks(invStack, stack, player.getInventory().getMaxCountPerStack()))
                    return true;
            }
        }

        return false;
    }

    public static boolean canMergeStacks(ItemStack stack1, ItemStack stack2, int invStackLimit) {
        return !stack1.isEmpty() && stackEqualExact(stack1, stack2) && stack1.isStackable() && stack1.getCount() < stack1.getMaxCount() && stack1.getCount() < invStackLimit;
    }

    public static boolean stackEqualExact(ItemStack stack1, ItemStack stack2) {
        return stack1.getItem() == stack2.getItem() && isSameItemSameTags(stack1, stack2);
    }

    public static boolean isSameItemSameTags(ItemStack stack1, ItemStack stack2) {
        if (!stack1.isOf(stack2.getItem())) {
            return false;
        } else {
            return stack1.isEmpty() && stack2.isEmpty() || Objects.equals(stack1.getName(), stack2.getName());
        }
    }

}
