package org.openrsc.editor.model.map;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Builder
@Getter
@EqualsAndHashCode
public class Point3D implements Comparable<Point3D> {
    private final int x;
    private final int y;
    private final int z;

    @Override
    public int compareTo(Point3D other) {
        Integer z = getZ();
        Integer z2 = other.getZ();
        if (!z.equals(z2)) {
            return z.compareTo(z2);
        }

        Integer x = getX();
        Integer x2 = other.getX();
        if (!x.equals(x2)) {
            return x.compareTo(x2);
        }

        Integer y = getY();
        Integer y2 = other.getY();
        return y.compareTo(y2);
    }
}
