package com.waka_coco_lego.enigmaticlegacy.registries;

import com.mojang.serialization.Codec;
import com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EnigmaticComponents {

    public static final ComponentType<Boolean> IS_ACTIVATED_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(EnigmaticLegacy.MODID, "is_activated_component"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );
    public static final ComponentType<Boolean> IS_TAINTED_COMPONENT = Registry.register(
            Registries.DATA_COMPONENT_TYPE,
            Identifier.of(EnigmaticLegacy.MODID, "is_tainted_component"),
            ComponentType.<Boolean>builder().codec(Codec.BOOL).build()
    );

    public static void registerComponents() {
        EnigmaticLegacy.LOGGER.info("Registering custom components for" + EnigmaticLegacy.MODID);
        // Technically this method can stay empty, but some developers like to notify
        // the console, that certain parts of the mod have been successfully initialized
    }
}
