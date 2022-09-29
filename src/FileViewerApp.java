import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import javax.swing.JFileChooser;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

class FileViewerApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String theline;
        File file = null;

        while (true) {
            System.out.println("\n----------------- Welcome to Hexa Editors --------------\n");

            System.out.println("1. Choose file by type path");
            System.out.println("2. Choose  the file by file chooser");
            System.out.println("3. Read as Text File");
            System.out.println("4. Read as Binary File");

            System.out.println("10. Exit");

            System.out.print("\n Choose from above: ");
            theline = sc.nextLine();

            if (theline.equals("10")) {
                break;
            } else if (theline.equals("1")) {
                System.out.print("Enter file path: ");
                theline = sc.nextLine();
                file = new File(theline);

            } else if (theline.equals("2")) {
                JFileChooser chooser = new JFileChooser();
                int btn = chooser.showOpenDialog(null);
                if (btn == JFileChooser.APPROVE_OPTION) {
                    file = chooser.getSelectedFile();
                }
            } else if (theline.equals("3")) {
                System.out.printf("\n\nReading (%s) as Text File\n\n", file.getName());
                try {
                    BufferedReader br = new BufferedReader(new FileReader(file));

                    while ((theline = br.readLine()) != null) {
                        System.out.println(theline);
                    }

                    System.out.println("\n-------- the end of file contents --------");

                    br.close();
                } catch (FileNotFoundException e) {
                    System.err.println(" Make sure the file you typed is exist !");
                    continue;
                } catch (IOException e) {
                    System.err.println(" Something went wrong!, Here the error message: " + e.getMessage());
                    continue;
                }
            } else if (theline.equals("4")) {

                int count = 0;
                try {
                    RandomAccessFile ra = new RandomAccessFile(file, "r");

                    byte[] array = new byte[((int) ra.length())];

                    // into array
                    while (true) {
                        array[count] = ra.readByte();
                        count++;
                        if (ra.read() == -1) {
                            break;
                        }
                    }

                    // to read from byte
                    for (int b : array) {
                        System.out.printf("%x ", b);

                        if (b % 6 == 0) {
                            System.out.println();
                        }
                    }
                    // for (int b = 0; b < array.length; b++) {

                    // System.out.printf("%x ", (array[b]));

                    // if (b % 6 == 0) {
                    // System.out.println();
                    // }

                    ra.close();
                } catch (FileNotFoundException be) {
                    System.out.println(" Make sure the file you typed is exist");
                }

                catch (IOException be) {
                }

            }
        }

    }
}