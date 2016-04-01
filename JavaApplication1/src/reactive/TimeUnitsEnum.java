package reactive;

public enum TimeUnitsEnum {
    MILLISECOND(1), SECOND(1000), MINUTE(60000), HOUR(360000);
    
    private final int val;
    
    TimeUnitsEnum(int val){
        this.val = val;
    }
    
    public int getVal(){
        return this.val;
    }
    
}
