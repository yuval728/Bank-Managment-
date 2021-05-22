/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank.management;

/**
 *
 * @author gamin
 */
public class EMI {
    double principal,rate,time,emi;
    EMI(double p,double r,double t)
    {
        principal=p;
        rate=r/100;
        time=t;
        emi=Emi();
    }

        
    double getp()
    {
        return principal;
    }
    double getr()
    {
        return rate;
    }
    double getn()
    {
        return time;
    }
    double getemi()
    {
        return emi;
    }
    double Emi()
    {
        return ((principal*rate)*Math.pow(1+rate, time)/(Math.pow(1+rate, time)-1));
    }
}


