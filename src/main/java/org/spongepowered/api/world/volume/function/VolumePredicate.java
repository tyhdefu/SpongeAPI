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
package org.spongepowered.api.world.volume.function;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.world.volume.Volume;
import org.spongepowered.api.world.volume.block.ReadableBlockVolume;

/**
 * A multi-argument {@link java.util.function.Predicate} that utilizes
 * extra parameters such as the {@link Volume} being used, and the
 * cartesian {@code int} coordinates of the element existing to possibly
 * filter. Used in {@link org.spongepowered.api.world.volume.stream.VolumeStream VolumeStreams}
 * much like in a traditional {@link java.util.stream.Stream} uses a
 * {@link java.util.function.Predicate}.
 *
 * @param <V> The volume type
 * @param <I> The input type
 */
@FunctionalInterface
public interface VolumePredicate<V extends Volume, I> {

    /**
     * Evaluates this predicate on the provided {@link Volume volume},
     * {@code I} element and cartesian coordinates within the volume.
     *
     * @param volume The volume
     * @param element The element found at the coordinates
     * @param x The x block position
     * @param y The y block position
     * @param z The z block position
     * @return True if this predicate is satisfied, otherwise false
     */
    boolean test(V volume, I element, int x, int y, int z);

    /**
     * Joins this predicate with the provided predicate in an "and" fashion
     * where both this and the provided predicate must evaluate to {@code true}
     * to return {@code true}.
     *
     * @param other The other predicate
     * @return The new predicate joining both this and the other
     */
    default VolumePredicate<V, I> and(VolumePredicate<V, ? super I> other) {
        return (volume, element, x, y, z) -> test(volume, element, x, y, z) && other.test(volume, element, x, y, z);
    }

    /**
     * Negates this predicate as a new predicate to the inverse of
     * this predicate where if this predicate were to return {@code true},
     * the new predicate will return {@code false} and vice versa.
     *
     * @return The negated predicate of this predicate
     */
    default VolumePredicate<V, I> negate() {
        return (v, i, x, y, z) -> !test(v, i, x, y, z);
    }

    /**
     * Joins this predicate with the other predicate to conditionally evaluate
     * either this or the other predicate, whichever returns {@code true}, the
     * resulting predicate will return {@code true}. If both return {@code false},
     * the resulting predicate will still return {@code false}.
     *
     * @param other The other predicate
     * @return True if either this predicate or the provided predicate return true,
     * false otherwise
     */
    default VolumePredicate<V, I> or(VolumePredicate<V, ? super I> other) {
        return (v, i, x, y, z) -> test(v, i, x, y, z) || other.test(v, i, x, y, z);
    }

    /**
     * Joins this predicate with the provided predicate in an exclusive
     * or relationship such that {@code true} is returned if and only if
     * this predicate returns {@code true} and the other returns {@code false},
     * or if this predicate returns {@code false} and the other returns {@code true}.
     *
     * @param other The other predicate
     * @return The new predicate exclusively or'ing the predicates
     */
    default VolumePredicate<V, I> xor(VolumePredicate<V, ? super I> other) {
        return (volume, element, x, y, z) -> test(volume, element, x, y, z) ^ other.test(volume, element, x, y, z);
    }

    /**
     * Creates a {@link VolumePredicate} for {@link ReadableBlockVolume ReadableBlockVolumes}
     * based on the provided {@link BlockState block states.} It is
     * @param states
     * @return
     */
    static VolumePredicate<? extends ReadableBlockVolume, BlockState> blockStates(BlockState... states) {
        return ((volume, element, x, y, z) -> {
            if (states == null || states.length == 0) {
                return false;
            }
            for (BlockState state : states) {
                if (state == element) {
                    return true;
                }
            }
            return false;
        });
    }

    static VolumePredicate<? extends ReadableBlockVolume, BlockState> blockTypes(BlockType... types) {
        return ((volume, element, x, y, z) -> {
            if (types == null || types.length == 0) {
                return false;
            }
            for (BlockType state : types) {
                if (state == element.getType()) {
                    return true;
                }
            }
            return false;
        });
    }
}
