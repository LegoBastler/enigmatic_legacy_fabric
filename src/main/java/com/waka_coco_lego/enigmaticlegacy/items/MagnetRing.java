package com.waka_coco_lego.enigmaticlegacy.items;

import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import com.waka_coco_lego.enigmaticlegacy.helpers.SuperpositionHandler;
import com.waka_coco_lego.enigmaticlegacy.objects.TransientPlayerData;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticItems;
import com.waka_coco_lego.omniconfig.wrappers.Omniconfig;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;

import java.util.List;
import java.util.function.Predicate;

public class MagnetRing extends TrinketItem {
    public static final String disabledMagnetTag = "DisabledMagnetEffects";
    public static Omniconfig.IntParameter range;
    public static Omniconfig.BooleanParameter invertShift;
    public static Omniconfig.BooleanParameter inventoryButtonEnabled;
    public static Omniconfig.IntParameter buttonOffsetX;
    public static Omniconfig.IntParameter buttonOffsetY;
    public static Omniconfig.IntParameter buttonOffsetXCreative;
    public static Omniconfig.IntParameter buttonOffsetYCreative;

    public MagnetRing(Settings settings) {
        super(settings);
    }

    // public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> list, TooltipFlag flagIn) {
    //
    //     ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.void");
    //
    //     if (Screen.hasShiftDown()) {
    //         ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.magnetRing1", ChatFormatting.GOLD, range.getValue());
    //         ItemLoreHelper.addLocalizedString(list, invertShift.getValue() ? "tooltip.enigmaticlegacy.magnetRing2_alt" : "tooltip.enigmaticlegacy.magnetRing2");
    //     } else {
    //         ItemLoreHelper.addLocalizedString(list, "tooltip.enigmaticlegacy.holdShift");
    //     }
    // }

    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
        if (stack.getHolder().isSneaking()) {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.magnetRing1", Formatting.GOLD, range.getValue());
            ItemLoreHelper.addLocalizedString(tooltip, invertShift.getValue() ? "tooltip.enigmaticlegacy.magnetRing2_alt" : "tooltip.enigmaticlegacy.magnetRing2");
        } else {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.holdShift");
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if ((invertShift.getValue() != entity.isSneaking()) || !(entity instanceof PlayerEntity))
            return;

        if (this.hasMagnetEffectsDisabled((PlayerEntity) entity))
            return;

        double x = entity.getX();
        double y = entity.getY() + 0.75;
        double z = entity.getZ();

        //.getEntitiesOfClass(ItemEntity.class, new AABB(x - range.getValue(), y - range.getValue(), z - range.getValue(), x + range.getValue(), y + range.getValue(), z + range.getValue()));
        List<ItemEntity> items = entity.getWorld().getEntitiesByClass(ItemEntity.class,
                new Box(x - range.getValue(), y - range.getValue(), z - range.getValue(), x + range.getValue(), y + range.getValue(), z + range.getValue()),
                itemEntity -> false);
        int pulled = 0;
        for (ItemEntity item : items)
            if (this.canPullItem(item)) {
                if (pulled > 200) {
                    break;
                }

                if (!SuperpositionHandler.canPickStack((PlayerEntity) entity, item.getStack())) {
                    continue;
                }

                SuperpositionHandler.setEntityMotionFromVector(item, new Vec3d(x, y, z), 0.45F);
                item.setPickupDelay(0);

                //for (int counter = 0; counter <= 2; counter++)
                //	living.world.addParticle(ParticleTypes.WITCH, item.getPosX(), item.getPosY() - item.getYOffset() + item.getHeight() / 2, item.getPosZ(), (Math.random() - 0.5D) * 0.1D, (Math.random() - 0.5D) * 0.1D, (Math.random() - 0.5D) * 0.1D);
                pulled++;
            }

    }

    @Override
    public boolean canEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return super.canEquip(stack, slot, entity) && !SuperpositionHandler.hasTrinket(entity, EnigmaticItems.SUPER_MAGNET_RING);
    }

    public boolean hasMagnetEffectsDisabled(PlayerEntity player) {
        return TransientPlayerData.get(player).getDisabledMagnetRingEffects();
    }

    protected boolean canPullItem(ItemEntity item) {
        ItemStack stack = item.getStack();
        if (!item.isAlive() || stack.isEmpty()) // TODO this if-statement needs a fabric version of this bit: ' || item.getPersistentData().getBoolean("PreventRemoteMovement")'
            return false;

        return true;
    }

}
