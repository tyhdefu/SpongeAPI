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
package org.spongepowered.api.item.recipe;

import org.spongepowered.api.item.recipe.crafting.CraftingRecipe;
import org.spongepowered.api.item.recipe.crafting.CraftingOutput;
import org.spongepowered.api.registry.CatalogRegistryModule;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A RecipeRegistry holds all registered recipes for a given game.
 */
public interface RecipeRegistry extends CatalogRegistryModule<Recipe<?,?>> {

    /**
     * Retrieves the recipe which would be crafted when the player clicks
     * the output slot.
     *
     * @param grid The crafting grid
     * @param world The world the player is in
     * @return The found {@link CraftingRecipe}, or {@link Optional#empty()}
     *         if no recipe was found for this configuration
     */
    default <T extends Recipe<I, O>, I extends RecipeInput, O extends RecipeOutput> Optional<T> findMatchingRecipe(Class<T> recipeType, I input) {
        return getAll().stream()
                .filter(recipeType::isInstance)
                .map(recipeType::cast)
                .map(recipe -> recipe.isValid(input) ? recipe : null)
                .filter(Objects::nonNull)
                .findFirst();
    }

    /**
     * Finds the matching recipe and creates the {@link CraftingOutput},
     * which is then returned.
     *
     * @param inventory The inventory
     * @param world The world the player is in
     * @return The {@link RecipeOutput} if a recipe was found,
     *         or {@link Optional#empty()} if not
     */

    default <R extends Recipe<I, O>, I extends RecipeInput, O extends RecipeOutput> Optional<RecipeResult<R, O>> getResult(
            Class<R> recipeType, I input) {
        return getAll().stream()
                .filter(recipeType::isInstance)
                .map(recipeType::cast)
                .map(recipe -> recipe.getOutput(input).map(output -> new RecipeResult<>(recipe, output)).orElse(null))
                .filter(Objects::nonNull)
                .findFirst();
    }

    default <R extends Recipe<I, O>, I extends RecipeInput, O extends RecipeOutput> Collection<R> getRecipes(Class<R> recipeType) {
        return getAll().stream()
                .filter(recipeType::isInstance)
                .map(recipeType::cast)
                .collect(Collectors.toList());
    }
}
