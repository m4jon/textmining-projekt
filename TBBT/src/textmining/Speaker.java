/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author Manuel-Mac
 */
public final class Speaker implements Comparable
{
    private static int numberOfWordsOverall=0;
    private final String name;
    private final String rawText;
    private final int numberOfDialoge;
    
    private Integer numberOfWords;
    private Map<String, Integer> spokenWords;
    
    public Speaker(String inputName,String rawText,int inputNumberOfDialoge)
    {
        
        this.name = inputName;
        this.rawText = rawText;
        this.numberOfDialoge = inputNumberOfDialoge;
        this.numberOfWords = 0;
        spokenWords = new HashMap<>();
        countWords();
        //printOutStats();
        //outPutHashMap();
        //System.out.println(name);
    }
    
    public int getNumberOfWords(){
        return numberOfWords;
    }
    public int getNumberOfWordsOverall(){
        return numberOfWordsOverall;
    }
    
    public String getName(){
        return name;
    }
    
    private void countWords(){
        boolean word = false;
        int start = 0;
        for (int i = 0; i < rawText.length(); i++) {
            if (Character.isLetter(rawText.charAt(i)) || rawText.charAt(i) == '’' ) {
                if(word==false){
                    start = i;
                }
                word = true;
            } else if (!Character.isLetter(rawText.charAt(i)) && word) {
                numberOfWords++;
                numberOfWordsOverall++;
                spokenWords.put(rawText.substring(start,i),1+spokenWords.getOrDefault(rawText.substring(start,i), 0));
                word = false;
            } 
        }
        //numberOfWordsOverall += spokenWords.size();
    }
    
    public void sortWordsQuantity(){
        //spokenWords.
        spokenWords = sortByValues((HashMap) spokenWords);
        //outPutHashMap();
    }
    
    private static HashMap sortByValues(HashMap map) { 
        List list = new LinkedList(map.entrySet());
        // Defined Custom Comparator here
        Collections.sort(list, (Object o1, Object o2) -> ((Comparable) ((Map.Entry) (o1)).getValue())
                .compareTo(((Map.Entry) (o2)).getValue()));

        // Here I am copying the sorted list in HashMap
        // using LinkedHashMap to preserve the insertion order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
               Map.Entry entry = (Map.Entry) it.next();
               sortedHashMap.put(entry.getKey(), entry.getValue());
        } 
        return sortedHashMap;
    }
    
    public void outPutHashMap(){
        spokenWords.entrySet().stream().forEach((e) -> {
            System.out.println(e.getKey() + " : " + e.getValue());
        });
    }
    
    public void printOutStats(){
        System.out.println("Speaker:"+name);
        double wtf = numberOfWordsOverall;
        double prozent = (numberOfWords/wtf)*100;
        
        System.out.println("Anzahl der Worte: "+numberOfWords + " (" + prozent + "%)");
        System.out.println("Anzahl unterschiedlicher Worte: "+spokenWords.size());
        top10Words();
    }
    
    public void top10Words(){
        sortWordsQuantity();
        int i = 0;
        System.out.println("top10");
        for(Map.Entry e : spokenWords.entrySet()){
          i++;
          if(i + 30 >= spokenWords.size()){
                System.out.println(e.getKey() + " = " + e.getValue());
          }
        }
    }
    
    public String getRawText(){
        return rawText;
    }

    @Override
    public int compareTo(Object o) {
        Speaker e = (Speaker) o;
        return Integer.compare(e.numberOfWords, this.numberOfWords);
    }
    
    private static class ValueComparer implements Comparator 
    {
        private Map  _data = null;
        public ValueComparer (Map data)
        {
            super();
            _data = data;
        }

        @Override
        public int compare(Object o1, Object o2) 
        {
            Integer e1 = (Integer) _data.get(o1);
            Integer e2 = (Integer) _data.get(o2);
            return e1.compareTo(e2);
        }
    }
}
