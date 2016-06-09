/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.File;

/**
 *
 * @author Manuel-Mac
 */
public class Reader {

    public static void main(String[] args) throws IOException {
        String target_dir = "/Users/Manuel-Mac/Desktop/txt";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();

        for (File f : files) {
            if(f.isFile()) {
                BufferedReader inputStream = null;

                try {
                    inputStream = new BufferedReader(
                                    new FileReader(f));
                    String line;

                    while ((line = inputStream.readLine()) != null) {
                        System.out.println(line);
                    }
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        }
    }

}