package com.acrylic.universal.entity;

import com.acrylic.universal.text.ChatUtils;
import org.bukkit.entity.Entity;

import static com.acrylic.universal.entity.EntityInstance.*;

@SuppressWarnings("all")
public abstract class EntityBuilder<B extends EntityBuilder<B>> {

    public B name(String name) {
        getBuildFrom().setName((name == null) ? null : ChatUtils.get(name));
        return (B) this;
    }

    public B upsideDown() {
        return name(UPSIDE_DOWN_NAME);
    }

    public B nameVisible(boolean visible) {
        getBuildFrom().setNameVisible(visible);
        return (B) this;
    }

    public B asAnimator() {
        getBuildFrom().asAnimator();
        return (B) this;
    }

    public abstract Entity buildEntity();

    public abstract EntityInstance buildEntityInstance();

    public abstract EntityInstance getBuildFrom();

}
