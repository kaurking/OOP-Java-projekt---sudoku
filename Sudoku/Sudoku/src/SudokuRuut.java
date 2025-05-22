public class SudokuRuut {

    private int value;
    private boolean isFixed;

    public SudokuRuut(int value, boolean isFixed) {
        this.value = value;
        this.isFixed = isFixed;
    }

    public boolean getIsFixed() {
        return isFixed;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setIsFixed(boolean isFixed) {
        this.isFixed = isFixed;
    }

}
