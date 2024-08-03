package com.waka_coco_lego.enigmaticlegacy.networking.payloads;

import com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record KeyBindResetPayload (String bind) implements CustomPayload {
    public static final CustomPayload.Id<KeyBindResetPayload> ID = new Id<>(Identifier.of(EnigmaticLegacy.MODID, "keybind_reset"));
    public static final PacketCodec<RegistryByteBuf, KeyBindResetPayload> CODEC = PacketCodec.tuple(PacketCodecs.STRING, KeyBindResetPayload::bind, KeyBindResetPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
