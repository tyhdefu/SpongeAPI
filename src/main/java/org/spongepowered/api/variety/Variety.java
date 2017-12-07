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
package org.spongepowered.api.variety;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.data.property.Properties;
import org.spongepowered.api.data.property.Property;
import org.spongepowered.api.data.property.PropertyHolder;
import org.spongepowered.api.data.type.TreeTypes;

import java.util.Collection;

/**
 * A variety is a kind of marker that can be used to group
 * {@link VarietyHolder}s. For example grouping all the
 * stair {@link BlockType}s that are available.
 * <p>Some varieties may also contain {@link Property}s which
 * can also be used to match the {@link VarietyHolder}. For example,
 * the {@link Varieties#BIRCH} will contain a {@link Properties#TREE_TYPE}
 * with the tree type {@link TreeTypes#BIRCH}. This property
 * can also be used to query for a block with a specific tree type.
 */
public interface Variety extends CatalogType, PropertyHolder {

    /**
     * Gets a {@link Collection} with all the {@link VarietyHolder}
     * types this variety supports.
     *
     * @return The collection
     */
    Collection<Class<? extends VarietyHolder>> getSupportedHolders();

    /**
     * Gets whether this {@link Variety} is applicable to
     * the given {@link VarietyHolder}.
     *
     * @param varietyHolder The variety holder
     * @return Whether this variety applies to the given variety holder
     */
    boolean appliesTo(VarietyHolder varietyHolder);
}
