package com.waka_coco_lego.enigmaticlegacy.items;

import com.google.common.collect.Multimap;
import com.waka_coco_lego.enigmaticlegacy.helpers.ItemLoreHelper;
import com.waka_coco_lego.enigmaticlegacy.helpers.SuperpositionHelper;
import com.waka_coco_lego.enigmaticlegacy.registries.EnigmaticItems;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketEnums;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.Angerable;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipData;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Unit;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CursedRing extends TrinketItem {
    //public static Omniconfig.PerhapsParameter painMultiplier;
    //public static Omniconfig.PerhapsParameter monsterDamageDebuff;
    //public static Omniconfig.PerhapsParameter armorDebuff;
    //public static Omniconfig.PerhapsParameter experienceBonus;
    //public static Omniconfig.IntParameter fortuneBonus;
    //public static Omniconfig.IntParameter lootingBonus;
    //public static Omniconfig.IntParameter enchantingBonus;
    //
    //public static Omniconfig.PerhapsParameter knockbackDebuff;
    //public static Omniconfig.DoubleParameter neutralAngerRange;
    //public static Omniconfig.DoubleParameter neutralXRayRange;
    //public static Omniconfig.DoubleParameter endermenRandomportRange;
    //public static Omniconfig.DoubleParameter endermenRandomportFrequency;
    //public static Omniconfig.BooleanParameter saveTheBees;
    //public static Omniconfig.BooleanParameter enableSpecialDrops;
    //public static Omniconfig.BooleanParameter enableLore;
    //public static Omniconfig.BooleanParameter concealAbilities;
    //public static Omniconfig.BooleanParameter disableInsomnia;
    //
    //public static Omniconfig.BooleanParameter ultraHardcore;
    //public static Omniconfig.BooleanParameter autoEquip;
    //public static final List<ResourceLocation> neutralAngerBlacklist = new ArrayList<>();
    //
    //public static Omniconfig.DoubleParameter superCursedTime;

    public static boolean enableLore = true;
    public static boolean concealAbilities = false;
    public static int painMultiplier = 200;
    public static int monsterDamageDebuff = 50;
    public static int armorDebuff = 30;
    public static int experienceBonus = 400;
    public static int fortuneBonus = 1;
    public static int lootingBonus = 1;
    public static int enchantingBonus = 10;
    public static boolean ultraHardcore = false;
    public static boolean autoEquip = false;
    public static boolean disableInsomnia = false;
    public static int knockbackDebuff = 200;
    public static int neutralAngerRange = 24;
    public static int neutralXRayRange = 4;
    public static int endermenRandomportFrequency = 1;
    public static int endermenRandomportRange = 32;
    public static double superCursedTime = 0.995;
    public static boolean saveTheBees = false;
    public static final List<Entity> neutralAngerBlacklist = new ArrayList<>();

    //@SubscribeConfig(receiveClient = true)
    //public static void onConfig(OmniconfigWrapper builder) {
    //    String prevCategory = builder.getCurrentCategory();
    //    builder.popCategory();
    //
    //    builder.pushCategory("The Seven Curses", "Config options directly affecting Ring of the Seven Curses");
    //    builder.pushPrefix("CursedRing");
    //
    //    if (builder.config.getSidedType() == Configuration.SidedConfigType.CLIENT) {
    //        enableLore = builder
    //                .comment("Set to false to disable displaying lore on Ring of the Seven Curses. Useful if you are a modpack"
    //                        + " developer wanting to have your own.")
    //                .getBoolean("DisplayLore", true);
    //        concealAbilities = builder
    //                .comment("If true, tooltip of Ring of the Seven Curses cannot be read before it is equipped. Fun way to"
    //                        + " teach players that not every mystery is worth investigating.")
    //                .getBoolean("ConcealAbilities", false);
    //    } else {
    //        painMultiplier = builder
    //                .comment("Defines how much damage bearers of the ring receive from any source. Measured as percentage.")
    //                .getPerhaps("PainModifier", 200);
    //
    //        monsterDamageDebuff = builder
    //                .comment("How much damage monsters receive from bearers of the ring will be decreased, in percents.")
    //                .getPerhaps("MonsterDamageDebuff", 50);
    //
    //        armorDebuff = builder
    //                .comment("How much less effective armor will be for those who bear the ring. Measured as percetage.")
    //                .max(100)
    //                .getPerhaps("ArmorDebuff", 30);
    //
    //        experienceBonus = builder
    //                .comment("How much experience will drop from mobs to bearers of the ring, measured in percents.")
    //                .getPerhaps("ExperienceBonus", 400);
    //
    //        fortuneBonus = builder
    //                .comment("How many bonus Fortune levels ring provides")
    //                .getInt("FortuneBonus", 1);
    //
    //        lootingBonus = builder
    //                .comment("How many bonus Looting levels ring provides")
    //                .getInt("LootingBonus", 1);
    //
    //        enchantingBonus = builder
    //                .comment("How much additional Enchanting Power ring provides in Enchanting Table.")
    //                .getInt("EnchantingBonus", 10);
    //
    //        enableSpecialDrops = builder
    //                .comment("Set to false to disable ALL special drops that can be obtained from vanilla mobs when "
    //                        + "bearing Ring of the Seven Curses.")
    //                .getBoolean("EnableSpecialDrops", true);
    //
    //        ultraHardcore = builder
    //                .comment("If true, Ring of the Seven Curses will be equipped into player's ring slot right away when "
    //                        + "entering a new world, instead of just being added to their inventory.")
    //                .getBoolean("UltraHardcore", false);
    //
    //        autoEquip = builder
    //                .comment("If true, Ring of the Seven Curses will be equipped into player's ring slot right away when "
    //                        + "it enters their inventory. This is different from ultra hardcore option as the way through "
    //                        + "which ring ends up in player's inventory does not matter.")
    //                .getBoolean("AutoEquip", false);
    //
    //        disableInsomnia = builder
    //                .comment("Set to true to prevent curse of insomnia from actually doing anything.")
    //                .getBoolean("disableInsomnia", false);
    //
    //        knockbackDebuff = builder
    //                .comment("How much knockback bearers of the ring take, measured in percents.")
    //                .getPerhaps("KnockbackDebuff", 200);
    //
    //        neutralAngerRange = builder
    //                .comment("Range in which neutral creatures are angered against bearers of the ring.")
    //                .min(4)
    //                .getDouble("NeutralAngerRange", 24);
    //
    //        neutralXRayRange = builder
    //                .comment("Range in which neutral creatures can see and target bearers of the ring even if they can't directly see them.")
    //                .getDouble("NeutralXRayRange", 4);
    //
    //        endermenRandomportFrequency = builder
    //                .comment("Allows to adjust how frequently Endermen will try to randomly teleport to player bearing the ring, even "
    //                        + "if they can't see the player and are not angered yet. Lower value = less probability of this happening.")
    //                .min(0.01)
    //                .getDouble("EndermenRandomportFrequency", 1);
    //
    //        endermenRandomportRange = builder
    //                .comment("Range in which Endermen can try to randomly teleport to bearers of the ring.")
    //                .min(8)
    //                .getDouble("EndermenRandomportRange", 32);
    //
    //        superCursedTime = builder
    //                .comment("A fraction of time the player should bear the Seven Curses to use Abyssal Artifacts.")
    //                .getDouble("SuperCursedTime", 0.995);
    //
    //        builder.popCategory();
    //        builder.pushCategory("Save the Bees", "This category exists solely because of Jusey1z who really wanted to protect his bees."
    //                + Configuration.NEW_LINE + "Btw Jusey, when I said 'very cute though', I meant you. Bees are cute either of course.");
    //
    //        saveTheBees = builder
    //                .comment("If true, bees will never be affected by the Second Curse of Ring of the Seven Curses.")
    //                .getBoolean("DontTouchMyBees", false);
    //
    //        // Ugly but gets the job done
    //        neutralAngerBlacklist.clear();
    //
    //        // See https://github.com/Aizistral-Studios/Enigmatic-Legacy/pull/412
    //        neutralAngerBlacklist.add(new ResourceLocation("the_bumblezone", "bee_queen"));
    //
    //        String[] blacklist = builder.config.getStringList("CursedRingNeutralAngerBlacklist", "The Seven Curses", new String[0], "List of entities that should never be affected"
    //                + " by the Second Curse of Ring of the Seven Curses. Examples: minecraft:iron_golem, minecraft:zombified_piglin. Changing this option required game restart to take effect.");
    //
    //        Arrays.stream(blacklist).forEach(entry -> neutralAngerBlacklist.add(new ResourceLocation(entry)));
    //    }
    //
    //    builder.popCategory();
    //    builder.popPrefix();
    //    builder.pushCategory(prevCategory);
    //}

    public CursedRing(Settings settings) {
        super(settings.
                component(DataComponentTypes.ATTRIBUTE_MODIFIERS, AttributeModifiersComponent.DEFAULT.withShowInTooltip(false))); // disables the trinket attribute tooltip
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");

        if (Screen.hasShiftDown()) {
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing3");
            if ((double) painMultiplier / 100 == 2.0) {
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing4");
            } else {
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing4_alt", Formatting.GOLD, painMultiplier + "%");
            }
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing5");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing6", Formatting.GOLD, armorDebuff + "%");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing7", Formatting.GOLD, monsterDamageDebuff + "%");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing8");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing9");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing10");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing11");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing12", Formatting.GOLD, lootingBonus);
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing13", Formatting.GOLD, fortuneBonus);
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing14", Formatting.GOLD, experienceBonus + "%");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing15", Formatting.GOLD, enchantingBonus);
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing16");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing17");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRing18");
        } else {
            if (enableLore) {
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRingLore1");
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRingLore2");
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRingLore3");
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRingLore4");
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRingLore5");
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRingLore6");
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.cursedRingLore7");
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
            }
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.eternallyBound1");

            if (MinecraftClient.getInstance().player != null && SuperpositionHelper.canUnequipBoundRelics(MinecraftClient.getInstance().player)) {
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.eternallyBound2_creative");
            } else {
                ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.eternallyBound2");
            }

            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.void");
            ItemLoreHelper.addLocalizedString(tooltip, "tooltip.enigmaticlegacy.holdShift");
        }
    }

    @Override
    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, Identifier slotIdentifier) {
        var modifiers = super.getModifiers(stack, slot, entity, slotIdentifier);
        modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(slotIdentifier, (double) -armorDebuff / 100, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        modifiers.put(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier(slotIdentifier, (double) -armorDebuff / 100, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return modifiers;
    }

    @Override
    public boolean canUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof PlayerEntity player && SuperpositionHelper.canUnequipBoundRelics(player))
            return super.canUnequip(stack, slot, entity);
        else
            return false;
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        SuperpositionHelper.setCurrentWorldCursed(false);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        // TODO fix triggers
        if (entity instanceof ServerPlayerEntity player) {
            // CursedRingEquippedTrigger.INSTANCE.trigger(player);
        }
        SuperpositionHelper.setCurrentWorldCursed(true);
    }

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        // TODO Dirty self-equipping tricks
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity.getWorld().isClient() || !(entity instanceof PlayerEntity player))
            return;

        if (player.isCreative() || player.isSpectator())
            return;

        List<LivingEntity> genericMobs = player.getWorld().getEntitiesByClass(LivingEntity.class, SuperpositionHelper.getBoundingBoxAroundEntity(player, neutralAngerRange), livingEntity -> true);
        List<EndermanEntity> endermen = player.getWorld().getEntitiesByClass(EndermanEntity.class, SuperpositionHelper.getBoundingBoxAroundEntity(player, endermenRandomportRange), livingEntity -> true);

        for (EndermanEntity enderman : endermen) {
            if (enderman.getWorld().random.nextDouble() <= (0.002 * endermenRandomportFrequency)) {
                if (player.canSee(enderman)) {
                    enderman.setTarget(player);
                }
            }
        }

        for (LivingEntity checkedEntity : genericMobs) {
            double visibility = player.getVisibilityBoundingBox().getAverageSideLength();
            double angerDistance = Math.max(neutralAngerRange * visibility, neutralXRayRange);

            if (checkedEntity.distanceTo(player) > angerDistance * angerDistance) {
                continue;
            }

            if (checkedEntity instanceof PiglinEntity piglin && !SuperpositionHelper.hasItemEquipped(player, EnigmaticItems.AVARICE_SCROLL)) {
                if (piglin.getTarget() == null || !piglin.getTarget().isAlive()) {
                    if (player.canSee(checkedEntity) || player.distanceTo(checkedEntity) <= neutralXRayRange) {
                        // TODO get RegistryEntry from RegistryKey
                        // piglin.damage(new DamageSource(DamageTypes.GENERIC, player), 0);
                    }
                }

            } else if (checkedEntity instanceof Angerable angerable) {
                if (neutralAngerBlacklist.contains(checkedEntity)) {
                    continue;
                }

                if (angerable instanceof TameableEntity tamable && tamable.isTamed()) {
                    continue;
                } else if (SuperpositionHelper.hasItemEquipped(player, EnigmaticItems.ANIMAL_GUIDEBOOK)) {
                    if (false/* EnigmaticItems.ANIMAL_GUIDEBOOK.isTamableAnimal(checkedEntity) */) { //TODO fix this once the GUIDEBOOK exists
                        continue;
                    }
                } else if (angerable instanceof IronGolemEntity golem && golem.isPlayerCreated()) {
                    continue;
                } else if (angerable instanceof BeeEntity) {
                    if (saveTheBees || SuperpositionHelper.hasItemEquipped(player, EnigmaticItems.ANIMAL_GUIDEBOOK)) {
                        continue;
                    }
                }

                if (angerable.getTarget() == null || !angerable.getTarget().isAlive()) {
                    if (player.canSee(checkedEntity) || player.distanceTo(checkedEntity) <= neutralXRayRange) {
                        angerable.setTarget(player);
                    }
                }
            }
        }
    }

    // TODO fix this once you find out how to do fortune
    // @Override
    // public int getFortuneLevel(SlotContext slotContext, LootContext lootContext, ItemStack curio) {
    //     return super.getFortuneLevel(slotContext, lootContext, curio) + fortuneBonus.getValue();
    // }

    // TODO fix this once you find out how to do looting
    // @Override
    // public int getLootingLevel(SlotContext slotContext, DamageSource source, LivingEntity target, int baseLooting, ItemStack curio) {
    //     return super.getLootingLevel(slotContext, source, target, baseLooting, curio) + lootingBonus.getValue();
    // }
}
