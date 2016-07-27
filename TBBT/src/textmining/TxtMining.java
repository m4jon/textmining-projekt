/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.io.BufferedReader; //Scanner oder Read von File
import java.io.File;
import java.io.FileReader; //Get the File
import java.util.*; //Für Vector

/**
 *
 * @author Manuel-Mac
 */
public class TxtMining
{
    public static void main(String[] args) throws Exception {  
        // für den Praser
        String emptySpace = "   ";
        String newScene = "Scene:";
        String newSpeaker = ": ";
        //String ende = "Written by";
        String ende ="Like this:";
        String uberschrift = "Series ";
        String klammer = " \\(";
        //für den Reader
        String line;
        String speaker="";
        boolean isThereASpeaker=false;
        //Die Hashmap in der gespeichert wird
        Map<String, String> hashMap = new HashMap<>();
        
        
        //Reader
        //BufferedReader reader;
        String target_dir = "Txt";
        File dir = new File(target_dir);
        File[] files = dir.listFiles();
        //Die Schleife
        for (File f : files) 
        {
            if(f.isFile()) {
                BufferedReader reader = new BufferedReader(new FileReader(f));
                System.out.println(f.getName());
                do{       
                    line = reader.readLine();
                    if(line.contains(newSpeaker) && !line.contains(newScene) && !line.contains("Flags:")) {
                        isThereASpeaker = true;
                        String[] parts = line.split(newSpeaker);
                        speaker = parts[0];
                        speaker = speaker.replaceAll(emptySpace, "");
                        line = parts[1];
                        if(speaker.contains("(")){
                            String[] partsSpeaker = speaker.split(klammer);
                            speaker = partsSpeaker[0];
                        }
                        hashMap.put(speaker, hashMap.get(speaker)+line);
                    }
                    else if(line.isEmpty()) {
                        isThereASpeaker = false;
                    }
                    else {
                        if(isThereASpeaker==true) {
                            hashMap.put(speaker, hashMap.get(speaker)+line.replaceAll(emptySpace," "));
                        }   
                    }
                }while(line.contains(ende) != true);
            }
        }
        //Bereinunung von "null" und Klammerne
        System.out.println("Starte Bereinung von Klammern");
        hashMap.entrySet().stream().forEach((Map.Entry<String, String> e) -> {
            int startIndex,endIndex;
            String toBeReplaced="",replacement="";
            
            while(e.getValue().contains("(")) {
                startIndex = e.getValue().indexOf("(");
                endIndex = e.getValue().indexOf(")");
                toBeReplaced = e.getValue().substring(startIndex, endIndex+1);
                e.setValue(e.getValue().replace(toBeReplaced, replacement));
            }
            e.setValue(e.getValue().substring(4));    
            System.out.println(e.getKey() + " = " + e.getValue());
            //System.out.println(e.getKey());
        });
        System.out.println(hashMap.size());
        //getOrDefault(rawText.substring(start,i);
        Speaker newSpeakerxD = new Speaker("Voice of Spock",hashMap.get("Voice of Spock"),10);
        System.out.println(newSpeakerxD.getRawText());
    }
}