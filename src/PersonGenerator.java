import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class PersonGenerator {
    public static void main(String[] args) {

      
        String ID = "";
        String fName = "";
        String lName = "";
        String title = "";
        int YOB = 0;
        String csvRec = "";
        boolean done = false;

        Scanner in = new Scanner(System.in);

        ArrayList<String> recs = new ArrayList<>();
        do {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID");
            fName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            lName = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            title = SafeInput.getNonZeroLenString(in, "Enter title");
            YOB = SafeInput.getRangedInt(in, "Enter the year for the age calc: ", 1000, 9999);

            csvRec = ("ID: " +ID+  ", First Name: " +fName+ ", Last Name: " +lName+ ", Title: " +title+ ", Year of Birth: "+YOB );

            done = SafeInput.getYNConfirm(in, "Are You Done?");


            recs.add(csvRec);


        } while (!done);

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "PersonTestData.txt");
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
