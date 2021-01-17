package com.acrylic.universal.blockresetter;

import com.acrylic.universal.files.AbstractFile;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface BlockResetter<T extends SerializedBlockResetInstance> {

    @NotNull
    AbstractFile getFileStore();

    Collection<T> getToReset();

    void addToReset()

}
