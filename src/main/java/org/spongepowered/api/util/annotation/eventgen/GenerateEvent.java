package org.spongepowered.api.util.annotation.eventgen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotate a static field of a generic type that allows forming a
 * "transactional" event of it's target field based on the {@link #value()}
 * target event type as a base. How the event is generated depends on the
 * parent type the event will target.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GenerateEvent {

    Class<?> value();

    Class<?>[] extend() default {};

    boolean transactions() default true;

    String customName() default "";

    Class<?>[] target() default Object.class;

}
