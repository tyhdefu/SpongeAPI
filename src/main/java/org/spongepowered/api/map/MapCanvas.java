/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.map;

import com.flowpowered.math.vector.Vector2i;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.persistence.DataBuilder;
import org.spongepowered.api.data.persistence.InvalidDataException;
import org.spongepowered.api.map.color.MapColor;
import org.spongepowered.api.map.color.MapColorTypes;

import java.awt.Image;
import java.util.Optional;

/**
 * Represents the canvas of a {@link org.spongepowered.api.item.inventory.ItemStack}
 * with {@link org.spongepowered.api.item.ItemType} {@link org.spongepowered.api.item.ItemTypes#FILLED_MAP}
 * Changes to maps will not apply until the client relogs
 */
public interface MapCanvas extends DataSerializable {

    static Builder builder() {
        return Sponge.getRegistry().createBuilder(Builder.class);
    }

    /**
     * Gets the map color at a location on the canvas
     * @param x int x location
     * @param y int y location
     * @return MapColor color at this location
     */
    MapColor getColor(int x, int y);

    /**
     * Gets an blank canvas (Populated with {@link MapColorTypes#AIR})
     * @return MapCanvas that is blank
     */
    static MapCanvas blank() {
        return builder().build();
    }

    /**
     * Creates an image from this MapCanvas
     * @return Image from the canvas
     */
    Image toImage();

    /**
     * Creates an image and replaces all {@link MapColorTypes#AIR}
     * with the specified color.
     * @param color to replace the air MapColorType
     * @return Image from the canvas
     */
    Image toImage(java.awt.Color color);

    Builder toBuilder(MapCanvas canvas);

    interface Builder extends DataBuilder<MapCanvas> {
        /**
         * Paints the whole canvas a certain {@link MapColor}
         * @param color to paint whole canvas
         * @return Builder, for chaining
         */
        Builder paintAll(MapColor color);

        /**
         * Paint a specified region a specified {@link MapColor}
         * All values are inclusive
         * @param startX int X start pos
         * @param startY int Y start pos
         * @param endX int X end pos
         * @param endY int Y end pos
         * @param mapColor MapColor to paint this region
         * @return This Builder, for chaining
         */
        Builder paint(int startX, int startY, int endX, int endY, MapColor mapColor);

        /**
         * Paint a specified region a specified {@link MapColor}
         * @param start Vector2i Start coordinate
         * @param end Vector2i End coordinate
         * @param mapColor MapColor to paint this region
         * @return This Builder, for chaining
         */
        default Builder paint(Vector2i start, Vector2i end, MapColor mapColor) {
            return paint(start.getX(), end.getX(), start.getY(), end.getY(), mapColor);
        }

        /**
         * Creates a builder from the specified canvas
         * @param canvas to create builder from
         * @return Builder, for chaining
         */
        Builder from(MapCanvas canvas);

        /**
         * Fills a canvas from an image
         * @param image to take from
         * @return This Builder, for chaining
         * @throws IllegalStateException if width/height is wrong or not known yet
         * @throws IllegalStateException if color found is not in the palette {@link MapColorTypes}
         */
        Builder fromImage(Image image) throws IllegalStateException;

        @Override
        Optional<MapCanvas> build(DataView container) throws InvalidDataException;

        MapCanvas build();
    }
}
