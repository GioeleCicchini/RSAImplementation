package src;

import Soggetti.Alice;
import Soggetti.Bob;

import java.math.BigInteger;

/**
 * Created by Gioele on 09/06/2016.
 */
public class Main  {


    public static void main(String [] args)
    {

        String messaggioStringa = "ciaocomevabene";


        TextEncripter messaggio = new TextEncripter(messaggioStringa);


        Bob bob = new Bob();
        Alice alice = new Alice();
        alice.setMessaggio(messaggio.getMessaggioFinale());
        alice.setMessaggioStringa(messaggioStringa);


        bob.Inizializza();

        alice.setChiavePubblica(bob.getN(),bob.getE());

        System.out.println("messaggio da criptare: "+messaggioStringa+" , "+ messaggio.getMessaggioFinale());
        BigInteger messaggioCifrato = alice.criptaMessaggio();

        System.out.println("Messaggio Criptato: "+messaggioCifrato);

        TextDecripter textDecripter = new TextDecripter(bob.decifraMessaggio(messaggioCifrato));

        System.out.println("Messaggio Decifrato: "+bob.decifraMessaggio(messaggioCifrato)+" , "+ textDecripter.getMessaggioDecriptatoStringa());




    }


}
