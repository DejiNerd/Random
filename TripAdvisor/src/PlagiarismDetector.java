//Written by Ayodeji Marquis

import java.io.FileNotFoundException;
import java.util.Scanner;

public class PlagiarismDetector {
    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println("Heydsr'1213 therEs'lol hj123".replaceAll("[^a-zA-Z]","").toLowerCase());
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter synonym file");
        String synonymFile = scan.nextLine();
        System.out.println("Please enter input file 1");
        String inputFileOne = scan.nextLine();
        System.out.println("Please enter input file 2");
        String inputFileTwo = scan.nextLine();
        System.out.println("Please enter N, the size of the Tuple. To use default value please enter 0");
        String no = scan.nextLine();

        if (synonymFile.equals("") && inputFileOne.equals("") && inputFileTwo.equals("") && no.equals("")) {
            System.out.println("***************************************************************************");
            System.out.println("************* Please follow the instructions on the screen ****************");
            System.out.println("***************************************************************************");
        } else {
            int N = Integer.parseInt(no);
            NTupleComparator nTupleComparator = new NTupleComparator(synonymFile, inputFileOne, inputFileTwo, N);
            System.out.println("Plagiarism percent is: " + nTupleComparator.getPercentage() + "%");
        }
    }
}
