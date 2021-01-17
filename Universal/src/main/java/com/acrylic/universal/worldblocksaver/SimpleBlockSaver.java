package com.acrylic.universal.worldblocksaver;

import com.acrylic.time.Time;
import com.acrylic.universal.Universal;
import com.acrylic.universal.files.CSVFile;
import com.acrylic.universal.files.fileeditor.CSVTextFileEditor;
import com.acrylic.universal.files.fileeditor.FileEditor;
import com.acrylic.universal.threads.Scheduler;
import com.acrylic.universal.threads.TaskType;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SimpleBlockSaver
        implements BlockSaver<SerializedBlockSaveInstance, BlockSaveObserver> {

    private final CSVFile csvFile;
    private final Scheduler<TaskType.RepeatingTask> scheduler;
    private final List<BlockSaveObserver> stored = new LinkedList<>();
    private boolean running = false;

    public SimpleBlockSaver() {
        this.csvFile = new CSVFile("blocksaves.csv", Universal.getPlugin());
        this.scheduler = getNewScheduler();
    }

    public SimpleBlockSaver(@NotNull CSVFile csvFile) {
        this.csvFile = csvFile;
        this.scheduler = getNewScheduler();
    }

    @NotNull
    private Scheduler<TaskType.RepeatingTask> getNewScheduler() {
        return Scheduler.async()
                .runRepeatingTask(60, Time.SECONDS, 60, Time.SECONDS)
                .handle(this);
    }

    @NotNull
    @Override
    public Scheduler<TaskType.RepeatingTask> getScheduler() {
        return scheduler;
    }

    @Override
    public boolean isRunning() {
        return running;
    }

    @Override
    public void setRunning(boolean running) {
        this.running = running;
    }

    @NotNull
    @Override
    public CSVFile getFileStore() {
        return csvFile;
    }

    @NotNull
    @Override
    public Collection<BlockSaveObserver> getObservers() {
        return stored;
    }

    @Override
    public void saveToFile() {
        CSVTextFileEditor fileEditor = getFileStore().getFileEditor();
        fileEditor.clearFileEditor();
        for (BlockSaveObserver blockSaveObserver : stored) {
            for (BlockSavable blockSavable : blockSaveObserver.getSavableCollection()) {
                
            }
        }
        csvFile.saveFile();
    }

    @NotNull
    @Override
    public SerializedBlockSaveInstance getSaveInstance(@NotNull Block block) {
        return null;
    }

    @Override
    public void restore(@NotNull SerializedBlockSaveInstance serializedBlockInstance) {
        serializedBlockInstance.restore();
    }

    @Override
    public void restore(@NotNull String serialized) {

    }

    @Override
    public void observe(@NotNull BlockSaveObserver observer) {
        stored.add(observer);
    }

    @Override
    public void stopObserving(@NotNull BlockSaveObserver observer) {
        stored.remove(observer);
    }

    @Override
    public void run() {
        saveToFile();
    }
}
