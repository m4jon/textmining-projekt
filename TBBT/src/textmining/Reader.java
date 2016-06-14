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
        String target_dir = "C:\\Users\\Zeisl\\Desktop\\Seasons";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();
        Map<String, Speaker> speaker = new HashMap<>();
        StringBuilder fulltextBuilder = new StringBuilder();

        for (File f : files) {
            if(f.isFile()) {
                try (
                        BufferedReader inputStream = new BufferedReader(
                        new FileReader(f))) {
                    String line;
         
                    while (null != (line = inputStream.readLine())) {
                        fulltextBuilder.append(line);
                        System.out.println(line);
                    }
                    
                    String fulltext = fulltextBuilder.toString();
                    // StringBuffer Bereinigung = new StringBuffer();
                    
                    Pattern Klammern;
                    Klammern = Pattern.compile(("[\\(\\w\\s\\W\\)]"));
                    Matcher m = Klammern.matcher(fulltext);
                    fulltext = m.replaceAll("");
                    
                    if (m.find()) {
                        String tagname = m.group(1);
                        if (tagname.startsWith("/(")) {
                            tagname.replace("[\\(\\w\\s\\W\\)]", "");
                        }
                    
                    
                    }
                } 
            }
        }
    }