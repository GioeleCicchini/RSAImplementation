package Util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Gioele on 12/06/2016.
 */
public class RSACripterDecripter {


    private static RSACripterDecripter singletonInstance = null;

    public static RSACripterDecripter getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new RSACripterDecripter();
        }
        return singletonInstance;
    }


    public RSACripterDecripter() {
    }


    public List<BigInteger> Cripta(ChiavePubblicaInterlocutore chiave,String messaggio){

        String messaggioNumerico = TextNumericEncripter.getSingletonInstance().trasformazioneNumerica(messaggio);

        MessageGenerator.getSingletonInstance().setMessaggio(messaggioNumerico);

        List<BigInteger> messaggioNumericoBlocchi = MessageGenerator.getSingletonInstance().getMessaggioBlocchi();


        List<BigInteger> messaggioCifratoBlocchi = new ArrayList<>();

        for(int i=0;i<messaggioNumericoBlocchi.size();i++){
            BigInteger messaggioCifratoBlocco;
            if(chiave.getChiavePubblicaInterlocutore().get("E") != null) {
                 messaggioCifratoBlocco = messaggioNumericoBlocchi.get(i).modPow(chiave.getChiavePubblicaInterlocutore().get("E"), chiave.getChiavePubblicaInterlocutore().get("N"));
            }
            else {
                messaggioCifratoBlocco = messaggioNumericoBlocchi.get(i).modPow(chiave.getChiavePubblicaInterlocutore().get("D"), chiave.getChiavePubblicaInterlocutore().get("N"));
            }

            messaggioCifratoBlocchi.add(messaggioCifratoBlocco);

        }


        return messaggioCifratoBlocchi;

    }


    public String Decripta(List<BigInteger> messaggioCriptato){

        Map<String,BigInteger> chiavePrivata =KeyGenerator.getSingletonInstance().getChiavePrivata();

        String messaggioDecifrato = "";


        for(int i=0;i<messaggioCriptato.size();i++){
            BigInteger messaggioDecifratoBlocco = messaggioCriptato.get(i).modPow(chiavePrivata.get("D"),chiavePrivata.get("N"));

            String m = messaggioDecifratoBlocco.toString();
            if(m.length()%3 != 0 ){
                m = "0"+m;
            }
            messaggioDecifrato = messaggioDecifrato +m;
        }


        return messaggioDecifrato;


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
