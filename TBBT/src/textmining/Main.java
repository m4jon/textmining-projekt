/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Manuel-Mac
 */
public class Main {
    private Vector<Speaker> vSpeaker;
    public static void main(String[] args) throws IOException {
        Vector<Speaker> vSpeaker = new Vector();
        Map<String, String> hashMap = new HashMap<>();
        
        Reader reader = new Reader(hashMap,vSpeaker);

        /*vSpeaker.get(13).printOutStats();
        vSpeaker.get(13).outPutHashMap();
        System.out.println(vSpeaker.get(13).getRawText());
        vSpeaker.get(13).sortWordsQuantity();
        vSpeaker.get(13).outPutHashMap();
        */
        //sortVectorAfterWords();

        Dialog dialog = new Dialog(vSpeaker);
        dialog.showInfos();
        dialog.listTop10Speaker();
    }
}
