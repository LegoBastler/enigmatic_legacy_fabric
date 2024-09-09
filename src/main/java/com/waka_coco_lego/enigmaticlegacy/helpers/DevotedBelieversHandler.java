package com.waka_coco_lego.enigmaticlegacy.helpers;

import static com.waka_coco_lego.enigmaticlegacy.EnigmaticLegacy.LOGGER;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;
import java.util.regex.Pattern;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.player.PlayerEntity;
import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public final class DevotedBelieversHandler {
    private static final Map<String, UUID> DEVOTED_BELIEVERS = loadDevotedBelievers();

    public static Map<String, UUID> getDevotedBelievers() {
        return DEVOTED_BELIEVERS;
    }

    public static boolean isDevotedBeliever(PlayerEntity player) {
        return isDevotedBeliever(player.getUuid());
    }

    public static boolean isDevotedBeliever(UUID playerID) {
        return DEVOTED_BELIEVERS.containsValue(playerID);
    }

    private static Map<String, UUID> loadDevotedBelievers() {
        try {
            var map = fetchDevotedBelievers();
            LOGGER.info("Successfully fetched the list of devoted believers from Enigmatic Legacy's repository.");

            try {
                File backup = new File(FabricLoader.getInstance().getGameDir().toFile().getCanonicalFile(), "enigmatic_persistence.dat");
                backupDevotedBelievers(map, backup);
                LOGGER.info("Successfully backed up the list of devoted believers.");
            } catch (IOException ex) {
                LOGGER.error("Encountered exception when trying to backup devoted believers list: ", ex);
            }

            return map;
        } catch (IOException ex) {
            LOGGER.error("Encountered exception when trying to fetch devoted believers from Enigmatic Legacy's repository: ", ex);

            try {
                File backup = new File(FabricLoader.getInstance().getGameDir().toFile().getCanonicalFile(), "enigmatic_persistence.dat");

                if (backup.exists()) {
                    LOGGER.info("Will try to restore the list from local backup...");

                    var map = restoreDevotedBelievers(backup);
                    LOGGER.info("Successfully restored the list from local backup.");
                    return map;
                } else {
                    LOGGER.error("No backup found.");
                    return new HashMap<>();
                }
            } catch (IOException anotherEx) {
                LOGGER.error("Restoration failed with exception: ", ex);

                LOGGER.error("We'll have to live without knowing the names of devoted believers for now.");
                return new HashMap<>();
            }
        }
    }

    private static Map<String, UUID> restoreDevotedBelievers(File file) throws IOException {
        List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
        Map<String, UUID> map = new HashMap<>();

        try {
            lines.forEach(line -> {
                char[] chars = line.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    chars[i] = (char)(chars[i] - 2 - (i % 4));
                }

                line = new String(chars);

                String[] splat = line.split(Pattern.quote(":"));
                String name = splat[0];
                UUID uuid = UUID.fromString(splat[1]);
                map.put(name, uuid);
            });
        } catch (Exception ex) {
            throw new RuntimeException("File " + file + " seems to have been corrupted! Try to delete it and restart the game.");
        }

        return map;
    }

    private static void backupDevotedBelievers(Map<String, UUID> map, File file) throws IOException {
        StringBuilder backup = new StringBuilder(1000);

        map.forEach((key, value) -> {
            if (backup.length() > 0) {
                backup.append(System.lineSeparator());
            }

            String line = key + ":" + value;
            char[] chars = line.toCharArray();

            for (int i = 0; i < chars.length; i++) {
                chars[i] = (char) (chars[i] + 2 + (i % 4));
            }

            backup.append(chars);
        });

        FileUtils.writeStringToFile(file, backup.toString(), StandardCharsets.UTF_8);
    }

    private static Map<String, UUID> fetchDevotedBelievers() throws IOException {
        try {
            String defaultBranch = fetchDefaultBranch();
            URL url = new URL("https://raw.githubusercontent.com/Aizistral-Studios/Enigmatic-Legacy/" + defaultBranch + "/devoted_believers.json");
            StringBuilder response = new StringBuilder(1000);

            try (Scanner scanner = new Scanner(url.openStream())) {
                while(scanner.hasNext()) {
                    response.append(scanner.next());
                }
            }

            Gson gson = new Gson();
            Map<String, String> map = gson.fromJson(response.toString(), Map.class);
            Map<String, UUID> finalMap = new HashMap<>();

            map.forEach((key, value) -> finalMap.put(key, UUID.fromString(value)));
            return finalMap;
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Impossible!", ex);
        }
    }

    private static String fetchDefaultBranch() throws IOException {
        try {
            URL url = new URL("https://api.github.com/repos/Aizistral-Studios/Enigmatic-Legacy");
            StringBuilder response = new StringBuilder(10000);

            try (Scanner scanner = new Scanner(url.openStream())) {
                while(scanner.hasNext()) {
                    response.append(scanner.next());
                }
            }

            JsonObject obj = JsonParser.parseString(response.toString()).getAsJsonObject();
            return obj.get("default_branch").getAsString();
        } catch (MalformedURLException ex) {
            throw new RuntimeException("Impossible!", ex);
        }
    }

}

