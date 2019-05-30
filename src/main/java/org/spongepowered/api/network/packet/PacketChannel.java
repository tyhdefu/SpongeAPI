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

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.network.ClientConnection;
import org.spongepowered.api.network.NoResponseException;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Represents a channel binding that sends and receives packets.
 */
public interface PacketChannel extends ChannelBinding {

    /**
     * Register a packet class to this channel without a receiving handler.
     * This will only allow the packet to be sent, this channel binding
     * will <strong>not</strong> be able to receive the packet.
     *
     * <p>The packet opcode is used to identify this packet class as it is
     * sent and received across the network, it is encoded as a varint.</p>
     *
     * @param packetClass The class of the packet being registered. Note:
     *        the class must have a publicly accessible no-args constructor
     * @param packetOpcode A unique opcode for this packet
     * @return The created packet binding
     */
    <M extends Packet> HandlerPacketBinding<M> register(Class<M> packetClass, int packetOpcode);

    /**
     * Gets the {@link PacketBinding} for the given packet class, if the packet
     * type is registered to this channel.
     *
     * @param packetClass The packet class
     * @param <M> The type of the packet
     * @return The packet binding, if found
     */
    <M extends Packet> Optional<PacketBinding<M>> getBinding(Class<M> packetClass);

    /**
     * Register a request/response packet class pair to this channel. A receiving handler
     * isn't required when sending the request using {@link #sendTo(ClientConnection, RequestPacket)}.
     *
     * <p>The packet opcode is used to identify this packet class as it is
     * sent and received across the network, it is encoded as a varint.</p>
     *
     * @param packetOpcode A unique opcode for this request/response packet pair
     * @param requestPacketClass The class of the request packet being registered
     * @param responsePacketClass The class of the response packet being registered
     * @return The created transactional packet binding
     */
    <M extends RequestPacket<R>, R extends ResponsePacket> TransactionalPacketBinding<M, R> register(
            Class<M> requestPacketClass, Class<R> responsePacketClass, int packetOpcode);

    /**
     * Gets the {@link TransactionalPacketBinding} for the given {@link RequestPacket} class, if
     * the type is registered to this channel as a transactional binding.
     *
     * @param requestPacket The request packet class
     * @param <M> The type of the request packet
     * @param <R> The type of the response packet
     * @return The transactional packet binding, if found
     */
    <M extends RequestPacket<R>, R extends ResponsePacket> Optional<TransactionalPacketBinding<M, R>> getTransactionalBinding(
            Class<M> requestPacket);

    /**
     * Sends the packet to the player using this channel. The packet may
     * not be sent if the player doesn't have a registered handler.
     *
     * @param player The player to send the packet to
     * @param packet The packet to send
     */
    default void sendTo(Player player, Packet packet) {
        sendTo(player.getConnection(), packet);
    }

    /**
     * Sends the {@link Packet} to the client using this channel.
     *
     * <p>A exception will be thrown if the specified packet type
     * isn't registered in this {@link PacketChannel}.</p>
     *
     * @param connection The player connection to send the packet to
     * @param packet The packet to send
     */
    void sendTo(ClientConnection connection, Packet packet);

    /**
     * Sends the {@link Packet} to the player using this channel. This method returns a
     * {@link CompletableFuture} that will be accepted once the response is received.
     *
     * <p>The {@link CompletableFuture} may fail exceptionally by a
     * {@link NoResponseException} if there wasn't a valid response
     * received for the given request.</p>
     *
     * <p>A exception will be thrown if the specified packet type
     * isn't registered in this {@link PacketChannel}.</p>
     *
     * @param player The player to send the packet to
     * @param packet The request packet to send
     * @return The completable future to handle the response packet and exceptions
     * @throws IllegalArgumentException If the given packet type isn't registered in this channel binding
     */
    default <R extends ResponsePacket> CompletableFuture<R> sendTo(Player player, RequestPacket<R> packet) {
        return sendTo(player.getConnection(), packet);
    }

    /**
     * Sends the {@link Packet} to the client using this channel. This method returns a
     * {@link CompletableFuture} that will be accepted once the response is received.
     *
     * <p>The {@link CompletableFuture} may fail exceptionally by a
     * {@link NoResponseException} if there wasn't a valid response
     * received for the given request.</p>
     *
     * <p>A exception will be thrown if the specified packet type
     * isn't registered in this {@link PacketChannel}.</p>
     *
     * @param connection The player connection to send the packet to
     * @param packet The request packet to send
     * @return The completable future to handle the response packet and exceptions
     * @throws IllegalArgumentException If the given packet type isn't registered in this channel binding
     */
    <R extends ResponsePacket> CompletableFuture<R> sendTo(ClientConnection connection, RequestPacket<R> packet);

    /**
     * Sends the {@link Packet} to the server using this channel. This method returns a
     * {@link CompletableFuture} that will be accepted once the response is received.
     *
     * <p>The {@link CompletableFuture} may fail exceptionally by a
     * {@link NoResponseException} if there wasn't a valid response
     * received for the given request.</p>
     *
     * <p>A exception will be thrown if the specified packet type
     * isn't registered in this {@link PacketChannel}.</p>
     *
     * @param packet The packet to send to the server
     * @return The completable future to handle the response packet and exceptions
     */
    <R extends ResponsePacket> CompletableFuture<R> sendToServer(RequestPacket<R> packet);

    /**
     * Sends the {@link Packet} to the server.
     *
     * <p>Calling this during the login phase is currently not supported, only
     * responses to {@link RequestPacket}s by {@link RequestPacketHandler}s
     * are. Doing so will result in a {@link IllegalStateException} when attempting
     * to use the method.</p>
     *
     * <p>A exception will be thrown if the specified packet type
     * isn't registered in this {@link PacketChannel}.</p>
     *
     * @param packet The packet to send
     * @throws IllegalStateException If the server connection isn't in the play phase
     */
    void sendToServer(Packet packet);

    /**
     * Sends the {@link Packet} to all players on the server.
     *
     * <p>A exception will be thrown if the specified packet type
     * isn't registered in this {@link PacketChannel}.</p>
     *
     * @param packet The packet to send
     */
    void sendToAll(Packet packet);
}
