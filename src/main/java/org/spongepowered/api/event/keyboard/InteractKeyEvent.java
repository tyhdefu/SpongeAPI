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
package org.spongepowered.api.event.keyboard;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.keyboard.KeyBinding;

import java.time.Duration;

/**
 * Is fired when a {@link Player} interacts with a {@link KeyBinding}.
 */
public interface InteractKeyEvent extends KeyboardEvent {

    /**
     * Is fired when a {@link Player} a pressed the
     * key assigned to a {@link KeyBinding}.
     */
    interface Press extends InteractKeyEvent, Cancellable {

    }

    /**
     * Is fired when a every "tick" when a {@link Player} holds the
     * key assigned to a {@link KeyBinding}.
     */
    interface Update extends InteractKeyEvent, Cancellable {

        /**
         * Gets the {@link Duration} that the key is currently being pressed for.
         *
         * @return The duration that the key is being pressed for
         */
        Duration getPressDuration();
    }

    /**
     * Is fired when a {@link Player} a released the
     * key assigned to a {@link KeyBinding}.
     */
    interface Release extends InteractKeyEvent, Cancellable {

        /**
         * Gets the {@link Duration} that the key was
         * being pressed for before the {@link Player}
         * released it.
         *
         * @return The duration that the key was being pressed for
         */
        Duration getPressDuration();
    }
}
