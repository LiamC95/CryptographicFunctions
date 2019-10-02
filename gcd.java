
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author grahamm
 */
public class gcd {

    public static void main(String[] args)
    {
    Scanner in = new Scanner(System.in);
   
// !    Getting in two integer values.        
       System.out.println("Please Enter first Big int");
       BigInteger a = in.nextBigInteger();
        
       System.out.println("\n\n");

       System.out.println("Please Enter second Big int");
       BigInteger b = in.nextBigInteger();
        

       
       System.out.println("Algorithm 1.1\nAnswer = " + algorithm1_1(a, b));

       System.out.println("\n\nalgorithm1.2\nAnswer = " + algorithm1_2(a, b));
    }
    public static BigInteger algorithm1_1(BigInteger a, BigInteger b)
    {
        BigInteger r;
        while(!b.equals(BigInteger.ZERO))
        {
            // Find the remainder of the division
            r = a.mod(b);

            //* pass the b value into the a position
            a = b;

            //* pass the remainder value into b
            b = r;
        }

        return a;
    }


    //? Trying to apply the extended euclidian alg.
    public static BigInteger algorithm1_2(BigInteger a, BigInteger b)
    {
        if(a.compareTo(b) == 1 || a.compareTo(b) == 0)
        {
           return algorithm1_1(a, b);
        }
        else{
            return algorithm1_1(a, b);
        }
    }
     

/*
    public static BigInteger gcdLiam(BigInteger a, BigInteger b)
    {
        BigInteger q;
        BigInteger r = new BigInteger("1");
        //* we first have to reduce the the numbers down 
        //? x = y * q + r

        
        while(1!=0)
        {
            
            q = a.mod(b);

            r = (a).subtract(b.multiply(q));
                        
            a = b;
            b = r;

            System.out.println("a = "+ a);

            System.out.println("b = "+ b);
    
            System.out.println("r = "+ r);
    
            System.out.println("q = "+ q);
            if(b.compareTo(BigInteger.ZERO)>0)
            {
                return a;
            }
            
        }

        
        

    }

    */

    
    public static int xModYOne(int x, int y){
        int ans;
        ans = x%y;
        return ans;
    }
    
    public static int xModYTwo(int x, int y){
        int ans;
        double z = (double)x/y;
        double r = z-Math.floor(z);
        ans = (int)(r*y);
        
        return ans;
    }
    
    public static int xModYThree(int x, int y){
        while(x>=y){
            x = x - y;
        }
        
        return x;
    }
    public static int xModYFour(int x, int y){
        x= x - ((x/y)*y);
        
        return x;
    }
    public static void readnumbers()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a file name to read from: ");
        String filename = keyboard.nextLine();

        try {
            Scanner myFile = new Scanner(new File(filename));
            int lineCount = 0;
            while (myFile.hasNextLine()) {
                lineCount++;
                String line = myFile.nextLine();
                String[] data = line.split(" ");
                if (data.length == 2) {
                    try {
                        BigInteger a = new BigInteger(data[0]);
                        BigInteger b = new BigInteger(data[1]);
//                        System.out.println("\nTest ans :\t"+a%b);
//                        System.out.println("Algorithm 1\t:\t"+xModYOne(a,b));
//                        System.out.println("Algorithm 2 "+a+" mod "+b+"\t:\t "+xModYTwo(a,b));
//                        System.out.println("Algorithm 3\t:\t"+xModYThree(a,b));
//                        System.out.println("Algorithm 4 "+a+" mod "+b+"\t:\t "+xModYFour(a,b));
                        
                        System.out.println("The result of "+a+" modulo "+b+" is = "+algorithm1_1(a,b));
                    } catch (NumberFormatException e) {
                        System.out.println("An exception occurred while parsing numbers from line " + lineCount + " : " + line);
                    }
                }
                else
                {
                    System.out.println("Line " + lineCount + " was not formatted properly: \"" + line + "\"");
                }
            }
        } catch (FileNotFoundException fe) {
            System.out.println("That file was not found in the system, sorry.");
        }
        
    }

}