import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class LoadManager {

    public HashMap<String, Vare> loadItems(String path, boolean skipHeader){
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

                Vare vare = convertToVare(values[1], values[2], values[3], values[4], values[5], values[6], values[7], values[8], values[9]);

                data.put(itemId, vare);
            }
        } catch(FileNotFoundException | ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
        }
        return data;
    }

    private Vare convertToVare(String name, String priceDate, String price, String isBudgetBrand, String quantity, String unit, String isWeighted, String isBio, String isAvailable) {
        double fPrice = Double.parseDouble(price);
        boolean fIsBudgetBrand = Boolean.parseBoolean(isBudgetBrand);
        double fQuantity = Double.parseDouble(quantity);
        boolean fIsWeighted = Boolean.parseBoolean(isWeighted);
        boolean fIsBio = Boolean.parseBoolean(isBio);
        boolean fIsAvailable = Boolean.parseBoolean(isAvailable);

        return new Vare(name, priceDate, fPrice, fIsBudgetBrand, fQuantity, unit, fIsWeighted, fIsBio, fIsAvailable);
    }
}
