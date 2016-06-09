package Soggetti;

import java.math.BigInteger;

/**
 * Created by Gioele on 09/06/2016.
 */
public class Alice {


    private BigInteger N ;
    private BigInteger E ;

    private BigInteger messaggio;
    private String messaggioStringa;

    public Alice(){}




    public void setChiavePubblica(BigInteger n,BigInteger e){
        this.N = n;
        this.E = e;
    }

    public BigInteger criptaMessaggio(){

        BigInteger messaggioCifrato = messaggio.modPow(E,N);

        return  messaggioCifrato;
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

    public BigInteger getMessaggio() {
        return messaggio;
    }

    public void setMessaggio(BigInteger messaggio) {
        this.messaggio = messaggio;
    }

    public String getMessaggioStringa() {
        return messaggioStringa;
    }

    public void setMessaggioStringa(String messaggioStringa) {
        this.messaggioStringa = messaggioStringa;
    }
}
