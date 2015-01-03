/*
 * This file is part of Sponge, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered.org <http://www.spongepowered.org>
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
package org.spongepowered.api.net;

import org.spongepowered.api.entity.player.Player;

import java.net.InetAddress;

/**
 * Represents a connection of a client to the server.
 */
public interface PlayerConnection {

    /**
     * Gets the associated Player that this player connection is associated
     * with.
     *
     * @return The associated player
     */
    Player getPlayer();

    /**
     * Gets the {@link InetAddress} of this connection.
     *
     * @return The address
     */
    InetAddress getAddress();

    /**
     * Gets the host name of the connection.
     *
     * @return The host name
     */
    String getHostname();

    /**
     * Pings the connection and returns the duration in milliseconds for the
     * round trip from server to client and back to server.
     *
     * @return The ping
     */
    int ping();

    /**
     * Sends a custom payload to the player connection.
     *
     * @param plugin The instance of the plugin
     * @param channel The channel to send to
     * @param data The data
     */
    void sendCustomPayload(Object plugin, String channel, byte[] data);
}
