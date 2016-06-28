/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

/**
 *
 * @author Manuel-Mac
 */
public class Parser{
    
    
    private String fulltext;
    
    //Löscht bestimmmte Zeichen aus String
    public String bearbeiteString(String inputString)
    {
        //Übergabe des Parameters
        this.fulltext = inputString;
        
        //Löschen von leeren Zeilen
        
        String adjusted = fulltext.replaceAll("(?m)^[ \t]*\r?\n", "");
        
        //Rückgabe des bearbeiteten Strings
        return fulltext;
    }
}