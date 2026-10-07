package it.unicam.cs.mpgc.rpg125861.persistence;

import java.io.IOException;

public interface GameRepository {

    void save(GameSaveData gameSaveData) throws IOException;

    boolean hasSave();

    GameSaveData load() throws IOException;
}