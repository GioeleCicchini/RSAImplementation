package Util.Network.Server.Controller;

import Ui.Chat.MessaggiChat;
import Util.Attack.TestoChiaroCorto;
import Util.KeyGenerator;
import Util.Network.Server.RispostaMaker;
import Util.Network.TransferObject.DTO;
import Util.Network.TransferObject.DTOMaker;
import Util.NumericTextDecripter;
import Util.RSACripterDecripter;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by Gioele on 12/06/2016.
 */
public class RiceviMessaggioObserver implements Observer {
    @Override
    public void update(ControllerFacade controller) {

        if(controller.getFunzione().equals("MessaggioInterlocutore")) {

            List<BigInteger> pezzi = (List)controller.getOggettiPersistenti().get(0);

            String messaggioCriptato = "";
            for(int i=0;i<pezzi.size();i++){
                messaggioCriptato = messaggioCriptato + pezzi.get(i);
            }

            System.out.println("Chiave Privata : "+KeyGenerator.getSingletonInstance().getChiavePrivata());
            System.out.println("Messaggio Crittato :   "+messaggioCriptato);




            String messaggio = RSACripterDecripter.getSingletonInstance().Decripta(pezzi);

            NumericTextDecripter numericTextDecripter = new NumericTextDecripter(messaggio);

            System.out.println("Messaggio Decrittato :   "+numericTextDecripter.getMessaggioDecriptatoStringa());

            DTO dto = DTOMaker.getSingletonInstance().inviaChiavePubblicaPersanale();
            RispostaMaker.getSingletonInstance().ImmettiRisposta(dto);

            MessaggiChat.getSingletonInstance().inserisciMessaggio(numericTextDecripter.getMessaggioDecriptatoStringa(),false);


       }


    }
}
