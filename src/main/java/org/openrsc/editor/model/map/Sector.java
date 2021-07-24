package org.openrsc.editor.model.map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.openrsc.editor.model.Tile;

@Builder(toBuilder = true)
@Getter
public class Sector implements Comparable<Sector> {
    private final Point3D location;

    @Setter
    private Tile[][] tiles;

    public String getName() {
        return "h" + location.getZ() + "x" + location.getX() + "y" + location.getY();
    }


    // Match the zip file so that we go in order
    @Override
    public int compareTo(Sector other) {
        return location.compareTo(other.getLocation());
    }
}
