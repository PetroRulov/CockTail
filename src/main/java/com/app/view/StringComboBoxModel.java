package com.app.view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prulov on 14.10.2016.
 */
public class StringComboBoxModel extends AbstractListModel implements ComboBoxModel {

    List<String> data = new ArrayList<>();
    String selection = null;


    public StringComboBoxModel(List<String> data) {
        this.data = data;
    }

    public Object getElementAt(int index) {
        return data.get(index);
    }

    public int getSize() {
        return data.size();
    }

    public void setSelectedItem(Object anItem) {
        selection = (String) anItem;
    }

    public Object getSelectedItem() {
        return selection;
    }
}
