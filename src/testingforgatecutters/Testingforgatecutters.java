package testingforgatecutters;

import java.io.IOException;
import java.net.URL;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JOptionPane;
import java.io.*;  
import javax.swing.JFrame;

public class Testingforgatecutters {

    public static void main(String[] args) throws IOException {
        
      int filenamecounter = 0;
      int filenamecounter2 = 0;
      int clockcounter = 0;
      int clockcounter2 = 0;
      int clockcounter3 = 0;
      int clockcounterphase2 = 0;
      int refinedcounter = 0;
      int refinedcounterphase2 = 0;
      int refinedcounterphase3 = 0;
      int importantcounter = 0;
      int importantholdercounter = 0;
      String holder = "";
      int[] supercounter1 = new int[99999];
      String[] superholder = new String[99999];
      String[] refinedholder = new String[99999];
      String[] refinedholderphase2 = new String[99999];
      String[] clockholder = new String[99999];
      String[] importantholder = new String[99999];
      String Tester = "Buy Now";
      int RefinedHolderStarter = 0;
      int RememberWhereItStarted = 0;
      String StuffToWrite = "";
      String GimmeAName ="";
      int filereadthingcount = 0;
      
      //Rename The Output File
GimmeAName = ("C:\\Users\\tremanleo\\Documents\\OutputForScrapers\\GateCuttersScrape.csv");    
File f3 = new File(GimmeAName);
FileWriter fw3 = new FileWriter(f3);
PrintWriter out3 = new PrintWriter(fw3);     

 String URL = ("http://www.pcs-company.com/viewproduct/molding-supplies/gate-cutters/GC1");
//Phase 1 starts, connect to the url, grab the data we want into refinedholder[] array.  
    Document document = Jsoup.connect(URL).timeout(1000 * 1000).get();
    Elements tdgrabber = document.select("td");
    for (Element tdgrab : tdgrabber) {
         holder = tdgrab.text();
         superholder[clockcounter] = holder;
         clockcounter = clockcounter +1;
         
         if(RefinedHolderStarter == 1)
         {
          refinedholder[refinedcounter] =  holder;
          refinedcounter++;
          clockcounter++;
         }             
         if(clockcounter == 10)
         {
             clockcounter = 0;
         }
          // The below turns on when we get to the right area. The right area always starts after "Buy Now"
         //Remember where it started will mark the <TD> after the one containing "Buy Now". It also turns on the refined holder
         if ( holder.toLowerCase().indexOf(Tester.toLowerCase()) != -1 ) 
              {
                 RefinedHolderStarter = 1;
                 RememberWhereItStarted = clockcounter;
                 RememberWhereItStarted++;
              }         
        }

    //PHASE 2 STARTS (PROCESS THE DATA Pulled From The Page) Here we take the data from the refinedholder[] array and process it. 
    //Currently each iteration is made of 10 items Starting At the, "item number," and ending with "Add".
    //The Data count 0 -> 1 -> 2-> 3-> up until 9 then 0 is a new set.
    //Slots 1, 7 and 9 are useless data and I will get rid of them.
    //This means I want the data passed to the excel sheet in phase 3 down to 7 strings, from 10.
    while(refinedcounterphase2 != refinedcounter)
    {     
        
        System.out.println("Refined Counter " + refinedcounterphase2 + " " + refinedholder[refinedcounterphase2] + " ");
        if(clockcounter2 != 1)
    {
     if(clockcounter2 != 4)
     {
         if(clockcounter2 != 5)
         {
                 if(clockcounter2 != 7)
                 {    
        importantholder[importantcounter] = refinedholder[refinedcounterphase2];
        System.out.println("Important Counter " + importantcounter + " " + importantholder[importantholdercounter] + " ");  
        importantholdercounter++;                   
        importantcounter++;        
                 }
         }
     }
    }
         if(clockcounter2 == 8)
         {
             clockcounter2 = 0;
         }
         refinedcounterphase2++;
         System.out.println("Clock Counter: " + clockcounter2);
         clockcounter2++;
    }
    importantholdercounter = 0;
  //Phase 3 Starts, Export The data to a spread sheet
  out3.print("Item Number" +"," + "Blad Open" + "," + "Weight" + "," + "Price" + "," + "\n");
 while(importantholdercounter != importantcounter)
 {  
    StuffToWrite = importantholder[importantholdercounter];
       
    if(clockcounter3 != 3)
    {
    out3.print("'" + StuffToWrite + "'");
    out3.print(",");  
    }

    if(clockcounter3 == 3)
    {
     out3.print("'" + StuffToWrite + "'");  
      out3.print("\n");  
    }  
    
clockcounter3++;
importantholdercounter++;

if(clockcounter3 == 4)
{
  clockcounter3 = 0;  
}
 }
     //Flush the output to the file
    out3.flush(); 
    //Close the Print Writer
   out3.close();       
   //Close the File Writer
   fw3.close(); 
   }
}    