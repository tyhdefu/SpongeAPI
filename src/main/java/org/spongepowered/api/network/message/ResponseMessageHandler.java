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
import org.spongepowered.api.network.NoResponseMessageException;
import org.spongepowered.api.network.RemoteConnection;

/**
 * A specialized {@link MessageHandler} to handle {@link ResponseMessage}s.
 *
 * @param <M> The type of the request message
 * @param <R> The type of the response message
 */
@FunctionalInterface
public interface ResponseMessageHandler<M extends RequestMessage<R>, R extends ResponseMessage> {

    /**
     * Handles the {@link ResponseMessage} sent by a client connection.
     *
     * @param responseMessage The response message that was received
     * @param requestMessage The message that was send to request the response
     * @param connection The connection that sent the message
     * @param side The side the message was received on (
     *        {@link org.spongepowered.api.Platform.Type#CLIENT}
     *        or {@link org.spongepowered.api.Platform.Type#SERVER})
     */
    void handleResponse(R responseMessage, M requestMessage, RemoteConnection connection, Platform.Type side);

    /**
     * Handles the failure of a {@link ResponseMessage}. The {@link MessageException} which
     * causes the failure will usually be a {@link NoResponseMessageException}, this is caused
     * by the other endpoint ignoring the request or failing to send a response.
     *
     * @param requestMessage The message that was send to request the response
     * @param connection The remote connection that received the failure
     * @param side The platform side the failure is received on
     * @param exception The exception that caused the failure
     */
    default void handleFailure(M requestMessage, RemoteConnection connection, Platform.Type side, MessageException exception) {
    }
}
