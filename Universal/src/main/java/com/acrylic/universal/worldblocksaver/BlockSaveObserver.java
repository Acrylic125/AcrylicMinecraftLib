package com.acrylic.universal.worldblocksaver;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface BlockSaveObserver {

    @NotNull
    Collection<BlockSavable> getSavableCollection();

}
