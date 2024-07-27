package com.waka_coco_lego.enigmaticlegacy.helpers;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemLoreHelper {

    // public static void indicateCursedOnesOnly(List<Text> list) {
    //     Formatting format;
    //     if (Minecraft.getInstance().player != null) {
    //         format = SuperpositionHandler.isTheCursedOne(Minecraft.getInstance().player) ? Formatting.GOLD : Formatting.DARK_RED;
    //     } else {
    //         format = Formatting.DARK_RED;
    //     }
    //     list.add(Text.translatable("tooltip.enigmaticlegacy.cursedOnesOnly1").formatted(format));
    //     list.add(Text.translatable("tooltip.enigmaticlegacy.cursedOnesOnly2").formatted(format));
    // }

    // public static void indicateWorthyOnesOnly(List<Text> list) {
    //     Formatting format = Formatting.DARK_RED;
    //     Player player = Minecraft.getInstance().player;
    //     if (player != null) {
    //         format = SuperpositionHandler.isTheWorthyOne(Minecraft.getInstance().player) ? Formatting.GOLD : Formatting.DARK_RED;
    //     }
    //     list.add(Text.translatable("tooltip.enigmaticlegacy.worthyOnesOnly1"));
    //     list.add(Text.translatable("tooltip.enigmaticlegacy.worthyOnesOnly2"));
    //     list.add(Text.translatable("tooltip.enigmaticlegacy.worthyOnesOnly3"));
    //     list.add(Text.translatable("tooltip.enigmaticlegacy.void"));
    //     list.add(Text.translatable("tooltip.enigmaticlegacy.worthyOnesOnly4").formatted(format).append(Text.literal(" " + SuperpositionHandler.getSufferingTime(player)).withStyle(Formatting.LIGHT_PURPLE)));
    // }

    // public static void indicateBlessedOnesOnly(List<Text> list) {
    //     Formatting format;
    //     if (EnigmaticLegacy.PROXY.getClientPlayer() != null) {
    //         format = SuperpositionHandler.isTheBlessedOne(EnigmaticLegacy.PROXY.getClientPlayer()) ? Formatting.GOLD : Formatting.DARK_RED;
    //     } else {
    //         format = Formatting.DARK_RED;
    //     }
    //     list.add(Text.translatable("tooltip.enigmaticlegacy.blessedOnesOnly1").formatted(format));
    //     list.add(Text.translatable("tooltip.enigmaticlegacy.blessedOnesOnly2").formatted(format));
    // }

    public static void addLocalizedFormattedString(List<Text> list, String str, Formatting format) {
        list.add(Text.translatable(str).formatted(format));
    }

    public static void addLocalizedString(List<Text> list, String str) {
        list.add(Text.translatable(str));
    }

    public static void addLocalizedString(List<Text> list, String str, @Nullable Formatting format, Object... values) {
        Text[] stringValues = new Text[values.length];

        int counter = 0;
        for (Object value : values) {
            MutableText comp;

            if (value instanceof MutableText) {
                comp = (MutableText)value;
            } else {
                comp = Text.literal(value.toString());
            }

            if (format != null) {
                comp.formatted(format);
            }

            stringValues[counter] = comp;
            counter++;
        }

        list.add(Text.translatable(str, (Object[])stringValues));
    }

    // public static ItemStack mergeDisplayData(ItemStack from, ItemStack to) {
    //     CompoundTag nbt = from.getOrCreateTagElement("display");
    //     ListTag loreList = nbt.getList("Lore", 8).size() > 0 ? nbt.getList("Lore", 8) : to.getOrCreateTagElement("display").getList("Lore", 8);
    //     StringTag displayName = nbt.getString("Name").length() > 0 ? StringTag.valueOf(nbt.getString("Name")) : StringTag.valueOf(to.getOrCreateTagElement("display").getString("Name"));
    //     CompoundTag mergedData = new CompoundTag();
    //     mergedData.put("Lore", loreList.copy());
    //     mergedData.put("Name", displayName.copy());
    //     to.getOrCreateTag().put("display", mergedData);
    //     return to;
    // }

    // public static ItemStack addLoreString(ItemStack stack, String string) {
    //     CompoundTag nbt = stack.getOrCreateTagElement("display");
    //     ListTag loreList = nbt.getList("Lore", 8);
    //     loreList.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(string))));
    //     nbt.put("Lore", loreList);
    //     return stack;
    // }

    // public static ItemStack setLoreString(ItemStack stack, String string, int index) {
    //     CompoundTag nbt = stack.getOrCreateTagElement("display");
    //     ListTag loreList = nbt.getList("Lore", 8);
    //     if (loreList.size() - 1 >= index) {
    //         loreList.set(index, StringTag.valueOf(Component.Serializer.toJson(Component.literal(string))));
    //     } else {
    //         loreList.add(StringTag.valueOf(Component.Serializer.toJson(Component.literal(string))));
    //     }
    //     nbt.put("Lore", loreList);
    //     return stack;
    // }

    // public static ItemStack removeLoreString(ItemStack stack, int index) {
    //     CompoundTag nbt = stack.getOrCreateTagElement("display");
    //     ListTag loreList = nbt.getList("Lore", 8);
    //     if (loreList.size() > 0) {
    //         if (index == -1) {
    //             loreList.remove(loreList.size() - 1);
    //         } else if (loreList.size() - 1 >= index) {
    //             loreList.remove(index);
    //         }
    //     }
    //     nbt.put("Lore", loreList);
    //     return stack;
    // }

    // public static ItemStack setLastLoreString(ItemStack stack, String string) {
    //     CompoundTag nbt = stack.getOrCreateTagElement("display");
    //     ListTag loreList = nbt.getList("Lore", 8);
    //     if (loreList.size() > 0) {
    //         loreList.set(loreList.size() - 1, StringTag.valueOf(Component.Serializer.toJson(Text.literal(string))));
    //     } else {
    //         loreList.add(StringTag.valueOf(Component.Serializer.toJson(Text.literal(string))));
    //     }
    //     nbt.put("Lore", loreList);
    //     return stack;
    // }

    // public static ItemStack setDisplayName(ItemStack stack, String name) {
    //     CompoundTag nbt = stack.getOrCreateTagElement("display");
    //     nbt.putString("Name", Component.Serializer.toJson(Text.literal(name)));
    //     return stack;
    // }

}
