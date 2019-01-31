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
package org.spongepowered.api.item.recipe.crafting;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.crafting.CraftingGridInventory;
import org.spongepowered.api.item.recipe.Recipe;
import org.spongepowered.api.world.World;

import java.util.Optional;

/**
 * A CraftingRecipe represents some craftable recipe in the game.
 *
 * <p>It is essentially a Predicate that checks for if a recipe is valid as well
 * as a function from a crafting matrix to a list of {@link ItemStack} (the
 * crafting result), therefore making it an immutable interface.</p>
 *
 * <p>The passed in {@link CraftingGridInventory} is usually a crafting
 * inventory, e.g. a 2x2 or 3x3 crafting matrix.</p>
 *
 * <p>The requirements of a CraftingRecipe can be general, they just have to
 * eventually return a boolean given an crafting grid.</p>
 */
public interface CraftingRecipe extends Recipe<CraftingInput, CraftingOutput> {

    /**
     * Checks if the given {@link CraftingGridInventory} fits the required
     * constraints to craft this {@link CraftingGridInventory}.
     *
     * @param grid The {@link CraftingGridInventory} to check for validity
     * @return True if the given input matches this recipe's requirements
     */
    default boolean isValid(CraftingGridInventory grid) {
        return getOutput(grid).isPresent();
    }

    /**
     * Checks if the given {@link CraftingGridInventory} fits the required
     * constraints to craft this {@link CraftingGridInventory}.
     *
     * @param grid The {@link CraftingGridInventory} to check for validity
     * @param world The world this recipe would be used in
     * @return True if the given input matches this recipe's requirements
     */
    default boolean isValid(CraftingGridInventory grid, World world) {
        return getOutput(grid, world).isPresent();
    }

    /**
     * Returns the {@link CraftingOutput} for the current crafting grid
     * configuration and the {@link World} the player is in.
     *
     * <p>Returns
     * {@link Optional#empty()} if the arguments do not satisfy
     * {@link #isValid(CraftingGridInventory, World)}.</p>
     *
     * @param grid The crafting input, typically 3x3 or 2x2
     * @return A {@link CraftingOutput} if the arguments satisfy
     *     {@link #isValid(CraftingGridInventory, World)}, or
     *     {@link Optional#empty()} if not
     */
    default Optional<CraftingOutput> getOutput(CraftingGridInventory grid) {
        return getOutput(CraftingInput.of(grid));
    }

    /**
     * Returns the {@link CraftingOutput} for the current crafting grid
     * configuration and the {@link World} the player is in.
     *
     * <p>Returns
     * {@link Optional#empty()} if the arguments do not satisfy
     * {@link #isValid(CraftingGridInventory, World)}.</p>
     *
     * @param grid The crafting input, typically 3x3 or 2x2
     * @param world The world this recipe would be used in
     * @return A {@link CraftingOutput} if the arguments satisfy
     *     {@link #isValid(CraftingGridInventory, World)}, or
     *     {@link Optional#empty()} if not
     */
    default Optional<CraftingOutput> getOutput(CraftingGridInventory grid, World world) {
        return getOutput(CraftingInput.of(grid, world));
    }

    /**
     * The group this CraftingRecipe belongs to or {@link Optional#empty()}
     * if not defined.
     *
     * @return The group this Recipe belongs to.
     */
    Optional<String> getGroup();

    /**
     * Provides a Builder for a {@link ShapedCraftingRecipe}.
     *
     * @return The Builder.
     */
    static ShapedCraftingRecipe.Builder shapedBuilder() {
        return Sponge.getRegistry().createBuilder(ShapedCraftingRecipe.Builder.class);
    }

    /**
     * Provides a Builder for a {@link ShapelessCraftingRecipe}.
     *
     * @return The Builder.
     */
    static ShapelessCraftingRecipe.Builder shapelessBuilder() {
        return Sponge.getRegistry().createBuilder(ShapelessCraftingRecipe.Builder.class);
    }

}
