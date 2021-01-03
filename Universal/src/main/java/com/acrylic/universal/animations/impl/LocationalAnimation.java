package com.acrylic.universal.animations.impl;

import org.jetbrains.annotations.NotNull;

import javax.xml.stream.Location;

public interface LocationalAnimation extends Animation {

    @NotNull
    Location getLocation();

}
