package com.waka_coco_lego.enigmaticlegacy.items;

import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class AstralPotato extends Item {
    public AstralPotato(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (Screen.hasShiftDown()) {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.astralPotato1");
        } else {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.holdShift");
        }
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (!world.isClient) {
            for(int i = 0; i < 16; ++i) {
                double d = user.getX() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                double e = MathHelper.clamp(user.getY() + (double)(user.getRandom().nextInt(16) - 8), world.getBottomY(), (world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1));
                double f = user.getZ() + (user.getRandom().nextDouble() - 0.5) * 16.0;
                if (user.hasVehicle()) {
                    user.stopRiding();
                }

                Vec3d vec3d = user.getPos();
                if (user.teleport(d, e, f, true)) {
                    world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(user));
                    SoundCategory soundCategory;
                    SoundEvent soundEvent;
                    if (user instanceof FoxEntity) {
                        soundEvent = SoundEvents.ENTITY_FOX_TELEPORT;
                        soundCategory = SoundCategory.NEUTRAL;
                    } else {
                        soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;
                        soundCategory = SoundCategory.PLAYERS;
                    }

                    world.playSound(null, user.getX(), user.getY(), user.getZ(), soundEvent, soundCategory);
                    user.onLanding();
                    break;
                }
            }

            if (user instanceof PlayerEntity playerEntity) {
                playerEntity.clearCurrentExplosion();
                playerEntity.getItemCooldownManager().set(this, 20);
            }
        }

        return itemStack;
    }
}
