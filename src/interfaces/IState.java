package interfaces;

public interface IState {
    void update();
    String getStateName();
    void changeState(String customerName); 
}
