package com.waka_coco_lego.enigmaticlegacy.items;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy.MODID;

public class IronRing extends TrinketItem {

    public IronRing(Settings settings) {
        super(settings);;;;
    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, @NotNull UUID uuid) {
        Identifier uuidIdentifier = Identifier.tryParse(uuid.toString());

        var modifiers = super.getModifiers(stack, slot, entity, uuidIdentifier);

        modifiers.put(EntityAttributes.GENERIC_ARMOR, new EntityAttributeModifier(Identifier.tryParse(MODID + ":Armor"), 1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        SlotAttributes.addSlotModifier(modifiers, "offhand/ring", uuidIdentifier, 1, EntityAttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }
}