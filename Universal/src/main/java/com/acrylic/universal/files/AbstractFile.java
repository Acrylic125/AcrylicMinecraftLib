package com.acrylic.universal.files;

import com.acrylic.universal.files.fileeditor.AbstractFileEditor;
import com.acrylic.universal.files.fileeditor.FileEditor;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractFile {

    private final File file;
    private boolean justCreated = false;

    public AbstractFile(@NotNull String path) {
        Logger logger = Logger.getGlobal();
        file = new File(path);
        try {
            if (!file.exists()) {
                justCreated = true;
                File parent = file.getParentFile();
                if (parent != null)
                    if (!parent.mkdirs())
                        logger.log(Level.WARNING, "Failed to create parent path for " + path);
                if (!file.createNewFile())
                    logger.log(Level.WARNING, "Failed to create file for " + path);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public boolean isJustCreated() {
        return justCreated;
    }

    @NotNull
    public File getFile() {
        return file;
    }

    @NotNull
    public abstract AbstractFileEditor getFileEditor();

    public abstract void saveFile();

}
