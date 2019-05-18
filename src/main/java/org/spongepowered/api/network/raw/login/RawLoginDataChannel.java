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
package org.spongepowered.api.network.raw.login;

import org.spongepowered.api.Platform;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ChannelBuf;
import org.spongepowered.api.network.ClientConnection;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

/**
 * Represents a raw login data channel. This channel can only send
 * messages to a {@link ClientConnection} during its login phase.
 */
public interface RawLoginDataChannel extends ChannelBinding {

    /**
     * Sets the {@link RawLoginDataRequestHandler} on the given platform side.
     *
     * @param side The platform side the handler should be set on
     * @param handler The handler to set
     */
    void setRequestHandler(Platform.Type side, RawLoginDataRequestHandler handler);

    /**
     * Sets the {@link RawLoginDataRequestHandler} on the
     * both the client and server sides.
     *
     * @param handler The handler to set
     */
    void setRequestHandler(RawLoginDataRequestHandler handler);

    /**
     * Sends a request message {@link ChannelBuf} to
     * the {@link ClientConnection}.
     *
     * @param connection The client connection to send the request to
     * @param payload The payload provider of the request
     * @return The completable future of the response
     */
    CompletableFuture<ChannelBuf> sendTo(ClientConnection connection, Consumer<ChannelBuf> payload);
}
