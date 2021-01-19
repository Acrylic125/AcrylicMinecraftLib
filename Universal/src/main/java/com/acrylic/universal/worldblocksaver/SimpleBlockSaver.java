package com.acrylic.universal.worldblocksaver;

import com.acrylic.time.Time;
import com.acrylic.universal.Universal;
import com.acrylic.universal.files.CSVFile;
import com.acrylic.universal.files.fileeditor.CSVTextFileEditor;
import com.acrylic.universal.threads.Scheduler;
import com.acrylic.universal.threads.TaskType;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SimpleBlockSaver
        implements BlockSaver<SerializedBlockSaveInstance, BlockSaveObserver> {

    private final CSVFile csvFile;
    private final Scheduler<TaskType.RepeatingTask> scheduler;
    private final List<BlockSaveObserver> stored = new LinkedList<>();
    private boolean running = false;
    private BlockSaverSerializer<SerializedBlockSaveInstance> serializer;

    public SimpleBlockSaver() {
        this.csvFile = new CSVFile("blocksaves.csv", Universal.getPlugin());
        this.csvFile.load();
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

    @Override
    public void setSerializer(@NotNull BlockSaverSerializer<SerializedBlockSaveInstance> serializer) {
        this.serializer = serializer;
    }

    @NotNull
    @Override
    public BlockSaverSerializer<SerializedBlockSaveInstance> getSerializer() {
        return serializer;
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
        try {
            CSVTextFileEditor fileEditor = getFileStore().getFileEditor();
            fileEditor.clearFileEditor();
            for (BlockSaveObserver blockSaveObserver : stored) {
                for (BlockSavable blockSavable : blockSaveObserver.getSavableCollection()) {
                    SerializedBlockSaveInstance serializedBlockSaveInstance = getSerializer().serialize(blockSavable);
                    fileEditor.writeNewLine(serializedBlockSaveInstance.serialize());
                }
            }
            csvFile.saveFile();
        } catch (Exception ex) {
            System.out.println("[ Block Saver Error @SaveToFile ]");
            ex.printStackTrace();
            System.out.println("[ End of Block Saver Error ]");
        }
    }

    @Override
    public void restore(@NotNull SerializedBlockSaveInstance serializedBlockInstance) {
        serializedBlockInstance.restore();
    }

    @Override
    public void restore(@NotNull String serialized) {
        try {
            String[] arr = csvFile.getFileEditor().toStringArray(serialized);
            if (arr.length > 0)
                getSerializer().deserialize(arr).restore();
        } catch (Exception ex) {
            System.out.println("[ Block Saver Error @Restore ]");
            ex.printStackTrace();
            System.out.println("[ End of Block Saver Error ]");
        }
    }

    @Override
    public void restoreAllCached() {
        List<String> contents = csvFile.getFileEditor().getRawContents();
        if (contents.size() > 0) {
            Collections.reverse(contents);
            for (String rawContent : contents)
                restore(rawContent);
            csvFile.getFileEditor().clearFileEditor();
            csvFile.saveFile();
        }
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
