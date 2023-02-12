package org.example.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.spy;

public class StoreImplTest {

    public StoreImpl store;

    private Map<String, Integer> expectedPositions = new HashMap<>();

    @BeforeEach
    public void setUp() {
        store = spy(StoreImpl.class);
    }


    @Test
    public void setPositionTest() {

        store.setPosition("merch1", 1);
        store.setPosition("merch2", 1);


        expectedPositions.put("merch1", 1);
        expectedPositions.put("merch2", 1);

        assertEquals(store.getPositions(), expectedPositions);
    }

    @Test
    public void removePositionTest() {
        store.setPosition("merch1", 1);
        store.setPosition("merch2", 1);

        store.removePosition("merch2");

        expectedPositions.put("merch1", 1);

        assertEquals(store.getPositions(), expectedPositions);
    }


}

