package com.designpattern.chapter21_composite_pattern.command03;

import java.util.ArrayList;
import java.util.List;

public class Composite extends Component {

    private List<Component> componentArrayList = new ArrayList<>();

    public void add(Component component) {
        this.componentArrayList.add(component);
    }

    public void remove(Component component) {
        this.componentArrayList.remove(component);
    }

    public List<Component> getChildren() {
        return this.componentArrayList;
    }
}
