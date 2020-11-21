package com.acrylic.universal.entityanimations.entities;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.Nullable;

public interface AbstractGiantAnimator extends LivingEntityAnimator {

    String UPSIDE_DOWN_NAME = "Dinnerbone";

    default AbstractGiantAnimator asAnimator() {
        return name(null).ai(false).visible(false);
    }

    default AbstractGiantAnimator upsideDown(boolean b) {
        return (b) ? name(UPSIDE_DOWN_NAME).nameVisible(false) : nameVisible(true);
    }

    default boolean isUpsideDown() {
        return getBukkitEntity().getName().equals(UPSIDE_DOWN_NAME);
    }

    @Override
    default AbstractGiantAnimator ai(boolean b) {
        LivingEntityAnimator.super.ai(b);
        return this;
    }

    @Override
    default AbstractGiantAnimator name(@Nullable String name) {
        LivingEntityAnimator.super.name(name);
        return this;
    }

    @Override
    default AbstractGiantAnimator nameVisible(boolean b) {
        LivingEntityAnimator.super.nameVisible(b);
        return this;
    }

    @Override
    default AbstractGiantAnimator visible(boolean b) {
        LivingEntityAnimator.super.visible(b);
        return this;
    }

    @Override
    default float getHeight() {
        return 13;
    }

    @Override
    default float getHologramHeight() {
        return isUpsideDown() ? 3f : 13.2f;
    }

}
