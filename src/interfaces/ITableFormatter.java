package interfaces;

public interface ITableFormatter<T> {
    String formatHeader();
    String formatRow(T item);
    String formatEmpty();
    String formatBorder();
    int getColumnWidth();
}
