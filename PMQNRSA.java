package com.phd.mywork;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class PMQNRSA {


    public static void main(String[] args) throws NumberFormatException, IOException{
    	
    	
    // Variable for message	
    String Msg  ;

    // Variables for passcount , fail count , total , p power of ivalue ,q power of qvalue 
    int passcount=0,failcount=0,total=0,ivalue = 0,jvalue=0,M,N;
    
    //Temp varaibles 
    int tempp =1, tempq=1;

    //Varaibles of   p,q, phi, N 
    int n,phipower , P ,Q ;
    int pubKey ;   
 
    // Reading the system input values 
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    

    // Reading the input message\plain message so please enter the numerical values
    System.out.println("Please enter the Message(In numerical value) :");
    Msg  = br.readLine();
    
    // P value it should be prime values
    System.out.println("Please enter the p value in prime number :");
    P  = Integer.parseInt(br.readLine());

    // q value it should be prime value
    System.out.println("Please enter the Q value in prime number :");
    Q  = Integer.parseInt(br.readLine());
    
    if(P==Q)
    {
    	System.out.println("Both values p and q are equal"+P+"=="+"Q");
    	System.out.println(P+","+Q+" both should be distnict value");
    	System.exit(0);
    }
    
    //Reading the power value 
    System.out.println("Please enter the M value which is the power of number:--> "+P+"^");
    ivalue = Integer.parseInt(br.readLine());
    M = ivalue;
    
   //Calculating p prime power value 
    tempp = P;
    for(int i=1; i< ivalue;i++)
    {
    	tempp = P *tempp ;
    }
    System.out.println("Please enter the N value which is the power of number:--> "+Q+"^");
    ivalue = Integer.parseInt(br.readLine());
    N = ivalue;
    
    tempq = Q;
    for(int i=1; i< ivalue;i++)
    {
    	tempq = Q *tempq ;
    }
    // phi value calculation   
      phipower = (tempp-1) * (tempq-1) ;
  
    //Calculate N value 
      n =  P * Q ;
  
     
    //N is converting to BigB_N
    BigInteger bigB_n = BigInteger.valueOf(n);
    //phi is converting to BigB_phi
    BigInteger bigB_phi = BigInteger.valueOf(phipower);
    int  bestSoul = 0 ;
    System.out.println(" P value : "+P);
    System.out.println(" Q value : "+Q);
    System.out.println(" M value : "+M);
    System.out.println(" N value : "+N);
    System.out.println(" n value : "+bigB_n);
    System.out.println(" phi value phi(("+P+"^"+M+")-1,("+Q+"^"+N+"-1)) :"+ bigB_phi);
    
    for (pubKey = 3 ; pubKey<bigB_phi.intValue();pubKey++ )
    {
     while (true){
        BigInteger BigB_GCD = bigB_phi.gcd(new BigInteger(""+pubKey));
        if (BigB_GCD.equals(BigInteger.ONE)){
        break;
        }
        pubKey++;
        }

    BigInteger bigB_pubKey = new BigInteger(""+pubKey);
    BigInteger bigB_prvKey = bigB_pubKey.modInverse(bigB_phi);

   
   System.out.println(" public key::  (e,n) ::  ["+bigB_pubKey+" , "+bigB_n+"]");
   System.out.println(" private key:: (d,n) ::  ["+bigB_prvKey+" , "+bigB_n+"]");
    
    BigInteger bigB_val = new BigInteger(Msg);
    BigInteger bigB_cipherVal = bigB_val.modPow(bigB_pubKey, bigB_n);
    
    
    
    BigInteger bigB_plainVal = bigB_cipherVal.modPow(bigB_prvKey, bigB_n);
    int plainVal = bigB_plainVal.intValue();
    
     
    if(plainVal==  Integer.parseInt(Msg))
    {
         System.out.println("VALIDATION PASSED:*********The plainVal and message are same*******");    
         passcount=passcount+1;
    }
    else
    {
        System.out.println("----FAILED-----------");
        failcount=failcount+1;

    }
    if(plainVal==Integer.parseInt(Msg) && Integer.parseInt(Msg)==bigB_cipherVal.intValue())
    {
            System.out.println("It's Not the best soultion ");
            bestSoul = bestSoul +1;
    }
    total=total+1;
    }
    
    System.out.println("********************************************************************");
    
    System.out.println(" P value : "+P);
    System.out.println(" Q value : "+Q);
    System.out.println(" M value : "+M);
    System.out.println(" N value : "+N);
    System.out.println(" n value : "+bigB_n);
    System.out.println(" phi value phi(("+P+"^"+M+")-1,("+Q+"^"+N+"-1)) \t\t\t=::\t"+ bigB_phi);
    
   
    System.out.println("********************************************************************");
    System.out.println("The Number of times Validation Success\t\t::\t"+(passcount));
    System.out.println("The Number of times Validation Failed\t\t::\t"+(failcount));
    System.out.println("Total Number of Soultion for these values\t::\t"+(total));
   // System.out.println("The Number of times Not a best soultion  "+(bestSoul));
    System.out.println("********************************************************************");
    }

}
