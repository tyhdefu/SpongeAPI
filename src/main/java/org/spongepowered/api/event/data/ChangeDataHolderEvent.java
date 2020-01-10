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
package org.spongepowered.api.event.data;

import org.spongepowered.api.data.DataHolder;
import org.spongepowered.api.data.DataTransactionResult;
import org.spongepowered.api.data.Transaction;
import org.spongepowered.api.data.type.DyeColor;
import org.spongepowered.api.data.type.Profession;
import org.spongepowered.api.data.value.Value;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Cancellable;
import org.spongepowered.api.event.Event;
import org.spongepowered.api.event.impl.data.AbstractValueChangeEvent;
import org.spongepowered.api.item.merchant.Merchant;
import org.spongepowered.api.util.annotation.eventgen.ImplementedBy;
import org.spongepowered.api.util.annotation.eventgen.PropertySettings;

import java.util.Optional;
import java.util.OptionalDouble;

/**
 * An event that is associated with a {@link org.spongepowered.api.data.DataHolder.Mutable} that may have some
 * {@link Value}s changed, offered, or removed. Note that calling any
 * methods relating to modifying a {@link org.spongepowered.api.data.DataHolder.Mutable} while this event
 * is being processed may produce awkward results.
 */
public interface ChangeDataHolderEvent extends Event, Cancellable {

    /**
     * Gets the {@link org.spongepowered.api.data.DataHolder.Mutable} targeted in this event.
     *
     * @return The data holder targeted in this event
     */
    DataHolder.Mutable getTargetHolder();

    @ImplementedBy(AbstractValueChangeEvent.class)
    interface ValueChange extends ChangeDataHolderEvent {

        /**
         * Gets the original {@link DataTransactionResult} of the {@link org.spongepowered.api.data.value.Value.Mutable}s
         * that have changed in this event.
         *
         * @return The original changes of values
         */
        DataTransactionResult getOriginalChanges();

        /**
         * Submits a new {@link DataTransactionResult} as a proposal of various
         * {@link org.spongepowered.api.data.value.Value.Mutable}s to be successfully offered/changed on the original
         * {@link org.spongepowered.api.data.DataHolder.Mutable}.
         *
         * <p>If the proposed {@link DataTransactionResult} provides additional
         * values that were not changed in the {@link #getOriginalChanges()},
         * the provided changes suggested to be successfully offered will be
         * re-offered </p>
         *
         * @param result The resulting offer
         * @return This event, for chaining
         */
        ValueChange proposeChanges(DataTransactionResult result);

        /**
         * Gets the ending resulting {@link DataTransactionResult} that will be
         * offered to the {@link DataHolder}.
         *
         * @return The final transaction details to be submitted
         */
        @PropertySettings(requiredParameter = false)
        DataTransactionResult getEndResult();

        // Generated

        /**
         * An event to listen for {@link org.spongepowered.api.data.Keys#DYE_COLOR}
         * changes on various targets
         */
        interface ChangeDyeColor extends ValueChange {

            Entity getTarget();

            DyeColor getOriginalDyeColor();

            DyeColor getDefaultDyeColor();

            Optional<DyeColor> getCustomDyeColor();

            DyeColor getFinalDyeColor();

            void setCustomDyeColor(DyeColor dyeColor);
        }

        /**
         * An event to listen for {@link org.spongepowered.api.data.Keys#EXHAUSTION}
         * changes.
         */
        interface ChangeExhaustion extends ValueChange {

            Player getPlayer();

            double getOriginalExhaustion();

            double getDefaultNewExhaustion();

            double getCustomNewExhaustion();

            void setCustomNewExhaustion(double exhaustion);

            double getFinalExhaustion();
        }

        interface ChangeFoodLevel extends ValueChange {

            Player getPlayer();

            int getOriginalFoodLevel();

            int getDefaultNewFoodLevel();

            int getCustomNewFoodLevel();

            void setCustomNewFoodLevel(int foodLevel);

            int getFinalFoodLevel();

        }

        interface ChangeHealth extends ValueChange {

            Living getLiving();

            double getOriginalHealth();

            double getDefaultNewHealth();

            OptionalDouble getCustomNewHealth();

            void setCustomNewHealth(double newHealth);

            double getFinalNewHealth();

        }

        interface ChangeProfession extends ValueChange {

            Merchant getMerchant();

            Transaction<Profession> getOriginalAir();


        }


    }
}
