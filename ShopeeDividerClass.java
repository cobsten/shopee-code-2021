/* IMPORTANT: Multiple classes and nested static classes are supported */

//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class ShopeeDividerClass {
    public static void main(String args[] ) throws Exception {
        /* Sample code to perform I/O:
         * Use either of these methods for input

        //BufferedReader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String name = br.readLine();                // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        //Scanner
        Scanner s = new Scanner(System.in);
        String name = s.nextLine();                 // Reading input from STDIN
        System.out.println("Hi, " + name + ".");    // Writing output to STDOUT

        */
 
        // Write your code here
        //Scanner
        Scanner s = new Scanner(System.in);
        String line1 = s.nextLine();
        String[] arrOfStr = line1.split(" ");
        int engineers = Integer.parseInt(arrOfStr[0]);
        int groups = Integer.parseInt(arrOfStr[1]);
        String line2 = s.nextLine();
        String[] arrOfStrNoise = line2.split(" ");
        int[] noises = new int[arrOfStrNoise.length];
        int index = 0;
        for(String i: arrOfStrNoise){
            noises[index++] = Integer.parseInt(i);
        }

        if(engineers<groups){
            System.out.println("0");
        }else{
            int min = recursiveNoise(groups,noises,0,noises.length-1);
            System.out.println(min);
        }
        
    }

    static HashMap<String, Integer> hmap = new HashMap<>();
    static int recursiveNoise(int group, int[] engineers,int startIndex, int endIndex){
        //base case
        if (hmap.containsKey(group+"-"+startIndex+"-"+endIndex)) {
            return hmap.get(group+"-"+startIndex+"-"+endIndex);
        }
                
        if(group == 1){
            int sum = 0;
            for(int i = startIndex; i<=endIndex;i++){
                sum += engineers[i];
            }
            sum *= (endIndex - startIndex + 1);
            hmap.put(group+"-"+startIndex+"-"+endIndex, sum);
            return sum;
        }



        int min = Integer.MAX_VALUE;
        for(int i=(endIndex-(group-1)); i>=startIndex ;i--){
            int placeHolder = recursiveNoise(1,engineers,startIndex,i) + recursiveNoise(group-1,engineers,i+1,endIndex);
            min = min > placeHolder ? placeHolder : min;
        }
        hmap.put(group+"-"+startIndex+"-"+endIndex, min);
        
        return min;
    }
}
