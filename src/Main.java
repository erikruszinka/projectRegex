import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String [] args) throws FileNotFoundException {

        String fileName = "rawText.txt";
        String line = null;
        PrintWriter writeSPZ = new PrintWriter("spz.txt");
        PrintWriter writeIDNum = new PrintWriter("idnum.txt");
        PrintWriter writeMACAdress = new PrintWriter("ipAdress.txt");
        Pattern IDNum = Pattern.compile("^\\d{2}(([0][1-9])|([1][012])|([5][1-9])|([6][012]))[0-3]\\d[\\/]?\\d{4}$");
        Pattern SPZ = Pattern.compile("^[A-Z]{2}\\d{3}[A-Z]{2}$");
        Pattern MAC = Pattern.compile("^([A-F0-9][A-F0-9][:]){5}[A-F0-9][A-F0-9]$");


        try {

            FileReader fileReader =
                    new FileReader(fileName);
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                String []parts = line.split(" ");
                for(String w : parts) {
                    Matcher matchSPZ = SPZ.matcher((String)w);
                    Matcher matchRC = IDNum.matcher((String)w);
                    Matcher matchMAC = MAC.matcher((String)w);

                    if(matchMAC.matches()){
                        writeMACAdress.println(w);
                    }
                    if(matchRC.matches()){
                        writeIDNum.println(w);
                    }
                    if(matchSPZ.matches()){
                        writeSPZ.println(w);
                    }

                }

            }
            bufferedReader.close();
            writeIDNum.close();
            writeMACAdress.close();
            writeSPZ.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
        }
        catch(IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");

        }
    }
}
