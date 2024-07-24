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

import java.util.UUID;

import static com.waka_coco_lego.enigmaticlegacy.Enigmatic_Legacy.MODID;

public class IronRing extends TrinketItem {

    public IronRing(Settings settings) {
        super(settings);
    }

    public Multimap<RegistryEntry<EntityAttribute>, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        Identifier uuidIdentifier = Identifier.tryParse(uuid.toString());

        var modifiers = super.getModifiers(stack, slot, entity, uuidIdentifier);

        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(Identifier.tryParse(MODID + "Armor bonus"), 0.1, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));

        SlotAttributes.addSlotModifier(modifiers, "hand/ring", uuidIdentifier, 1, EntityAttributeModifier.Operation.ADD_VALUE);
        return modifiers;
    }
}