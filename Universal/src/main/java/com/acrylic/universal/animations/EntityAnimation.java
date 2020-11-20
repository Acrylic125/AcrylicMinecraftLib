package com.acrylic.universal.animations;

import com.acrylic.universal.entityanimations.EntityAnimator;
import com.acrylic.universal.interfaces.Deletable;

public abstract class EntityAnimation extends Animation implements Deletable {

    private final EntityAnimator entityAnimator;

    public EntityAnimation(EntityAnimator entityAnimator) {
        this.entityAnimator = entityAnimator;
    }

    public EntityAnimator getEntityAnimator() {
        return entityAnimator;
    }

    @Override
    public void delete() {
        entityAnimator.delete();
    }

}
