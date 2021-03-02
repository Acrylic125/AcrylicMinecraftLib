package com.acrylic.universal.geometry.turtle;

import org.jetbrains.annotations.NotNull;

public class TurtleMove implements TurtleInstruction {

    private final Turtle turtle;

    public TurtleMove(@NotNull Turtle turtle) {
        this.turtle = turtle;
    }

    @NotNull
    @Override
    public Turtle getTurtle() {
        return turtle;
    }

    @Override
    public void run() {

    }

    @NotNull
    @Override
    public String getInstruction() {
        return null;
    }
}
