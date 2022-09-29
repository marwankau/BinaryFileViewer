import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.swing.JFileChooser;

class FileViewerApp {
    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line;
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
                    System.err.println("Somethign went wrong!, Here the error message: " + e.getMessage() );
                    continue;
                }
            } else if (line.equals("4")) {
                System.out.printf("\n\nReading (%s) as binary File\n\n", file.getName());
                FileInputStream fis = new FileInputStream("/Users/2035998/Pictures/Camera Roll");
                DataInputStream dis = new DataInputStream(fis);
        
                while (true) {
                    try {
                        byte[] buff = new byte[150];
                        dis.read(buff);
                        
                        String str = "";
                        for (int i = 0; i < buff.length; i++) {
                            if (buff[i] == 0) break;
                            str += (char) buff[i];
                        }
                       return str;
                    } catch (EOFException ignored) {
                        break;
                    }
                 
                }
                dis.close();

                  
        
    }
 
        scan.close();
    }  
    }
}


