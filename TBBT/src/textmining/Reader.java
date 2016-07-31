/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Manuel-Mac
 */
public class Reader {
    private final String target_dir = "Txt";
    private final File dir = new File(target_dir);
    private final File[] files = dir.listFiles();
    private Map<String, String> hashMap = new HashMap<>();
    private Vector<Speaker> vSpeaker;
    
    public Reader(Map<String, String> inputHashMap,  Vector<Speaker> inputVSpeaker) throws FileNotFoundException, IOException{
        this.hashMap = inputHashMap;
        this.vSpeaker = inputVSpeaker;
        readFiles();
    }

    private void readFiles() throws FileNotFoundException, IOException 
    {
        System.out.println("Beginne die Scripte einzulesen");
        BufferedReader reader;
        Parser parser = new Parser(hashMap,vSpeaker);
        String line;
        String ende ="Like this:";
        for (File f : files) 
        { 
            if(f.isFile()) {
                reader = new BufferedReader(new FileReader(f));
                System.out.println(f.getName());
                do{       
                    line = reader.readLine();
                    parser.praseTxt(line);
                }while(line.contains(ende) != true);
            }
        }
        parser.purgeTxt();
    }
}
