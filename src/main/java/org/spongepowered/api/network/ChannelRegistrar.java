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
package org.spongepowered.api.network;

import org.spongepowered.api.CatalogKey;
import org.spongepowered.api.Platform;

import java.util.Optional;
import java.util.Set;

/**
 * A registrar handling custom payloads via {@link ChannelBinding}s to and from
 * {@link PlayerConnection}s and the server.
 */
public interface ChannelRegistrar {

    /**
     * Creates a new channel binding for the given channel name. The channel can
     * be used to send and receive messages.
     *
     * @param channelKey The channel key to register
     * @return A new {@link ChannelBinding} instance bound to the channel name
     * @throws ChannelRegistrationException The channel name is too long
     * @throws ChannelRegistrationException The channel name is reserved
     */
    MessageChannel createChannel(CatalogKey channelKey) throws ChannelRegistrationException;

    /**
     * Creates a new raw channel binding. The channel can be used to send and
     * Receive data from {@link ChannelBuf} objects.
     *
     * @param channelKey The channel key to register
     * @return A new {@link ChannelBinding} instance bound to the channel name
     * @throws ChannelRegistrationException The channel name is too long
     * @throws ChannelRegistrationException The channel name is reserved
     * @see #createChannel
     */
    RawDataChannel createRawChannel(CatalogKey channelKey) throws ChannelRegistrationException;

    /**
     * Gets a channel binding if a channel registered by that name exists.
     *
     * @param channelKey The channel key
     * @return The channel if it exists
     */
    Optional<ChannelBinding> getChannel(CatalogKey channelKey);

    /**
     * Gets or creates a {@link MessageChannel} by the
     * given name. If the channel exists and is a indexed message channel, then
     * it is returned. If the channel is not an indexed message channel, then
     * {@link IllegalStateException} is thrown. Otherwise, a new channel is
     * created.
     *
     * @param channelKey The channel key
     * @return A new or existing indexed message channel binding
     * @throws IllegalStateException if the existing channel is not an
     *         IndexedMessageChannel
     * @throws ChannelRegistrationException for same reasons as
     *         {@link #createChannel}.
     */
    default MessageChannel getOrCreate(CatalogKey channelKey) throws ChannelRegistrationException {
        Optional<ChannelBinding> existing = getChannel(channelKey);
        if (existing.isPresent()) {
            if (existing.get() instanceof MessageChannel) {
                return (MessageChannel) existing.get();
            }
            throw new IllegalStateException("Tried to get existing channel "
                    + channelKey + " as an IndexedMessageChannel but found it was a RawDataChannel");
        }
        return createChannel(channelKey);
    }

    /**
     * Gets or creates a {@link RawDataChannel} by the given
     * name. If the channel exists and is a raw data channel, then it is
     * returned. If the channel is not a raw data channel, then
     * {@link IllegalStateException} is thrown. Otherwise, a new channel is
     * created.
     *
     * @param channelKey The channel key
     * @return A new or existing raw data channel binding
     * @throws IllegalStateException if the existing channel is not an
     *         RawDataChannel
     * @throws ChannelRegistrationException for same reasons as
     *         {@link #createRawChannel}.
     */
    default RawDataChannel getOrCreateRaw(CatalogKey channelKey) throws ChannelRegistrationException {
        Optional<ChannelBinding> existing = getChannel(channelKey);
        if (existing.isPresent()) {
            if (existing.get() instanceof RawDataChannel) {
                return (RawDataChannel) existing.get();
            }
            throw new IllegalStateException("Tried to get existing channel "
                    + channelKey + " as a RawDataChannel but found it was an IndexedMessageChannel");
        }
        return createRawChannel(channelKey);
    }

    /**
     * Remove the channel binding from this registrar, freeing up the channel
     * name. All method calls on the channel binding instance will now throw
     * {@link IllegalStateException}.
     *
     * @param channel The channel to unbind
     */
    void unbindChannel(ChannelBinding channel);

    /**
     * Gets the set of registered channels.
     *
     * @param side The side to get the registered channels from
     * @return A copy of the list of channels
     */
    Set<CatalogKey> getRegisteredChannels(Platform.Type side);

    /**
     * Returns whether the given channel name is available for creating with
     * {@link ChannelRegistrar#createChannel}.
     *
     * @param channelKey The channel id to test
     * @return True if available
     */
    boolean isChannelAvailable(CatalogKey channelKey);

}
