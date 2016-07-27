/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Manuel-Mac
 */
public final class Speaker {
    private static int numberOfWordsOverall=0;
    private final String name;
    private final String rawText;
    private final int numberOfDialoge;
    
    private int numberOfWords;
    private final Map<String, Integer> spokenWords;
    
    public Speaker(String inputName,String rawText,int inputNumberOfDialoge)
    {
        this.name = inputName;
        this.rawText = rawText;
        this.numberOfDialoge = inputNumberOfDialoge;
        this.numberOfWords = 0;
        spokenWords = new HashMap<>();
        countWords();
        printOutStats();
        outPutHashMap();
    }
    
    public String getName(){
        return name;
    }
    
    public void countWords(){
        boolean word = false;
        int start = 0;
        for (int i = 0; i < rawText.length(); i++) {
            if (Character.isLetter(rawText.charAt(i))) {
                if(word==false){
                    start = i;
                }
                word = true;
            } else if (!Character.isLetter(rawText.charAt(i)) && word) {
                numberOfWords++;
                spokenWords.put(rawText.substring(start,i),1+spokenWords.getOrDefault(rawText.substring(start,i), 0));
                word = false;
            } 
        }
        numberOfWordsOverall += spokenWords.size();
    }
    
    public void outPutHashMap(){
        spokenWords.entrySet().stream().forEach((e) -> {
            System.out.println(e.getKey() + " : " + e.getValue());
        });
    }
    
    public void printOutStats(){
        System.out.println("Speaker:"+name);
        System.out.println("Anzahl der Worte: "+numberOfWords);
        System.out.println("Anzahl unterschiedlicher Worte: "+spokenWords.size());
    }
    
    public String getRawText(){
        return rawText;
    }   
}