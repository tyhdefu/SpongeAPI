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
package org.spongepowered.api.item.fuel;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.recipe.Ingredient;
import org.spongepowered.api.util.CatalogBuilder;

import java.time.Duration;
import java.util.Optional;

/**
 * Represents the base of fuel.
 */
public interface BaseFuel extends CatalogType {

    /**
     * Gets the {@link Ingredient} that lies at the
     * base of this fuel.
     *
     * @return The ingredient
     */
    Ingredient getIngredient();

    /**
     * Gets the {@link Duration} that this fuel will burn and produce
     * energy for the given {@link ItemStackSnapshot}.
     *
     * @return The duration, or {@link Optional#empty()} if not applicable
     */
    default Optional<Duration> getBurnDuration(ItemStackSnapshot input) {
        return getBurnDuration(input.createStack());
    }

    /**
     * Gets the {@link Duration} that this fuel will burn and produce
     * energy for the given {@link ItemStack}.
     *
     * @return The duration, or {@link Optional#empty()} if not applicable
     */
    Optional<Duration> getBurnDuration(ItemStack input);

    /**
     * Gets whether the given {@link ItemStack}
     * matches this fuel.
     *
     * @param input The input
     * @return Whether the input is valid
     */
    default boolean isValid(ItemStack input) {
        return getIngredient().test(input);
    }

    /**
     * Gets whether the given {@link ItemStackSnapshot}
     * matches this fuel.
     *
     * @param input The input
     * @return Whether the input is valid
     */
    default boolean isValid(ItemStackSnapshot input) {
        return getIngredient().test(input.createStack());
    }

    interface Builder<C extends BaseFuel, B extends Builder<C, B>> extends CatalogBuilder<C, B> {

        // TODO
    }
}
