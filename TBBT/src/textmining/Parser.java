/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Manuel-Mac
 */
public class Parser{
    
    
    private String fulltext;
    
    //Löscht bestimmmte Zeichen aus String
    public void setString(String inputString)
    {
        //Erstellen einer Hashmap zum Speichern von Dialog/Speaker
        Map<String, String> RawData = new HashMap<>();
        
        // Erstellen des Speaker-Objekts für Übergabe an Parser/Speaker
        Speaker TBBTSpeaker = new Speaker(); 
        
        //Übergabe des Strings aus Readerklasse
        this.fulltext = inputString;
        
        //Löschen von leeren Zeilen
        String adjusted = fulltext.replaceAll("(?m)^[ \t]*\r?\n", "");
        
        //Suche nach Klammern und deren Inhalt (Im Drehbuch Anweisungen) und ersetzte diese durch nichts.
        Pattern PatternKlammer = Pattern.compile ("\\(\\w\\W\\)");
        Matcher MatcherKlammer = PatternKlammer.matcher(adjusted);
        adjusted = MatcherKlammer.replaceAll("");
        
        //Suche nach AngangsNotiz - immer die selbe - ersetzt durch nichts
        Pattern PatternAnfangsNotiz = Pattern.compile ("#[1]Big Bang Theory Transcripts » Feed [2]Big Bang Theory Transcripts » Comments Feed [3]alternate [4]alternate [5]Big Bang Theory Transcripts [6]WordPress.com [7]Big Bang Theory Transcripts All the episodes, right here.");
        Matcher MatcherAnfangsNotiz = PatternAnfangsNotiz.matcher(adjusted);
        adjusted = MatcherAnfangsNotiz.replaceAll("");
        
        //Suche nach Endkommentaren - Zeile fängt immer mit einem Asterisk an - ersetzt sie durch nichts.
        Pattern PatternAsterisk = Pattern.compile ("\\*.");
        Matcher MatcherAsterisk = PatternAsterisk.matcher(adjusted);
        adjusted = MatcherAsterisk.replaceAll("");
        
        //Suche nach DonationNotiz (ist immer die selbe) und ersetzte diese durch nichts.
        Pattern PatternDonationNotiz = Pattern.compile ("Like this: Like Loading... This page takes a lot of time and effort to keep up to date. Perhaps if you get some use out of it, you could chuck me a few pennies towards keeping it going. [8][btn_donate_SM.gif]");
        Matcher MatcherDonationNotiz = PatternDonationNotiz.matcher(adjusted);
        adjusted = MatcherDonationNotiz.replaceAll("");
        
        //Suche nach Scene-Beschreibungen und ersetzt diese druch nichts
        Pattern PatternScene = Pattern.compile ("Scene\\:.");
        Matcher MatcherScene = PatternScene.matcher(adjusted);
        adjusted = MatcherScene.replaceAll("");
                
        //Suche nach Doppelpunkt in Zeile, was davor steht = Speaker; was danach steht = Dialog
        String[] parts = adjusted.split(":");
                        String SpeakerString = parts[0];
                        String MonologString = parts[1];
                        
                        // Speichern der beiden Strings in die Hashmap RawData
                        RawData.put(SpeakerString, MonologString);
        
                        
         //Übergabe des Speaker-Strings in Speaker-Klasse             
         TBBTSpeaker.setString(SpeakerString);
    }
}