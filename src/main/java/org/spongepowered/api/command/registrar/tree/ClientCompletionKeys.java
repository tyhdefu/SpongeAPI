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

import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.Color;
import org.spongepowered.api.util.generator.dummy.DummyObjectProvider;

public class ClientCompletionKeys {

    // SORTFIELDS: ON

    /**
     * Indicates that tab completing this element should query the server for
     * potential completions.
     *
     * <p><strong>Important note:</strong> if you wish to have any control
     * over the completion process, you should use this key.</p>
     */
    public static ClientCompletionKey<CommandTreeBuilder.StringParser> ASK_SERVER =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "ASK_SERVER");

    /**
     * Completions will attempt to return arguments that represent
     * {@link BlockState}s
     */
    public static ClientCompletionKey<CommandTreeBuilder.Empty> BLOCK_STATE =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "BLOCK_STATE");

    /**
     * Completions will attempt to return arguments that represent
     * {@link BlockState}s - // TODO: Predicate
     */
    public static ClientCompletionKey<CommandTreeBuilder.Empty> BLOCK_PREDICATE =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "BLOCK_PREDICATE");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> BOOLEAN =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "BOOLEAN");

    /**
     * Completions will attempt to return arguments that represent
     * {@link Color}s
     */
    public static ClientCompletionKey<CommandTreeBuilder.Empty> COLOR =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "COLOR");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> DIMENSION =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "DIMENSION");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> DOUBLE =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "DOUBLE");

    /**
     * Completions will attempt to return arguments that represent
     * {@link Entity}s
     */
    public static ClientCompletionKey<CommandTreeBuilder.EntitySelection> ENTITY =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "ENTITY");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> ENTITY_ANCHOR =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "ENTITY_ANCHOR");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> ENTITY_SUMMON =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "ENTITY_SUMMON");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> FLOAT =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "FLOAT");

    public static ClientCompletionKey<CommandTreeBuilder.Range<Float>> FLOAT_RANGE =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "FLOAT_RANGE");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> FUNCTION =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "FUNCTION");

    /**
     * Completions will attempt to return arguments that represent
     * {@link GameProfile}s
     */
    public static ClientCompletionKey<CommandTreeBuilder.Empty> GAME_PROFILE =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "GAME_PROFILE");

    public static ClientCompletionKey<CommandTreeBuilder.Range<Integer>> INT_RANGE =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "INT_RANGE");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> INTEGER =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "INTEGER");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> ITEM_ENCHANTMENT =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "ITEM_ENCHANTMENT");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> ITEM_SLOT =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "ITEM_SLOT");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> LONG =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "LONG");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> MESSAGE =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "MESSAGE");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> MOB_EFFECT =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "MOB_EFFECT");

    // TODO -> dataview?
    public static ClientCompletionKey<CommandTreeBuilder.Empty> NBT_COMPOUND =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "NBT_TAG");

    // TODO -> datapath?
    public static ClientCompletionKey<CommandTreeBuilder.Empty> NBT_PATH =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "NBT_PATH");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> NBT_TAG =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "NBT_TAG");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> OBJECTIVE =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "OBJECTIVE");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> OBJECTIVE_CRITERIA =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "OBJECTIVE_CRITERIA");

    // TODO -> check
    public static ClientCompletionKey<CommandTreeBuilder.Empty> OPERATION =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "OPERATION");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> PARTICLE =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "PARTICLE");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> RESOURCE_LOCATION =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "RESOURCE_LOCATION");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> ROTATION =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "ROTATION");

    public static ClientCompletionKey<CommandTreeBuilder.Amount> SCORE_HOLDER =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "SCORE_HOLDER");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> SCOREBOARD_SLOT =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "SCOREBOARD_SLOT");

    public static ClientCompletionKey<CommandTreeBuilder.StringParser> STRING =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "STRING");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> SWIZZLE =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "SWIZZLE");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> TEAM =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "TEAM");

    /**
     * Completions will attempt to return arguments that represent
     * {@link Text}s
     */
    public static ClientCompletionKey<CommandTreeBuilder.Empty> TEXT =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "TEXT");

    public static ClientCompletionKey<CommandTreeBuilder.Empty> TIME =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "TIME");

    /**
     * Completions will attempt to return arguments that represent a
     * real-space position (that is, two-dimensional decimal co-ordinates).
     */
    public static ClientCompletionKey<CommandTreeBuilder.Empty> VECTOR_2D =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "VECTOR_2D");

    /**
     * Completions will attempt to return arguments that represent a
     * block position (that is, two-dimensional integer co-ordinates).
     */
    public static ClientCompletionKey<CommandTreeBuilder.Empty> VECTOR_2I =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "VECTOR_2I");

    /**
     * Completions will attempt to return arguments that represent a
     * real-space position (that is, three-dimensional decimal co-ordinates).
     */
    public static ClientCompletionKey<CommandTreeBuilder.Empty> VECTOR_3D =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "VECTOR_3D");

    /**
     * Completions will attempt to return arguments that represent a
     * block position (that is, three-dimensional integer co-ordinates).
     */
    public static ClientCompletionKey<CommandTreeBuilder.Empty> VECTOR_3I =
            DummyObjectProvider.createExtendedFor(ClientCompletionKey.class, "VECTOR_3I");

    // SORTFIELDS: OFF

    private ClientCompletionKeys() {
        throw new AssertionError("This cannot be instantiated.");
    }

}
