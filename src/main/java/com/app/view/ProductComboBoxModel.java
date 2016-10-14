package com.app.view;

import com.app.domain.Product;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by prulov on 14.10.2016.
 */
public class ProductComboBoxModel<E extends Product> extends AbstractListModel implements ComboBoxModel {

    List<E> data = new ArrayList<E>();
    Product selection = null;

    public ProductComboBoxModel(List<E> data){this.data = data;}

    public Object getElementAt(int index) {
        return data.get(index);
    }

    public int getSize() {
        return data.size();
    }

    public void setSelectedItem(Object anItem) {selection = (E) anItem;}

    public Object getSelectedItem() {return selection;}
}
