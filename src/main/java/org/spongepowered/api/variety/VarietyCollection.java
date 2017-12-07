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

import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.property.Property;
import org.spongepowered.api.data.property.PropertyMatcher;
import org.spongepowered.api.util.ResettableBuilder;

import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

/**
 * Represents a collection of {@link VarietyHolder}s that can be filtered
 * using a {@link VarietyMatcher}.
 *
 * @param <T> The variety holder type
 */
@SuppressWarnings("unchecked")
public interface VarietyCollection<T extends VarietyHolder> {

    /**
     * Creates a {@link Builder}.
     *
     * @param <T> The variety holder type
     * @return The builder
     */
    static <T extends VarietyHolder> Builder<T> builder() {
        return Sponge.getRegistry().createBuilder(Builder.class);
    }

    /**
     * Creates a {@link VarietyCollection} for the given {@link Iterable}.
     *
     * @param iterable The iterable
     * @param <T> The variety holder type
     * @return The variant collection
     */
    static <T extends VarietyHolder> VarietyCollection<T> of(Iterable<T> iterable) {
        return VarietyCollection.<T>builder().addAll(iterable).build();
    }

    /**
     * Creates a {@link VarietyCollection} for the given {@link Iterable}.
     *
     * @param holders The variety holders
     * @param <T> The variety holder type
     * @return The variant collection
     */
    static <T extends VarietyHolder> VarietyCollection<T> of(T... holders) {
        return VarietyCollection.<T>builder().add(holders).build();
    }

    /**
     * Gets a {@link VarietyCollection} with all
     * the {@link T}s that match the {@link VarietyMatcher}.
     *
     * @param matcher The variety matcher
     * @return A new variant collection with the matches
     */
    VarietyCollection<T> query(VarietyMatcher matcher);

    /**
     * Gets a {@link VarietyCollection} with all
     * the {@link T}s that match the {@link Variety}.
     *
     * @param variety The variety
     * @return A new variant collection with the matches
     */
    default VarietyCollection<T> queryApplicable(Variety variety) {
        return query(VarietyMatcher.applicable(variety));
    }

    /**
     * Gets a {@link VarietyCollection} with all
     * the {@link T}s that match the {@link Variety}.
     *
     * @param variety The variety
     * @return A new variant collection with the matches
     */
    default VarietyCollection<T> queryInapplicable(Variety variety) {
        return query(VarietyMatcher.inapplicable(variety));
    }

    /**
     * Gets a {@link VarietyCollection} with all
     * the {@link T}s that match the {@link Variety} with
     * the specific {@link org.spongepowered.api.variety.VarietyMatcher.Operator}.
     *
     * @param variety The variety
     * @param operator The operator
     * @return A new variant collection with the matches
     */
    default VarietyCollection<T> query(Variety variety, VarietyMatcher.Operator operator) {
        return query(VarietyMatcher.of(variety, operator));
    }

    /**
     * Gets a {@link VarietyCollection} with all
     * the {@link T}s that match the {@link Property property}.
     *
     * @param property The property
     * @return A new variant collection with the matches
     */
    default <V> VarietyCollection<T> query(Property<V> property, V value) {
        return query(VarietyMatcher.of(property, value));
    }

    /**
     * Gets a {@link VarietyCollection} with all
     * the {@link T}s that match the {@link Property property}.
     *
     * @param property The property
     * @return A new variant collection with the matches
     */
    default <V> VarietyCollection<T> query(Property<V> property, V value, PropertyMatcher.Operator operator) {
        return query(VarietyMatcher.of(property, value, operator));
    }

    /**
     * Gets the first {@link T} within this collection.
     *
     * @return The first matched type, if present
     */
    Optional<T> first();

    /**
     * Gets all the {@link T} within this collection.
     *
     * @return The collection with all the matched types
     */
    Collection<T> all();

    /**
     * A builder to create {@link VarietyCollection}s.
     *
     * @param <T> The type of the variety queryable
     */
    interface Builder<T extends VarietyHolder> extends ResettableBuilder<VarietyCollection<T>, Builder<T>> {

        /**
         * Adds the {@link VarietyHolder}s.
         *
         * @param queryable The queryable
         * @return This builder, for chaining
         */
        Builder<T> add(T queryable);

        /**
         * Adds the {@link VarietyHolder}s.
         *
         * @param holders The holders
         * @return This builder, for chaining
         */
        Builder<T> add(T... holders);

        /**
         * Adds the {@link VarietyHolder}s.
         *
         * @param iterable The holders iterable
         * @return This builder, for chaining
         */
        Builder<T> addAll(Iterable<T> iterable);

        /**
         * Adds the {@link VarietyHolder}s.
         *
         * @param iterator The holders iterator
         * @return This builder, for chaining
         */
        Builder<T> addAll(Iterator<T> iterator);

        /**
         * Builds the {@link VarietyCollection}.
         *
         * @return The variant collection
         */
        VarietyCollection<T> build();
    }
}
