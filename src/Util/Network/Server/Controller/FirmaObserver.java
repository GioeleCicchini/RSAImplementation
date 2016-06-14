package Util.Network.Server.Controller;

import Util.Attack.TestoChiaroCorto;
import Util.ChiavePubblicaInterlocutore;
import Util.KeyGenerator;
import Util.Network.Server.RispostaMaker;
import Util.Network.TransferObject.DTO;
import Util.Network.TransferObject.DTOMaker;
import Util.RSACripterDecripter;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Gioele on 14/06/2016.
 */
public class FirmaObserver implements Observer {
    @Override
    public void update(ControllerFacade controller) {

        if(controller.getFunzione().equals("FirmaRichiesta")){

            List<BigInteger> pezzi = (List)controller.getOggettiPersistenti().get(0);

            System.out.println(KeyGenerator.getSingletonInstance().getChiavePrivata());

            String messaggio = RSACripterDecripter.getSingletonInstance().Decripta(pezzi);

            ChiavePubblicaInterlocutore MiaChiavePrivata = new ChiavePubblicaInterlocutore();

            MiaChiavePrivata.setChiavePubblicaInterlocutore(KeyGenerator.getSingletonInstance().getChiavePrivata());


            List<BigInteger> messaggioFirmato = RSACripterDecripter.getSingletonInstance().Cripta(MiaChiavePrivata,messaggio);


            DTO dto = DTOMaker.getSingletonInstance().firmaInviata(messaggioFirmato);
            RispostaMaker.getSingletonInstance().ImmettiRisposta(dto);



        }

    }
}
