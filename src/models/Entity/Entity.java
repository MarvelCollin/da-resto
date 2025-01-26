package models.Entity;

public abstract class Entity {
    private String initial;
    private String outputString;

    public Entity(String initial) {
        this.initial = initial;
        this.outputString = this.initial;    
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    public String getOutputString() {
        return outputString;
    }

    public void setOutputString(String outputString) {
        this.outputString = outputString;
    }
}
