import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.math.BigInteger;
import java.sql.Array;

/**
 *
 * @author Liam Clarke
 */
public class labOne {

    public static void main(String[] args)
    {
        try{

            menuChoice();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
       

    }


/*
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!   Euclidian Algorithm
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!
*/
    public static BigInteger myGCD(BigInteger a, BigInteger b)
    {


        //? Checking input
        if(a.compareTo(BigInteger.ONE)== -1 ||b.compareTo(BigInteger.ONE)==-1)
        {
            throw new ArithmeticException("A and B values must be non negative integers");
        }
        //todo : Have to if a is greater than b value or swap values
        if(a.compareTo(b) == -1)
        {
           return myGCD(b, a);
        }




        BigInteger r;
        BigInteger q;
        while(!b.equals(BigInteger.ZERO))
        {
            // Find the remainder of the division
            q = a.divide(b);
            r = a.subtract(q.multiply(b));

            //* pass the b value into the a position
            a = b;

            //* pass the remainder value into b
            b = r;
        }

        return a;
    }

/*
!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!          Extended Euclidian Algorithm
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!
*/
    public static BigInteger[] myExtGCD(BigInteger a, BigInteger b)
    {
        /*
        !      Array Values
        !   arrayDXY[0] == d;
        !   arrayDXY[1] == x;
        !   arrayDXY[2] == y;
        */
        BigInteger[] arrayDXY = new BigInteger[3];
        if(a.compareTo(BigInteger.ONE)== -1 ||b.compareTo(BigInteger.ONE)==-1)
        {
            throw new ArithmeticException("A and B values must be non negative integers");
        }
        //todo : Have to if a is greater than b value or swap values
        if(a.compareTo(b) == -1)
        {
           return myExtGCD(b, a);
        }

        //* Now to begin coding the algorithm
        if(b.equals(BigInteger.ZERO))
        {
            //? if b = 0 then d = a, x = 1, y = 0
            arrayDXY[0] = a;
            arrayDXY[1] = BigInteger.ONE;
            arrayDXY[2] = BigInteger.ZERO;
            return arrayDXY;
        }
        
        BigInteger[] xValue = new BigInteger[2];
        BigInteger[] yValue = new BigInteger[2];
        BigInteger q;
        BigInteger r; 
        xValue[0] = BigInteger.ZERO;
        xValue[1] = BigInteger.ONE;
        yValue[0] = BigInteger.ONE;
        yValue[1] = BigInteger.ZERO;

        //todo : while b>0 do 
        while(b.compareTo(BigInteger.ZERO) == 1)
        {
            
            //todo : q ← ba/bc
            q = a.divide(b);
            
            //todo : r ← a − qb
            r = a.subtract(q.multiply(b));
            
            

            //todo :  x ← x2 − qx1
            arrayDXY[1] = xValue[1].subtract(q.multiply(xValue[0]));



            //todo : y ← y2 − qy1
            arrayDXY[2] = yValue[1].subtract(q.multiply(yValue[0]));
            

            //todo : a, b ← r, x2 ← x1, x1 ← x, y2 ← y1 and y1 ← y.
            a = b;
            b = r;


            xValue[1] = xValue[0];

            xValue[0] = arrayDXY[1];

            yValue[1] = yValue[0];

            yValue[0] = arrayDXY[2];
        }

        //todo : d ← a, x ← x2, y ← y2, and return (d, x, y).
        arrayDXY[0] = a;
        arrayDXY[1] = xValue[1];
        arrayDXY[2] = yValue[1];
        
        
        return arrayDXY;
    }
 
/*
!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!       Modular Inverse of A mod b
!                  A^-1
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!
*/
    public static BigInteger findModInverse(BigInteger a, BigInteger n)
    {
        BigInteger[] DXY = myExtGCD(a, n);
        //* Looks to see if ints are coprime
        if(DXY[0].compareTo(BigInteger.ONE) == 1)
        {
            throw new ArithmeticException("Numbers are not co-prime! Inverse doesn't exsist");
        }
        else{
            //* returning X value 
            return DXY[1];
        }
    }
    


    public static BigInteger repeatedSquare(BigInteger a, BigInteger k, BigInteger n)
    {
        BigInteger b = BigInteger.ONE;

        if(k.equals(BigInteger.ZERO))
        {
            return b;
        }
        Stack<BigInteger> binaryStack = new Stack<>();
        BigInteger ko = k;

        BigInteger A = a;

        //Getting the binary stack
        while(!ko.equals(BigInteger.ZERO))
        {
            binaryStack.add(modVal(ko, BigInteger.valueOf(2)));
            ko = ko.divide(BigInteger.valueOf(2));
        }
        if(binaryStack.pop().equals(BigInteger.ONE))
        {
            b = a;
        }
        for(int i = 0; i <= binaryStack.size(); i++)
        {
            A = modVal(A.multiply(A), n);
            if(binaryStack.pop().equals(BigInteger.ONE))
            {
                b  = modVal(A.multiply(b), n);
            }
            System.out.println("B = "+b);
        }

        return b;
        

    }





    


    public static BigInteger modVal(BigInteger a, BigInteger n)
    {
        if(a.compareTo(n)==1)
        {
            return modVal(n, a);
        }
        BigInteger q = a.divide(n);
        return a.subtract(n.multiply(q));
    }


   
    public static BigInteger getValue()
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter Value :");
        return in.nextBigInteger();
    }

