package Ui.Chat;

import Ui.Controllers.controller;

/**
 * Created by Gioele on 12/06/2016.
 */
public class MessaggiChat {


    private static  MessaggiChat singletonInstance = null;
  static private controller controllereInterfaccia = null;


    public static MessaggiChat getSingletonInstance() {
        if (singletonInstance == null) {
            singletonInstance = new MessaggiChat();
        }
        return singletonInstance;
    }

    public MessaggiChat() {
    }



    public static void inserisciMessaggio(String messaggio,boolean utente){


        controllereInterfaccia.aggiungiMessaggio(messaggio,utente);

    }

    public static controller getControllerinterfaccia() {
        return controllereInterfaccia;
    }

    public static void setControllerinterfaccia(controller controllerinterfaccia) {
       controllereInterfaccia = controllerinterfaccia;
    }
}
