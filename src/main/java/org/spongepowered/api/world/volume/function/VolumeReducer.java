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
package org.spongepowered.api.world.volume.function;

import org.spongepowered.api.world.volume.MutableVolume;
import org.spongepowered.api.world.volume.UnmodifiableVolume;
import org.spongepowered.api.world.volume.Volume;

import java.util.function.Supplier;

public interface VolumeReducer<V extends Volume, T, M extends MutableVolume, M2 extends M, U extends UnmodifiableVolume> {

    Supplier<V> getSecond();

    Supplier<U> getReference();

    Supplier<M2> getAccumilator();

    VolumeMerger<T, ? super U> GetReducer();

    VolumeFiller<? extends M, ? extends T> getFinisher();

    public static <NV extends Volume,
            NT,
            NM extends MutableVolume,
            NM2 extends NM,
            NU extends UnmodifiableVolume>
    VolumeReducer<NV, NT, NM, NM2, NU> of(final Supplier<NV> second,
                                          final Supplier<NU> reference,
                                          final Supplier<NM2> accumilator,
                                          final VolumeMerger<NT, ? super NU> reducer,
                                          final VolumeFiller<? extends NM, ? extends NT> finisher) {
        return new Impl<>(second, reference, accumilator, reducer, finisher);
    }

    static class Impl<V extends Volume, T, M extends MutableVolume, M2 extends M, U extends UnmodifiableVolume>
    implements VolumeReducer<V, T, M, M2, U> {
        private final Supplier<V> second;
        private final Supplier<U> reference;
        private final Supplier<M2> accumilator;
        private final VolumeMerger<T, ? super U> reducer;
        private final VolumeFiller<? extends M, ? extends T> finisher;

        public Impl(Supplier<V> second, Supplier<U> reference, Supplier<M2> accumilator, VolumeMerger<T, ? super U> reducer, VolumeFiller<? extends M, ? extends T> finisher) {
            this.second = second;
            this.reference = reference;
            this.accumilator = accumilator;
            this.reducer = reducer;
            this.finisher = finisher;
        }

        @Override
        public Supplier<V> getSecond() {
            return this.second;
        }

        @Override
        public Supplier<U> getReference() {
            return this.reference;
        }

        @Override
        public Supplier<M2> getAccumilator() {
            return this.accumilator;
        }

        @Override
        public VolumeMerger<T, ? super U> GetReducer() {
            return this.reducer;
        }

        @Override
        public VolumeFiller<? extends M, ? extends T> getFinisher() {
            return this.finisher;
        }
    }

}
