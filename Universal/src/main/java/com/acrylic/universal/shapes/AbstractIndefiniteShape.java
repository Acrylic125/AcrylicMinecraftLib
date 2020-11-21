package com.acrylic.universal.shapes;

import com.acrylic.universal.animations.InvocationLocationAnimation;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractIndefiniteShape
        extends AbstractShape
        implements InvocationLocationAnimation {

    private int index = 0;
    private int indexIncrement = 1;

    public AbstractIndefiniteShape(float frequency) {
        super(frequency);
    }

    public Location getLocation(@NotNull final Location location) {
        index += indexIncrement;
        return getLocation(location, index);
    }

    public Location getLocation(@NotNull final Location location, int index) {
        return transformPoint(new Location(location.getWorld(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch()), index);
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public void setIndexIncrement(int indexIncrement) {
        this.indexIncrement = indexIncrement;
    }

    @Override
    public int getIndexIncrement() {
        return indexIncrement;
    }

    @Override
    public int getIndex() {
        return index;
    }

    public abstract Location transformPoint(Location location);

    public abstract Location transformPoint(Location location, int index);

    public abstract int getFullCycleIndex();

}
