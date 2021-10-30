package enums;

public enum numberOfStarsEnum {
    NONE("none"), ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5");

    private String numVal;

    numberOfStarsEnum(String numVal) {
        this.numVal = numVal;
    }

    public String getNumVal() {
        return numVal;
    }

}
