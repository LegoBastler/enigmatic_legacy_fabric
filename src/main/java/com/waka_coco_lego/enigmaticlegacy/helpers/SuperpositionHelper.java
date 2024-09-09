package com.waka_coco_lego.enigmaticlegacy.helpers;

import com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy;
import com.waka_coco_lego.enigmaticlegacy.api.PlayerData;
import com.waka_coco_lego.enigmaticlegacy.api.StateSaverAndLoader;
import com.waka_coco_lego.enigmaticlegacy.items.CursedRing;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticItems;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.joml.RoundingMode;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class SuperpositionHelper {

    public static boolean hasItemEquipped(LivingEntity entity, Item checkedItem) {
        Optional<TrinketComponent> optional = TrinketsApi.getTrinketComponent(entity);
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

    public static void setEntityMotionFromVector(Entity entity, Vec3d originalPosVector, float modifier) {
        final Vec3d entityVector = fromEntityCenter(entity);
        Vec3d finalVector = originalPosVector.subtract(entityVector);
        if (finalVector.length() > 1.0) {
            finalVector = finalVector.normalize();
        }
        entity.setVelocity(finalVector.x * modifier, finalVector.y * modifier, finalVector.z * modifier);
    }

    public static Vec3d fromEntityCenter(Entity entity) {
        return new Vec3d(entity.getX(), entity.getY() + entity.getHeight() / 2, entity.getZ());
    }

    public static void playerTouch(PlayerEntity player, ItemEntity itemEntity) {
        ItemStack stack = itemEntity.getStack();
        int i = stack.getCount();
        if (!itemEntity.isAlive()) {
            return;
        }
        if (i >= 0 && (itemEntity.getOwner() == null || itemEntity.getOwner().equals(player))) {
            player.increaseStat(Stats.PICKED_UP.getOrCreateStat(stack.getItem()), i);
            player.getInventory().offer(stack, true);
        }
    }

    public static boolean isTheCursedOne(PlayerEntity player) {
        return SuperpositionHelper.hasItemEquipped(player, EnigmaticItems.CURSED_RING);
    }

    public static boolean isTheWorthyOne(PlayerEntity player) {
        if (isTheCursedOne(player)) {
            return getSufferingFraction(player) >= CursedRing.superCursedTime;
        } else
            return false;
    }

    public static String getSufferingTime(@Nullable PlayerEntity player) {
        String text = "";

        double ringPercent = 100 * getSufferingFraction(player);

        ringPercent = roundToPlaces(ringPercent, 1);
        if (ringPercent - Math.floor(ringPercent) == 0) {
            text += ((int) ringPercent) + "%";
        } else {
            text += ringPercent + "%";
        }

        return text;
    }

    public static double getSufferingFraction(@Nullable PlayerEntity player) {
        if (player == null) {
            System.out.println("no player");
            return 0;
        }

        PlayerData data = StateSaverAndLoader.getPlayerState(player);
        long timeWithRing = data.timeWithRing;

        if (timeWithRing <= 0)
            return 0;

        if (timeWithRing > 100000) {
            timeWithRing = timeWithRing / 100;
        }

        double ringFraction = (double) timeWithRing / data.totalTime;
        ringFraction = roundToPlaces(ringFraction, 3);

        return ringFraction;
    }

    public static double roundToPlaces(double value, int places) {
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public static boolean canUnequipBoundRelics(PlayerEntity player) {
        return player.isCreative() /* || EnigmaticLegacy.SOUL_OF_THE_ARCHITECT.equals(player.getUuid()) */;
    }

    public static Box getBoundingBoxAroundEntity(final Entity entity, final double radius) {
        return new Box(entity.getX() - radius, entity.getY() - radius, entity.getZ() - radius, entity.getX() + radius, entity.getY() + radius, entity.getZ() + radius);
    }

    public static boolean isTheBlessedOne(PlayerEntity player) {
        if (EnigmaticLegacy.SOUL_OF_THE_ARCHITECT.equals(player.getUuid()))
            return true;
        else
            return DevotedBelieversHandler.isDevotedBeliever(player);
    }

    public static void setCurrentWorldCursed(boolean cursed) {
        // TODO fix this once the custom world mode is up
        // getSingleplayerServer().ifPresent(server -> {
        //     File saveFolder = server.getWorldPath(LevelResource.ROOT).toFile();
        //     EnigmaticTransience transience = EnigmaticTransience.read(saveFolder);
        //     transience.setCursed(cursed);
        //     transience.write(saveFolder);
        // });
    }

}
