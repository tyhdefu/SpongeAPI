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

import org.spongepowered.api.Platform;
import org.spongepowered.api.entity.living.player.Player;

import java.util.concurrent.CompletableFuture;

/**
 * Represents a channel binding that sends and receives messages.
 */
public interface MessageChannel extends ChannelBinding {

    /**
     * Register a message class to this channel without a receiving handler.
     * This will only allow the message to be sent, this channel binding
     * will <strong>not</strong> be able to receive the message.
     *
     * <p>The message ID is used to identify this message class as it is
     * sent and received across the network, it is encoded as a varint.</p>
     *
     * @param messageClass The class of the message being registered. Note:
     *        the class must have a publicly accessible no-args constructor
     * @param messageId A unique ID for this message
     */
    void registerMessage(Class<? extends Message> messageClass, int messageId);

    /**
     * Register a request/response message class pair to this channel. A receiving handler
     * isn't required when sending the request using {@link #sendTo(ClientConnection, RequestMessage)}.
     *
     * <p>The message ID is used to identify this message class as it is
     * sent and received across the network, it is encoded as a varint.</p>
     *
     * @param messageId A unique id for this request/response message pair
     * @param requestMessageClass The class of the request message being registered
     * @param responseMessageClass The class of the response message being registered
     */
    <M extends RequestMessage<R>, R extends ResponseMessage> void registerMessage(
            Class<M> requestMessageClass, Class<R> responseMessageClass, int messageId);

    /**
     * Register a request/response message class pair to this channel. A receiving handler
     * isn't required when sending the request using {@link #sendTo(ClientConnection, RequestMessage)}.
     * Request/response messages will be able to be send in both directions.
     *
     * <p>The message id is used to identify this message class as it is
     * sent and received across the network, it is encoded as a varint.</p>
     *
     * @param messageId A unique id for this request/response message pair
     * @param requestMessageClass The class of the request message being registered
     * @param responseMessageClass The class of the response message being registered
     * @param requestHandler The handler of the request message which will produce responses
     */
    <M extends RequestMessage<R>, R extends ResponseMessage> void registerMessage(Class<M> requestMessageClass, Class<R> responseMessageClass,
            int messageId, RequestMessageHandler<M, R> requestHandler);

    /**
     * Register a request/response message class pair to this channel. A receiving handler
     * isn't required when sending the request using {@link #sendTo(ClientConnection, RequestMessage)}.
     * Request messages will be handled on a specific platform side, this means that requests
     * can only be send in one direction and responses in the other direction.
     *
     * <p>The message id is used to identify this message class as it is
     * sent and received across the network, it is encoded as a varint.</p>
     *
     * @param messageId A unique id for this request/response message pair
     * @param requestMessageClass The class of the request message being registered
     * @param responseMessageClass The class of the response message being registered
     * @param requestHandleSide The side to listen to handle request messages on
     * @param requestHandler The handler of the request message which will produce responses
     */
    <M extends RequestMessage<R>, R extends ResponseMessage> void registerMessage(Class<M> requestMessageClass, Class<R> responseMessageClass,
            int messageId, Platform.Type requestHandleSide, RequestMessageHandler<M, R> requestHandler);

    /**
     * Register a message class to this channel and a handler for receiving
     * the message. The handler is invoked every time the message is sent to
     * <strong>either</strong> side.
     *
     * <p>The message ID is used to identify this message class as it is
     * sent and received across the network, it is encoded as a varint.</p>
     *
     * @param messageClass The class of the message being registered
     * @param messageId A unique ID for this message
     * @param handler The handler that acts upon the message being received
     */
    <M extends Message> void registerMessage(Class<M> messageClass, int messageId, MessageHandler<M> handler);

