package dictionaryapplication;

import java.io.FileNotFoundException;


class DictionaryApplication{


    public static void main(String args[]) throws FileNotFoundException{

	// declare instance of EasyIn class
	EasyIn easy = new EasyIn();
	// declare instance of FastDictionary class
	FastDictionary dict=new FastDictionary();

	long startTime,endTime;  // measure of time in ms
        int loadedOrNot = 0;

	
	System.out.println();
	System.out.println("Welcome to the Dictionary App");
	System.out.println("=============================");

        String pause;     
	int command = -1;
	while (command != 0) {
	    //if (command>0) {pause = easy.readString();}   // just to create a pause before printing the menu again
	    System.out.println();
	    System.out.println("Current Dictionary is '"+dict.getName()+"' of size "+dict.getSize()+" and "+dict.getStatus());  
	    System.out.println();
            
	    dict.ToolsMenu(); // print menu
	    command = easy.readInt();


	   

	    
	    switch(command)
		{
		case  1: 
                    System.out.println("Enter file name: ");
                    dict.loadDictionary(easy.readString());
                    loadedOrNot++;  //keeps track of whether dictionary/file has been loaded or not
		    break;

		case  2: 
                    if(loadedOrNot==0){
                        System.out.println(">>>>>>>>> Dictionary must be loaded first <<<<<<<<< ");
                        System.out.println("Selection Invalid!- Try Again");
                    }
                    startTime = System.currentTimeMillis();
                    dict.insertionSort();
                    endTime = System.currentTimeMillis();
                    long duration = -startTime +endTime;
                    System.out.println("<<OK dictionary sorted in" + duration +"ms "+ "and saved in file " + "sorted-" + dict.getName() +" >>");
		    break;
		    
                case  3: 
                    if(loadedOrNot==0){
                        System.out.println(">>>>>>>>> Dictionary must be loaded first <<<<<<<<< ");
                        System.out.println("Selection Invalid!- Try Again");
                    }
                    startTime = System.currentTimeMillis();
                    dict.insertionSortEnhanced();
                    endTime = System.currentTimeMillis();
                    duration = -startTime +endTime;
                    System.out.println("<<OK dictionary sorted in " + duration +"ms "+ "and saved in file " + "sorted-" + dict.getName() +" >>");
		    break;
		    
                case 4: 
                    if(loadedOrNot==0){
                        System.out.println(">>>>>>>>> Dictionary must be loaded first <<<<<<<<< ");
                        System.out.println("Selection Invalid!- Try Again");
                    }
                    dict.shuffleArray();           // shuffle dictionary
		    break;

		case  5: 
                    if("null".equals(dict.getStatus()) || "unsorted".equals(dict.getStatus())){
                        System.out.println(">>>>>>>>> Dictionary must be sorted first <<<<<<<<< ");
                        System.out.println("Selection Invalid!- Try Again");
                    }
                    else if(dict.searchWord()== -1){
                        System.out.println("word not found!");
                    }
                    else System.out.println(dict.searchWord());  // search word in dictionary
		    break;

		case 6:
                    if("null".equals(dict.getStatus()) || "unsorted".equals(dict.getStatus())){
                        System.out.println(">>>>>>>>> Dictionary must be sorted first <<<<<<<<< ");
                        System.out.println("Selection Invalid!- Try Again");
                    }
                    else dict.spellChecker(); // spellchecker
		    break;
		      
		case 7:
                    if("null".equals(dict.getStatus()) || "unsorted".equals(dict.getStatus())){
                        System.out.println(">>>>>>>>> Dictionary must be sorted first <<<<<<<<< ");     //if dictionary has not been loaded print the following
                        System.out.println("Selection Invalid!- Try Again");
                    }
                    else dict.anagram();     // anagram
		    break;
                 
		case 0: // exit
		    break;

		default:
		    System.out.println("Selection Invalid!- Try Again");
		}
	}

	System.out.println("Goodbye!");
    }
}
