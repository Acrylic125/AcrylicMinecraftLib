package com.acrylic.universal.animations;

import com.acrylic.universal.interfaces.Deletable;
import com.acrylic.universal.interfaces.Terminable;

public interface Animation extends Deletable, Terminable {

    boolean isRunning();

}
