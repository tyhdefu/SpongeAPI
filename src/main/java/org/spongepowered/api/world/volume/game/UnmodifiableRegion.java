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
package org.spongepowered.api.world.volume.game;

import org.spongepowered.api.util.RandomProvider;
import org.spongepowered.api.world.gen.GenerationRegion;
import org.spongepowered.api.world.volume.UnmodifiableVolume;
import org.spongepowered.api.world.volume.biome.UnmodifiableBiomeVolume;
import org.spongepowered.api.world.volume.block.UnmodifiableBlockVolume;
import org.spongepowered.api.world.volume.block.entity.UnmodifiableBlockEntityVolume;
import org.spongepowered.api.world.volume.entity.UnmodifiableEntityVolume;
import org.spongepowered.math.vector.Vector3i;

public interface UnmodifiableRegion extends
        EnvironmentalVolume,
        UnmodifiableBiomeVolume<UnmodifiableRegion>,
        UnmodifiableBlockVolume<UnmodifiableRegion>,
        UnmodifiableEntityVolume<UnmodifiableRegion>,
        UnmodifiableBlockEntityVolume<UnmodifiableRegion>,
        ChunkVolume,
        HeightAwareVolume,
        RandomProvider,
        UnmodifiableVolume
{
    @Override
    default UnmodifiableRegion asUnmodifiableBiomeVolume() {
        return this;
    }

    @Override
    UnmodifiableRegion getView(Vector3i newMin, Vector3i newMax);

    @Override
    default UnmodifiableRegion asUnmodifiableEntityVolume() {
        return this;
    }

    @Override
    default UnmodifiableRegion asUnmodifiableBlockVolume() {
        return this;
    }

    @Override
    default UnmodifiableRegion asUnmodifiableVolume() {
        return this;
    }

    @Override
    GenerationRegion asMutableVolume();
}
