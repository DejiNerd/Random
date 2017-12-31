package dictionaryapplication;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;


public class FastDictionary {
    String dictName;
    int dictSize;
    String status ;
    


    public FastDictionary() {

//default constructor

    }   


///////// Methods


    public void ToolsMenu() {
	System.out.println("Menu");
	System.out.println("====");
	System.out.println("1-Load dictionary file");          
	System.out.println("2-Sort dictionary using Insertion sort");
	System.out.println("3-Sort dictionary using Enhanced Insertion sort");
	System.out.println("4-Shuffle dictionary");
	System.out.println("5-Search word");
	System.out.println("6-SpellChecker");
	System.out.println("7-Anagram");
	System.out.println("0-Exit");
	System.out.println("");
	System.out.print("Command: ");
    }


    
    public void loadDictionary(String name) throws FileNotFoundException {
        File dict= new File(name);
        dictName = name;
        Scanner scan = new Scanner(dict);
        dictSize = scan.nextInt();
        status="unsorted";
    }

    
        
    public void saveDictionary(String name) {

// to complete

    }


/////////// Other methods to complete 

// binarySearch method for enhanced insertion sort
    public static int binarySearch(String[] dict, int lowerBound, 
        int upperBound, String value) {
        while (lowerBound <= upperBound){
            int curIn = (lowerBound + upperBound)/2;
            int compRes = dict[curIn].compareTo(value);
            if (compRes == 0) {
                return curIn;
        } else if (compRes > 0){
            upperBound = curIn - 1;
        } else {
            lowerBound = curIn + 1;
        }
    }
        return lowerBound;
    }

// sortDictionary

// insertionSort
    public void insertionSort() throws FileNotFoundException{
        
        Scanner scan = new Scanner(new File(dictName));
        String dict[] = new String[dictSize];
        scan.nextLine();
        while(scan.hasNext()){
            for(int i = 0; i < dict.length; i++){
                dict[i]=scan.next();                          //INITIALIZIES ARRAY
            }
        }
        
        int in,out;                                         // Insertion sorting begins
        for(out=1; out< dictSize; out++){
            String temp = dict[out];      // Stores the marked value in a temp
            in=out;
            
            while(in>0 && dict[in-1].compareTo(temp) > 0){ //comparison of string begins
                dict[in] = dict[in-1];
                in--;
            }
            dict[in] = temp;
        }
        
        status = "sorted";
        
       
         try (PrintWriter output = new PrintWriter( "sorted-" + dictName)) {
            output.println(dict.length);
            for(int i=0; i<dict.length ; i++){
                output.println(dict[i]);
            }
            output.close();
        }
    
        
        
        
    }

// insertionSortEnhanced
    public void insertionSortEnhanced() throws FileNotFoundException{
        Scanner scan = new Scanner(new File(dictName));
        String dict[] = new String[dictSize];
        scan.nextLine();
        while(scan.hasNext()){
            for(int i = 0; i < dict.length; i++){
                dict[i]=scan.next();                          //INITIALIZIES ARRAY
            }
        }
        
        for (int out = 1; out < dict.length; out++) {
        String temp = dict[out];
        int indexToInsert = binarySearch(dict, 0, out - 1, temp);
        if (indexToInsert < out) {
            System.arraycopy(dict, indexToInsert, dict, 
                    indexToInsert + 1, out - indexToInsert);
            dict[indexToInsert] = temp;
        }
    }


        try (PrintWriter out = new PrintWriter( "sorted-" + dictName)) {
            out.println(dict.length);
            for(int i=0; i<dict.length ; i++){
                out.println(dict[i]);
            }
            out.close();
        }
        
        status = "sorted";
       

    
    

        
    }
   
// shuffleDictionary
    public void shuffleArray() throws FileNotFoundException { 
        Scanner scan = new Scanner(new File(dictName));
        String dict[] = new String[dictSize];
        scan.nextLine();
        while(scan.hasNext()){
            for(int i = 0; i < dict.length; i++){
                dict[i]=scan.next();                          //INITIALIZIES ARRAY
            }
        }
        
        // shuffling begins using fisher yate algorithm of permutations
        Random rnd= new Random();
         int out,index;
         String temp; 
        for(out= dict.length -1; out>0; out--)    // outer loop (backward)
            {      index=rnd.nextInt(out+1); //select random number in [0:out] 
                // simple swap    
                temp = dict[index]; 
                dict[index] = dict[out];
                dict[out] = temp; } 
        
        status = "unsorted";
        try (PrintWriter write = new PrintWriter( "unsorted-" + dictName)) {
            write.println(dict.length);
            for(int i=0; i<dict.length ; i++){
                write.println(dict[i]);
            }
        }
    
        System.out.println("<<OK dictionary shuffled and saved in file " + "unsorted-" + dictName +" >>");

        
}               // end shuffleArray()
    
//SearchWord
public int searchWord() throws FileNotFoundException{
        Scanner scan = new Scanner(new File("sorted-" + dictName));
        String dict[] = new String[dictSize];
        scan.nextLine();
        while(scan.hasNext()){
            for(int i = 0; i < dict.length; i++){
                dict[i]=scan.next();                          //INITIALIZIES ARRAY
            }
        }
        
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter word to search");
        String word = userInput.next().toLowerCase();
        
        //binary search
        int lowerBound = 0;
        int upperBound = dict.length -1;
        int curIn;
        while(true){
            curIn = (lowerBound+upperBound)/2;
            if((dict[curIn].toLowerCase()).compareTo(word) == 0)
                return curIn + 1 ;      // +1 because the dictioniary size isn't included in the array, so we have to increase the index by 1
            else if(lowerBound > upperBound)
                return -1;
            else{
                if (dict[curIn].compareTo(word)<0)
                    lowerBound = curIn+1;
                else
                    upperBound = curIn-1;
            }
        }
        
}
    

// spellCheckFile
public void spellChecker() throws FileNotFoundException{
    Scanner scan = new Scanner(new File("sorted-" + dictName));
        String dict[] = new String[dictSize];
        scan.nextLine();
        while(scan.hasNext()){
            for(int i = 0; i < dict.length; i++){
                dict[i]=scan.next();                          //INITIALIZIES ARRAY
            }
        }
        
    Scanner userInput = new Scanner(System.in);
    System.out.println("Enter name of text file");      
    String fileToBeChecked = userInput.nextLine();   //Store userinput in the string fileToBeChecked
    Scanner scanFile = new Scanner (new File (fileToBeChecked));   //Scans the file
    int j ;
    while(scanFile.hasNext()){
        String y = scanFile.next().toLowerCase();
        StringTokenizer smooth = new StringTokenizer(y,".,:;?/<>[]{}|_()!&");   //delimiter removes all the punctuations not identified by the dictionary(i.e smoothens the string)
        while(smooth.hasMoreTokens()){
            String u = smooth.nextToken();
            for(j=0; j<dict.length; j++)
                if(u.equals(dict[j]))  
                    break;      // if word is found in the dictionary stop, loop moves on to the next word
            if( j == dict.length)
                System.out.println("Incorrectly spelled (' " + u + " ')");
        }
    }
}

// anagram

public void anagram() throws FileNotFoundException{
   Scanner scan = new Scanner(new File("sorted-" + dictName));
        String dict[] = new String[dictSize];
        scan.nextLine();
        while(scan.hasNext()){
            for(int i = 0; i < dict.length; i++){
                dict[i]=scan.next();                          //INITIALIZIES ARRAY
            }
        }
        
        Scanner input = new Scanner(System.in);
        System.out.println("Enter word to analyze");
        String s = input.next().toLowerCase();
        String arranged1="" ;
       
        char[] storeChar = new char[s.length()];
        for(int t = 0; t<s.length(); t++){
            storeChar[t]= s.charAt(t);              //Store every character in the string in an array
        }
        
        int in, out;          //insertion sort, it sorts the char [] in order
        for(out=1; out< s.length(); out++){
            char temp = storeChar[out];
        
            in = out;
            while(in>0 && storeChar[in-1]>temp){
                
                storeChar[in] = storeChar[in-1];
                --in;
            }
            storeChar[in]= temp;
            }
        for(int y = 0; y<storeChar.length; y++){
             arranged1 = arranged1 + storeChar[y];    //stores the alphabetically arranged string
            }
        
        for(int i=0; i<dict.length; i++){
            String arranged2="";
            String unarranged =  dict[i].toLowerCase();
            char[] storeIt = new char[dict[i].length()];
            
            for(int t = 0; t<unarranged.length(); t++){
            storeIt[t]= unarranged.charAt(t);
            }
        
            int inside , outside;          //insertion sort
            for(outside=1; outside< storeIt.length; outside++){
            char temp = storeIt[outside];
        
            inside = outside;
            while(inside>0 && storeIt[inside-1]>temp){
                storeIt[inside] = storeIt[inside-1];
                --inside;
            }
            storeIt[inside]= temp;
            }
            for(int y = 0; y<storeIt.length; y++){
             arranged2= arranged2 + storeIt[y];        //arranged every character of every word in the dict in alphabetical order
             
            }
            
            if(arranged1.equals(arranged2)){
                System.out.println(unarranged); 
            }
        }
        }



    String getName() {
        if(dictName == null)return "N/A";
        return dictName;
    }

    int getSize() {
        return dictSize;
    }

    String getStatus() {
        return status;
    }



}

    

