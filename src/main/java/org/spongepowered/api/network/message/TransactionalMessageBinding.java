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
import org.spongepowered.api.network.ClientConnection;

import java.util.concurrent.CompletableFuture;

/**
 * A message binding that represents a request/response pair. One endpoint of a connection
 * can send a {@link RequestMessage} and the other endpoint will normally respond with a
 * {@link ResponseMessage}.
 *
 * <p>Request/response pairs share a opcode, a additional byte will specify whether it's a
 * request, response or a no-response.</p>
 *
 * @param <M> The type of the request message
 * @param <R> The type of the response message
 */
public interface TransactionalMessageBinding<M extends RequestMessage<R>, R extends ResponseMessage> {

    /**
     * Gets the opcode of the {@link TransactionalMessageBinding}. This opcode
     * will be shared by the request and response {@link Message}s.
     *
     * @return The opcode
     */
    int getOpcode();

    /**
     * Gets the {@link MessageBinding} of the request message type.
     *
     * @return The request message binding
     */
    MessageBinding<M> getRequestBinding();

    /**
     * Gets the {@link MessageBinding} of the response message type.
     *
     * @return The response message binding
     */
    MessageBinding<R> getResponseBinding();

    /**
     * Adds a {@link RequestMessageHandler} to handle a {@link RequestMessage}
     * on a specific platform side. The difference with a normal {@link MessageHandler}
     * is that it requires a {@link ResponseMessage} as return value.
     *
     * @param requestHandleSide The side the request message should be handled on
     * @param requestHandler The handler of the request message
     * @return This message binding, for chaining
     */
    TransactionalMessageBinding<M, R> addRequestHandler(Platform.Type requestHandleSide, RequestMessageHandler<M, R> requestHandler);

    /**
     * Adds a {@link RequestMessageHandler} to handle a {@link RequestMessage}
     * on both platform sides. The difference with a normal {@link MessageHandler}
     * is that it requires a {@link ResponseMessage} as return value.
     *
     * @param requestHandler The handler of the request message
     * @return This message binding, for chaining
     */
    TransactionalMessageBinding<M, R> addRequestHandler(RequestMessageHandler<M, R> requestHandler);

    /**
     * Adds a {@link MessageHandler} to handle a {@link ResponseMessage}
     * on a specific platform side.
     *
     * <p>Binding a response {@link MessageHandler} is completely optional. It is also
     * possible to append a handler to the {@link CompletableFuture} returned by
     * {@link MessageChannel#sendTo(ClientConnection, RequestMessage)}.</p>
     *
     * @param responseHandleSide The side the response message should be handled on
     * @param responseHandler The handler of the response message
     * @return This message binding, for chaining
     */
    TransactionalMessageBinding<M, R> addReponseHandler(Platform.Type responseHandleSide, MessageHandler<R> responseHandler);

    /**
     * Adds a {@link ResponseMessageHandler} to handle a {@link ResponseMessage}
     * on a specific platform side.
     *
     * <p>Binding a response {@link MessageHandler} is completely optional. It is also
     * possible to append a handler to the {@link CompletableFuture} returned by
     * {@link MessageChannel#sendTo(ClientConnection, RequestMessage)}.</p>
     *
     * @param responseHandleSide The side the response message should be handled on
     * @param responseHandler The handler of the response message
     * @return This message binding, for chaining
     */
    TransactionalMessageBinding<M, R> addReponseHandler(Platform.Type responseHandleSide, ResponseMessageHandler<M, R> responseHandler);

    /**
     * Adds a {@link MessageHandler} to handle a {@link ResponseMessage}
     * on both platform sides.
     *
     * <p>Binding a response {@link MessageHandler} is completely optional. It is also
     * possible to append a handler to the {@link CompletableFuture} returned by
     * {@link MessageChannel#sendTo(ClientConnection, RequestMessage)}.</p>
     *
     * @param responseHandler The handler of the response message
     * @return This message binding, for chaining
     */
    TransactionalMessageBinding<M, R> addReponseHandler(MessageHandler<R> responseHandler);

    /**
     * Adds a {@link ResponseMessageHandler} to handle a {@link ResponseMessage}
     * on both platform sides.
     *
     * <p>Binding a response {@link MessageHandler} is completely optional. It is also
     * possible to append a handler to the {@link CompletableFuture} returned by
     * {@link MessageChannel#sendTo(ClientConnection, RequestMessage)}.</p>
     *
     * @param responseHandler The handler of the response message
     * @return This message binding, for chaining
     */
    TransactionalMessageBinding<M, R> addReponseHandler(ResponseMessageHandler<M, R> responseHandler);
}
