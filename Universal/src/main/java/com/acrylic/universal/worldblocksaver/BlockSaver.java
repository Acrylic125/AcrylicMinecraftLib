package com.acrylic.universal.worldblocksaver;

import com.acrylic.universal.files.AbstractFile;
import com.acrylic.universal.interfaces.Runner;
import com.acrylic.universal.threads.TaskType;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface BlockSaver<T extends SerializedBlockSaveInstance, O extends BlockSaveObserver>
        extends Runner<TaskType.RepeatingTask> {

    void setSerializer(@NotNull BlockSaverSerializer<T> serializer);

    @NotNull
    BlockSaverSerializer<T> getSerializer();

    @NotNull
    AbstractFile getFileStore();

    @NotNull
    Collection<O> getObservers();

    void saveToFile();

    void restore(@NotNull T serializedBlockInstance);

    void restore(@NotNull String serialized);

    void restoreAllCached();

    void observe(@NotNull O observer);

    void stopObserving(@NotNull O observer);

}
