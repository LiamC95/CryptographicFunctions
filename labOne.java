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
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!
!   Miller - Rabin Probabilistic Primality Checks
!   Input = n and t
!       n >= 3
!       t >1 & is a security check
!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
*/
    


/*
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!   Euclidian Algorithm
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!
*/
    public static BigInteger greatestCommonDivissorBigInteger(BigInteger a, BigInteger b)
    {


        //? Checking input
        if(a.compareTo(BigInteger.ONE)== -1 ||b.compareTo(BigInteger.ONE)==-1)
        {
            throw new ArithmeticException("A and B values must be non negative integers");
        }
        //todo : Have to if a is greater than b value or swap values
        if(a.compareTo(b) == -1)
        {
           return greatestCommonDivissorBigInteger(b, a);
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
    public static BigInteger[] extendedEuclidianBigIntegers(BigInteger a, BigInteger b)
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
           return extendedEuclidianBigIntegers(b, a);
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
    public static BigInteger modularInverseOfaBigInteger(BigInteger a, BigInteger n)
    {
        BigInteger[] DXY = extendedEuclidianBigIntegers(a, n);
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

    public static BigInteger modularExponentiationOfaPowbModn(BigInteger a, BigInteger k, BigInteger n)
    {
        BigInteger b = BigInteger.ONE;

        if(!k.equals(BigInteger.ZERO))
        {
            BigInteger A = a;
            Stack<Boolean>  test = convertkToBinaryStack(k);
            while(!test.empty())
            {
                if(test.pop())
                {
                    System.out.print("1");
                }
                else{
                    System.out.print("0");
                }

            }

            System.out.print("\n\n");
            Stack<Boolean>  binaryOfK = convertkToBinaryStack(k);
            if(binaryOfK.pop())
            {
                b =a;
            }
            while(!binaryOfK.empty())
            {

                System.out.println("b value = "+b);
                A = modBigInteger(A.multiply(A), n);

                if(binaryOfK.pop())
                {
                    b = modBigInteger(A.multiply(b), n);
                }
            }

            
        }

        return b;
    }



    public static Stack<Boolean> convertkToBinaryStack(BigInteger k)
    {
        Stack<Boolean> binaryStack = new Stack<>();

        String s = k.toString(2);
        for(int i = 0; i < s.length(); i++)
        {
            if(s.charAt(i)=='1')
            {
                binaryStack.add(true);
            }else{
                binaryStack.add(false);
            }
        }
        return binaryStack;
    }





    


    public static BigInteger modBigInteger(BigInteger a, BigInteger n)
    {
        
        BigInteger q = a.divide(n);
        return a.subtract(n.multiply(q));
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
        int option = 1;

        
        BigInteger d;
        BigInteger[] DXY = new BigInteger[3]; 

        System.out.println("Enter a value: ");
        BigInteger a = in.nextBigInteger();
        BigInteger aInverse;
        System.out.println("Enter b value: ");
        BigInteger b = in.nextBigInteger();
        System.out.println("Enter n value: ");
        BigInteger n = in.nextBigInteger();

        boolean exit = false;
        while(!exit)
        {

            printMenu();
            option = in.nextInt();
            if(option==1)
            {   
                //* allows you to change your values easily
                System.out.println("Enter a value: ");
                a = in.nextBigInteger();
                System.out.println("Enter b value: ");
                b = in.nextBigInteger();
                System.out.println("Enter n value: ");
                n = in.nextBigInteger();
                

                display(a, b);
            }
            else if(option==2)
            {
                d = greatestCommonDivissorBigInteger(a, b);
                display(a, b, d);
            }
            else if(option == 3)
            {
                DXY = extendedEuclidianBigIntegers(a, b);
                display(a, b, DXY);
            }
            else if(option == 4)
            {
                aInverse = modularInverseOfaBigInteger(a, b);
                display(aInverse);
            }
            else if(option == 5)
            {
                System.out.println("Input the k value you want to use");
                BigInteger k = in.nextBigInteger();
                System.out.println("\n"+a+"^"+k+" MOD "+n+" = "+modularExponentiationOfaPowbModn(a,k,n));
                System.out.println("True output = "+a.pow(k.intValue()).mod(n));
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