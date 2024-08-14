package com.waka_coco_lego.enigmaticlegacy.items;

import com.google.common.collect.Multimap;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemHelper;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticComponents;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticItems;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticSounds;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class MiningCharm extends TrinketItem {

    // public static Omniconfig.PerhapsParameter breakSpeedBonus;
    // public static Omniconfig.DoubleParameter reachDistanceBonus;
    // public static Omniconfig.BooleanParameter enableNightVision;
    // public static EnigmaConfig.BooleanParameter bonusFortuneEnabled;
    public static boolean enableNightVision = true;
    public static double reachDistanceBonus = 2.15;
    public static int breakSpeedBonus = 30; // should be Percentage
    public final int nightVisionDuration = 310;

    public MiningCharm(Settings settings) {
        super(settings.component(EnigmaticComponents.IS_ACTIVATED_COMPONENT, false));
    }

    // @SubscribeConfig
    // public static void onConfig(OmniconfigWrapper builder) {
    //     builder.pushPrefix("MiningCharm");
    //
    //     breakSpeedBonus = builder
    //             .comment("Mining speed boost granted by Charm of Treasure Hunter. Defined as percentage.")
    //             .max(1000)
    //             .getPerhaps("BreakSpeed", 30);
    //
    //     reachDistanceBonus = builder
    //             .comment("Additional block reach granted by Charm of Treasure Hunter.")
    //             .max(16)
    //             .getDouble("ReachDistance", 2.15);
    //
    //     enableNightVision = builder
    //             .comment("Whether Night Vision ability of Charm of Treasure Hunter should be enabled.")
    //             .getBoolean("EnableNightVision", true);
    //
    //     builder.popPrefix();
    // }


    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        MutableText mode = Text.translatable("tooltip.enigmaticlegacy.enabled");

        if (Boolean.FALSE.equals(stack.get(EnigmaticComponents.IS_ACTIVATED_COMPONENT)))
            mode = Text.translatable("tooltip.enigmaticlegacy.disabled");

        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");

        if (Screen.hasShiftDown()) {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.miningCharm1", Formatting.GOLD, breakSpeedBonus + "%");
            // TODO remove this comment once Fortune works (it wont)
            // ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.miningCharm2", Formatting.GOLD, 1);
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.miningCharm3");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.miningCharm4");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.miningCharm5");
        } else {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.holdShift");
        }

        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.miningCharmNightVision", null, mode.getString());
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);

        if (Boolean.TRUE.equals(stack.get(EnigmaticComponents.IS_ACTIVATED_COMPONENT))) {
            stack.set(EnigmaticComponents.IS_ACTIVATED_COMPONENT, false);
            world.playSound(null, user.getBlockPos(), EnigmaticSounds.CHARGED_OFF, SoundCategory.PLAYERS, (float) (0.8F + (Math.random() * 0.2F)), (float) (0.8F + (Math.random() * 0.2F)));
        } else {
            if (enableNightVision) {
                stack.set(EnigmaticComponents.IS_ACTIVATED_COMPONENT, true);
                world.playSound(null, user.getBlockPos(), EnigmaticSounds.CHARGED_ON, SoundCategory.PLAYERS, (float) (0.8F + (Math.random() * 0.2F)), (float) (0.8F + (Math.random() * 0.2F)));
            }
        }
        return TypedActionResult.success(user.getStackInHand(hand), true);
    }

    public void removeNightVisionEffect(PlayerEntity player, int duration) {
        if (player.getStatusEffect(StatusEffects.NIGHT_VISION) != null) {
            StatusEffectInstance effect = player.getStatusEffect(StatusEffects.NIGHT_VISION);
            if (effect.getDuration() <= (duration - 1)) {
                player.removeStatusEffect(StatusEffects.NIGHT_VISION);
            }
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player && !entity.getWorld().isClient()) {
            if (ItemHelper.hasItemEquipped(player, EnigmaticItems.MINING_CHARM)) {
                if (Boolean.TRUE.equals(stack.get(EnigmaticComponents.IS_ACTIVATED_COMPONENT))) {
                    if (enableNightVision) {
                        if (player.getY() < 50 && !player.getWorld().getRegistryKey().equals(World.NETHER)
                                && !player.getWorld().getRegistryKey().equals(World.END)
                                && !player.isSubmergedInWater()
                                && !player.getWorld().isSkyVisibleAllowingSea(player.getBlockPos())
                                && player.getWorld().getLightLevel(player.getBlockPos()) <= 8) {
                            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, this.nightVisionDuration, 0, true, false));
                        }
                    } else {
                        stack.set(EnigmaticComponents.IS_ACTIVATED_COMPONENT, false);
                    }
                }
            }
            // ItemStack item = player.getActiveItem();
            // if (item.isIn(ItemTags.PICKAXES) || item.isIn(ItemTags.SHOVELS) || item.isIn(ItemTags.AXES) || item.isIn(ItemTags.HOES)) {
            //     item.addEnchantment(Enchantments.FORTUNE, 1);
            // }
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player) {
            this.removeNightVisionEffect(player, this.nightVisionDuration);
        }
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        modifiers.put(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED, new EntityAttributeModifier(slotIdentifier, (double) breakSpeedBonus / 100, EntityAttributeModifier.Operation.ADD_VALUE));
        modifiers.put(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE, new EntityAttributeModifier(slotIdentifier, reachDistanceBonus, EntityAttributeModifier.Operation.ADD_VALUE));
        return modifiers;
    }

}
