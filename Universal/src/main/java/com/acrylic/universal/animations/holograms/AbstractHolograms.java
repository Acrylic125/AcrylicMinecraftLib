package com.acrylic.universal.animations.holograms;

import com.acrylic.universal.animations.Animation;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public abstract class AbstractHolograms
        extends Animation
        implements Iterable<AbstractHologram> {

    private final ArrayList<AbstractHologram> holograms = new ArrayList<>();

    public void clear() {
        holograms.clear();
    }

    public void teleport(final Location location, float offsetHeight) {
        for (AbstractHologram hologram : holograms)
            hologram.teleport(location, offsetHeight);
    }

    public void teleport(final Location location) {
        teleport(location, 0f);
    }

    public void addHologram(AbstractHologram hologram) {
        holograms.add(hologram);
    }

    public void addHologram(@NotNull final Location location, float offsetHeight, String... text) {
        for (String str : text) {
            addHologram(location, str, offsetHeight);
            offsetHeight += 0.25f;
        }
    }

    public void addHologram(@NotNull final Location location, String... text) {
        addHologram(location, 0f, text);
    }

    public abstract void addHologram(@NotNull final Location location, String text, float offsetHeight);

    public abstract void addHologram(AbstractHologram hologram, float offsetHeight);

    public void removeHologram(AbstractHologram hologram) {
        holograms.remove(hologram);
    }

    @Override
    public void delete() {
        for (AbstractHologram hologram : holograms)
            hologram.delete();
        clear();
    }

    @Override
    public void forEach(Consumer<? super AbstractHologram> action) {
        holograms.forEach(action);
    }

    @Override
    public Spliterator<AbstractHologram> spliterator() {
        return holograms.spliterator();
    }

    @NotNull
    @Override
    public Iterator<AbstractHologram> iterator() {
        return holograms.iterator();
    }

}
