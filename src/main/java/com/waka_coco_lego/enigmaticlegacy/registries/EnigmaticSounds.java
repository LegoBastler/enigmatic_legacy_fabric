package com.waka_coco_lego.enigmaticlegacy.registries;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class EnigmaticSounds {

    public static final Identifier CHARGED_ON_ID = Identifier.of("enigmaticlegacy:misc.hhon");
    public static final Identifier CHARGED_OFF_ID = Identifier.of("enigmaticlegacy:misc.hhoff");
    public static final Identifier SHIELD_TRIGGER_ID = Identifier.of("enigmaticlegacy:misc.shield_trigger");
    public static final Identifier DEFLECT_ID = Identifier.of("enigmaticlegacy:misc.deflect");
    public static final Identifier WRITE_ID = Identifier.of("enigmaticlegacy:misc.write");
    public static final Identifier LEARN_ID = Identifier.of("enigmaticlegacy:misc.learn");
    public static final Identifier SWORD_HIT_REJECT_ID = Identifier.of("enigmaticlegacy:misc.sword_hit_reject");
    public static final Identifier EAT_REVERSE_ID = Identifier.of("enigmaticlegacy:misc.uneat");
    public static final Identifier PAN_CLANG_ID = Identifier.of("enigmaticlegacy:misc.pan_clang");
    public static final Identifier PAN_CLANG_FR_ID = Identifier.of("enigmaticlegacy:misc.pan_clang_fr");

    public static SoundEvent CHARGED_ON = SoundEvent.of(CHARGED_ON_ID);
    public static SoundEvent CHARGED_OFF = SoundEvent.of(CHARGED_OFF_ID);
    public static SoundEvent SHIELD_TRIGGER = SoundEvent.of(SHIELD_TRIGGER_ID);
    public static SoundEvent DEFLECT = SoundEvent.of(DEFLECT_ID);
    public static SoundEvent WRITE = SoundEvent.of(WRITE_ID);
    public static SoundEvent LEARN = SoundEvent.of(LEARN_ID);
    public static SoundEvent SWORD_HIT_REJECT = SoundEvent.of(SWORD_HIT_REJECT_ID);
    public static SoundEvent EAT_REVERSE = SoundEvent.of(EAT_REVERSE_ID);
    public static SoundEvent PAN_CLANG = SoundEvent.of(PAN_CLANG_ID);
    public static SoundEvent PAN_CLANG_FR = SoundEvent.of(PAN_CLANG_FR_ID);

}
