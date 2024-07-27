package com.waka_coco_lego.enigmaticlegacy.proxy;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.WeakHashMap;

import com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy;
import com.waka_coco_lego.enigmaticlegacy.helpers.SuperpositionHandler;
import com.waka_coco_lego.enigmaticlegacy.objects.TransientPlayerData;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ClientProxy extends CommonProxy {
    private static final Random RANDOM = new Random();
    protected final Map<PlayerEntity, TransientPlayerData> clientTransientPlayerData;
    protected final List<InfinitumCounterEntry> theInfinitumHoldTicks;

    public ClientProxy() {
        super();
        this.clientTransientPlayerData = new WeakHashMap<>();
        this.theInfinitumHoldTicks = new ArrayList<>();

       // IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
       // modBus.addListener(this::onClientSetup);
       // MinecraftForge.EVENT_BUS.register(QuoteHandler.INSTANCE);
    }

    // @Override
    // public void displayPermadeathScreen() {
    //     if (Minecraft.getInstance().level != null) {
    //         boolean local = Minecraft.getInstance().isLocalServer();
    //         Minecraft.getInstance().level.disconnect();
    //
    //         if (local) {
    //             Minecraft.getInstance().clearLevel(new GenericDirtMessageScreen(Text.translatable("menu.savingLevel")));
    //         } else {
    //             Minecraft.getInstance().clearLevel();
    //         }
    //     }
    //
    //     PermadeathScreen permadeath = new PermadeathScreen(new SelectWorldScreen(new TitleScreen()),
    //             Text.translatable("gui.enigmaticlegacy.permadeathTitle"),
    //             Text.translatable("message.enigmaticlegacy.permadeath"));
    //     PermadeathScreen.active = permadeath;
    //     Minecraft.getInstance().setScreen(permadeath);
    // }

    @Override
    public void clearTransientData() {
        super.clearTransientData();
        this.clientTransientPlayerData.clear();
        this.theInfinitumHoldTicks.clear();
    }

    @Override
    public Map<PlayerEntity, TransientPlayerData> getTransientPlayerData(boolean clientOnly) {
        if (clientOnly)
            return this.clientTransientPlayerData;
        else
            return this.commonTransientPlayerData;
    }

    // @Override
    // public void displayReviveAnimation(int entityID, int reviveType) {
    //     PlayerEntity player = this.getClientPlayer();
    //     Entity entity = player.getWorld().getEntityById(entityID);
    //
    //     if (entity != null) {
    //         Item item = reviveType == 0 ? EnigmaticItems.COSMIC_SCROLL : EnigmaticItems.THE_CUBE;
    //         int i = 40;
    //         Minecraft.getInstance().particleEngine.createTrackingEmitter(entity, ParticleTypes.TOTEM_OF_UNDYING, 30);
    //
    //         int amount = 50;
    //
    //         for (int counter = 0; counter <= amount; counter++) {
    //             player.level().addParticle(ParticleTypes.FLAME, true, entity.getX() + (Math.random() - 0.5), entity.getY() + (entity.getBbHeight()/2) + (Math.random() - 0.5), entity.getZ() + (Math.random() - 0.5), (Math.random() - 0.5D) * 0.2, (Math.random() - 0.5D) * 0.2, (Math.random() - 0.5D) * 0.2);
    //         }
    //
    //         player.level().playLocalSound(entity.getX(), entity.getY(), entity.getZ(), SoundEvents.TOTEM_USE, entity.getSoundSource(), 1.0F, 1.0F, false);
    //
    //         if (entity == player) {
    //             ItemStack stack = SuperpositionHandler.getCurioStack(player, item);
    //
    //             if (stack == null) {
    //                 stack = new ItemStack(item, 1);
    //             }
    //
    //             Minecraft.getInstance().gameRenderer.displayItemActivation(stack);
    //         }
    //     }
    // }

    //@Override
    //public void handleItemPickup(int pickuper_id, int item_id) {
    //    try {
    //        Entity pickuper = Minecraft.getInstance().level.getEntity(pickuper_id);
    //        Entity entity = Minecraft.getInstance().level.getEntity(item_id);
    //
    //        // TODO Verify fix... someday
    //
    //        Minecraft.getInstance().particleEngine.add(new PermanentItemPickupParticle(Minecraft.getInstance().getEntityRenderDispatcher(), Minecraft.getInstance().renderBuffers(), Minecraft.getInstance().level, pickuper, entity));
    //        Minecraft.getInstance().level.playLocalSound(pickuper.getX(), pickuper.getY(), pickuper.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, (ClientProxy.RANDOM.nextFloat() - ClientProxy.RANDOM.nextFloat()) * 1.4F + 2.0F, false);
    //    } catch (Throwable ex) {
    //        EnigmaticLegacy.LOGGER.error("Unknown error when rendering permanent item pickup", ex);
    //    }
    //}

    // @SubscribeEvent
    // @OnlyIn(Dist.CLIENT)
    // public void addLayers(EntityRenderersEvent.AddLayers evt) {
    //     this.addPlayerLayer(evt, "default");
    //     this.addPlayerLayer(evt, "slim");
    //     this.addEntityLayer(evt, EntityType.ARMOR_STAND);
    // }

    // @OnlyIn(Dist.CLIENT)
    // @SuppressWarnings({"rawtypes", "unchecked"})
    // private void addPlayerLayer(EntityRenderersEvent.AddLayers evt, String skin) {
    //     EntityRenderer<? extends PlayerEntity> renderer = evt.getSkin(skin);
    //
    //     if (renderer instanceof LivingEntityRenderer livingRenderer) {
    //         livingRenderer.addLayer(new EnigmaticElytraLayer(livingRenderer, evt.getEntityModels()));
    //         livingRenderer.addLayer(new ShieldAuraLayer(livingRenderer, evt.getEntityModels()));
    //     }
    // }

    // TODO fix this once the MajesticElytra is added
    // @OnlyIn(Dist.CLIENT)
    // private <T extends LivingEntity, M extends HumanoidModel<T>, R extends LivingEntityRenderer<T, M>> void addEntityLayer(EntityRenderersEvent.AddLayers evt, EntityType<? extends T> entityType) {
    //     R renderer = evt.getRenderer(entityType);
    //
    //     if (renderer != null) {
    //         renderer.addLayer(new EnigmaticElytraLayer<>(renderer, evt.getEntityModels()));
    //     }
    // }

    // TODO fix this once once added Infinitium or InfernalShield
    // @OnlyIn(Dist.CLIENT)
    // public void onClientSetup(FMLClientSetupEvent event) {
    //     ItemProperties.register(EnigmaticItems.INFERNAL_SHIELD, new ResourceLocation("blocking"),
    //             (stack, world, entity, seed) -> entity != null && entity.isUsingItem()
    //                     && entity.getUseItem() == stack ? 1 : 0);
    //
    //     ItemProperties.register(EnigmaticItems.THE_INFINITUM, new ResourceLocation(EnigmaticLegacy.MODID, "the_infinitum_open"), (stack, world, entity, seed) -> {
    //         if (entity instanceof PlayerEntity player) {
    //             for (InfinitumCounterEntry entry : this.theInfinitumHoldTicks) {
    //                 if (entry.getPlayer() == player && entry.getStack() == stack)
    //                     return entry.animValue;
    //             }
    //
    //             ItemStack mainhand = player.getMainHandStack();
    //             ItemStack offhand = player.getOffHandStack();
    //
    //             if (mainhand == stack || offhand == stack)
    //                 if (SuperpositionHandler.isTheWorthyOne(player)) {
    //                     this.theInfinitumHoldTicks.add(new InfinitumCounterEntry(player, stack));
    //                 }
    //         }
    //
    //         return 0;
    //     });
    //
    //     BlockEntityRenderers.register(EnigmaticTiles.END_ANCHOR, EndAnchorRenderer::new);
    // }

    @Override
    public void updateInfinitumCounters() {
        for (InfinitumCounterEntry entry : new ArrayList<>(this.theInfinitumHoldTicks)) {
            if (entry.getPlayer() == null || entry.getStack() == null) {
                this.theInfinitumHoldTicks.remove(entry);
                continue;
            }

            ItemStack stack = entry.getStack();
            PlayerEntity player = entry.getPlayer();
            int holdTicks = entry.counter;
            ItemStack mainhand = player.getMainHandStack();
            ItemStack offhand = player.getOffHandStack();

            if (mainhand == stack || offhand == stack)
                if (SuperpositionHandler.isTheWorthyOne(player)) {
                    if (entry.counter > 5) {
                        entry.animValue = 1F;
                    } else if (entry.counter == 5) {
                        entry.animValue = 0.5F;
                    } else {
                        entry.animValue = 0F;
                    }

                    entry.counter++;
                    continue;
                }

            if (entry.counter <= 0) {
                this.theInfinitumHoldTicks.remove(entry);
            } else {
                if (entry.counter > 5) {
                    entry.counter = 5;
                }

                if (entry.counter > 1) {
                    entry.animValue = 1F;
                } else if (entry.counter == 1) {
                    entry.animValue = 0.5F;
                } else {
                    entry.animValue = 0F;
                }

                entry.counter--;

            }
        }
    }

    // TODO fix this once the Renderer is up
    // @Override
    // public void initEntityRendering() {
    //     EntityRenderers.register(EnigmaticEntities.PERMANENT_ITEM_ENTITY, renderManager -> new PermanentItemRenderer(renderManager, Minecraft.getInstance().getItemRenderer()));
    //     EntityRenderers.register(EnigmaticEntities.ENIGMATIC_POTION, ThrownItemRenderer::new);
    //     EntityRenderers.register(EnigmaticEntities.ULTIMATE_WITHER_SKULL, UltimateWitherSkullRenderer::new);
    // }

    @Override
    public boolean isInVanillaDimension(PlayerEntity player) {
        return player.getWorld().getRegistryKey().equals(this.getOverworldKey()) || player.getWorld().getRegistryKey().equals(this.getNetherKey()) || player.getWorld().getRegistryKey().equals(this.getEndKey());
    }

    @Override
    public boolean isInDimension(PlayerEntity player, RegistryKey<World> world) {
        return player.getWorld().getRegistryKey().equals(world);
    }

    // @Override
    // public World getCentralWorld() {
    //     return Minecraft.getInstance().level;
    // }

    // @Override
    // public UseAction getVisualBlockAction() {
    //     return UseAction.BLOCK;
    // }

    // TODO my brain too stupid to comprehend what this does - Leg
    // @Override
    // public void pushRevelationToast(ItemStack renderedStack, int xp, int knowledge) {
    //     ToastComponent gui = Minecraft.getInstance().getToasts();
    //     gui.addToast(new RevelationTomeToast(renderedStack, xp, knowledge));
    // }

    @Override
    @SuppressWarnings("deprecation")
    public void spawnBonemealParticles(World world, BlockPos pos, int data) {
        if (data == 0) {
            data = 15;
        }

        BlockState blockstate = world.getBlockState(pos);
        if (!blockstate.isAir()) {
            double d0 = 0.5D;
            double d1;
            if (blockstate.isOf(Blocks.WATER)) {
                data *= 3;
                d1 = 1.0D;
                d0 = 3.0D;
            } else if (blockstate.isSolidBlock(world, pos)) {
                pos = pos.up();
                data *= 3;
                d0 = 3.0D;
                d1 = 1.0D;
            } else {
                d1 = blockstate.getCollisionShape(world, pos).getMax(Direction.Axis.Y);
            }

            world.addParticle(ParticleTypes.HAPPY_VILLAGER, pos.getX() + 0.5D, pos.getY() + 0.5D, pos.getZ() + 0.5D, 0.0D, 0.0D, 0.0D);

            for(int i = 0; i < data; ++i) {
                double d2 = RANDOM.nextGaussian() * 0.02D;
                double d3 = RANDOM.nextGaussian() * 0.02D;
                double d4 = RANDOM.nextGaussian() * 0.02D;
                double d5 = 0.5D - d0;
                double d6 = pos.getX() + d5 + RANDOM.nextDouble() * d0 * 2.0D;
                double d7 = pos.getY() + RANDOM.nextDouble() * d1;
                double d8 = pos.getZ() + d5 + RANDOM.nextDouble() * d0 * 2.0D;

                world.addParticle(ParticleTypes.HAPPY_VILLAGER, d6, d7, d8, d2, d3, d4);
            }

        }
    }

    // @Override
    // public String getClientUsername() {
    //     return Minecraft.getInstance().getUser().getName();
    // }

    private static class InfinitumCounterEntry {
        private final WeakReference<ItemStack> stack;
        private final WeakReference<PlayerEntity> player;
        private int counter = 0;
        private float animValue = 0F;

        public InfinitumCounterEntry(PlayerEntity player, ItemStack stack) {
            this.player = new WeakReference<>(player);
            this.stack = new WeakReference<>(stack);
        }

        public PlayerEntity getPlayer() {
            return this.player.get();
        }

        public ItemStack getStack() {
            return this.stack.get();
        }
    }

}

