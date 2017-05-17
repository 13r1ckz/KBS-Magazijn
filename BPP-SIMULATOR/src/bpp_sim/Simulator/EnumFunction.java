package bpp_sim.Simulator;

public enum EnumFunction {
    FIRST_FIT("<html><h3 style=\"text-align:center\">FIRST FIT</h3></html>"),
    FIRST_FIT_DECREASING("<html><h3 style=\"text-align:center\">FIRST FIT<br>DECREASING</h3></html>"),
    NEXT_FIT("<html><h3 style=\"text-align:center\">NEXT FIT</h3></html>"),
    NEXT_FIT_DECREASING("<html><h3 style=\"text-align:center\">EVEN/UNEVEN</h3></html>");
    private final String labelName;
    
    private EnumFunction(String name){
        this.labelName = name;
    }
    
    public String getLabelName(){
        return labelName;
    }
}
