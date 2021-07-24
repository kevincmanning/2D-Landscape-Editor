package org.openrsc.editor.model.map;

import lombok.Data;

@Data
public class Point {
    private final int x;
    private final int y;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Point) {
            Point other = (Point) obj;
            return other.getX() == x && other.getY() == getY();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (x << 16) | y;
    }
}
