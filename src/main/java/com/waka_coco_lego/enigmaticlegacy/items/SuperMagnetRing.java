package com.waka_coco_lego.enigmaticlegacy.items;

import com.waka_coco_lego.enigmaticlegacy.helpers.EntityHelper;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemHelper;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticItems;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;

import java.util.List;

public class SuperMagnetRing extends MagnetRing {
    private static int range = 16; // should be influenced by the config

    public SuperMagnetRing(Settings settings) {
        super(settings);
    }

    // @SubscribeConfig
    // public static void onConfig(OmniconfigWrapper builder) {
    //     builder.pushPrefix("SuperMagnetRing");
    //
    //     range = builder
    //             .comment("The radius in which Dislocation Ring will collect items.")
    //             .min(1)
    //             .max(256)
    //             .getInt("Range", 16);
    //
    //     soundEnabled = builder
    //             .comment("Whether or not Dislocation Ring should play sound effect when teleporting items to player.")
    //             .getBoolean("Sound", false);
    //
    //     builder.popPrefix();
    // }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
        if (ItemHelper.isShiftHeldOnStack(stack)) {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.superMagnetRing1");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.superMagnetRing2", Formatting.GOLD, range);
            ItemLoreHelper.addLocalizedString(tooltip, invertShift ? "tooltip.enigmaticlegacy.superMagnetRing3_alt" : "tooltip.enigmaticlegacy.superMagnetRing3");
        } else {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.holdShift");
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if ((invertShift != entity.isSneaking()) || !(entity instanceof PlayerEntity))
            return;

        if (this.hasMagnetEffectsDisabled((PlayerEntity) entity))
            return;

        double x = entity.getX();
        double y = entity.getY() + 0.75;
        double z = entity.getZ();

        List<ItemEntity> items = entity.getWorld().getEntitiesByClass(ItemEntity.class,
                new Box(x - range, y - range, z - range, x + range, y + range, z + range),
                itemEntity -> true);
        int pulled = 0;
        for (ItemEntity item : items)
            if (this.canPullItem(item)) {
                if (pulled > 512) {
                    break;
                }

                if (!ItemHelper.canPickStack((PlayerEntity) entity, item.getStack())) {
                    break;
                }

				// if (ConfigHandler.SUPER_MAGNET_RING_SOUND.getValue())
				// 	item.world.playSound(null, item.getPosition(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1.0F, (float) (0.8F + (Math.random() * 0.2D)));

                item.setPickupDelay(0);
                EntityHelper.playerTouch((PlayerEntity) entity, item);
                pulled++;
            }

    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return super.canEquip(stack, slot, entity) && !ItemHelper.hasItemEquipped(entity, EnigmaticItems.MAGNET_RING);
    }

    @Override
    protected boolean canPullItem(ItemEntity item) {
        return super.canPullItem(item);
    }

}
