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
package org.spongepowered.api.item.recipe.brewing;

import org.spongepowered.api.block.tileentity.carrier.BrewingStand;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.potion.PotionTypes;
import org.spongepowered.api.item.recipe.Ingredient;
import org.spongepowered.api.item.recipe.Recipe;

import java.time.Duration;

/**
 * Represents a recipe that can be used in a {@link BrewingStand}.
 */
public interface BrewingRecipe extends Recipe<BrewingInput, BrewingOutput> {

    /**
     * Gets the exemplary solute {@link ItemStackSnapshot}. This is the item
     * that will be dissolved into the solvent.
     *
     * <p>E.g. {@link ItemTypes#NETHER_WART} will be dissolved into a potion
     * with {@link PotionTypes#WATER water} resulting in the
     * {@link PotionTypes#AWKWARD} potion.</p>
     *
     * @return The exemplary solute
     */
    ItemStackSnapshot getExemplarySolute();

    /**
     * Gets the exemplary solvent {@link ItemStackSnapshot}. This is the item
     * that the solute will be dissolved into.
     *
     * <p>E.g. {@link ItemTypes#NETHER_WART} will be dissolved into a potion
     * with {@link PotionTypes#WATER water} resulting in the
     * {@link PotionTypes#AWKWARD} potion.</p>
     *
     * @return The exemplary solvent
     */
    ItemStackSnapshot getExemplarySolvent();

    /**
     * Gets the exemplary {@link Duration} of this recipe. The actual duration
     * can be retrieved from the brewing result, see
     * {@link BrewingOutput#getDuration()}.
     *
     * @return The exemplary duration
     */
    Duration getExemplaryDuration();

    /**
     * Gets the solute {@link Ingredient}. This is the ingredient that will
     * be dissolved into the solvent.
     *
     * <p>E.g. {@link ItemTypes#NETHER_WART} will be dissolved into a potion
     * with {@link PotionTypes#WATER water} resulting in the
     * {@link PotionTypes#AWKWARD} potion.</p>
     *
     * @return The solute ingredient
     */
    Ingredient getSoluteIngredient();

    /**
     * Gets the solvent {@link Ingredient}. This is the ingredient that the
     * solute will be dissolved into.
     *
     * <p>E.g. {@link ItemTypes#NETHER_WART} will be dissolved into a potion
     * with {@link PotionTypes#WATER water} resulting in the
     * {@link PotionTypes#AWKWARD} potion.</p>
     *
     * @return The solvent ingredient
     */
    Ingredient getSolventIngredient();
}
