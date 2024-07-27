package com.waka_coco_lego.enigmaticlegacy.proxy;

import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;


import com.waka_coco_lego.enigmaticlegacy.helpers.SuperpositionHandler;
import com.waka_coco_lego.enigmaticlegacy.objects.TransientPlayerData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CommonProxy {
    protected final Map<PlayerEntity, TransientPlayerData> commonTransientPlayerData;

    public CommonProxy() {
        this.commonTransientPlayerData = new WeakHashMap<>();
    }

    public void displayPermadeathScreen() {
        // NO-OP
    }

    public void clearTransientData() {
        this.commonTransientPlayerData.clear();
    }

    public Map<PlayerEntity, TransientPlayerData> getTransientPlayerData(boolean clientOnly) {
        return this.commonTransientPlayerData;
    }

    public void handleItemPickup(int pickuper_id, int item_id) {
        // Insert existential void here
    }

    public void initAuxiliaryRender() {
        // Insert existential void here
    }

    public boolean isInVanillaDimension(PlayerEntity player) {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
        return serverPlayer.getWorld().getRegistryKey().equals(this.getOverworldKey()) || serverPlayer.getWorld().getRegistryKey().equals(this.getNetherKey()) || serverPlayer.getWorld().getRegistryKey().equals(this.getEndKey());
    }

    public boolean isInDimension(PlayerEntity player, RegistryKey<World> world) {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) player;
        return serverPlayer.getWorld().getRegistryKey().equals(world);
    }

    public World getCentralWorld() {
        return SuperpositionHandler.getOverworld();
    }

    public RegistryKey<World> getOverworldKey() {
        return World.OVERWORLD;
    }

    public RegistryKey<World> getNetherKey() {
        return World.NETHER;
    }

    public RegistryKey<World> getEndKey() {
        return World.END;
    }

    public UseAction getVisualBlockAction() {
        return UseAction.BOW;
    }

    public PlayerEntity getPlayer(UUID playerID) {
        return null;
    }

    public PlayerEntity getClientPlayer() {
        return null;
    }

    public String getClientUsername() {
        return null;
    }

    public void pushRevelationToast(ItemStack renderedStack, int xp, int knowledge) {
        // NO-OP
    }

    public void initEntityRendering() {
        // NO-OP
    }

    public void spawnBonemealParticles(World world, BlockPos pos, int data) {
        // NO-OP
    }

    public void updateInfinitumCounters() {
        // NO-OP
    }

    public void displayReviveAnimation(int entityID, int reviveType) {
        // NO-OP
    }

}

