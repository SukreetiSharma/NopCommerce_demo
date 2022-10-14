package Enums;

public enum PageEnum {
    NOTEBOOK("Notebook", 3);
    private String name;
    private int index;

    PageEnum(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
    }
}
