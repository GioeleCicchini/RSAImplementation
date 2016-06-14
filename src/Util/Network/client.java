package Util.Network;

import Util.ChiavePubblicaInterlocutore;
import Util.KeyGenerator;
import Util.Network.TransferObject.DTO;
import Util.Network.TransferObject.DTOMaker;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by Gioele on 11/06/2016.
 */
public class client   {




    public void inviaAlServer(DTO dto) throws IOException {

        Socket clientSocket = new Socket(Configuration.getSingletonInstance().getIndirizzoIp(), Integer.parseInt(Configuration.getSingletonInstance().getPortaClient()));
        try {
            System.out.println("Invio qualcosa al server");
            ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutput.writeObject(dto);

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            DTO risp = (DTO) objectInputStream.readObject();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }

    }


    public void richiediChiavePubblica() throws IOException {

        DTO dto = DTOMaker.getSingletonInstance().getChiavePubblicaInterlocutore();


        Socket clientSocket = new Socket(Configuration.getSingletonInstance().getIndirizzoIp(), Integer.parseInt(Configuration.getSingletonInstance().getPortaClient()));
        try {
            System.out.println("Invio qualcosa al server");
            ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutput.writeObject(dto);

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            DTO risp = (DTO) objectInputStream.readObject();

            KeyGenerator.getSingletonInstance().setChiavePubblicaInterlocutore((ChiavePubblicaInterlocutore) risp.getOggettiTrasferimento().get(0));



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }

    }



    }


