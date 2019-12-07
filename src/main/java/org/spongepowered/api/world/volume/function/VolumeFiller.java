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
import org.spongepowered.api.block.entity.BlockEntity;
import org.spongepowered.api.block.entity.BlockEntityArchetype;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityArchetype;
import org.spongepowered.api.world.volume.MutableVolume;
import org.spongepowered.api.world.volume.archetype.entity.MutableEntityArchetypeVolume;
import org.spongepowered.api.world.volume.block.MutableBlockVolume;
import org.spongepowered.api.world.volume.block.entity.MutableBlockEntityVolume;
import org.spongepowered.api.world.volume.entity.MutableEntityVolume;

@FunctionalInterface
public interface VolumeFiller<M extends MutableVolume, I> {

    void apply(M volumeTarget, I element, int x, int y, int z);

    VolumeFiller<? extends MutableBlockVolume<?>, ? extends BlockState> BLOCK_APPLIER = (m, b, x, y, z) -> m.setBlock(x, y, z, b);

    VolumeFiller<? extends MutableBlockEntityVolume<?>, ? extends BlockEntity> BLOCK_ENTITY_APPLIER = (m, b, x, y, z) -> m.addBlockEntity(x, y, z, b);

    VolumeFiller<? extends MutableEntityVolume<?>, ? extends Entity> ENTITY_APPLIER = (m, e, x, y, z) -> m.spawnEntity(e);

    VolumeFiller<? extends MutableEntityVolume<?>, ? extends EntityArchetype> ENTITY_ARCHETYPE_APPLIER = (m, e, x, y, z) -> m.createEntity(e.getEntityData());

    VolumeFiller<? extends MutableBlockEntityVolume<?>, ? extends BlockEntityArchetype> BLOCK_ENTITY_ARCHETYPE_APPLIER = (m, e, x, y, z) -> {
        m.setBlock(x, y, z, e.getState());
        m.getBlockEntity(x, y, z)
                .ifPresent(found -> found.setRawData(e.getBlockEntityData()));
    };


}
