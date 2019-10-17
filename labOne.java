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
!       Modular Inverse of 
!           input = A mod b
!           output = A^-1
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
    

    /*
!       Modular exponentiation
!       
!       Input   = a^k mod n
!
!       output  = BigInter r


?        Algorithm 1.4 Repeated square-and-multiply algorithms for exponentiation
?       in Zn
    */

    public static BigInteger modularExponentiations(BigInteger a, BigInteger b, BigInteger n)
    {
        //todo  Set r ← 1. 
        BigInteger r = BigInteger.valueOf(1);

        //todo  If b = 0 then return (r).
        if(b.equals(BigInteger.ZERO))
        {
            return r;
        }

        //todo  Set A ← a
        BigInteger A = a;

        //todo  If b0 = 1 then set r ← a
        Stack<Boolean> binaryOfb = getBinaryRep(b);
        if(BigInteger.ONE == modVal(a, BigInteger.valueOf(2)))
        {
            r = a;
        }
        

        //todo  For bi from 1 to t − 1 do the following:

        while(binaryOfb.size() > 1)
        {   
            //todo   Set A ← A2 mod n

            A = modVal(A.multiply(A),n);

            //todo  if bi = 1
            if(binaryOfb.pop())
            {

                //todo  set r ← A · r mod n
                r = modVal(A.multiply(r),n);
            }
        }
        return r;
    }



    public static Stack<Boolean> getBinaryRep(BigInteger k)
    {
        Stack<Boolean> binaryStack = new Stack<>();

        while(!k.equals(BigInteger.ZERO))
        {
            if(BigInteger.ZERO == modVal(k, BigInteger.valueOf(2)))
            {
                binaryStack.add(false);
            }
            else{
                binaryStack.add(true);
            }
            k = k.divide(BigInteger.valueOf(2));

        }
        return binaryStack;
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








    /*
!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!       Menu System
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!
    */
    public static void menuChoice()
    {
        Scanner in = new Scanner(System.in);
        int option;

        System.out.println("Enter a value: ");
        BigInteger a = in.nextBigInteger();
        BigInteger aInverse;
        System.out.println("Enter b value: ");
        BigInteger b = in.nextBigInteger();
        BigInteger d;
        System.out.println("Enter a (mod n) value: ");
        BigInteger n = in.nextBigInteger();

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
                System.out.println("A^b MOD n = "+modularExponentiations(a,b,n));
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