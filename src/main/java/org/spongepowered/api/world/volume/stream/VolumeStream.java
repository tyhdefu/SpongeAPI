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
package org.spongepowered.api.world.volume.stream;

import org.spongepowered.api.world.World;
import org.spongepowered.api.world.volume.MutableVolume;
import org.spongepowered.api.world.volume.UnmodifiableVolume;
import org.spongepowered.api.world.volume.Volume;
import org.spongepowered.api.world.volume.archetype.block.entity.StreamableBlockEntityArchetypeVolume;
import org.spongepowered.api.world.volume.block.StreamableBlockVolume;
import org.spongepowered.api.world.volume.entity.StreamableEntityVolume;
import org.spongepowered.api.world.volume.function.VolumeFiller;
import org.spongepowered.api.world.volume.function.VolumeConsumer;
import org.spongepowered.api.world.volume.function.VolumeMapper;
import org.spongepowered.api.world.volume.function.VolumeMerger;
import org.spongepowered.api.world.volume.function.VolumePredicate;
import org.spongepowered.api.world.volume.function.VolumeReducer;
import org.spongepowered.api.world.volume.function.VolumeResult;
import org.spongepowered.api.world.volume.function.VolumeResultSupplier;
import org.spongepowered.math.vector.Vector3i;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Equivalent to a {@link Stream} of {@link VolumeResult}s, with the added
 * benefit of being intrinsic to a specific type of {@link Volume} and {@code I}
 * element type per position. While it is recommended to verify the {@link Volume}
 * is equivalent to a live {@link World} instance, some volumes may be mutable, or
 * {@link UnmodifiableVolume} such that asynchronous access may be allowed. The
 * cases where a {@link Volume} may be available for streaming is dependent on
 * the type of volume, such as {@link StreamableEntityVolume}, {@link StreamableBlockVolume},
 * {@link StreamableBlockEntityArchetypeVolume}, etc.
 *
 * <p>Much like {@link Stream}, there are different types of operations that
 * can be performed:
 * <ul>
 *     <li>Filtering Operations
 *     <ul>
 *         <li>{@link #filter(VolumePredicate)}</li>
 *         <li>{@link #filter(VolumePredicate)}</li>
 *     </ul>
 *     </li>
 *     <li>Mapping Operations
 *     <ul>
 *         <li>{@link #map(VolumeMapper)}</li>
 *         <li>{@link #map(Function)}</li>
 *     </ul>
 *     </li>
 *     <li>Terminal Operations - These operations mark a
 *     stream as consumed, and therefor prevent it from
 *     being re-used after any of these operations are called.
 *     <ul>
 *         <li>{@link #anyMatch(VolumePredicate)}</li>
 *         <li>{@link #anyMatch(Predicate)}</li>
 *         <li>{@link #allMatch(VolumePredicate)}</li>
 *         <li>{@link #allMatch(Predicate)}</li>
 *         <li>{@link #noneMatch(VolumePredicate)} </li>
 *         <li>{@link #noneMatch(Predicate)}</li>
 *         <li>{@link #findAny()}</li>
 *         <li>{@link #findFirst()}</li>
 *         <li>{@link #merge(Volume, VolumeMerger, MutableVolume, VolumeFiller)}</li>
 *         <li>{@link #forEach(VolumeConsumer)}</li>
 *         <li>{@link #forEach(Consumer)}</li>
 *     </ul>
 *     </li>
 * </ul>
 *
 * <p>Because the original {@link Volume source volume} is
 * not guaranteed to change, it's adviseable to use the
 * relative {@link UnmodifiableVolume} creation methods
 * available when processing large amounts of data, or
 * otherwise if workign with live data over several game ticks,
 * it's adviseable to make smaller views with
 * {@link Volume#getView(Vector3i, Vector3i)} prior to
 * calling to create a stream.</p>
 *
 * @param <V> The type of Volume being acted on
 * @param <I> The type of element being streamed
 */
public interface VolumeStream<V extends Volume, I> extends BaseVolumeStream<VolumeStream<V, I>, V, I>{

    /**
     * Gets the {@link Volume} associated with this stream. Does not offer any operations
     * alone that would affect this stream, except by definition, if the volume itself is
     * mutable to which a modification on the volume may result in a modified result when
     * consuming this stream.
     *
     * @return The volume used as a source for this stream
     */
    V getVolume();

    /**
     * Returns a new stream chain that will contain {@code E elements} and coordinates of
     * the targeted {@link Volume volume} that satisfy the provided {@link VolumePredicate}.
     * Similar to {@link Stream#filter(Predicate)}, this is an intermediate operation.
     *
     * @param predicate The predicate to be used as a filter
     * @return A new stream of elements that satisfy the predicate
     */
    VolumeStream<V, I> filter(VolumePredicate<V, I> predicate);

    /**
     * Returns a new stream chain that will contain {@code E elements} and coordinates of
     * the targeted {@link Volume volume} that satisfy the provided {@link Predicate}
     * operating on a {@link VolumeResult}, equivalent to {@link #filter(VolumePredicate)}.
     * Similar to {@link Stream#filter(Predicate)}, this is an intermediate operation.
     *
     * @param predicate The predicate to be used as a filter
     * @return A new stream of elements that satisfy the predicate
     */
    default VolumeStream<V, I> filter(Predicate<VolumeResult<V, ? super I>> predicate) {
        return filter((volume, element, x, y, z) -> predicate.test(VolumeResult.of(volume, element, new Vector3i(x, y, z))));
    }

    /**
     * Returns a new stream chain that will contain elements of {@code T new types}
     * based on the existing elements of {@code type I} that are otherwise mapped with
     * {@link VolumeMapper}. Similar to {@link Stream#map(Function)}, this is an
     * intermediate operation.
     *
     * @param mapper The mapping function
     * @param <T> The new desired type
     * @return A new stream
     */
    <T, U extends UnmodifiableVolume> VolumeStream<V, T> map(VolumeMapper<I, U, T> mapper);

    /**
     * Returns a new stream chain that will contain elements of {@code T new types}
     * based on the existing elements of {@code type I} that are otherwise mapped with
     * {@link Function}, equivalent to {@link #map(VolumeMapper)}. Similar to
     * {@link Stream#map(Function)}, this is an intermediate operation.
     *
     * @param mapper The mapping function
     * @param <T> The new desired type
     * @return A new stream
     */
    default <T, U extends UnmodifiableVolume> VolumeStream<V, T> map(Function<VolumeResult<U, I>, T> mapper) {
        return map((VolumeMapper<I, U, T>) (volume, value, x, y, z) -> mapper.apply(VolumeResult.of(volume, value, new Vector3i(x, y, z))));
    }

    /**
     * Gets a total count of all elements within this stream. Similar to
     * {@link Stream#count()}, this is a terminal operation.
     *
     * @return The count of found elements in this stream
     */
    long count();

    /**
     * A terminal operator on this stream to find if all elements in this stream
     * will satisfy the predicate. If the stream is empty, {@code false} is returned.
     * This is a short-circuiting terminal operator and will consume the stream.
     *
     * @see Stream#allMatch(Predicate)
     *
     * @param predicate the predicate to filter
     * @return True if all elements in the stream satisfy the predicate
     */
    boolean allMatch(VolumePredicate<? super V, ? super I> predicate);

    /**
     * A terminal operator on this stream to find if all elements in this stream
     * will satisfy the predicate. If the stream is empty, {@code false} is returned.
     * This is a short-circuiting terminal operator and will consume the stream.
     *
     * @see Stream#allMatch(Predicate)
     *
     * @param predicate the predicate to filter
     * @return True if all elements in the stream satisfy the predicate
     */
    boolean allMatch(Predicate<VolumeResult<? super V, ? super I>> predicate);

    /**
     * A terminal operator on this stream to find if no elements in this stream
     * will satisfy the predicate. If the stream is empty, {@code false} is returned.
     * This is a short-circuiting terminal operator and will consume the stream.
     *
     * @see Stream#allMatch(Predicate)
     *
     * @param predicate the predicate to filter
     * @return True if no elements in the stream satisfy the predicate
     */
    boolean noneMatch(VolumePredicate<? super V, ? super I> predicate);

    /**
     * A terminal operator on this stream to find if no elements in this stream
     * will satisfy the predicate. If the stream is empty, {@code false} is returned.
     * This is a short-circuiting terminal operator and will consume the stream.
     *
     * @see Stream#allMatch(Predicate)
     *
     * @param predicate the predicate to filter
     * @return True if no elements in the stream satisfy the predicate
     */
    boolean noneMatch(Predicate<VolumeResult<? super V, ? super I>> predicate);

    /**
     * A terminal operator on this stream to find if any elements in this stream
     * will satisfy the predicate. If the stream is empty, {@code false} is returned.
     * This is a short-circuiting terminal operator and will consume the stream.
     *
     * @see Stream#allMatch(Predicate)
     *
     * @param predicate the predicate to filter
     * @return True if any elements in the stream satisfy the predicate
     */
    boolean anyMatch(VolumePredicate<? super V, ? super I> predicate);

    /**
     * A terminal operator on this stream to find if any elements in this stream
     * will satisfy the predicate. If the stream is empty, {@code false} is returned.
     * This is a short-circuiting terminal operator and will consume the stream.
     *
     * @see Stream#allMatch(Predicate)
     *
     * @param predicate the predicate to filter
     * @return True if any elements in the stream satisfy the predicate
     */
    boolean anyMatch(Predicate<VolumeResult<? super V, ? super I>> predicate);

    /**
     * Terminally processes this stream to find the first element of this stream,
     * or an empty {@link Optional} if the stream is empty. If order is not deterministic,
     * any element may be returned.
     *
     * @return an {@code Optional} describing the first element of this stream,
     * or an empty {@code Optional} if the stream is empty
     * @throws NullPointerException if the element selected is null
     */
    Optional<VolumeResult<V, I>> findFirst();

    /**
     * Terminally processes this stream to return an {@link Optional} of some
     * element of the stream, or an empty {@code Optional} if the stream is empty.
     *
     * <p>The behavior of this operation is explicitly nondeterministic; it is
     * free to select any element in the stream.  This is to allow for maximal
     * performance in parallel operations; the cost is that multiple invocations
     * on the same source may not return the same result.  (If a stable result
     * is desired, use {@link #findFirst()} instead.)
     *
     * @return an {@code Optional} describing some element of this stream, or an
     * empty {@code Optional} if the stream is empty
     * @throws NullPointerException if the element selected is null
     * @see #findFirst()
     */
    Optional<VolumeResult<V, I>> findAny();

    /**
     * Provides a new {@link Stream} compiled of {@link VolumeResult VolumeResults}
     * in the event the provided methods of {@link VolumeStream} are not satisfactory,
     * and therefor would be better suited to exist as a Java {@code Stream}.
     *
     * @return A new stream
     */
    Stream<VolumeResult<V, I>> toStream();

    <M extends MutableVolume, U extends UnmodifiableVolume> M fill(Supplier<M> volumeSupplier, VolumeMerger<I, ? super U> merger, VolumeFiller<M, I> applicator);

    <M extends MutableVolume, U extends UnmodifiableVolume> M fill(M target, VolumeMerger<I, ? super U> merger, VolumeFiller<M, I> filler);

    /**
     * A terminal operation that will apply all existing elements of this stream,
     * along with elements of the second {@link Volume} according to a defined
     * offset comparing the {@code second#getBlockSize()} to this stream's
     * {@link #getVolume() volume's} {@link Volume#getBlockSize()}, and matching
     * the offset to the destination's size offset. After which, the {@link VolumeMerger}
     * will perform a merge and offer a {@code I result} to apply to the destination's
     * {@link MutableVolume volume} at the pre-determined offset coordinates.
     *
     * <p>It is required that neither the target's {@link Volume#getBlockSize()}
     * or {@code destination} block size equal or greater than this volume's block size.
     * If the case of the second volume's size is smaller than this volume's size, or
     * the destination's volume size is smaller than this volume's size,
     * an exception is thrown before starting to consume this stream. This is a
     * terminal operation.</p>
     *
     * @param <M> The base type of mutable volume
     * @param second The second volume to merge elements from
     * @param merger The resolving merging function
     * @param destination The destination volume
     */
    default <M extends MutableVolume, M2 extends M, U extends UnmodifiableVolume> M2 merge(V second, VolumeMerger<I, ? super U> merger, M2 destination, VolumeFiller<? extends M, ? extends I> applier) {
        return merge(VolumeReducer.of(() -> second, () -> (U) getVolume().asUnmodifiableVolume(), () -> destination, merger, applier));
    }

    <M extends MutableVolume, M2 extends M, U extends UnmodifiableVolume> M2 merge(VolumeReducer<V, I, M, M2, U> reducer);

    /**
     * A terminal operation that will walk through all available
     * elements within this stream and call {@link VolumeConsumer#consume(Volume, Object, int, int, int)}
     * for all elements and their coordinates remaining.
     *
     * @param visitor The visitor
     */
    void forEach(VolumeConsumer<V, I> visitor);

    /**
     * A terminal operation that will walk through all available
     * elements within this stream and call {@link VolumeConsumer#consume(Volume, Object, int, int, int)}
     * for all elements and their coordinates remaining.
     *
     * @param consumer The visitor
     */
    void forEach(Consumer<VolumeResult<V, I>> consumer);

}
