package utils;

import java.util.List;
import java.util.function.Function;
import models.Entity.*;
import interfaces.ITableFormatter;

public class StringUtils {
    private static final int PADDING = 2;

    public static <T> String joinInitials(List<T> items, Function<T, String> getInitial) {
        StringBuilder result = new StringBuilder();
        for(T item : items) {
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
        for(int i = 0; i < maxRows; i++) {
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
        
        
        row.append(index < customers.size() ? 
            customerFormatter.formatRow(customers.get(index)) : 
            customerFormatter.formatEmpty());
        
        
        row.append(index < waiters.size() ? 
            waiterFormatter.formatRow(waiters.get(index)) : 
            waiterFormatter.formatEmpty());
        
        
        row.append(index < chefs.size() ? 
            chefFormatter.formatRow(chefs.get(index)) : 
            chefFormatter.formatEmpty());
        
        return row + "|";
    }

    private static String createHeaderRow(ITableFormatter<?>... formatters) {
        StringBuilder header = new StringBuilder();
        for(ITableFormatter<?> formatter : formatters) {
            header.append(formatter.formatHeader());
        }
        return header + "|";
    }

    private static String createTableBorder(ITableFormatter<?>... formatters) {
        StringBuilder border = new StringBuilder();
        for(ITableFormatter<?> formatter : formatters) {
            border.append(formatter.formatBorder());
        }
        return border + "+";
    }

    private static ITableFormatter<Customer> createCustomerFormatter() {
        return new ITableFormatter<Customer>() {
            public String formatHeader() { return "| Customer  "; }
            public String formatRow(Customer c) { return String.format("| %-8s  ", c.getInitial()); }
            public String formatEmpty() { return "|          "; }
            public String formatBorder() { return "+----------"; }
            public int getColumnWidth() { return 10; }
        };
    }

    private static ITableFormatter<Waiter> createWaiterFormatter() {
        return new ITableFormatter<Waiter>() {
            public String formatHeader() { return "| Waiter    "; }
            public String formatRow(Waiter w) { return String.format("| %-8s  ", w.getInitial()); }
            public String formatEmpty() { return "|          "; }
            public String formatBorder() { return "+----------"; }
            public int getColumnWidth() { return 10; }
        };
    }

    private static ITableFormatter<Chef> createChefFormatter() {
        return new ITableFormatter<Chef>() {
            public String formatHeader() { return "| Chef      "; }
            public String formatRow(Chef c) { return String.format("| %-8s  ", c.getInitial()); }
            public String formatEmpty() { return "|          "; }
            public String formatBorder() { return "+----------"; }
            public int getColumnWidth() { return 10; }
        };
    }
}
