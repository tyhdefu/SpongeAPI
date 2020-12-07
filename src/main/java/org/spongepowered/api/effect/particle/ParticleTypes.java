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
package org.spongepowered.api.effect.particle;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.entity.living.player.Player;

import java.util.function.Supplier;

/**
 * An enumeration of all possible {@link ParticleType}s in vanilla minecraft.
 */
public final class ParticleTypes {

    // SORTFIELDS:ON

    public static final Supplier<ParticleType> AMBIENT_ENTITY_EFFECT = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "ambient_entity_effect");

    public static final Supplier<ParticleType> ANGRY_VILLAGER = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "angry_villager");

    public static final Supplier<ParticleType> ASH = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "ash");

    public static final Supplier<ParticleType> BARRIER = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "barrier");

    public static final Supplier<ParticleType> BLOCK = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "block");

    public static final Supplier<ParticleType> BUBBLE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "bubble");

    public static final Supplier<ParticleType> BUBBLE_COLUMN_UP = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "bubble_column_up");

    public static final Supplier<ParticleType> BUBBLE_POP = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "bubble_pop");

    public static final Supplier<ParticleType> CAMPFIRE_COSY_SMOKE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "campfire_cosy_smoke");

    public static final Supplier<ParticleType> CAMPFIRE_SIGNAL_SMOKE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "campfire_signal_smoke");

    public static final Supplier<ParticleType> CLOUD = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "cloud");

    public static final Supplier<ParticleType> COMPOSTER = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "composter");

    public static final Supplier<ParticleType> CRIMSON_SPORE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "crimson_spore");

    public static final Supplier<ParticleType> CRIT = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "crit");

    public static final Supplier<ParticleType> CURRENT_DOWN = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "current_down");

    public static final Supplier<ParticleType> DAMAGE_INDICATOR = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "damage_indicator");

    public static final Supplier<ParticleType> DOLPHIN = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "dolphin");

    public static final Supplier<ParticleType> DRAGON_BREATH = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "dragon_breath");

    public static final Supplier<ParticleType> DRIPPING_HONEY = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "dripping_honey");

    public static final Supplier<ParticleType> DRIPPING_LAVA = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "dripping_lava");

    public static final Supplier<ParticleType> DRIPPING_OBSIDIAN_TEAR = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "dripping_obsidian_tear");

    public static final Supplier<ParticleType> DRIPPING_WATER = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "dripping_water");

    public static final Supplier<ParticleType> DUST = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "dust");

    public static final Supplier<ParticleType> EFFECT = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "effect");

    public static final Supplier<ParticleType> ELDER_GUARDIAN = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "elder_guardian");

    public static final Supplier<ParticleType> ENCHANT = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "enchant");

    public static final Supplier<ParticleType> ENCHANTED_HIT = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "enchanted_hit");

    public static final Supplier<ParticleType> END_ROD = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "end_rod");

    public static final Supplier<ParticleType> ENTITY_EFFECT = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "entity_effect");

    public static final Supplier<ParticleType> EXPLOSION = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "explosion");

    public static final Supplier<ParticleType> EXPLOSION_EMITTER = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "explosion_emitter");

    public static final Supplier<ParticleType> FALLING_DUST = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "falling_dust");

    public static final Supplier<ParticleType> FALLING_HONEY = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "falling_honey");

    public static final Supplier<ParticleType> FALLING_LAVA = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "falling_lava");

    public static final Supplier<ParticleType> FALLING_NECTAR = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "falling_nectar");

    public static final Supplier<ParticleType> FALLING_OBSIDIAN_TEAR = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "falling_obsidian_tear");

    public static final Supplier<ParticleType> FALLING_WATER = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "falling_water");

    public static final Supplier<ParticleType> FIREWORK = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "firework");

    public static final Supplier<ParticleType> FISHING = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "fishing");

    public static final Supplier<ParticleType> FLAME = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "flame");

    public static final Supplier<ParticleType> FLASH = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "flash");

    public static final Supplier<ParticleType> HAPPY_VILLAGER = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "happy_villager");

    public static final Supplier<ParticleType> HEART = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "heart");

    public static final Supplier<ParticleType> INSTANT_EFFECT = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "instant_effect");

    public static final Supplier<ParticleType> ITEM = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "item");

    public static final Supplier<ParticleType> ITEM_SLIME = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "item_slime");

    public static final Supplier<ParticleType> ITEM_SNOWBALL = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "item_snowball");

    public static final Supplier<ParticleType> LANDING_HONEY = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "landing_honey");

    public static final Supplier<ParticleType> LANDING_LAVA = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "landing_lava");

    public static final Supplier<ParticleType> LANDING_OBSIDIAN_TEAR = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "landing_obsidian_tear");

    public static final Supplier<ParticleType> LARGE_SMOKE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "large_smoke");

    public static final Supplier<ParticleType> LAVA = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "lava");

    public static final Supplier<ParticleType> MYCELIUM = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "mycelium");

    public static final Supplier<ParticleType> NAUTILUS = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "nautilus");

    public static final Supplier<ParticleType> NOTE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "note");

    public static final Supplier<ParticleType> POOF = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "poof");

    public static final Supplier<ParticleType> PORTAL = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "portal");

    public static final Supplier<ParticleType> RAIN = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "rain");

    public static final Supplier<ParticleType> REVERSE_PORTAL = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "reverse_portal");

    public static final Supplier<ParticleType> SMOKE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "smoke");

    public static final Supplier<ParticleType> SNEEZE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "sneeze");

    public static final Supplier<ParticleType> SOUL = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "soul");

    public static final Supplier<ParticleType> SOUL_FIRE_FLAME = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "soul_fire_flame");

    public static final Supplier<ParticleType> SPIT = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "spit");

    public static final Supplier<ParticleType> SPLASH = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "splash");

    public static final Supplier<ParticleType> SQUID_INK = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "squid_ink");

    public static final Supplier<ParticleType> SWEEP_ATTACK = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "sweep_attack");

    public static final Supplier<ParticleType> TOTEM_OF_UNDYING = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "totem_of_undying");

    public static final Supplier<ParticleType> UNDERWATER = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "underwater");

    public static final Supplier<ParticleType> WARPED_SPORE = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "warped_spore");

    public static final Supplier<ParticleType> WHITE_ASH = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "white_ash");

    public static final Supplier<ParticleType> WITCH = Sponge.getRegistry().getCatalogRegistry().provideSupplier(ParticleType.class, "witch");

    // SORTFIELDS:OFF

    // Suppress default constructor to ensure non-instantiability.
    private ParticleTypes() {
        throw new AssertionError("You should not be attempting to instantiate this class.");
    }

}
