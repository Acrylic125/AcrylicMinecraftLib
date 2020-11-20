package com.acrylic.universal.entityanimations.entities;

import com.acrylic.universal.entityanimations.LivingEntityAnimator;
import org.jetbrains.annotations.Nullable;

public interface AbstractGiantAnimator extends LivingEntityAnimator {

    default AbstractGiantAnimator asAnimator() {
        return name(null).ai(false).visible(false);
    }

    default AbstractGiantAnimator upsideDown(boolean b) {
        return (b) ? name("Dinnerbone").nameVisible(false) : nameVisible(true);
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
}
