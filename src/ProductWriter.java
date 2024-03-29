import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter {

    public static void main(String[] args) {


        String ID = "";
        String name = "";
        String description = "";
        String title = "";
        double cost = 0;
        String csvRec = "";
        boolean done = false;

        Scanner in = new Scanner(System.in);

        ArrayList<String> recs = new ArrayList<>();
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID");
            name = SafeInput.getNonZeroLenString(in, "Enter Name");
            description = SafeInput.getNonZeroLenString(in, "Enter Description");
            cost = SafeInput.getRangedDouble(in, "Enter Cost", 0, 99999);

            csvRec = ("ID: " +ID+  ", " +name+ ", " +description+  ", " +cost );

            done = SafeInput.getYNConfirm(in, "Are You Done?");


            recs.add(csvRec);


        } while (!done);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "ProductTestData.txt");
        try
        {
         
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));


            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());  
                writer.newLine(); 

            }
            writer.close(); 
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }






    }
}
