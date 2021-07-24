package org.openrsc.editor.model.data;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GameObjectType {
    SCENERY("Scenery", 0),
    BOUNDARY("Boundary", 1);

    private final String label;
    @JsonValue
    private final int id;

    public static GameObjectType fromInt(int value) {
        return value == SCENERY.getId() ? SCENERY : BOUNDARY;
    }

    @Override
    public String toString() {
        return this.getLabel();
    }
}
