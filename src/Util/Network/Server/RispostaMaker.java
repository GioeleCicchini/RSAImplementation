package Util.Network.Server;

import Util.Network.TransferObject.DTO;

/**
 * Created by Gioele on 05/03/2016.
 */
public class RispostaMaker {

    private static RispostaMaker singletonInstance = null;
    private DTO risposta;

    private RispostaMaker() {

    }

    public static RispostaMaker getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new RispostaMaker();
        }
        return singletonInstance;
    }

    public void ImmettiRisposta(DTO dto) {
        this.risposta = dto;
    }

    public DTO PrelevaRisposta() {
        DTO risp = this.risposta;
        this.risposta = null;
        return risp;
    }


}
