package com.acrylic.universal.geometry.turtle;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public interface TurtleInstruction {

    @NotNull
    Turtle getTurtle();

    void run();

    @NotNull
    String getInstruction();

    default TurtleInstruction consolePrintInstruction() {
        System.out.println(getInstruction());
        return this;
    }

    default TurtleInstruction broadcastPrintInstruction() {
        Bukkit.broadcastMessage(getInstruction());
        return this;
    }

}
