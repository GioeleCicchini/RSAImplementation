package Util.Network.TransferObject;

import Util.ChiavePubblicaInterlocutore;
import Util.KeyGenerator;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by gioele on 04/03/16.
 */
public class DTOMaker {

    private static DTOMaker singletonInstance = null;


    private DTOMaker() {


    }

    public static DTOMaker getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new DTOMaker();
        }
        return singletonInstance;
    }


    public DTO getChiavePubblicaInterlocutore(){

        DTO dto = new DTO();

        dto.setFunzione("PrelevaChiavePubblica");


        return dto;
    }

    public DTO inviaChiavePubblicaPersanale(){

        DTO dto = new DTO();

        dto.setFunzione("ChiavePubblicaRichiesta");

        ChiavePubblicaInterlocutore chiavePubblicaInterlocutore = new ChiavePubblicaInterlocutore();

        dto.aggiungiOggettoTrasferimento(chiavePubblicaInterlocutore);

        return dto;

    }


    public DTO inviaMessaggio(List<BigInteger> messaggio){

        DTO dto = new DTO();

        dto.setFunzione("MessaggioInterlocutore");

        dto.aggiungiOggettoTrasferimento((Serializable) messaggio);

        return dto;

    }

    public DTO richiediFirma(List<BigInteger> messaggio){
        DTO dto = new DTO();

        dto.setFunzione("FirmaRichiesta");

        dto.aggiungiOggettoTrasferimento((Serializable) messaggio);

        return dto;

    }

    public DTO firmaInviata(List<BigInteger> messaggio){
        DTO dto = new DTO();

        dto.setFunzione("FirmaInviata");

        dto.aggiungiOggettoTrasferimento((Serializable) messaggio);

        return dto;

    }



}
