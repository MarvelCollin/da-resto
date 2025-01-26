package utils;

import java.util.List;
import java.util.function.Function;
import models.Entity.*;
import interfaces.ITableFormatter;

public class StringUtils {
    private static final int PADDING = 2;
    private static final int MIN_WIDTH = 35; 

    public static <T> String joinInitials(List<T> items, Function<T, String> getInitial) {
        StringBuilder result = new StringBuilder();
        for (T item : items) {
            result.append(getInitial.apply(item));
        }
        return result.toString();
    }

    public static String createTable(List<Customer> customers, List<Waiter> waiters, List<Chef> chefs) {
        ITableFormatter<Customer> customerFormatter = createCustomerFormatter();
        ITableFormatter<Waiter> waiterFormatter = createWaiterFormatter();
        ITableFormatter<Chef> chefFormatter = createChefFormatter();

        StringBuilder table = new StringBuilder();
        String border = createTableBorder(customerFormatter, waiterFormatter, chefFormatter);

        table.append(border).append("\n");
        table.append(createHeaderRow(customerFormatter, waiterFormatter, chefFormatter)).append("\n");
        table.append(border).append("\n");

        int maxRows = Math.max(Math.max(customers.size(), waiters.size()), chefs.size());
        for (int i = 0; i < maxRows; i++) {
            table.append(createDataRow(i, customers, customerFormatter,
                    waiters, waiterFormatter,
                    chefs, chefFormatter)).append("\n");
        }

        table.append(border);
        return table.toString();
    }

    private static String createDataRow(int index,
            List<Customer> customers, ITableFormatter<Customer> customerFormatter,
            List<Waiter> waiters, ITableFormatter<Waiter> waiterFormatter,
            List<Chef> chefs, ITableFormatter<Chef> chefFormatter) {

        StringBuilder row = new StringBuilder();

        row.append(index < customers.size() ? customerFormatter.formatRow(customers.get(index))
                : customerFormatter.formatEmpty());

        row.append(
                index < waiters.size() ? waiterFormatter.formatRow(waiters.get(index)) : waiterFormatter.formatEmpty());

        row.append(index < chefs.size() ? chefFormatter.formatRow(chefs.get(index)) : chefFormatter.formatEmpty());

        return row + "|";
    }

    private static String createHeaderRow(ITableFormatter<?>... formatters) {
        StringBuilder header = new StringBuilder();
        for (ITableFormatter<?> formatter : formatters) {
            header.append(formatter.formatHeader());
        }
        return header + "|";
    }

    private static String createTableBorder(ITableFormatter<?>... formatters) {
        StringBuilder border = new StringBuilder();
        for (ITableFormatter<?> formatter : formatters) {
            border.append(formatter.formatBorder());
        }
        return border + "+";
    }

    private static ITableFormatter<Customer> createCustomerFormatter() {
        return new ITableFormatter<Customer>() {
            public String formatHeader() {
                return String.format("| %-" + MIN_WIDTH + "s", "Customer");
            }

            public String formatRow(Customer c) {
                String stateInfo = c.getState() != null ? 
                    formatStateSimple(c.getState().getStateName()) : "waiting";
                return String.format("| %-" + MIN_WIDTH + "s", 
                    String.format("%s(%d) ~> %s", 
                        c.getInitial(), 
                        c.getTolerance(),
                        stateInfo));
            }

            public String formatEmpty() {
                return String.format("| %-" + MIN_WIDTH + "s", "");
            }

            public String formatBorder() {
                return "+" + "-".repeat(MIN_WIDTH + 2);
            }

            public int getColumnWidth() {
                return MIN_WIDTH + 2;
            }
        };
    }

    private static ITableFormatter<Waiter> createWaiterFormatter() {
        return new ITableFormatter<Waiter>() {
            public String formatHeader() {
                return String.format("| %-" + MIN_WIDTH + "s", "Waiter");
            }

            public String formatRow(Waiter w) {
                String stateInfo = w.getState() != null ? 
                    formatStateSimple(w.getState().getStateName()) : "waiting";
                return String.format("| %-" + MIN_WIDTH + "s", 
                    String.format("%s ~> %s", 
                        w.getInitial(),
                        stateInfo));
            }

            public String formatEmpty() {
                return String.format("| %-" + MIN_WIDTH + "s", "");
            }

            public String formatBorder() {
                return "+" + "-".repeat(MIN_WIDTH + 2);
            }

            public int getColumnWidth() {
                return MIN_WIDTH + 2;
            }
        };
    }

    private static ITableFormatter<Chef> createChefFormatter() {
        return new ITableFormatter<Chef>() {
            public String formatHeader() {
                return String.format("| %-" + MIN_WIDTH + "s", "Chef");
            }

            public String formatRow(Chef c) {
                String stateInfo = c.getState() != null ? 
                    formatStateSimple(c.getState().getStateName()) : "waiting";
                return String.format("| %-" + MIN_WIDTH + "s", 
                    String.format("%s ~> %s", 
                        c.getInitial(),
                        stateInfo));
            }

            public String formatEmpty() {
                return String.format("| %-" + MIN_WIDTH + "s", "");
            }

            public String formatBorder() {
                return "+" + "-".repeat(MIN_WIDTH + 2);
            }

            public int getColumnWidth() {
                return MIN_WIDTH + 2;
            }
        };
    }

    private static String formatStateSimple(String stateName) {
        if (stateName == null) return "waiting";
        int bracketIndex = stateName.indexOf('(');
        if (bracketIndex != -1) {
            String state = stateName.substring(0, bracketIndex).trim();
            String target = stateName.substring(bracketIndex + 1, stateName.length() - 1);
            return String.format("%s %s", state, target);
        }
        return stateName;
    }
}
