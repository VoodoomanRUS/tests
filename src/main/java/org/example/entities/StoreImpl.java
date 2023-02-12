package org.example.entities;

import lombok.Getter;
import org.example.interfaces.Store;

import java.util.HashMap;
import java.util.Map;

@Getter
public class StoreImpl implements Store {

    private Map<String, Integer> positions = new HashMap<>();


    @Override
    public void setPosition(String merchName, Integer unitsCount) {
        if (unitsCount > 0) {
            positions.put(merchName, unitsCount);
        }
    }

    @Override
    public void removePosition(String merchName) {
        positions.remove(merchName);
    }
}
