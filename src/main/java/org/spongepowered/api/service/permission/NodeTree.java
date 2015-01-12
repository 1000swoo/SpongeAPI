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

import org.spongepowered.api.util.Tristate;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.regex.Pattern;

/**
 * An immutable tree structure for determining node data. Any changes will create new copies of the necessary tree objects.
 * Keys are case-insensitive.
 * Segments of nodes are split by the '.' character
 */
public class NodeTree {
    private static final Pattern SPLIT_REGEX = Pattern.compile("\\.");
    private final Node rootNode;


    private static class Node {
        private Tristate value = Tristate.UNDEFINED;
        private final Map<String, Node> children;

        private Node(Map<String, Node> children) {
            this.children = children;
        }
    }

    private NodeTree(Tristate value) {
        this.rootNode = new Node(new HashMap<String, Node>());
        this.rootNode.value = value;
    }

    private NodeTree(Node rootNode) {
        this.rootNode = rootNode;
    }

    public Tristate get(String node) {
        String[] parts = SPLIT_REGEX.split(node.toLowerCase());
        Node currentNode = this.rootNode;
        Tristate lastUndefinedVal = Tristate.UNDEFINED;
        for (String str : parts) {
            if (!currentNode.children.containsKey(str)) {
                break;
            }
            currentNode = currentNode.children.get(str);
            if (currentNode.value != Tristate.UNDEFINED) {
                lastUndefinedVal = currentNode.value;
            }
        }
        return lastUndefinedVal;

    }

    public Map<String, Tristate> asMap() {
        Map<String, Tristate> ret = new HashMap<String, Tristate>();
        Queue<String> keys = new LinkedList<String>();
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public NodeTree withValue(String node, Tristate value) {
        String[] parts = SPLIT_REGEX.split(node.toLowerCase());
        Node newRoot = new Node(new HashMap<String, Node>(rootNode.children)), newPtr = newRoot, currentPtr = rootNode;
        newPtr.value = currentPtr.value;
        for (String part : parts) {
            Node oldChild = currentPtr.children.get(part);
            Node newChild = new Node(oldChild != null ? new HashMap<String, Node>(oldChild.children) : new HashMap<String, Node>());
            newPtr.children.put(part, newChild);
            currentPtr = oldChild;
            newPtr = newChild;
        }
        newPtr.value = value;
        return new NodeTree(newRoot);
    }

    public NodeTree withAll(Map<String, Tristate> values) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public static NodeTree of(Map<String, Tristate> values) {
        return of(values, Tristate.UNDEFINED);
    }

    public static NodeTree of(Map<String, Tristate> values, Tristate defaultValue) {
        NodeTree newTree = new NodeTree(defaultValue);
        for (Map.Entry<String, Tristate> value : values.entrySet()) {
            String[] parts = SPLIT_REGEX.split(value.getKey().toLowerCase());
            Node currentNode = newTree.rootNode;
            for (String part : parts) {
                if (currentNode.children.containsKey(part)) {
                    currentNode = currentNode.children.get(part);
                } else {
                    Node newNode = new Node(new HashMap<String, Node>());
                    currentNode.children.put(part, newNode);
                    currentNode = newNode;
                }
            }
            currentNode.value = value.getValue();
        }
        return newTree;
    }
}