    public static void menuChoice()
    {
        Scanner in = new Scanner(System.in);
        int option;
        BigInteger a = BigInteger.valueOf(42823);
        BigInteger aInverse;
        BigInteger b = BigInteger.valueOf(6407);
        BigInteger d ;
        BigInteger[] DXY = new BigInteger[3]; 

        boolean exit = false;
        while(!exit)
        {

            printMenu();
            option = in.nextInt();
            if(option==1)
            {   
                display(a, b);

                a = getValue();
                b = getValue();

                display(a, b);
            }
            else if(option==2)
            {
                d = myGCD(a, b);
                display(a, b, d);
            }
            else if(option == 3)
            {
                DXY = myExtGCD(a, b);
                display(a, b, DXY);
            }
            else if(option == 4)
            {
                aInverse = findModInverse(a, b);
                display(aInverse);
            }
            else if(option == 5)
            {
                System.out.println("A^b MOD n = "+repeatedSquare(a,b,BigInteger.valueOf(123445)));
            }
            else if(option == 6)
            {
                exit = true;
            }
        }


       in.close();
    }






    /*
    !
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!
    !       DISPLAYS
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!
    !
    */

    public static void printMenu()
    {
        System.out.println("Cryptographic Functions:");
        System.out.println("Add Numbers             \t-1");
        System.out.println("Euclidian               \t-2");
        System.out.println("Extended Euclidian      \t-3");
        System.out.println("Find Modular Inverse    \t-4");
        System.out.println("Modular Exponentiation  \t-5");
        System.out.println("Exit                    \t-6\n");
    }
    public static void display(BigInteger a, BigInteger b)
    {
        System.out.println("Integer Values :");
        System.out.println("A value =   "+a);
        System.out.println("B value =   "+b);
        System.out.println("\n");
        
    }
    public static void display(BigInteger a, BigInteger b, BigInteger d)
    {
        System.out.println("Integer Values :");
        System.out.println("A value =   "+a);
        System.out.println("B value =   "+b);
        System.out.println("D value =   "+d);
        System.out.println("\n");
        
    }
    public static void display(BigInteger a, BigInteger b, BigInteger[] DXY)
    {
        System.out.println("Integer Values :");
        System.out.println("A value =   "+a);
        System.out.println("B value =   "+b);
        System.out.println("D value =   "+DXY[0]);
        System.out.println("X value =   "+DXY[1]);
        System.out.println("Y value =   "+DXY[2]);
        System.out.println("line    =   xa + yb = d");
        System.out.println("Line equation = ("+DXY[1] + " x " + a + ") + (" + DXY[2] + " x " + b + ") = " + DXY[0]);
        System.out.println("\n");
        
    }
    public static void display( BigInteger aInverse)
    {
        System.out.println("Integer Values :");
        System.out.println("A^-1 value  =   "+aInverse);
        System.out.println("\n");
        
    }

}