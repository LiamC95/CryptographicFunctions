import java.util.Stack;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author Liam Clarke
 */
public class CA1 {
public static void main(String[] args)
{
    ArrayList<BigInteger> factorsOf3743908387 = getFactors(new BigInteger("3743908387"));
    System.out.println("Factors of 3743908387 = \t(" +questionaPrint(factorsOf3743908387)+")");

    ArrayList<BigInteger> factorsOf3744065361 = getFactors(new BigInteger("3744065361"));
    System.out.println("Factors of 3743908387 = \t(" +questionaPrint(factorsOf3744065361)+")");
    
    ArrayList<BigInteger> factorsOf3744003789 = getFactors(new BigInteger("3744003789"));
    System.out.println("Factors of 3743908387 = \t(" +questionaPrint(factorsOf3744003789)+")");
    
    ArrayList<BigInteger> factorsOf3744160767 = getFactors(new BigInteger("3744160767"));
    System.out.println("Factors of 3743908387 = \t(" +questionaPrint(factorsOf3744160767)+")");


    /*
    ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
    ?           Part b 
    ?   Chose p,q From this list 
    ?   There are five numbers that I could use 
    ?   47701, 78487, 15901, 78487, 15901
    ?   
    ?   I will use p = 47701, q = 78487, find e
    :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: 
    */
    
    BigInteger p = factorsOf3743908387.get(0);
    BigInteger q = factorsOf3743908387.get(1);
    BigInteger n = p.multiply(q);
    BigInteger phiN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
    
    BigInteger i  = new BigInteger("3743908380");
    BigInteger e = new BigInteger("0");
    boolean eFound = false;
    while(i.equals(BigInteger.ZERO)|| !eFound)
    {
        

        if((BigInteger.ONE).equals(greatestCommonDivissorBigInteger(i, phiN)))
        {
            e = i;
            eFound = true;
        }
        i = i.subtract(BigInteger.ONE);
    } 

    // Now we have e we must find d which is e^-1
    System.out.println("e = "+e);
    
    // We pass in e and our phiN to find d
    BigInteger d = modularInverseOfaBigInteger(e, phiN);


    // ALices keys 
    BigInteger[] publicKey  = {n,e};
    BigInteger[] privateKey = {d,p,q};

    /*
    Now Implement RSA

    Bob Wants to send his age to alice
    */


    //Bob creates the message 
    BigInteger message = new BigInteger("24"); 

    // And then encrypts using alices public key
    message = modularExponentiationOfaPowbModn(message,publicKey[1], publicKey[0]);

    System.out.println("Bob cipher sent across network  = "+message);

    // Alice receives message
    message = modularExponentiationOfaPowbModn(message, privateKey[0], privateKey[1].multiply(privateKey[2]));

    System.out.println("Alice can now view bobs age = "+message);





}



public static String questionaPrint(ArrayList<BigInteger> a)
{
    String s = "";
    for(BigInteger b: a)
    {
        s += b+", ";
    }
        


    return s;

}
    

public static ArrayList<BigInteger> getFactors(BigInteger factorise)
{
    ArrayList<BigInteger> factors = new ArrayList<>();

    BigInteger n = factorise;
    BigInteger i = new BigInteger("0");
    for(i=new BigInteger("2");i.compareTo(n)==-1;i =
    i.add(BigInteger.ONE))
    {
    while ((n.mod(i)).compareTo(BigInteger.ZERO) == 0)
    {
    n = n.divide(i);
    factors.add(i);
    }
    }
    if (n.compareTo(BigInteger.ONE) == 1)
    {
    factors.add(n);
    }
    
    return factors;
}


public static BigInteger greatestCommonDivissorBigInteger(BigInteger a, BigInteger b)
    {


        //* Checking input
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
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
!
!       Modular exponentiation
!       
!       Input   = a^k mod n
!
!       output  = BigInter r
!
!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    */

    public static BigInteger modularExponentiationOfaPowbModn(BigInteger a, BigInteger k, BigInteger n)
    {
        BigInteger b = BigInteger.ONE;
        
        

        if(!k.equals(BigInteger.ZERO))
        {
            BigInteger A = a;
            Stack<Boolean>  binaryOfK = convertkToBinaryStack(k);
            if(binaryOfK.pop())
            {
                b =a;
            }
            while(!binaryOfK.empty())
            {

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

    

}

