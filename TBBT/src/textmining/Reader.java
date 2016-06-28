/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Zeisl
 */
public class Reader {
    
    public static void main(String[] args) throws IOException {
       // Erstellen  des Parser-Objekts für Übergabe
       Parser TBBTParser = new Parser();
       
        
       String target_dir = "/Users/Manuel-Mac/Documents/Projekte/txt";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();

        for (File f : files) {
            if(f.isFile()) {
                try (BufferedReader inputStream = new BufferedReader(
                        new FileReader(f))) {
                    String fulltext;

                    while ((fulltext = inputStream.readLine()) != null) {
                    
                        // Übergabe an Klasse Parser
                        TBBTParser.setString(fulltext);
                                
                     System.out.println(fulltext);
                    }
                }
            }
        }
    }

}