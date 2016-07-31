/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Manuel-Mac
 */
public class Parser {
    private final String emptySpace = "   ";
    private final String newScene = "Scene:";
    private final String newSpeaker = ": ";

    private final String uberschrift = "Series ";
    private final String klammer = " \\(";
    private String speaker="";
    private boolean isThereASpeaker;
    
    private Map<String,String> hashMap;
    private Vector<Speaker> vSpeaker = new Vector();
    
    public Parser(Map<String, String> inputHashMap, Vector<Speaker> inputVSpeaker) {
        this.isThereASpeaker = false;
        this.hashMap = inputHashMap;
        this.vSpeaker = inputVSpeaker;
    }
    
    public void praseTxt(String line){
        if(line.contains(newSpeaker) && !line.contains(newScene) && !line.contains("Flags:")) 
        {
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
    }
    
    public void purgeTxt(){
        System.out.println("Starte Bereinigung von Klammern");
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
            vSpeaker.add(new Speaker(e.getKey(),e.getValue(),10));
        });
        System.out.println("Reinigung beendet!");
    }
}
