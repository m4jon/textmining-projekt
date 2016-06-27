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
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Manuel-Mac
 */
public class Reader {

    public static void main(String[] args) throws IOException {
        String target_dir = "/Users/Manuel-Mac/Documents/Projekte/txt";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();
        Map<String, String> speakers = new HashMap<>();
        

        for (File f : files) {
            if(f.isFile()) {
                try (BufferedReader inputStream = new BufferedReader(
                        new FileReader(f))) {
                   
                    
                   String line = inputStream.readLine();
                   
                  
                   while ((line = inputStream.readLine()) != null) {
                    
                    //Löschen von leeren Zeilen
                    String adjusted = line.replaceAll("(?m)^[ \t]*\r?\n", "");
                    
                    //Suche nach Klammern und deren Inhalt (Im Drehbuch Anweisungen) und ersetzte diese durch nichts.
                    Pattern PatternKlammer = Pattern.compile ("[\\(\\w\\W\\)]");
                    Matcher MatcherKlammer = PatternKlammer.matcher(line);
                    line = MatcherKlammer.replaceAll("");     
                    
                    //Suche nach Zeilen mit Doppelpunkt - Speichere alles vor dem Doppelpunkt als Speaker, alles nach dem Doppelpunkt als Dialog
                    String[] parts = line.split(":");
                    String Speaker = parts[0];
                    String Monolog = parts[1];
                    
                   
                   
                   speakers.put(Speaker, Monolog);
                   
                   System.out.println(adjusted);
                   }
                      
                    }
                }
            }
        }
    }