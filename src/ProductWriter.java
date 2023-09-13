import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import static java.nio.file.StandardOpenOption.CREATE;

public class ProductWriter
{
    public static void main(String[] args)
    {
        ArrayList<String>  people = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\ProductTestData.txt");

        boolean done = false;

        String personRec = "";
        String name = "";
        String ID = "";
        String description = "";
        double cost = 0;

        do
        {
            ID = SafeInput.getNonZeroLenString(in, "Enter the ID [6 digits]: ");
            name = SafeInput.getNonZeroLenString(in, "Enter your name: ");
            description = SafeInput.getNonZeroLenString(in, "Enter a short description: ");
            cost = SafeInput.getRangedDouble(in, "Enter the cost: ", 1, 9999);

            personRec = ID +", " + name + ", " + description + ", " + cost + ", ";
            people.add(personRec);

            done = SafeInput.getYNConfirm(in, "Are you done? ");
        }
        while(!done);
        System.out.println();

        for ( String p: people)
            System.out.println(p);

        try
        {
            OutputStream out =
                    new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer =
                    new BufferedWriter(new OutputStreamWriter(out));

            for (String rec : people)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();
            }
            writer.close();
            System.out.println();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}