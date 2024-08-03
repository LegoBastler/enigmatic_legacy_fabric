package com.waka_coco_lego.enigmaticlegacy.networking.payloads;

import com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;

public record EnderRingPayload(boolean A) implements CustomPayload {
    public static final CustomPayload.Id<EnderRingPayload> ID = new Id<>(Identifier.of(EnigmaticLegacy.MODID, "ender_ring"));
    public static final PacketCodec<RegistryByteBuf, EnderRingPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, EnderRingPayload::A, EnderRingPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
