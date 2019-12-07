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
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.entity.BlockEntity;
import org.spongepowered.api.world.volume.MutableVolume;
import org.spongepowered.api.world.volume.UnmodifiableVolume;
import org.spongepowered.api.world.volume.block.UnmodifiableBlockVolume;
import org.spongepowered.api.world.volume.block.entity.UnmodifiableBlockEntityVolume;

import java.util.Optional;

/**
 * A merging "reduction" operation
 * @param <I>
 * @param <U>
 */
@FunctionalInterface
public interface VolumeMerger<I, U extends UnmodifiableVolume> {

    /**
     * Provides a resulting {@code I element} using the provided {@code left}
     * and {@code right} {@link UnmodifiableVolume volumes} with their
     * associated coordinates. This is useful for   conflict resolution when
     * taking two volumes to merge onto a new {@link MutableVolume}. There is
     * no contract requiring the usage of either left or right volumes to
     * provide a "merged" result, as a constant result can be returned.
     *
     * @param left The left side volume
     * @param xLeft The left side volume x position
     * @param yLeft The left side volume y position
     * @param zLeft The left side volume z position
     * @param right The right side volume
     * @param xRight The right side volume x position
     * @param yRight The right side volume y position
     * @param zRight The right side volume z position
     * @return The merge result element
     */
    I merge(U left, int xLeft, int yLeft, int zLeft, U right, int xRight, int yRight, int zRight);

    static <U extends UnmodifiableBlockVolume<U>> VolumeMerger<BlockState, ? super U> leftBlocks() {
        return (left, xLeft, yLeft, zLeft, right, xRight, yRight, zRight) -> left.getBlock(xLeft, yLeft, zLeft);
    }

    static <U extends UnmodifiableBlockVolume<U>> VolumeMerger<BlockState, ? super U> rightBlocks() {
        return (left, xLeft, yLeft, zLeft, right, xRight, yRight, zRight) -> right.getBlock(xRight, yRight, zRight);
    }

    static <U extends UnmodifiableBlockVolume<U>> VolumeMerger<BlockState, ? super U> leftNonAirPreferred() {
        return (left, xLeft, yLeft, zLeft, right, xRight, yRight, zRight)
                -> {
            final BlockState rightBlock = right.getBlock(xRight, yRight, zRight);
            return rightBlock.getType() == BlockTypes.AIR ? left.getBlock(xLeft, yLeft, zLeft) : rightBlock;
        };
    }

    static <U extends UnmodifiableBlockVolume<U>> VolumeMerger<BlockState, ? super U> rightNonAirPreferred() {
        return (left, xLeft, yLeft, zLeft, right, xRight, yRight, zRight)
                -> {
            final BlockState leftBlock = left.getBlock(xRight, yRight, zRight);
            return leftBlock.getType() == BlockTypes.AIR ? right.getBlock(xLeft, yLeft, zLeft) : leftBlock;
        };
    }

    static <U extends UnmodifiableBlockEntityVolume<U>> VolumeMerger<Optional<? extends BlockEntity>, ? super U> leftBlockEntities() {
        return (left, xLeft, yLeft, zLeft, right, xRight, yRight, zRight) -> left.getBlockEntity(xLeft, yLeft, zLeft);
    }

    static <U extends UnmodifiableBlockEntityVolume<U>> VolumeMerger<Optional<? extends BlockEntity>, ? super U> rightBlockEntities() {
        return (left, xLeft, yLeft, zLeft, right, xRight, yRight, zRight) -> right.getBlockEntity(xRight, yRight, zRight);
    }



    static <T, U extends UnmodifiableVolume> VolumeMerger<? extends T, ? super U> left(VolumeResultSupplier<? super U, T> supplier) {
        return (left, xLeft, yLeft, zLeft, right, xRight, yRight, zRight) -> supplier.supply(left, xLeft, yLeft, zLeft);
    }

    static <T, U extends UnmodifiableVolume> VolumeMerger<? super T, ? super U> right(VolumeResultSupplier<? super U, ? extends T> supplier) {
        return (left, xLeft, yLeft, zLeft, right, xRight, yRight, zRight) -> supplier.supply(right, xLeft, yLeft, zLeft);
    }
}
