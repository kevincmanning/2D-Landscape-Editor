package org.openrsc.editor.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;


@Builder
@Jacksonized
public class Point {
    @JsonProperty("X")
    private final int x;
    @JsonProperty("Y")
    private final int y;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Point)) {
            return false;
        }
        Point other = (Point) obj;
        return other.x == x && other.y == y;
    }

    @Override
    public int hashCode() {
        return (x << 16) | y;
    }

    public java.awt.Point toAWT() {
        return new java.awt.Point(x, y);
    }

    public static Point fromAWT(java.awt.Point point) {
        return new Point(point.x, point.y);
    }
}
