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
    public static String[] stopwords = {"that’s","Hi","Hello","I’ll","we’re","he’s","And","So","It’s","I","You","don’t","it’s","can’t","You","I","I’m","you’re","","a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
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
    
    public void removeStopWords(){
        //spokenWords.remove()
        System.out.println("Starte bereinigung der Stopwörte");
        spokenWords.entrySet().stream().forEach((e) -> {
            for(int i=0; i < stopwords.length -1; i++){
                
                //System.out.println(e.getKey());
                //System.out.println(stopwords[i]);
                if(e.getKey().equals(stopwords[i]))
                {
                    e.setValue(1);
                }
            }
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
        System.out.println("Top 30 Words");
        removeStopWords();
        sortWordsQuantity();
        int i = 0;
        for(Map.Entry e : spokenWords.entrySet()){
          i++;
          if(i + 30 >= spokenWords.size()){
                System.out.println(e.getKey() + " = " + e.getValue());
          }
        }
    }
    
    public void removeStopWörter(){
        String[] stopWortListe;
        
        spokenWords.entrySet().stream().forEach((e) -> {
            if(e.getKey()=="asd"){}
        });
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
