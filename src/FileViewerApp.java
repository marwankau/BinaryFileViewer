import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.swing.JFileChooser;
// اعتذر عن التأخير ، كان سبب التأخير هو مرضي وقت الكلاس وماقدرت احضر واسوي الأكتفتي وتواصلت معك من خلال تطبيق الجامعة
class FileViewerApp {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        String line;
        int hexLine;
        File file = null;

        while (true) {
            System.out.println("\n------------------ Welcome to my Hex Editor ---------------\n");

            System.out.println("1. Choose file by typing path");
            System.out.println("2. Choose file by file chooser");
            System.out.println("3. Read as Text File");
            System.out.println("4. Read as Binary File");

            System.out.println("10. Exit");

            System.out.print("\nChoose from above: ");
            line = scan.nextLine();

            if (line.equals("10")) {
                break;
            } else if (line.equals("1")) {
                System.out.print("Enter file path: ");
                line = scan.nextLine();
                file = new File(line);

            } else if (line.equals("2")) {
                JFileChooser chooser = new JFileChooser();
                int btn = chooser.showOpenDialog(null);
                if (btn == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                }
            } else if (line.equals("3")) {
                System.out.printf("\n\nReading (%s) as Text File\n\n", file.getName());
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    while ((line = br.readLine()) != null) {
                        System.out.println(line);
                    }

                    System.out.println("\n--------- end of file contents ---------");

                    br.close();
                } catch (FileNotFoundException e) {
                    System.err.println("Make sure the file you typed is exist!");
                    continue;
                } catch (IOException e) {
                    System.err.println("Somethign went wrong!, Here the error message: " + e.getMessage());
                    continue;
                }
            } else if (line.equals("4")) {
                try {
                    RandomAccessFile raf = new RandomAccessFile(file, "rw");
                    while ((hexLine = raf.read()) != -1) {
                        line = String.format("%2x", hexLine);
                        System.out.print(line + " ");
                    }
                } catch (IOException e) {
                    System.out.println("Exception");
                }

            }
        }
        scan.close();
    }
}