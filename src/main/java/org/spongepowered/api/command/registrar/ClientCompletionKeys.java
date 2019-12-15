package org.spongepowered.api.command.registrar;

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
    public static ClientCompletionKey ASK_SERVER = DummyObjectProvider.createFor(ClientCompletionKey.class, "ASK_SERVER");

    /**
     * Completions will attempt to return arguments that represent
     * {@link BlockState}s
     */
    public static ClientCompletionKey BLOCK_STATE = DummyObjectProvider.createFor(ClientCompletionKey.class, "BLOCK_STATE");

    /**
     * Completions will attempt to return arguments that represent
     * {@link BlockState}s - // TODO: Predicate
     */
    public static ClientCompletionKey BLOCK_PREDICATE = DummyObjectProvider.createFor(ClientCompletionKey.class, "BLOCK_PREDICATE");

    /**
     * Completions will attempt to return arguments that represent
     * {@link Color}s
     */
    public static ClientCompletionKey COLOR = DummyObjectProvider.createFor(ClientCompletionKey.class, "COLOR");

    public static ClientCompletionKey DIMENSION = DummyObjectProvider.createFor(ClientCompletionKey.class, "DIMENSION");

    /**
     * Completions will attempt to return arguments that represent
     * {@link Entity}s
     */
    public static ClientCompletionKey ENTITY = DummyObjectProvider.createFor(ClientCompletionKey.class, "ENTITY");

    public static ClientCompletionKey ENTITY_ANCHOR = DummyObjectProvider.createFor(ClientCompletionKey.class, "ENTITY_ANCHOR");

    public static ClientCompletionKey ENTITY_SUMMON = DummyObjectProvider.createFor(ClientCompletionKey.class, "ENTITY_SUMMON");

    public static ClientCompletionKey FLOAT_RANGE = DummyObjectProvider.createFor(ClientCompletionKey.class, "FLOAT_RANGE");

    public static ClientCompletionKey FUNCTION = DummyObjectProvider.createFor(ClientCompletionKey.class, "FUNCTION");

    /**
     * Completions will attempt to return arguments that represent
     * {@link GameProfile}s
     */
    public static ClientCompletionKey GAME_PROFILE = DummyObjectProvider.createFor(ClientCompletionKey.class, "GAME_PROFILE");

    public static ClientCompletionKey INT_RANGE = DummyObjectProvider.createFor(ClientCompletionKey.class, "INT_RANGE");

    public static ClientCompletionKey ITEM_ENCHANTMENT = DummyObjectProvider.createFor(ClientCompletionKey.class, "ITEM_ENCHANTMENT");

    public static ClientCompletionKey ITEM_SLOT = DummyObjectProvider.createFor(ClientCompletionKey.class, "ITEM_SLOT");

    public static ClientCompletionKey MESSAGE = DummyObjectProvider.createFor(ClientCompletionKey.class, "MESSAGE");

    public static ClientCompletionKey MOB_EFFECT = DummyObjectProvider.createFor(ClientCompletionKey.class, "MOB_EFFECT");

    // TODO -> dataview?
    public static ClientCompletionKey NBT_COMPOUND = DummyObjectProvider.createFor(ClientCompletionKey.class, "NBT_TAG");

    // TODO -> datapath?
    public static ClientCompletionKey NBT_PATH = DummyObjectProvider.createFor(ClientCompletionKey.class, "NBT_PATH");

    public static ClientCompletionKey NBT_TAG = DummyObjectProvider.createFor(ClientCompletionKey.class, "NBT_TAG");

    public static ClientCompletionKey OBJECTIVE = DummyObjectProvider.createFor(ClientCompletionKey.class, "OBJECTIVE");

    public static ClientCompletionKey OBJECTIVE_CRITERIA = DummyObjectProvider.createFor(ClientCompletionKey.class, "OBJECTIVE_CRITERIA");

    // TODO -> check
    public static ClientCompletionKey OPERATION = DummyObjectProvider.createFor(ClientCompletionKey.class, "OPERATION");

    public static ClientCompletionKey PARTICLE = DummyObjectProvider.createFor(ClientCompletionKey.class, "PARTICLE");

    public static ClientCompletionKey RESOURCE_LOCATION = DummyObjectProvider.createFor(ClientCompletionKey.class, "RESOURCE_LOCATION");

    public static ClientCompletionKey ROTATION = DummyObjectProvider.createFor(ClientCompletionKey.class, "ROTATION");

    public static ClientCompletionKey SCORE_HOLDER = DummyObjectProvider.createFor(ClientCompletionKey.class, "SCORE_HOLDER");

    public static ClientCompletionKey SCOREBOARD_SLOT = DummyObjectProvider.createFor(ClientCompletionKey.class, "SCOREBOARD_SLOT");

    public static ClientCompletionKey SWIZZLE = DummyObjectProvider.createFor(ClientCompletionKey.class, "SWIZZLE");

    public static ClientCompletionKey TEAM = DummyObjectProvider.createFor(ClientCompletionKey.class, "TEAM");

    /**
     * Completions will attempt to return arguments that represent
     * {@link Text}s
     */
    public static ClientCompletionKey TEXT = DummyObjectProvider.createFor(ClientCompletionKey.class, "TEXT");

    public static ClientCompletionKey TIME = DummyObjectProvider.createFor(ClientCompletionKey.class, "TIME");

    /**
     * Completions will attempt to return arguments that represent a
     * real-space position (that is, two-dimensional decimal co-ordinates).
     */
    public static ClientCompletionKey VECTOR_2D = DummyObjectProvider.createFor(ClientCompletionKey.class, "VECTOR_2D");

    /**
     * Completions will attempt to return arguments that represent a
     * block position (that is, two-dimensional integer co-ordinates).
     */
    public static ClientCompletionKey VECTOR_2I = DummyObjectProvider.createFor(ClientCompletionKey.class, "VECTOR_2I");

    /**
     * Completions will attempt to return arguments that represent a
     * real-space position (that is, three-dimensional decimal co-ordinates).
     */
    public static ClientCompletionKey VECTOR_3D = DummyObjectProvider.createFor(ClientCompletionKey.class, "VECTOR_3D");

    /**
     * Completions will attempt to return arguments that represent a
     * block position (that is, three-dimensional integer co-ordinates).
     */
    public static ClientCompletionKey VECTOR_3I = DummyObjectProvider.createFor(ClientCompletionKey.class, "VECTOR_3I");

    // SORTFIELDS: OFF

    private ClientCompletionKeys() {
        throw new AssertionError("This cannot be instantiated.");
    }
}
