package it.unicam.cs.mpgc.rpg125861.persistence;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonGameRepository implements GameRepository {

    private final Path saveFile = Path.of("savegame.json");

    @Override
    public void save(GameSaveData gameSaveData) throws IOException {
        JSONObject jsonObject = new JSONObject();

        jsonObject.put("heroName", gameSaveData.getHeroName());
        jsonObject.put("heroHealth", gameSaveData.getHeroHealth());
        jsonObject.put(
                "currentMonsterIndex",
                gameSaveData.getCurrentMonsterIndex()
        );

        JSONArray monsterHealths = new JSONArray();
        monsterHealths.addAll(gameSaveData.getMonsterHealths());
        jsonObject.put("monsterHealths", monsterHealths);

        try (Writer writer = Files.newBufferedWriter(saveFile)) {
            writer.write(jsonObject.toJSONString());
        }
    }

    @Override
    public boolean hasSave() {
        return Files.exists(saveFile);
    }

    @Override
    public GameSaveData load() throws IOException {
        Object parsedObject;

        try (Reader reader = Files.newBufferedReader(saveFile)) {
            parsedObject = new JSONParser().parse(reader);
        } catch (ParseException exception) {
            throw new IOException("The save file is not valid JSON.", exception);
        }

        if (!(parsedObject instanceof JSONObject)) {
            throw new IOException("The save file must contain a JSON object.");
        }

        JSONObject jsonObject = (JSONObject) parsedObject;

        GameSaveData gameSaveData = new GameSaveData();
        gameSaveData.setHeroName((String) jsonObject.get("heroName"));
        gameSaveData.setHeroHealth(
                ((Number) jsonObject.get("heroHealth")).intValue()
        );
        gameSaveData.setCurrentMonsterIndex(
                ((Number) jsonObject.get("currentMonsterIndex")).intValue()
        );

        JSONArray monsterHealths = (JSONArray) jsonObject.get("monsterHealths");
        List<Integer> healthValues = new ArrayList<>();

        for (Object health : monsterHealths) {
            healthValues.add(((Number) health).intValue());
        }

        gameSaveData.setMonsterHealths(healthValues);
        return gameSaveData;
    }
}