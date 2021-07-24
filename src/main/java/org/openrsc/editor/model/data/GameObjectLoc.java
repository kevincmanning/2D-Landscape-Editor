package org.openrsc.editor.model.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;

@Getter
@Jacksonized
@Builder(toBuilder = true)
public class GameObjectLoc {
    private final int direction;
    private final int id;
    private final GameObjectType type;
    @JsonProperty("pos")
    private final Point location;

    @JsonInclude(value = JsonInclude.Include.NON_NULL)
    private final String owner;

    @Data
    public static class SceneryWrapper {
        private Collection<GameObjectLoc> sceneries;
    }

    @Data
    public static class BoundaryWrapper {
        private Collection<GameObjectLoc> boundaries;
    }
}
