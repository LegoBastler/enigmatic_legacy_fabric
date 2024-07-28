package com.waka_coco_lego.enigmaticlegacy.helpers;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.Vec3d;

public class EntityHelper {

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

}
