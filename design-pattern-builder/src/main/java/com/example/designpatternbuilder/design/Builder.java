package com.example.designpatternbuilder.design;

public class Builder {

    public IMenu levelOne(Double area) {
        return new DecorationPackageMenu(area, "豪华欧式")
        .appendCelling(new LevelTwoCeiling())
        .appendCoat(new DuluxCoat())
        .appendFloor(new ShengXiangFloor());
    }

    public IMenu levelTwo(Double area) {
        return new DecorationPackageMenu(area, "轻奢田园")
        .appendCelling(new LevelTwoCeiling())
        .appendCoat(new LiBangCoat())
        .appendTile(new MarcoPoloTile());
    }

    public IMenu levelThree(Double area) {
        return new DecorationPackageMenu(area, "现代简约")
        .appendCelling(new LevelOneCeiling())
        .appendCoat(new LiBangCoat())
        .appendTile(new DongPengTile());
    }





    
}
