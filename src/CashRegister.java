import java.util.ArrayList;
import java.util.Collection;

public class CashRegister {

    Robot robot = new Robot();
    LoadManager loadManager = new LoadManager();
    ArrayList<Vare> kurv;

    CashRegister() {
        kurv = (ArrayList<Vare>) robot.fyldIKurv(loadManager.loadItems().values());
    }

    public double getTotalBill () {
        double totalPrice = 0;
        for (Vare v : kurv) {
            if (v.isDiscounted()) {
                totalPrice += v.getDiscountPrice();
            } else {
                totalPrice += v.getPrice();
            }
        }
        return totalPrice;
    }

    public double getTax(double amount) {
        double price = getTotalBill();
        return price * 0.2;
    }

    public void printReceipt(ArrayList<Vare> items) {
        for (Vare v : kurv) {
            System.out.println(v.toString());
        }
    }

}
