package com.acrylic.universal.gui.modules;

import com.acrylic.universal.gui.guiitems.AbstractGUIItem;

import java.util.ArrayList;

public class GUIModule<T extends AbstractGUIItem> implements AbstractGUIModule<T> {

    private final ArrayList<T> list = new ArrayList<>();

    @Override
    public ArrayList<T> getList() {
        return list;
    }
}
