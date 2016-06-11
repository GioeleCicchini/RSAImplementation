package Util.Network.Server;

import Util.Network.Configuration;
import Util.Network.Server.Controller.ControllerFacade;
import Util.Network.TransferObject.DTO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Gioele on 11/06/2016.
 */
public class ConnectionListner extends Thread {



    private ControllerFacade controllerFacade;
    private ServerSocket welcomeSocket = null;
    private DTO dto = null;

    public void run() {
        try {
            welcomeSocket = new ServerSocket(Integer.parseInt(Configuration.getSingletonInstance().getPortaServer()));
            System.out.println("Apertura Server sulla porta "+Integer.parseInt(Configuration.getSingletonInstance().getPortaServer()) );
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {

            Socket connectionSocket = null;
            try {
                connectionSocket = welcomeSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }

            ObjectInputStream objectInput = null;
            ObjectOutputStream objectOutputStream = null;

            try {
                objectInput = new ObjectInputStream(connectionSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            // E questo che potrebbe generare molti problemi!!
            try {
                objectOutputStream = new ObjectOutputStream(connectionSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            // -----------------------------------------


            try {

                dto = (DTO) objectInput.readObject();
                controllerFacade.ArrivaRichiesta(dto);

                DTO Risposta = RispostaMaker.getSingletonInstance().PrelevaRisposta();
                objectOutputStream.writeObject(Risposta);

                System.out.println("mi è arrivato qualcosa : "+ dto.getFunzione());




            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    connectionSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public ControllerFacade getControllerFacade() {
        return controllerFacade;
    }

    public void setControllerFacade(ControllerFacade controllerFacade) {
        this.controllerFacade = controllerFacade;
    }
}
