import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;

public class FileIO {

    public HashSet<String> readData(String path, boolean skipHeader){
        File file = new File(path);
        HashSet<String> data = new HashSet<>();
        try {
            Scanner scan = new Scanner(file);
            if (skipHeader) {
                scan.nextLine();//skip header
            }
            while(scan.hasNextLine()){
                String s = scan.nextLine();
                data.add(s);
            }
        } catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("der findes kun 2 værdier pr. række");
        }
        return data;
    }

}
