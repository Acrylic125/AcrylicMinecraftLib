package com.acrylic.universal.worldblocksaver;

import com.acrylic.universal.files.AbstractFile;
import com.acrylic.universal.interfaces.Runner;
import com.acrylic.universal.threads.TaskType;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public interface BlockSaver<T extends SerializedBlockSaveInstance, O extends BlockSaveObserver>
        extends Runner<TaskType.RepeatingTask> {

    @NotNull
    AbstractFile getFileStore();

    @NotNull
    Collection<O> getObservers();

    void saveToFile();

    @NotNull
    T getSaveInstance(@NotNull Block block);

    void restore(@NotNull T serializedBlockInstance);

    void restore(@NotNull String serialized);

    void observe(@NotNull O observer);

    void stopObserving(@NotNull O observer);

}
