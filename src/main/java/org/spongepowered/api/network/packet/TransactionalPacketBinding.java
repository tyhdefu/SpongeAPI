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
import org.spongepowered.api.network.ClientConnection;

import java.util.concurrent.CompletableFuture;

/**
 * A packet binding that represents a request/response pair. One side of a connection
 * can send a {@link RequestPacket} and the other side will normally respond with a
 * {@link ResponsePacket}.
 *
 * <p>Request/response pairs share an opcode, a additional byte will specify whether it's a
 * request, response or a no-response.</p>
 *
 * @param <P> The type of the request packet
 * @param <R> The type of the response packet
 */
public interface TransactionalPacketBinding<P extends RequestPacket<R>, R extends ResponsePacket> {

    /**
     * Gets the opcode of the {@link TransactionalPacketBinding}. This opcode
     * will be shared by the request and response {@link Packet}s.
     *
     * @return The opcode
     */
    int getOpcode();

    /**
     * Gets the {@link PacketBinding} of the request packet type.
     *
     * @return The request packet binding
     */
    PacketBinding<P> getRequestBinding();

    /**
     * Gets the {@link PacketBinding} of the response packet type.
     *
     * @return The response packet binding
     */
    PacketBinding<R> getResponseBinding();

    /**
     * Sets the {@link RequestPacketHandler} to handle a {@link RequestPacket}
     * on a specific platform side. The difference with a normal {@link PacketHandler}
     * is that it requires a {@link ResponsePacket} as return value.
     *
     * @param requestHandleSide The side the request packet should be handled on
     * @param requestHandler The handler of the request packet
     * @return This packet binding, for chaining
     */
    TransactionalPacketBinding<P, R> setRequestHandler(Platform.Type requestHandleSide, RequestPacketHandler<P, R> requestHandler);

    /**
     * Sets the {@link RequestPacketHandler} to handle a {@link RequestPacket}
     * on both platform sides. The difference with a normal {@link PacketHandler}
     * is that it requires a {@link ResponsePacket} as return value.
     *
     * @param requestHandler The handler of the request packet
     * @return This packet binding, for chaining
     */
    TransactionalPacketBinding<P, R> setRequestHandler(RequestPacketHandler<P, R> requestHandler);

    /**
     * Adds a {@link PacketHandler} to handle a {@link ResponsePacket}
     * on a specific platform side.
     *
     * <p>Binding a response {@link PacketHandler} is completely optional. It is also
     * possible to append a handler to the {@link CompletableFuture} returned by
     * {@link PacketChannel#sendTo(ClientConnection, RequestPacket)}.</p>
     *
     * @param responseHandleSide The side the response packet should be handled on
     * @param responseHandler The handler of the response packet
     * @return This packet binding, for chaining
     */
    TransactionalPacketBinding<P, R> addResponseHandler(Platform.Type responseHandleSide, PacketHandler<R> responseHandler);

    /**
     * Adds a {@link ResponsePacketHandler} to handle a {@link ResponsePacket}
     * on a specific platform side.
     *
     * <p>Binding a response {@link PacketHandler} is completely optional. It is also
     * possible to append a handler to the {@link CompletableFuture} returned by
     * {@link PacketChannel#sendTo(ClientConnection, RequestPacket)}.</p>
     *
     * @param responseHandleSide The side the response packet should be handled on
     * @param responseHandler The handler of the response packet
     * @return This packet binding, for chaining
     */
    TransactionalPacketBinding<P, R> addResponseHandler(Platform.Type responseHandleSide, ResponsePacketHandler<P, R> responseHandler);

    /**
     * Adds a {@link PacketHandler} to handle a {@link ResponsePacket}
     * on both platform sides.
     *
     * <p>Binding a response {@link PacketHandler} is completely optional. It is also
     * possible to append a handler to the {@link CompletableFuture} returned by
     * {@link PacketChannel#sendTo(ClientConnection, RequestPacket)}.</p>
     *
     * @param responseHandler The handler of the response packet
     * @return This packet binding, for chaining
     */
    TransactionalPacketBinding<P, R> addResponseHandler(PacketHandler<R> responseHandler);

    /**
     * Adds a {@link ResponsePacketHandler} to handle a {@link ResponsePacket}
     * on both platform sides.
     *
     * <p>Binding a response {@link PacketHandler} is completely optional. It is also
     * possible to append a handler to the {@link CompletableFuture} returned by
     * {@link PacketChannel#sendTo(ClientConnection, RequestPacket)}.</p>
     *
     * @param responseHandler The handler of the response packet
     * @return This packet binding, for chaining
     */
    TransactionalPacketBinding<P, R> addResponseHandler(ResponsePacketHandler<P, R> responseHandler);
}
