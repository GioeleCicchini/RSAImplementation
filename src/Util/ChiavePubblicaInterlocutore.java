package Util;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Gioele on 11/06/2016.
 */
public class ChiavePubblicaInterlocutore implements Serializable{


    private Map<String,BigInteger> chiavePubblicaInterlocutore = null;

    public ChiavePubblicaInterlocutore() {
        chiavePubblicaInterlocutore = KeyGenerator.getSingletonInstance().getChiavePubblica();
    }

    public Map<String, BigInteger> getChiavePubblicaInterlocutore() {
        return chiavePubblicaInterlocutore;
    }

    public void setChiavePubblicaInterlocutore(Map<String, BigInteger> chiavePubblicaInterlocutore) {
        this.chiavePubblicaInterlocutore = chiavePubblicaInterlocutore;
    }
}
