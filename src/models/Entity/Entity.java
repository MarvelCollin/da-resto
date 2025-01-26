package models.Entity;

import interfaces.IState;
import interfaces.IRestaurant;
import controllers.Mediators.RestaurantMediator;

public abstract class Entity implements IRestaurant {
    private String initial;
    private String outputString;
    protected IState state;  
    private volatile boolean isPaused = false;
    private RestaurantMediator mediator;

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

    @Override
    public void setMediator(RestaurantMediator mediator) {
        this.mediator = mediator;
    }

    public RestaurantMediator getMediator() {
        return mediator;
    }

    @Override
    public void pause() {
        this.isPaused = true;
    }

    @Override
    public void resume() {
        this.isPaused = false;
    }

    @Override
    public void stop() {
        this.isPaused = true;
    }

    @Override
    public void update() {
        if (!isPaused && state != null) {
            state.update();
            // Debug state changes
            System.out.println(getInitial() + " state: " + state.getStateName());
        }
    }
}
