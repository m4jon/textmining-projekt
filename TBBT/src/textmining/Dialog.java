/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.util.Collections;
import java.util.Vector;

/**
 *
 * @author Manuel-Mac
 */
public class Dialog{
    
    Vector<Speaker> vSpeaker;
    
    public Dialog(Vector<Speaker> inputVSpeaker){
        this.vSpeaker = inputVSpeaker;
        sortVectorAfterWords();
        
    }

    public void sortVectorAfterWords(){
        Collections.sort(vSpeaker);
    }

    public void showInfos(){
        System.out.println();
        System.out.println();
        System.out.println("Ausgabe der Ergebnisse:");
        System.out.println(vSpeaker.size() +  " verschiendene Sprecher gefunden");
        System.out.println(vSpeaker.get(0).getNumberOfWordsOverall() + " Worte gesamt");
    }

    public void listTop10Speaker(){
        System.out.println();
        System.out.println("Ausgabe der Top 10 Sprecher");
        for(int i=0; i < 10; i++){
            System.out.println((i+1));
            vSpeaker.get(i).printOutStats();
        }    
    }
}