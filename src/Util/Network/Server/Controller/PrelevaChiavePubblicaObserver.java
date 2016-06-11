package Util.Network.Server.Controller;

import Util.Network.Server.RispostaMaker;
import Util.Network.TransferObject.DTO;
import Util.Network.TransferObject.DTOMaker;

/**
 * Created by Gioele on 11/06/2016.
 */
public class PrelevaChiavePubblicaObserver implements Observer  {
    @Override
    public void update(ControllerFacade controller) {


        DTO dto ;

        dto = DTOMaker.getSingletonInstance().inviaChiavePubblicaPersanale();

        RispostaMaker.getSingletonInstance().ImmettiRisposta(dto);

        System.out.println("prelevo la chiave pubblica");

    }
}
