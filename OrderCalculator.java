import java.util.*;

public class OrderCalculator {

    // discount codes
    public static final int d1 = 10;
    public static final int d2 = 20;
    public static final int d3 = 30;

    public double calc(List items, String code) {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            Object[] item = (Object[]) items.get(i);
            double price = Double.parseDouble(item[1].toString());
            int qty   = Integer.parseInt(item[2].toString());
            total = total + price * qty;
        }

        double discount = 0;
        if (code != null) {
            if (code.equals("SAVE10")) {
                discount = total * d1 / 100;
            }
            if (code.equals("SAVE20")) {
                discount = total * d2 / 100;
            }
            if (code.equals("SAVE30")) {
                discount = total * d3 / 100;
            }
        }

        total = total - discount;

        // apply tax
        double tax;
        if (total > 1000) {
            tax = total * 0.20;
        } else if (total > 500) {
            tax = total * 0.15;
        } else if (total > 100) {
            tax = total * 0.10;
        } else {
            tax = total * 0.05;
        }
        total = total + tax;

        // round to 2 decimal places
        total = Math.round(total * 100);
        total = total / 100;

        return total;
    }

    public double calcWithShipping(List items, String code, String country) {
        double total = 0;
        for (int i = 0; i < items.size(); i++) {
            Object[] item = (Object[]) items.get(i);
            double price = Double.parseDouble(item[1].toString());
            int qty   = Integer.parseInt(item[2].toString());
            total = total + price * qty;
        }

        double discount = 0;
        if (code != null) {
            if (code.equals("SAVE10")) {
                discount = total * d1 / 100;
            }
            if (code.equals("SAVE20")) {
                discount = total * d2 / 100;
            }
            if (code.equals("SAVE30")) {
                discount = total * d3 / 100;
            }
        }

        total = total - discount;

        double tax;
        if (total > 1000) {
            tax = total * 0.20;
        } else if (total > 500) {
            tax = total * 0.15;
        } else if (total > 100) {
            tax = total * 0.10;
        } else {
            tax = total * 0.05;
        }
        total = total + tax;

        // shipping
        double shipping = 0;
        if (country == "US") {
            shipping = 5.99;
        } else if (country == "CA") {
            shipping = 9.99;
        } else {
            shipping = 24.99;
        }
        total = total + shipping;

        total = Math.round(total * 100);
        total = total / 100;

        return total;
    }

    public String getOrderStatus(int id) {
        if (id == 1) return "PENDING";
        if (id == 2) return "PROCESSING";
        if (id == 3) return "SHIPPED";
        if (id == 4) return "DELIVERED";
        if (id == 5) return "CANCELLED";
        return null;
    }
}
