package models.Entity;

import interfaces.IState;

public abstract class Entity {
    private String initial;
    private String outputString;
    protected IState state;  // Add state field

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

    public void setState(IState state) {
        this.state = state;
    }

    public IState getState() {
        return state;
    }

    public void update() {
        if(state != null) {
            state.update();
        }
    }
}
