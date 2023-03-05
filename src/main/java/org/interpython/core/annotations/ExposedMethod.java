package org.interpython.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface ExposedMethod {
    String name() default "";
}
