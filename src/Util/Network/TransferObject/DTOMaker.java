package Util.Network.TransferObject;

import Util.ChiavePubblicaInterlocutore;
import Util.KeyGenerator;

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

}
