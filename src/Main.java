import java.util.HashMap;

public class Main {

    public static void main (String[] args) {

        LoadManager lm = new LoadManager();

        HashMap<String, Vare> items = lm.loadItems("data/varer.csv", true);
        System.out.println();
    }
}
