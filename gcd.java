
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Liam Clarke
 */
public class gcd {

    public static void main(String[] args)
    {
    Scanner in = new Scanner(System.in);
   /*
    // !    Getting in two integer values.        
    //     System.out.println("Please Enter first Big int");
    //     BigInteger a = in.nextBigInteger();
    //      
    //     System.out.println("\n\n");
    //
    //     System.out.println("Please Enter second Big int");
    //     BigInteger b = in.nextBigInteger();
        
*/

BigInteger a = BigInteger.valueOf(4864);

BigInteger b = BigInteger.valueOf(3458);

       
       System.out.println("Algorithm 1.1\nAnswer = " + algorithm1_1(a, b));

       System.out.println("\n\nalgorithm1.2\nAnswer = ");
       displayAlg1_2(algorithm1_2(a,b));

       in.close();
    }


    //* GCD 
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
    //? xa + yb = d
    public static BigInteger[] algorithm1_2(BigInteger a, BigInteger b)
    {
        if(a.compareTo(b)==-1)
        {
            BigInteger temp = b;
            b = a;
            a = temp;
        }
        BigInteger[] returnArray = new BigInteger[3];
        BigInteger d = algorithm1_1(a, b);
        BigInteger[] x = new BigInteger[3];
        BigInteger[] y = new BigInteger[3];

        //todo 1. if b = 0 then set d ← a, x ← 1, y ← 0, and return (d, x, y)
        if(b.equals(BigInteger.ZERO))
        {
            d = a;
            x[0] = BigInteger.ONE;
            y[1]= BigInteger.ZERO;
            returnArray[0] = d;
            returnArray[1] = x[0];
            returnArray[2] = y[0];
            return(returnArray);
        }
        //todo 2. Set x2 ← 1, x1 ← 0, y2 ← 0, y1 ← 1. 
        else{

            x[1] = BigInteger.ZERO;
            x[2] = BigInteger.ONE;
            y[1] = BigInteger.ZERO;
            y[2] = BigInteger.ONE;


        //todo 3. While b > 0 do the following:
            //? This compare to method returns 1 if b is larger than 0
            while(b.compareTo(BigInteger.ZERO) == 1)
            {
                //todo ~ q ← a/b
                BigInteger q = a.divide(b);
                //todo ~ r ← a − qb
                BigInteger r = algorithm1_1(a, b);
                //todo ~ x ← x2 − qx1
                x[0] = x[2].subtract(q.multiply(x[1]));
                //todo ~ y ← y2 − qy1
                y[0] = y[2].subtract(q.multiply(y[1]));

                //todo  a, b ← r
                b = r;

                //todo x2 ← x1
                x[2] = x[1];
                //todo  x1 ← x
                x[1] = x[0];
                //todo y2 ← y1
                y[2] = y[1];
                //todo  y1 ← y
                y[1] = y[0];
                
            }

            //todo 4. Set d ← a, x ← x2, y ← y2, and return (d, x, y).
            d = a;
            x[0] = x[2];
            y[0] = y[2];

            returnArray[0] = d;
            returnArray[1] = x[0];
            returnArray[2] = y[0];

            return(returnArray);
        }
    }



    public static void displayAlg1_2(BigInteger[] ans)
    {
        for(BigInteger b: ans)
        {
            System.out.println(b);
        }
    }
     
    
    
    public static void readnumbers()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a file name to read from: ");
        String filename = in.nextLine();

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
            myFile.close();
        } catch (FileNotFoundException fe) {
            System.out.println("That file was not found in the system, sorry.");
        }
        
    }

}