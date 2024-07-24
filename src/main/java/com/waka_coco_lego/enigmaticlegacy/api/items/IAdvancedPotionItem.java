package com.waka_coco_lego.enigmaticlegacy.api.items;

public interface IAdvancedPotionItem {

    public enum PotionType {
        COMMON, ULTIMATE;
    }

    public PotionType getPotionType();

}

