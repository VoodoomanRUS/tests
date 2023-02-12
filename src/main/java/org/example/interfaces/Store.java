package org.example.interfaces;

import java.io.IOException;

public interface Store {
    void setPosition(String merchName, Integer count) throws IOException;
    void removePosition(String merchName);
}
