package Soggetti;

import java.math.BigInteger;

/**
 * Created by Gioele on 09/06/2016.
 */
public class Bob {

    private BigInteger P;
    private BigInteger Q;
    private BigInteger N;
    private BigInteger E;
    private BigInteger D;

    public Bob(){}



    public void Inizializza(){

        P = new BigInteger("885320963");
        BigInteger PmenoUno = P.subtract(new BigInteger("1"));

        Q = new BigInteger("2147483647") ;
        BigInteger QmenoUno = Q.subtract(new BigInteger("1"));

        BigInteger PQmenoUno= PmenoUno.multiply(QmenoUno);
        N = P.multiply(Q);

        E=PQmenoUno.subtract(new BigInteger("1"));


        System.out.println("Bob Sceglie due primi p e q segreti e calcola n= p*q");
        System.out.println("P: "+P+" Q: "+Q+" N:"+N);

        System.out.println("Bob Sceglie E con MCD(e,(p-1)*(q-1))=1");
        System.out.println("E : "+E);

        D = E.modInverse(PQmenoUno);

        System.out.println("Bob Calcola d");
        System.out.println("D: "+D);



    }


    public BigInteger decifraMessaggio(BigInteger C){

       BigInteger messaggioDecifrato = C.modPow(D,N);
        return messaggioDecifrato;
    }




    public BigInteger getQ() {
        return Q;
    }

    public void setQ(BigInteger q) {
        Q = q;
    }

    public BigInteger getP() {
        return P;
    }

    public void setP(BigInteger p) {
        P = p;
    }

    public BigInteger getN() {
        return N;
    }

    public void setN(BigInteger n) {
        N = n;
    }

    public BigInteger getE() {
        return E;
    }

    public void setE(BigInteger e) {
        E = e;
    }

    public BigInteger getD() {
        return D;
    }

    public void setD(BigInteger d) {
        D = d;
    }


}
