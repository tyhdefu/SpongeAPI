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
package org.spongepowered.api.command.registrar.tree;

import org.checkerframework.checker.nullness.qual.Nullable;
import org.spongepowered.api.command.registrar.CommandRegistrar;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Builds a tree of command parameters for sending to clients. For use with
 * custom {@link CommandRegistrar}s.
 */
public interface CommandTreeBuilder<T extends CommandTreeBuilder<T>> {

    /**
     * Creates a child that accepts a literal only.
     *
     * @param key The name of the child node
     * @param childNode A {@link Supplier} that contains a
     *                  {@link CommandTreeBuilder} for the newly created child.
     * @return This, for chaining.
     */
    T child(String key, Consumer<Empty> childNode);

    /**
     * Creates a child of this node with the given key that accepts a
     * non-literal argument.
     *
     * <p>The builder returned by this method is the same builder as that
     * invoked on. The child builder is provided in the {@code childNode}
     * node.</p>
     *
     * @param key The name of the child node
     * @param completionKey The {@link ClientCompletionKey} that indicates to
     *                      the client how a tab completion should be handled.
     * @return This, for chaining.
     */
    <S extends CommandTreeBuilder<S>> T child(String key, ClientCompletionKey<S> completionKey, Consumer<S> childNode);

    /**
     * Declares that the given {@code key} should be considered as if the
     * string specified in {@code to} has been executed.
     *
     * @param key The key
     * @param to The string to redirect to
     * @return This, for chaining
     */
    T redirect(String key, String to);

    /**
     * Declares that the node this {@link CommandTreeBuilder} represents is
     * executable, meaning that a command string that stops here is considered
     * complete.
     *
     * @return This, for chaining
     */
    T executable();

    /**
     * Sets an arbitrary property for this node with a string based value.
     *
     * @param key The property key.
     * @param value The property value.
     * @return This, for chaining.
     */
    T property(String key, String value);

    /**
     * Sets an arbitrary property for this node with a long based value.
     *
     * @param key The property key.
     * @param value The property value.
     * @return This, for chaining.
     */
    T property(String key, long value);

    /**
     * Sets an arbitrary property for this node with a double based value.
     *
     * @param key The property key.
     * @param value The property value.
     * @return This, for chaining.
     */
    T property(String key, double value);

    /**
     * Sets an arbitrary property for this node with a boolean based value.
     *
     * @param key The property key.
     * @param value The property value.
     * @return This, for chaining.
     */
    T property(String key, boolean value);

    /**
     * A {@link CommandTreeBuilder} with no known properties to set.
     */
    interface Empty extends CommandTreeBuilder<Empty> { }

    /**
     * A {@link CommandTreeBuilder} that allows for a min-max range to be set.
     */
    interface Range<S extends Number> extends CommandTreeBuilder<Range<S>> {

        /**
         * Sets the minimum {@link S} acceptable for this parameter.
         *
         * @param min The minimum, or {@code null} if there is no minimum
         * @return This, for chaining
         */
        Range<S> min(@Nullable S min);

        /**
         * Sets the maximum {@link S} acceptable for this parameter.
         *
         * @param max The maximum, or {@code null} if there is no maximum
         * @return This, for chaining
         */
        Range<S> max(@Nullable S max);

    }

    /**
     * A {@link CommandTreeBuilder} that controls whether multiple entries
     * can be parsed.
     */
    interface Amount extends CommandTreeBuilder<Amount>, AmountBase<Amount> { }

    /**
     * A {@link CommandTreeBuilder} that augments entity based parameters.
     */
    interface EntitySelection extends CommandTreeBuilder<EntitySelection>, AmountBase<EntitySelection> {

        /**
         * Indicates that only players can be selected. If not called, all
         * entities may selected by this element.
         *
         * @return This, for chaining.
         */
        EntitySelection playersOnly();

    }

    /**
     * A {@link CommandTreeBuilder} that allows for the setting of how
     * a string based parser will behave.
     */
    interface StringParser extends CommandTreeBuilder<StringParser> {

        /**
         * Determines how the string parser will parse a string.
         *
         * @param type The type
         * @return This, for chaining.
         */
        StringParser type(Types type);

        /**
         * The behaviors available to the string parser.
         */
        enum Types {

            /**
             * Will parse the next word
             */
            WORD,

            /**
             * Will parse the next word, or, if quoted, the quoted string
             */
            PHRASE,

            /**
             * Will parse the remainder of the argument string
             */
            GREEDY

        }
    }

    /**
     * Indicates that a {@link CommandTreeBuilder} supports marking a parameter
     * as having a single target only.
     *
     * @param <T> The type of {@link CommandTreeBuilder} this extends
     */
    interface AmountBase<T> {

        /**
         * Indicates that only one object can be selected by this parameter.
         * If this is not called, this element will default to allowing the
         * selection of multiple object.
         *
         * @return This, for chaining.
         */
        T single();
    }

}
