package org.openrsc.editor.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;

@Getter
@Builder(toBuilder = true)
@Jacksonized
public class ItemLoc {
    private final int amount;
    private final int id;
    private final int respawn;
    @JsonProperty("pos")
    private final Point location;

    @Data
    public static class Wrapper {
        private Collection<ItemLoc> grounditems;
    }
}
