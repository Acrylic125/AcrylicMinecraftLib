package com.acrylic.universal.animations.holograms;

import com.acrylic.universal.animations.Animation;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Consumer;

public abstract class AbstractHolograms
        implements Animation, Iterable<AbstractHologram> {

    private final List<AbstractHologram> holograms;
    private boolean running = true;

    public AbstractHolograms() {
        this(new ArrayList<>());
    }

    public AbstractHolograms(@NotNull List<AbstractHologram> holograms) {
        this.holograms = holograms;
    }

    @Nullable
    public AbstractHologram getHologram(int index) {
        int size = holograms.size();
        return (index >= size || index < 0) ? null : holograms.get(index);
    }

    public List<AbstractHologram> getHolograms() {
        return holograms;
    }

    public void clear() {
        holograms.clear();
    }

    public void teleport(final Location location, float offsetHeight) {
        for (AbstractHologram hologram : holograms)
            hologram.teleportMainEntity(location, offsetHeight);
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

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void terminate() {
        this.running = false;
        delete();
    }
}
