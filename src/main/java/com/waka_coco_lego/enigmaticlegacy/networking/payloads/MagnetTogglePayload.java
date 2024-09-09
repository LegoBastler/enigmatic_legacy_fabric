package com.waka_coco_lego.enigmaticlegacy.networking.payloads;

import com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record MagnetTogglePayload(boolean A) implements CustomPayload {
    public static final CustomPayload.Id<MagnetTogglePayload> ID = new Id<>(Identifier.of(EnigmaticLegacy.MODID, "magnet_toggle"));
    public static final PacketCodec<RegistryByteBuf, MagnetTogglePayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, MagnetTogglePayload::A, MagnetTogglePayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
