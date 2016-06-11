package Soggetti;

import src.Util.KeyGenerator;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by Gioele on 09/06/2016.
 */
public class Alice implements Utente{

    private List<BigInteger> messaggioNumerico;
    private String messaggioStringa;

    public Alice(){}

    private Map<String,BigInteger> chiavePubblica = null;



    public String criptaMessaggio(){

        String messaggioCifrato = "";
        String messaggioCifratoBloccoString;

        for(int i=0;i<messaggioNumerico.size();i++){
            BigInteger messaggioCifratoBlocco = messaggioNumerico.get(i).modPow(chiavePubblica.get("E"), chiavePubblica.get("N"));
            messaggioCifratoBloccoString = messaggioCifratoBlocco.toString();

            if(messaggioCifratoBloccoString.length() < KeyGenerator.getSingletonInstance().getLunghezzaN()){
                for(int j=0;j<(KeyGenerator.getSingletonInstance().getLunghezzaN()-messaggioCifratoBloccoString.length()+1);j++){
                    messaggioCifratoBloccoString = "0"+messaggioCifratoBloccoString;
                }
            }

            messaggioCifrato = messaggioCifrato +messaggioCifratoBloccoString;
        }


        return  messaggioCifrato;
    }


    public List<BigInteger> getMessaggioNumerico() {
        return messaggioNumerico;
    }

    public void setMessaggioNumerico(List<BigInteger> messaggioNumerico) {
        this.messaggioNumerico = messaggioNumerico;
    }

    public String getMessaggioStringa() {
        return messaggioStringa;
    }

    public void setMessaggioStringa(String messaggioStringa) {
        this.messaggioStringa = messaggioStringa;
    }


    @Override
    public void inviaChiavePubblica(Map<String, BigInteger> ChiavePubblica, Utente utente) {

    }

    @Override
    public void ImpostaChiavePubblica(Map<String, BigInteger> ChiavePubblica) {
        this.chiavePubblica = ChiavePubblica;
    }
}
