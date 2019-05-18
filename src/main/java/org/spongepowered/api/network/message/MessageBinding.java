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
package org.spongepowered.api.network.message;

import org.spongepowered.api.Platform;

/**
 * Represents a binding of a {@link Message} type
 * in a {@link MessageChannel}.
 *
 * @param <M> The message type
 */
public interface MessageBinding<M extends Message> {

    /**
     * Gets the opcode that is assigned to the message.
     *
     * @return The opcode
     */
    int getOpcode();

    /**
     * Gets the type of the {@link Message}.
     *
     * @return The message type
     */
    Class<M> getMessageType();

    /**
     * Adds a {@link MessageHandler} a handler for receiving the message
     * of this binding. The handler is invoked every time the message is sent to
     * the given side.
     *
     * @param side The platform side the handler should be used on
     * @param handler The handler to add
     * @return This binding, for chaining
     */
    MessageBinding<M> addHandler(Platform.Type side, MessageHandler<? super M> handler);

    /**
     * Adds a {@link MessageHandler} a handler for receiving the message
     * of this binding. The handler is invoked every time the message is
     * sent to <strong>either</strong> side.
     *
     * @param handler The handler to add
     * @return This binding, for chaining
     */
    MessageBinding<M> addHandler(MessageHandler<? super M> handler);
}
