package com.example.designpatternbuilder.design;

public interface IMenu {
    
    IMenu appendCelling(Matter matter);

    IMenu appendCoat(Matter matter);

    IMenu appendFloor(Matter matter);

    IMenu appendTile(Matter matter);

    String getDetail();
}
