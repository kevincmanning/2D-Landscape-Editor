package org.openrsc.editor.model.data;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Singular;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;
import java.util.List;

@Jacksonized
@Builder(toBuilder = true)
@Getter
public class NpcLoc {
    private final int id;
    private final Point max;
    private final Point min;
    private final Point start;
    @Singular(value = "patrolPoint")
    private final List<Point> patrolRoute;

    @Data
    public static class Wrapper {
        private Collection<NpcLoc> npclocs;
    }
}
