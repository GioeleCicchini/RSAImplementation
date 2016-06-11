package Soggetti;

import java.math.BigInteger;
import java.util.Map;

/**
 * Created by Gioele on 10/06/2016.
 */
public interface Utente {

    public void inviaChiavePubblica(Map<String,BigInteger> ChiavePubblica, Utente utente);
    public void ImpostaChiavePubblica(Map<String,BigInteger> ChiavePubblica);

}
