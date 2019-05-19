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
package org.spongepowered.api.network.packet;

import org.spongepowered.api.Platform;

/**
 * Represents a binding of a {@link Packet} type
 * in a {@link PacketChannel}.
 *
 * @param <M> The packet type
 */
public interface PacketBinding<M extends Packet> {

    /**
     * Gets the opcode that is assigned to the packet.
     *
     * @return The opcode
     */
    int getOpcode();

    /**
     * Gets the type of the {@link Packet}.
     *
     * @return The packet type
     */
    Class<M> getPacketType();

    /**
     * Adds a {@link PacketHandler} a handler for receiving the packet
     * of this binding. The handler is invoked every time the packet is sent to
     * the given side.
     *
     * @param side The platform side the handler should be used on
     * @param handler The handler to add
     * @return This binding, for chaining
     */
    PacketBinding<M> addHandler(Platform.Type side, PacketHandler<? super M> handler);

    /**
     * Adds a {@link PacketHandler} a handler for receiving the packet
     * of this binding. The handler is invoked every time the packet is
     * sent to <strong>either</strong> side.
     *
     * @param handler The handler to add
     * @return This binding, for chaining
     */
    PacketBinding<M> addHandler(PacketHandler<? super M> handler);
}
