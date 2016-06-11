package Soggetti;

import src.Util.KeyGenerator;
import src.Util.MillerRabin;

import java.math.BigInteger;
import java.util.*;

/**
 * Created by Gioele on 09/06/2016.
 */
public class Bob implements  Utente{

    private Map<String,BigInteger> chiavePrivata = null;
    private Map<String,BigInteger> chiavePubblica = null;

    public Bob(){

    }


    public String decifraMessaggio(String C){

       chiavePrivata=KeyGenerator.getSingletonInstance().getChiavePrivata();

       List<BigInteger> messaggioCriptatoBlocchi;
        String messaggioDecifrato = "";

        int lunghezzaBlocchi = KeyGenerator.getSingletonInstance().getLunghezzaN();


       messaggioCriptatoBlocchi = getParts(C,lunghezzaBlocchi);
        for(int i=0;i<messaggioCriptatoBlocchi.size();i++){
            BigInteger messaggioDecifratoBlocco = messaggioCriptatoBlocchi.get(i).modPow(chiavePrivata.get("D"),chiavePrivata.get("N"));

            String m = messaggioDecifratoBlocco.toString();
            if(m.length()%3 != 0 ){
                m = "0"+m;
            }
            messaggioDecifrato = messaggioDecifrato +m;
        }

        return messaggioDecifrato;
    }


    @Override
    public void inviaChiavePubblica(Map<String, BigInteger> ChiavePubblica, Utente utente) {

        utente.ImpostaChiavePubblica(ChiavePubblica);


    }

    @Override
    public void ImpostaChiavePubblica(Map<String, BigInteger> ChiavePubblica) {

        this.chiavePubblica = ChiavePubblica;

    }


    private static List<BigInteger> getParts(String string, int partitionSize) {
        List<BigInteger> parts = new ArrayList<>();
        int len = string.length();
        for (int i=0; i<len; i+=partitionSize)
        {
            parts.add(new BigInteger(string.substring(i, Math.min(len, i + partitionSize))));
        }
        return parts;
    }
}
