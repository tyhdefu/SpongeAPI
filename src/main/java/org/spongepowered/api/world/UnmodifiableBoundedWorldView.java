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
package org.spongepowered.api.world;

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.world.gen.GeneratingWorldVolume;
import org.spongepowered.api.world.gen.GenerationRegion;
import org.spongepowered.api.world.schematic.Schematic;
import org.spongepowered.api.world.volume.block.MutableBlockVolume;
import org.spongepowered.api.world.volume.block.UnmodifiableBlockVolume;
import org.spongepowered.api.world.volume.block.stream.BlockVolumeStream;
import org.spongepowered.api.world.volume.function.VolumeFiller;
import org.spongepowered.api.world.volume.function.VolumeMerger;
import org.spongepowered.api.world.volume.function.VolumeReducer;

public interface UnmodifiableBoundedWorldView extends
        UnmodifiableWorldView<GeneratingWorldVolume, GenerationRegion, UnmodifiableBoundedWorldView> {
    @Override
    default UnmodifiableBoundedWorldView asUnmodifiableBlockVolume() {
        final BlockVolumeStream<UnmodifiableBoundedWorldView> stream = this.toBlockStream();
        final GenerationRegion region = this.asMutableVolume();
        final VolumeMerger<BlockType, UnmodifiableBlockVolume<?>> merger2 = (left, xLeft, yLeft, zLeft, right, xRight, yRight, zRight) -> {
            return left.getBlock(xLeft, yLeft, zLeft).getType();
        };
        final VolumeMerger<BlockState, UnmodifiableBlockVolume<?>> merger = (left, xLeft, yLeft, zLeft, right, xRight, yRight, zRight) -> BlockTypes.AIR.getDefaultState();
        final VolumeReducer<UnmodifiableBoundedWorldView, BlockState, MutableBlockVolume<?>, GeneratingWorldVolume, UnmodifiableBoundedWorldView> reducer =
                VolumeReducer.of(() -> this, () -> this.asUnmodifiableBlockVolume(), VolumeMerger.leftBlocks(), region, VolumeFiller.BLOCK_APPLIER);
        stream
                .filter((volume, element, x, y, z) -> element.getType().doesUpdateRandomly())
                .merge(reducer)

        final GenerationRegion mergedResult = stream
                .filter((unmodifiable, block, x, y, z) -> block.getType() == BlockTypes.AIR)
                .merge(this, UnmodifiableBoundedWorldView::asUnmodifiableBlockVolume, merger, region, VolumeFiller.BLOCK_APPLIER);
        return this;
    }
}
