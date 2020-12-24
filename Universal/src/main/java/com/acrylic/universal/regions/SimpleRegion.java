package com.acrylic.universal.regions;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class SimpleRegion implements Region {

    private final Location start;
    private final Location end;
    private final UUID id = UUID.randomUUID();

    public SimpleRegion(@NotNull Block start, @NotNull Block end) {
        this(start.getLocation(), end.getLocation());
    }

    public SimpleRegion(@NotNull Location start, @NotNull Location end) {
        this.start = start;
        this.end = end;
    }

    @NotNull
    @Override
    public Location getLocation1() {
        return start;
    }

    @NotNull
    @Override
    public Location getLocation2() {
        return end;
    }

    @NotNull
    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "SimpleRegion{" +
                "start=" + start +
                ", end=" + end +
                ", id=" + id +
                '}';
    }
}
