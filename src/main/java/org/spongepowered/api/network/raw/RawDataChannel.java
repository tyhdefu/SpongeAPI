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
package org.spongepowered.api.network.raw;

import org.spongepowered.api.Platform;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ChannelBuf;
import org.spongepowered.api.network.ClientConnection;
import org.spongepowered.api.network.raw.login.RawLoginDataChannel;

import java.util.function.Consumer;

/**
 * Represents a channel that sends and receives raw data. This
 * channel cannot be used during the login phase of a
 * {@link ClientConnection}, use {@link RawLoginDataChannel}
 * instead.
 */
public interface RawDataChannel extends ChannelBinding {

    /**
     * Adds a handler to this channel that is invoked every time data is
     * sent to it on <strong>either</strong> side.
     *
     * @param handler The handler
     */
    void addHandler(RawDataHandler handler);

    /**
     * Adds a listener to this channel that is invoked every time data is
     * sent to it on the given side.
     *
     * @param side The side to listen to data on
     * @param handler The handler
     */
    void addHandler(Platform.Type side, RawDataHandler handler);

    /**
     * Removes the handler from handling data.
     *
     * @param handler The handler
     */
    void removeHandler(RawDataHandler handler);

    /**
     * Sends the raw payload to the player across this channel. The data may
     * not be sent if the player doesn't have a registered handler.
     *
     * @param player The player to send the message to
     * @param payload A consumer to write the data to
     */
    default void sendTo(Player player, Consumer<ChannelBuf> payload) {
        sendTo(player.getConnection(), payload);
    }

    /**
     * Sends the raw payload to the player across this channel. The data may
     * not be sent if the player doesn't have a registered handler.
     *
     * @param connection The client connection to send the message to
     * @param payload A consumer to write the data to
     */
    void sendTo(ClientConnection connection, Consumer<ChannelBuf> payload);

    /**
     * Sends the raw payload to the server. The data may not be sent if
     * there is no registered handler. This <strong>must</strong> be called
     * from the client side.
     *
     * @param payload A consumer to write the data to
     */
    void sendToServer(Consumer<ChannelBuf> payload);

    /**
     * Sends the raw payload to all players on the server.
     *
     * @param payload A consumer to write the data to
     */
    void sendToAll(Consumer<ChannelBuf> payload);

}
