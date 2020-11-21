package com.acrylic.universal.animations.rotational;

import com.acrylic.universal.entityanimations.EntityAnimator;

public class HeadRotationAnimation extends AbstractHeadRotationAnimation {

    public HeadRotationAnimation(EntityAnimator entityAnimator) {
        super(entityAnimator);
    }

    public HeadRotationAnimation(EntityAnimator entityAnimator, float yawFrequency) {
        super(entityAnimator);
        setYawFrequency(yawFrequency);
    }

}
