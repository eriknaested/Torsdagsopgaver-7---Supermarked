import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class CashRegister {

    private Robot robot = new Robot();
    private HashMap<String, Vare> allItems = new HashMap<>();
    private HashMap<String, Vare> discountedItems = new HashMap<>();
    private LoadManager loadManager = new LoadManager();


    CashRegister() {
        allItems = loadManager.loadItems();
        discountedItems = loadManager.loadDiscounts();
    }

    public Collection<Vare> createBasket () {
        Collection<Vare> basket = robot.fyldIKurv(allItems.values());

        return basket;
    }

    public boolean isDiscounted(String id) {
        return discountedItems.containsKey(id);
    }

    public double getCorrectPrice(String id) {
        double price;
        if (isDiscounted(id)) {
            price = discountedItems.get(id).getPrice();
        } else {
            price = allItems.get(id).getPrice();
        }
        return price;
    }

    public HashMap<String, Integer> countItems(Collection<Vare> basket) {
        HashMap<String, Integer> duplicateCounter = new HashMap<>();
        for (Vare vare : basket) {
            duplicateCounter.put(vare.getId(), duplicateCounter.getOrDefault(vare.getId(), 0) + 1);
        }
        return duplicateCounter;
    }

    public double calculateTotal(HashMap<String, Integer> duplicateCounter) {
        double total = 0;
        for (String id : duplicateCounter.keySet()) {
            int count = duplicateCounter.get(id);
            double price = getCorrectPrice(id);
            double totalPrice = count * price;
            total += totalPrice;
        }
        return total;
    }

    public double getTax(double amount) {
        return amount * 0.2;
    }

    public void printReceipt(Collection<Vare> basket) {
        HashMap<String, Integer> duplicateCounter = countItems(basket);
        System.out.println("=== RECEIPT ===");
        for (String id : duplicateCounter.keySet()) {
            String name = allItems.get(id).getName();
            int count = duplicateCounter.get(id);
            double price = getCorrectPrice(id);
            double normalPrice = allItems.get(id).getPrice();
            double totalPrice = normalPrice * count;
            System.out.println(name);
            System.out.printf("  %d x %.2f kr. = %.2f kr.%n", count, normalPrice, totalPrice);
            if (isDiscounted(id)) {
                double discount = (price - normalPrice) * count;
                System.out.printf("  Discount: %.2f kr.%n", discount);
            }
            System.out.printf("  Price after discount: %.2f %n", price * count);
        }
        double total = calculateTotal(duplicateCounter);
        System.out.printf("TOTAL: %.2f kr.%n", total);
        double taxPaid = getTax(total);
        System.out.printf("VAT (25%%): %.2f kr.%n", taxPaid);
        System.out.println("============");
    }
}
