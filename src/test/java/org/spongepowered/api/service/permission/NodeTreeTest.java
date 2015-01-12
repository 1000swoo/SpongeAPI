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
package org.spongepowered.api.service.permission;

import org.junit.Test;
import org.spongepowered.api.util.Tristate;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class NodeTreeTest {

    @Test
    public void testWithValue() throws Exception {
        final Map<String, Tristate> testPermissions = new HashMap<String, Tristate>();
        testPermissions.put("generate.rainbow", Tristate.TRUE);
        testPermissions.put("generate.sunset", Tristate.FALSE);
        testPermissions.put("generate", Tristate.TRUE);
        testPermissions.put("generate.thunderstorm.explosive", Tristate.FALSE);

        NodeTree oldTree = NodeTree.of(testPermissions);
        assertEquals(Tristate.FALSE, oldTree.get("generate.thunderstorm.explosive"));
        NodeTree newTree = oldTree.withValue("generate.thunderstorm.explosive", Tristate.TRUE);
        assertEquals(Tristate.FALSE, oldTree.get("generate.thunderstorm.explosive"));
        assertEquals(Tristate.TRUE, newTree.get("generate.thunderstorm.explosive"));
    }

    @Test
    public void testWithAll() throws Exception {
        // TODO Implement
    }

    @Test
    public void testCreateFromValues() throws Exception {
        final Map<String, Tristate> testPermissions = new HashMap<String, Tristate>();
        testPermissions.put("generate.rainbow", Tristate.TRUE);
        testPermissions.put("generate.sunset", Tristate.FALSE);
        testPermissions.put("generate", Tristate.TRUE);
        testPermissions.put("generate.thunderstorm.explosive", Tristate.FALSE);

        NodeTree nodes = NodeTree.of(testPermissions, Tristate.UNDEFINED);

        assertEquals(Tristate.TRUE, nodes.get("generate.rainbow"));
        assertEquals(Tristate.TRUE, nodes.get("generate.rainbow.double"));
        assertEquals(Tristate.FALSE, nodes.get("generate.sunset"));
        assertEquals(Tristate.FALSE, nodes.get("generate.sunset.east"));
        assertEquals(Tristate.TRUE, nodes.get("generate.thunderstorm"));
        assertEquals(Tristate.FALSE, nodes.get("generate.thunderstorm.explosive"));
        assertEquals(Tristate.UNDEFINED, nodes.get("random.perm"));
    }
}