    /**
     * Register a message class to this channel and a handler for receiving
     * the message. The handler is invoked every time the message is sent to
     * the given side.
     *
     * @param messageClass The class of the message being registered
     * @param messageId A unique ID for this message
     * @param side The side to listen to messages on
     * @param handler The handler that acts upon the message being received
     */
    <M extends Message> void registerMessage(Class<M> messageClass, int messageId, Platform.Type side, MessageHandler<M> handler);

    /**
     * Register a {@link MessageHandler} for a {@link Message}.
     * @param messageClass The class to handle
     * @param side The side being handled
     * @param handler The handler
     * @param <M> The type of message
     */
    <M extends Message> void addHandler(Class<M> messageClass, Platform.Type side, MessageHandler<M> handler);

    /**
     * Register a {@link MessageHandler} for a {@link Message}
     * @param messageClass The class to handle
     * @param handler The handler
     * @param <M> The type of message
     */
    <M extends Message> void addHandler(Class<M> messageClass, MessageHandler<M> handler);

    /**
     * Sends the message to the player using this channel. The message may
     * not be sent if the player doesn't have a registered handler.
     *
     * @param player The player to send the message to
     * @param message The message to send
     */
    default void sendTo(Player player, Message message) {
        sendTo(player.getConnection(), message);
    }

    /**
     * Sends the message to the player across this channel. The message may
     * not be sent if the player doesn't have a registered handler.
     *
     * @param connection The player connection to send the message to
     * @param message The message to send
     */
    void sendTo(ClientConnection connection, Message message);

    /**
     * Sends the message to the player using this channel. This method returns a
     * {@link CompletableFuture} that will be accepted once the response is received.
     *
     * <p>The {@link CompletableFuture} may fail exceptionally by a
     * {@link NoResponseMessageException} if there wasn't a valid response
     * received for the given request.</p>
     *
     * <p>A exception will be thrown if the specified message type
     * isn't registered in this {@link ChannelBinding}.</p>
     *
     * @param player The player to send the message to
     * @param message The request message to send
     * @return The completable future to handle the response message and exceptions
     * @throws IllegalArgumentException If the given message type isn't registered in this channel binding
     */
    default <R extends ResponseMessage> CompletableFuture<R> sendTo(Player player, RequestMessage<R> message) {
        return sendTo(player.getConnection(), message);
    }

    /**
     * Sends the message to the player using this channel. This method returns a
     * {@link CompletableFuture} that will be accepted once the response is received.
     *
     * <p>The {@link CompletableFuture} may fail exceptionally by a
     * {@link NoResponseMessageException} if there wasn't a valid response
     * received for the given request.</p>
     *
     * <p>A exception will be thrown if the specified message type
     * isn't registered in this {@link ChannelBinding}.</p>
     *
     * @param connection The player connection to send the message to
     * @param message The request message to send
     * @return The completable future to handle the response message and exceptions
     * @throws IllegalArgumentException If the given message type isn't registered in this channel binding
     */
    <R extends ResponseMessage> CompletableFuture<R> sendTo(ClientConnection connection, RequestMessage<R> message);

    /**
     * Sends the message to the server using this channel. This method returns a
     * {@link CompletableFuture} that will be accepted once the response is received.
     *
     * <p>The {@link CompletableFuture} may fail exceptionally by a
     * {@link NoResponseMessageException} if there wasn't a valid response
     * received for the given request.</p>
     *
     * <p>A exception will be thrown if the specified message type
     * isn't registered in this {@link ChannelBinding}.</p>
     *
     * @param message The message to send to the server
     * @return The completable future to handle the response message and exceptions
     */
    <R extends ResponseMessage> CompletableFuture<R> sendToServer(RequestMessage<R> message);

    /**
     * Sends the message to the server. The message may not be sent if there
     * is no registered handler. This <strong>must</strong> be called from
     * the client side.
     *
     * @param message The message to send
     */
    void sendToServer(Message message);

    /**
     * Sends the message to all players on the server.
     *
     * @param message The message to send
     */
    void sendToAll(Message message);
}
