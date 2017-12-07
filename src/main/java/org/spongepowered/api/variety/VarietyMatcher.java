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
import org.spongepowered.api.util.Tuple;

import java.util.Arrays;
import java.util.Collection;

/**
 * A variety matcher will be used to match a set of specific
 * {@link Variety varieties} and {@link Property properties} on
 * a {@link VarietyHolder}.
 */
public interface VarietyMatcher {

    /**
     * Creates a {@link Builder} to create {@link VarietyMatcher variety matchers}.
     *
     * @return The builder
     */
    static Builder builder() {
        return Sponge.getRegistry().createBuilder(Builder.class);
    }

    /**
     * Creates a {@link VarietyMatcher} from the provided {@link Variety}
     * with the {@link Operator#APPLICABLE applicable} operator.
     *
     * @param variety The variety
     * @return The constructed variety matcher
     */
    static VarietyMatcher applicable(Variety variety) {
        return builder().applicable(variety).build();
    }

    /**
     * Creates a {@link VarietyMatcher} from the provided {@link Variety}
     * with the {@link Operator#INAPPLICABLE inapplicable} operator.
     *
     * @param variety The variety
     * @return The constructed variety matcher
     */
    static VarietyMatcher inapplicable(Variety variety) {
        return builder().inapplicable(variety).build();
    }

    /**
     * Creates a {@link VarietyMatcher} from the provided {@link Variety}
     * and the specific {@link Operator}.
     *
     * @param variety The variety
     * @param operator The variety matcher operator
     * @return The constructed variety matcher
     */
    static VarietyMatcher of(Variety variety, Operator operator) {
        return builder().variety(variety, operator).build();
    }

    /**
     * Creates a {@link VarietyMatcher} from the provided
     * {@link PropertyMatcher property matchers}.
     *
     * @param propertyMatchers The property matchers
     * @return The constructed variety matcher
     */
    static VarietyMatcher of(PropertyMatcher<?>... propertyMatchers) {
        return builder().properties(propertyMatchers).build();
    }

    static <V> VarietyMatcher of(Property<V> property, V value) {
        return builder().property(property, value).build();
    }

    static <V> VarietyMatcher of(Property<V> property, V value, PropertyMatcher.Operator operator) {
        return builder().property(property, value, operator).build();
    }

    /**
     * Gets a {@link Collection} with all {@link Variety}s
     * and {@link Operator}s that will be used.
     *
     * @return The variety entries
     */
    Collection<Tuple<Variety, Operator>> getVarietyEntries();

    /**
     * Gets the {@link PropertyMatcher property matchers} that
     * will be used.
     *
     * @return The property matchers
     */
    Collection<PropertyMatcher<?>> getPropertyMatchers();

    /**
     * Gets whether the given {@link VarietyHolder} is matched by this matcher.
     *
     * @param holder The variety holder
     * @return Whether the variety holder is matched
     */
    boolean matches(VarietyHolder holder);

    /**
     * A simple operator to match {@link Variety}s.
     */
    enum Operator {
        /**
         * Matches when a {@link Variety} is applicable
         * to a specific {@link VarietyHolder}.
         */
        APPLICABLE,
        /**
         * Matches when a {@link Variety} isn't applicable
         * to a specific {@link VarietyHolder}.
         */
        INAPPLICABLE,
    }

    /**
     * A builder to create {@link VarietyMatcher variety queries}.
     */
    interface Builder extends ResettableBuilder<VarietyMatcher, Builder> {

        /**
         * Adds the {@link Variety varieties} with the
         * {@link Operator#APPLICABLE applicable} operator.
         *
         * @param varieties The varieties
         * @return This builder, for chaining
         */
        default Builder applicable(Variety... varieties) {
            return applicable(Arrays.asList(varieties));
        }

        /**
         * Adds the {@link Variety varieties} with the
         * {@link Operator#APPLICABLE applicable} operator.
         *
         * @param varieties The varieties
         * @return This builder, for chaining
         */
        default Builder applicable(Iterable<Variety> varieties) {
            varieties.forEach(this::applicable);
            return this;
        }

        /**
         * Adds the {@link Variety} with the
         * {@link Operator#APPLICABLE applicable} operator.
         *
         * @param variety The variety
         * @return This builder, for chaining
         */
        default Builder applicable(Variety variety) {
            return variety(variety, Operator.APPLICABLE);
        }

        /**
         * Adds the {@link Variety varieties} with the
         * {@link Operator#INAPPLICABLE inapplicable} operator.
         *
         * @param varieties The varieties
         * @return This builder, for chaining
         */
        default Builder inapplicable(Variety... varieties) {
            return inapplicable(Arrays.asList(varieties));
        }

        /**
         * Adds the {@link Variety varieties} with the
         * {@link Operator#INAPPLICABLE inapplicable} operator.
         *
         * @param varieties The varieties
         * @return This builder, for chaining
         */
        default Builder inapplicable(Iterable<Variety> varieties) {
            varieties.forEach(this::inapplicable);
            return this;
        }

        /**
         * Adds the {@link Variety} with the
         * {@link Operator#INAPPLICABLE inapplicable} operator.
         *
         * @param variety The variety
         * @return This builder, for chaining
         */
        default Builder inapplicable(Variety variety) {
            return variety(variety, Operator.INAPPLICABLE);
        }

        /**
         * Adds the {@link Variety} with a specific {@link Operator}.
         *
         * @param variety The variety
         * @param operator The operator
         * @return This builder, for chaining
         */
        Builder variety(Variety variety, Operator operator);

        /**
         * Adds the {@link PropertyMatcher} that should be used.
         *
         * @param propertyMatcher The property matcher
         * @return This builder, for chaining
         */
        Builder property(PropertyMatcher<?> propertyMatcher);

        /**
         * Adds a new {@link PropertyMatcher} that should be used from
         * the given property and value.
         *
         * @param property The property of which the value should be matched
         * @param value The matcher value that property values will be matched against
         * @param <V> The value type
         * @return This builder, for chaining
         */
        default <V> Builder property(Property<V> property, V value) {
            return property(PropertyMatcher.of(property, value));
        }

        /**
         * Adds a new {@link PropertyMatcher} that should be used from
         * the given property, value and operator.
         *
         * @param property The property of which the value should be matched
         * @param value The matcher value that property values will be matched against
         * @param operator The operator how the value should be matched
         * @param <V> The value type
         * @return This builder, for chaining
         */
        default <V> Builder property(Property<V> property, V value, PropertyMatcher.Operator operator) {
            return property(PropertyMatcher.of(property, value, operator));
        }

        /**
         * Adds the {@link PropertyMatcher property matchers} that should be used.
         *
         * @param propertyMatchers The property matchers
         * @return This builder, for chaining
         */
        default Builder properties(PropertyMatcher<?>... propertyMatchers) {
            return properties(Arrays.asList(propertyMatchers));
        }

        /**
         * Adds the {@link PropertyMatcher property matchers} that should be used.
         *
         * @param propertyMatchers The property matchers
         * @return This builder, for chaining
         */
        default Builder properties(Iterable<PropertyMatcher<?>> propertyMatchers) {
            propertyMatchers.forEach(this::property);
            return this;
        }

        /**
         * Builds the {@link VarietyMatcher}.
         *
         * @return The variety matcher
         */
        VarietyMatcher build();
    }
}
