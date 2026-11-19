import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class LoadManager {

    private HashMap<String, Vare> itemData = new HashMap<>();

    public HashMap<String, Vare> loadItems() {
        return readData("data/varer.csv", true);
    }

    public HashMap<String, Vare> loadDiscounts() {
        HashMap<String, Vare> discountData = readData("data/tilbud.csv", true);

        for (Vare vare : discountData.values()) {
            String checkId = vare.getId();

            itemData.get(checkId).setDiscounted(true);
            itemData.get(checkId).setDiscountPrice(vare.getPrice());
        }
        return itemData;
    }

    public HashMap<String, Vare> readData(String path, boolean skipHeader){
        File file = new File(path);
        HashMap<String, Vare> data = new HashMap();
        try {
            Scanner scan = new Scanner(file);
            if (skipHeader) {
                scan.nextLine();//skip header
            }
            while(scan.hasNextLine()){
                String s = scan.nextLine();
                String[] values = s.split(";");
                String itemId = (values[0]);

                Vare vare = convertToVare(values[0], values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);

                data.put(itemId, vare);
            }
        } catch(FileNotFoundException | ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        return data;
    }

    private Vare convertToVare(String id, String name, String priceDate, String price, String isBudgetBrand, String quantity, String unit, String isWeighted, String isBio, String isAvailable) {
        double fPrice = Double.parseDouble(price);
        boolean fIsBudgetBrand = Boolean.parseBoolean(isBudgetBrand);
        double fQuantity = Double.parseDouble(quantity);
        boolean fIsWeighted = Boolean.parseBoolean(isWeighted);
        boolean fIsBio = Boolean.parseBoolean(isBio);
        boolean fIsAvailable = Boolean.parseBoolean(isAvailable);

        return new Vare(id, name, priceDate, fPrice, fIsBudgetBrand, fQuantity, unit, fIsWeighted, fIsBio, fIsAvailable);
    }

}
