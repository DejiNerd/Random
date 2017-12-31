/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mstw;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.*;
import java.util.*;

class EasyRead
{
    
    public static int wordIndexSize;
    public static int lineCount;
    public static int[] lineWordCount = new int[1000];
    public static int finalCount = 0;
    public static String[] ArrayOfLines = new String[100];
    public static String[] wordsUnsortedFinal;
    public static String[] words_p = null;
    public static String[] words_filtered = new String[500];
    public String[] getLines(){
        return ArrayOfLines;
    }
    public int[] getLineWordCount(){
        return lineWordCount;
    }
    public int getWordIndexSize(){
        return wordIndexSize;
    }
    public String[] getUnsortedArray(){
        return wordsUnsortedFinal;
    }
    public String[] getWordsWithP(){
        return words_p;
    }
    public String[] getWordsNoP(){
        return words_filtered;
    }
    public int getSize(){
        return finalCount;
    }
    public int getLineCount(){
        return lineCount;
    }
    public static void readFile1(String fileName) {
	// Read file line by line 
	int count=0;
        int size=0;
	
	
	try {
	    File file = new File(fileName);
	    Scanner scanner = new Scanner(file);
            size = scanner.nextInt();
            finalCount = size;
            String[] wordsUnsorted =new String[size];
            for(count = 0; count<size; count++){
                wordsUnsorted[count] = scanner.next().toLowerCase();
            }
	    scanner.close();
            wordsUnsortedFinal = wordsUnsorted;
            
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}//end of catch
       //System.out.println("\nsize of "+ fileName + ": "+ wordsUnsortedFinal.length);
       /*for (int i=0;i<size;i++) {
       System.out.println(wordsUnsortedFinal[i] +" is word "+ (i+1));
       }//end of for-loop debug*/
    }//end readFile1
    
    
    public static void readFile(String fileName) {
	// Read file line by line, and also word by word. 
        
	int count=0;
        int wordIndex = -1;
        String[] wordArray = new String[500];
	try {
	    File file = new File(fileName);
	    Scanner scanner = new Scanner(file);

            String[] ArrayOfLines =new String[100];
            while(scanner.hasNextLine()){
                ArrayOfLines[count] = scanner.nextLine();
                //System.out.println(ArrayOfLines[count]);
                count++;
            }
            
	    scanner.close();
            lineCount = count;
            EasyRead.ArrayOfLines = ArrayOfLines;
            //System.out.println("Number of lines of words: " + count);
            for(int i = 0; i <lineCount; i++){
                int[] lineWordCount = new int[lineCount];
                StringTokenizer st = new StringTokenizer(ArrayOfLines[i]);
                while(st.hasMoreTokens()){
                    wordIndex++;
                    lineWordCount[i]++;
                    wordArray[wordIndex] = st.nextToken();
                     //wordArray still has punctuation
                     
                     
                }
                
                EasyRead.lineWordCount[i] = lineWordCount[i];
                //System.out.println(wordArray[wordIndex] + " has " + EasyRead.lineWordCount[i] + " words on the line"); 
            }
            wordIndexSize = wordIndex;
            for(int i = 0; i<=wordIndex; i++){
        words_filtered[i] = wordArray[i].replaceAll("[^\\p{L}]", "").toLowerCase();
        //System.out.println(words_filtered[i]);
        }
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}//end of catch
        words_p = wordArray;
        
        
    }//end readFile

    public static void readFile2(String fileName,int maxItems) {
	// Read file line by line with different elements on each line
	int count=0;
	String[] name =new String[maxItems];
	int[] age = new int[maxItems];
        
        
	try {
	    File file = new File(fileName);
	    Scanner scanner = new Scanner(file);
	    Scanner line;
	    while (scanner.hasNext() && count<maxItems) {
		line = new Scanner(scanner.nextLine()); // scan next line
		name[count]=line.next();                // read first element of line into string
		age[count]=line.nextInt();              // read second element into integer
		count ++;
		line.close();                           // close line
	    }
	    scanner.close();
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	}
       
	System.out.println(count);
        
	for (int i=0;i<count;i++) {
	    System.out.println(name[i] +" "+ age[i]);	  
	}
        
    }
    
      public static void readFile3(String fileName,int maxItems) {
	// Read file line by line with different elements on each line
	String Items[][] = new String[maxItems + 1][6];
        int count=1;
        
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            Scanner line;
            while (scanner.hasNext() || count<maxItems+1){
                line = new Scanner(scanner.nextLine());
                if (line.next().equals("Book")){
                    Items[count][0] = "Book";
                    Items[count][1] = line.nextLine();
                    Items[count][2] = line.nextLine();
                    Items[count][3] = line.nextLine();
                    Items[count][4] = line.nextLine();
                    count++;
                }
            
                if (line.next().equals("Movie")){
                    Items[count][0] = "Movie";
                    Items[count][1] = line.nextLine();
                    Items[count][2] = line.nextLine();
                    Items[count][3] = line.nextLine();
                    Items[count][4] = line.nextLine();
                    Items[count][5] = line.nextLine();
                    count++;
                }
                line.close();
            }  
        }catch (FileNotFoundException e) {
	    e.printStackTrace();
           }
        }

    

    // This method is just here to test the class
    /*
    public static void main (String args[]){   

	System.out.println("Test method readFile1");
	readFile1("data1.txt",6);

	System.out.println("");
	System.out.println("Test method readFile2");
	readFile2("data2.txt",6);
    }
    */
}
