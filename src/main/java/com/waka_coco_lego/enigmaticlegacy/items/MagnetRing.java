package com.waka_coco_lego.enigmaticlegacy.items;

import com.waka_coco_lego.enigmaticlegacy.helpers.EntityHelper;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemHelper;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import com.waka_coco_lego.enigmaticlegacy.objects.TransientPlayerData;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticItems;
import com.waka_coco_lego.omniconfig.wrappers.Omniconfig;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;

import java.util.List;

public class MagnetRing extends TrinketItem {
    public static final String disabledMagnetTag = "DisabledMagnetEffects";
    public static int range = 8; // should be influenced by the config
    public static boolean invertShift = false; // should be influenced by the config

    public MagnetRing(Settings settings) {
        super(settings);
    }

    // @SubscribeConfig(receiveClient = true)
    // public static void onConfig(OmniconfigWrapper builder) {
    //     builder.pushPrefix("MagnetRing");
    //
    //     if (builder.config.getSidedType() != Configuration.SidedConfigType.CLIENT) {
    //         range = builder.comment("The radius in which Magnetic Ring will attract items.")
    //                 .min(1)
    //                 .max(256)
    //                 .getInt("Range", 8);
    //
    //         invertShift = builder.comment("Inverts the Shift behaviour of Magnetic Ring and Dislocation Ring.")
    //                 .getBoolean("InvertShift", false);
    //     } else {
    //         inventoryButtonEnabled = builder
    //                 .comment("Whether or not button for toggling magnet effects should be added to inventory GUI when player has Ring of Ender equipped.")
    //                 .getBoolean("ButtonEnabled", true);
    //
    //         buttonOffsetX = builder
    //                 .comment("Allows to set offset for Magnet Effects button on X axis.")
    //                 .minMax(32768)
    //                 .getInt("ButtonOffsetX", 0);
    //
    //         buttonOffsetY = builder
    //                 .comment("Allows to set offset for Magnet Effects button on Y axis.")
    //                 .minMax(32768)
    //                 .getInt("ButtonOffsetY", 0);
    //
    //         buttonOffsetXCreative = builder
    //                 .comment("Allows to set offset for Magnet Effects button on X axis, for creative inventory specifically.")
    //                 .minMax(32768)
    //                 .getInt("ButtonOffsetXCreative", 0);
    //
    //         buttonOffsetYCreative = builder
    //                 .comment("Allows to set offset for Magnet Effects button on Y axis, for creative inventory specifically.")
    //                 .minMax(32768)
    //                 .getInt("ButtonOffsetYCreative", 0);
    //
    //     }
    //
    //     builder.popPrefix();
    // }

    @Environment(EnvType.CLIENT)
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
        if (Screen.hasShiftDown()) {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.magnetRing1", Formatting.GOLD, range);
            ItemLoreHelper.addLocalizedString(tooltip, invertShift ? "tooltip.enigmaticlegacy.magnetRing2_alt" : "tooltip.enigmaticlegacy.magnetRing2");
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

        //.getEntitiesOfClass(ItemEntity.class, new AABB(x - range.getValue(), y - range.getValue(), z - range.getValue(), x + range.getValue(), y + range.getValue(), z + range.getValue()));
        List<ItemEntity> items = entity.getWorld().getEntitiesByClass(ItemEntity.class,
                new Box(x - range, y - range, z - range, x + range, y + range, z + range),
                itemEntity -> true);
        int pulled = 0;
        for (ItemEntity item : items)
            if (this.canPullItem(item)) {
                if (pulled > 200) {
                    break;
                }

                if (!ItemHelper.canPickStack((PlayerEntity) entity, item.getStack())) {
                    continue;
                }

                EntityHelper.setEntityMotionFromVector(item, new Vec3d(x, y, z), 0.45F);
                item.setPickupDelay(0);

                //for (int counter = 0; counter <= 2; counter++)
                //	living.world.addParticle(ParticleTypes.WITCH, item.getPosX(), item.getPosY() - item.getYOffset() + item.getHeight() / 2, item.getPosZ(), (Math.random() - 0.5D) * 0.1D, (Math.random() - 0.5D) * 0.1D, (Math.random() - 0.5D) * 0.1D);
                pulled++;
            }

    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return super.canEquip(stack, slot, entity) && !ItemHelper.hasItemEquipped(entity, EnigmaticItems.SUPER_MAGNET_RING);
    }

    public boolean hasMagnetEffectsDisabled(PlayerEntity player) {
        // TODO fix this once the config is up
        // return TransientPlayerData.get(player).getDisabledMagnetRingEffects();
        return false;
    }

    protected boolean canPullItem(ItemEntity item) {
        ItemStack stack = item.getStack();
        if (!item.isAlive() || stack.isEmpty()) // TODO this if-statement needs a fabric version of this bit: ' || item.getPersistentData().getBoolean("PreventRemoteMovement")'
            return false;
        return true;
    }

    @Override
    public boolean canEquipFromUse(ItemStack stack, LivingEntity entity) {
        return true;
    }

}
