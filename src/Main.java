import java.util.Collection;

public class Main {

    public static void main (String[] args) {

        CashRegister cr = new CashRegister();

        Collection<Vare> basket = cr.createBasket();

        cr.printReceipt(basket);
    }
}
