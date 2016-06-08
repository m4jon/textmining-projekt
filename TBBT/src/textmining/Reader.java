/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package textmining;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Manuel-Mac
 */
public class Reader {
    
    public static void main(String[] args) {

    try {
     try (BufferedReader br = new BufferedReader(new FileReader("/Users/Manuel-Mac/Desktop/Series 1 Episode 01 – Pilot Episode.txt")))
  {

   String sCurrentLine;

   while ((sCurrentLine = br.readLine()) != null) {
    System.out.println(sCurrentLine);
   }
}
  } catch (IOException e) {
  } 
}
}