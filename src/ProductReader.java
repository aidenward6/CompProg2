import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class ProductReader
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "" ;

        ArrayList<String> lines = new ArrayList<>();

        try
        {

            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));

                // Finally we can read the file LOL!
                int line = 0;

                String words[];

                System.out.println("              ID#     Name       Description        Cost");
                System.out.println("====================================================================");

                while(reader.ready())
                {
                    rec = reader.readLine();
                    line++;
                    // echo to screen
                    System.out.printf("\nLine %4d %-60s ", line, rec);

                }



                for(String l : lines) {
                    String fields[] = l.split(", ");

                    // for(String f:fields)
                    System.out.printf("%-50s %-50s %-50s %-50s %-55s %n",fields[1],fields[2], fields[3], fields[4], fields[5]);

                }

                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!");
            }
            else  // user closed the file dialog without choosing
            {
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again!");
                System.exit(0);
            }
        }  // end of TRY
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!!!");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
