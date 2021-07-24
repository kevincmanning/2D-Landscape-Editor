package org.openrsc.editor;

import org.openrsc.editor.event.TerrainTemplateUpdateEvent;
import org.openrsc.editor.model.Tile;
import org.openrsc.editor.model.template.TerrainProperty;
import org.openrsc.editor.model.template.TerrainTemplate;

import java.util.HashMap;
import java.util.Map;

public class TemplateUtil {
    public static TerrainTemplate merge(
            TerrainTemplate currentTemplate,
            TerrainTemplateUpdateEvent updateEvent
    ) {
        Map<TerrainProperty, Integer> values;
        TerrainTemplate.TerrainTemplateBuilder builder;
        if (currentTemplate != null) {
            values = new HashMap<>(currentTemplate.getValues());
            builder = currentTemplate.toBuilder();
        } else {
            values = new HashMap<>(updateEvent.getTemplate().getValues());
            builder = TerrainTemplate.builder();
        }

        updateEvent.getTemplate().getValues().forEach((key, value) -> {
            if (value != null) {
                values.put(key, value);
            } else {
                values.remove(key);
            }
        });
        builder.clearValues();
        builder.values(values);
        return builder.build();
    }

    public static double getResolvedIntensity(double distanceFromOrigin, double brushIntensity) {
        return Math.max(brushIntensity, 1 - distanceFromOrigin) * brushIntensity;
    }

    public static void applyBrush(Tile tile, TerrainTemplate template) {
        applyBrush(tile, template, 0, 1.0);
    }

    public static void applyBrush(Tile tile, TerrainTemplate template, double distance, double intensity) {
        double weight = getResolvedIntensity(
                distance,
                intensity
        );
        if (tile != null && template != null) {
            template.getValues().forEach((property, value) -> {
                switch (property) {
                    case GROUND_TEXTURE:
                        tile.setGroundTexture(
                                weightValue(tile.getGroundTextureInt(), value, weight).byteValue()
                        );
                        break;
                    case GROUND_OVERLAY:
                        tile.setGroundOverlay(
                                weightValue(tile.getGroundOverlayInt(), value, weight).byteValue()
                        );
                        break;
                    case WALL_DIAGONAL:
                        tile.setDiagonalWalls(value);
                        break;
                    case WALL_EAST:
                        tile.setEastWall(value.byteValue());
                        break;
                    case WALL_NORTH:
                        tile.setNorthWall(value.byteValue());
                        break;
                    case GROUND_ELEVATION:
                        tile.setGroundElevation(
                                weightValue(tile.getGroundElevationInt(), value, weight).byteValue()
                        );
                        break;
                    case ROOF_TEXTURE:
                        tile.setRoofTexture(value.byteValue());
                        break;
                }
            });
            Util.sectorModified = true;
        }
    }

    public static Integer weightValue(int previousValue, int updatedValue, double weight) {
        return (int) ((previousValue * (1 - weight)) + (updatedValue * weight));
    }
}
