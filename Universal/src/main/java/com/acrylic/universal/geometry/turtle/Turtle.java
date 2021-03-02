package com.acrylic.universal.geometry.turtle;

import org.jetbrains.annotations.NotNull;

/**
 * The turtle class represents a moving cursor
 * which can help with sketching geometric shapes
 * or even complex objects.
 */
public interface Turtle {

    void execute(@NotNull TurtleInstruction turtleInstruction);



}
