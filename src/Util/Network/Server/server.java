package Util.Network.Server;

import Util.Network.Server.Controller.ControllerFacade;
import Util.Network.Server.Controller.FirmaObserver;
import Util.Network.Server.Controller.PrelevaChiavePubblicaObserver;
import Util.Network.Server.Controller.RiceviMessaggioObserver;


/**
 * Created by Gioele on 11/06/2016.
 */
public class server{

    public void StartServer() {

        ConnectionListner connectionListner = new ConnectionListner();

        ControllerFacade controllerFacade = new ControllerFacade();

        connectionListner.setControllerFacade(controllerFacade);


        controllerFacade.Attach(new PrelevaChiavePubblicaObserver());
        controllerFacade.Attach(new RiceviMessaggioObserver());
        controllerFacade.Attach(new FirmaObserver());



        connectionListner.start();

    }

}
