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
package org.spongepowered.api.world.storage;

import com.google.common.base.Optional;
import org.spongepowered.api.service.persistence.data.DataContainer;

import java.util.Iterator;

/**
 * A chunk iterator represents a buffer for obtaining chunk data from
 * storage without having to explicitly load into memory all available
 * chunks.
 * <p>This avoid loading all chunks into memory at once, reducing the memory
 * footprint and persistence operations.</p>
 * <p>The chunks are loaded individually in sequence. Strong references to
 * the chunks represented by {@link DataContainer}s should be avoided
 * <strong>AT ALL COSTS</strong>. The data represented is a copy and
 * therefore shouldn't be considered synchronized to live data.</p>
 *
 */
public interface ChunkIterator extends Iterator<Optional<DataContainer>> {

    /**
     * Chunks can not be removed by a ChunkIterator.
     *
     * @throws UnsupportedOperationException Not supported at all
     */
    @Override
    void remove() throws UnsupportedOperationException;

}
