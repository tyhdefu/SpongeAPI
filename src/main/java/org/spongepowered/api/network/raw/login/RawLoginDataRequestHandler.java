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
import org.spongepowered.api.network.ChannelBuf;
import org.spongepowered.api.network.NoResponseMessageException;
import org.spongepowered.api.network.RemoteConnection;

/**
 * Handles a raw login data request.
 */
@FunctionalInterface
public interface RawLoginDataRequestHandler {

    /**
     * Handles the request message {@link ChannelBuf} for the given
     * {@link RemoteConnection} and returns a response.
     *
     * <p>Throwing a {@link NoResponseMessageException} will result in
     * a {@link NoResponseMessageException} on the other endpoint of
     * the connection.</p>
     *
     * @param request The request channel buf
     * @param connection The connection that received the message
     * @param side The platform side this request is handled on
     * @return The response message
     */
    ChannelBuf handleMessage(ChannelBuf request, RemoteConnection connection, Platform.Type side);
}
