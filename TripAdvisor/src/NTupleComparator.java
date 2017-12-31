//Written by Ayodeji Marquis

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class NTupleComparator {
    private String synonymFile;
    private String inputFileOne;
    private String inputFileTwo;
    private int N;

    public NTupleComparator(String synonymFile, String inputFileOne, String inputFileTwo, int N) {

        this.synonymFile = synonymFile;
        this.inputFileOne = inputFileOne;
        this.inputFileTwo = inputFileTwo;
        if (N <= 0) {
            this.N = 3;
        } else {
            this.N = N;
        }
    }

    public double getPercentage() throws FileNotFoundException {
        int i, j, count;
        String str, s;
        i = j = count = 0;
        str = s = "";
        ArrayList<Integer> tuplesHashCode = new ArrayList<>();
        Scanner synonyms = new Scanner(new File(synonymFile));
        Scanner inputOne = new Scanner(new File(inputFileOne));
        Scanner inputTwo = new Scanner(new File(inputFileTwo));

        // make synonyms map to the same word {first word in the line}
        HashMap<String, String> hashMap = new HashMap<>();
        while (synonyms.hasNextLine()) {
            String[] array = synonyms.nextLine().toLowerCase().split(" ");
            if (array.length > 1) {
                String temp = array[0];
                for (String word : array) {
                    hashMap.put(word, temp);
                }
            }
        }

        // store the hashcode of all N-tuples in file 2
        while (inputTwo.hasNext()) {
            String word = stringEdit(inputTwo.next());
            if (word.equals("")) break;
            String getSynonym = hashMap.get(word);
            if (getSynonym != null) {
                str = str + getSynonym + " ";
            } else {
                str = str + word + " ";
            }
            if (i < N) {
                if (++i == N) {
                    tuplesHashCode.add(str.trim().hashCode());
                }
            } else {
                String[] temp = str.split(" ", 2);             //remove first word
                str = temp[1];
                tuplesHashCode.add(str.trim().hashCode());
            }
        }

        // counts the number of tuples in file 1 that are in file 2
        while (inputOne.hasNext()) {
            String word = stringEdit(inputOne.next());
            if (word.equals("")) break;
            String getSynonym = hashMap.get(word);
            if (getSynonym != null) {
                s = s + getSynonym + " ";
            } else {
                s = s + word + " ";
            }
            if (j < N) {
                if (++j == N) {
                    if (tuplesHashCode.contains(s.trim().hashCode())) {
                        count++;
                    }
                }
            } else {
                String[] temp = s.split(" ", 2);              //remove first word
                s = temp[1];
                if (tuplesHashCode.contains(s.trim().hashCode())) {
                    count++;
                }
            }
        }

        return ((double) (count * 100)) / (double) tuplesHashCode.size();
    }

    //removes all non letters in a string, then converts to lowercase
    public String stringEdit(String s) {
        return s.replaceAll("[^a-zA-Z]","").toLowerCase();
    }
}
