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

import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.crafting.CraftingGridInventory;
import org.spongepowered.api.item.recipe.RecipeInput;
import org.spongepowered.api.world.World;

import java.util.Optional;

import javax.annotation.Nullable;

public interface CraftingInput extends RecipeInput {

    Optional<World> getWorld();

    int getRows();

    int getColumns();

    ItemStack get(int x, int y);

    static CraftingInput of(CraftingGridInventory inventory) {
        return of(inventory, null);
    }

    static CraftingInput of(CraftingGridInventory inventory, @Nullable World world) {
        return new CraftingInput() {

            @Override
            public Optional<World> getWorld() {
                return Optional.ofNullable(world);
            }

            @Override
            public int getRows() {
                return inventory.getRows();
            }

            @Override
            public int getColumns() {
                return inventory.getColumns();
            }

            @Override
            public ItemStack get(int x, int y) {
                return inventory.peek(x, y).orElseThrow(() ->
                        new IllegalArgumentException(String.format("Invalid slot coordinates (%s, %s)", x, y)));
            }
        };
    }
}
