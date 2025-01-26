package interfaces;

public interface IParser<T> {
    T parse(String input) throws Exception;
}
