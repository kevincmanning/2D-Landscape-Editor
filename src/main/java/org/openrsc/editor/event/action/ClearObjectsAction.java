package org.openrsc.editor.event.action;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.openrsc.editor.model.SelectRegion;

@Builder
@Getter
public class ClearObjectsAction {
    private final SelectRegion selectRegion;
    private final Type objectType;

    @AllArgsConstructor
    @Getter
    public enum Type {
        SCENERY("Scenery"),
        BOUNDARY("Boundary"),
        GROUND_ITEM("Ground Item"),
        NPC("Npc"),
        ALL("All");

        private final String label;

        @Override
        public String toString() {
            return label;
        }
    }
}
