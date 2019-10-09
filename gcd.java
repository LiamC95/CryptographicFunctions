
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    //     Getting in two integer values.        
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

       
       System.out.println("Euclidian algorithm\nAnswer = " + algorithm1_1(a, b));

       System.out.println("\n\nExtended euclidian algorithm\nAnswer = ");
       displayAlg1_2(extendedEuclidianAlg(a,b));

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



    public static BigInteger[] extendedEuclidianAlg(BigInteger a, BigInteger b)
    {
        /*
        !      Array Values
        !   extEuclidLine[0] == d;
        !   extEuclidLine[1] == x;
        !   extEuclidLine[2] == y;
        */
        BigInteger[] extEuclidLine = new BigInteger[3];
        if(a.compareTo(BigInteger.ONE)== -1 ||b.compareTo(BigInteger.ONE)==-1)
        {
            throw new ArithmeticException("A and B values must be non negative integers");
        }
        //todo : Have to if a is greater than b value or swap values
        if(a.compareTo(b) == -1)
        {
           return extendedEuclidianAlg(b, a);
        }

        //* Now to begin coding the algorithm
        if(b.equals(BigInteger.ZERO))
        {
            //? if b = 0 then d = a, x = 1, y = 0
            extEuclidLine[0] = a;
            extEuclidLine[1] = BigInteger.ONE;
            extEuclidLine[2] = BigInteger.ZERO;
            return extEuclidLine;
        }
        
        BigInteger[] xValue = new BigInteger[2];
        BigInteger[] yValue = new BigInteger[2];
        BigInteger q;
        BigInteger r; 
        xValue[0] = BigInteger.ZERO;
        xValue[1] = BigInteger.ONE;
        yValue[0] = BigInteger.ZERO;
        yValue[1] = BigInteger.ONE;

        //todo : while b>0 do 
        while(b.compareTo(BigInteger.ZERO)!=0)
        {
            //todo : q ← ba/bc, r ← a − qb, x ← x2 − qx1, y ← y2 − qy1
            q = a.divide(b);
            r = algorithm1_1(a, b);
            extEuclidLine[1] = xValue[1].subtract(q.multiply(xValue[0]));
            extEuclidLine[2] = yValue[1].subtract(q.multiply(yValue[0]));

            //todo : a, b ← r, x2 ← x1, x1 ← x, y2 ← y1 and y1 ← y.
            a = b;
            b = r;
            xValue[1] = xValue[0];
            xValue[0] = extEuclidLine[1];
            yValue[1] = yValue[0];
            yValue[0] = extEuclidLine[2];
            extEuclidLine[0] = a;
            extEuclidLine[1] = xValue[1];
            extEuclidLine[2] = yValue[1];
        }
        //todo : d ← a, x ← x2, y ← y2, and return (d, x, y).
        

        return extEuclidLine;
    }



    public static void displayAlg1_2(BigInteger[] ans)
    {
        System.out.println("d value = "+ans[0]+"\nx value = "+ ans[1] + "\ny value = "+ ans[2] + "\n");
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