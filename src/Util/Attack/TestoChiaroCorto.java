package Util.Attack;

import Util.ChiavePubblicaInterlocutore;
import Util.KeyGenerator;
import Util.Network.Configuration;
import Util.Network.TransferObject.DTO;
import Util.Network.TransferObject.DTOMaker;
import Util.RSACripterDecripter;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Gioele on 13/06/2016.
 */
public class TestoChiaroCorto {
    public TestoChiaroCorto() {
    }


    public void Attacca(List<BigInteger> blocchiMessaggioCriptato) {


    }


    public void richiediFirma() throws IOException {


/*
        Socket clientSocket = new Socket(Configuration.getSingletonInstance().getIndirizzoIp(), Integer.parseInt(Configuration.getSingletonInstance().getPortaClient()));
        try {
            System.out.println("Invio qualcosa al server");
            ObjectOutputStream objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
            objectOutput.writeObject(dto);

            ObjectInputStream objectInputStream = new ObjectInputStream(clientSocket.getInputStream());

            DTO risp = (DTO) objectInputStream.readObject();


            List messaggioFirmato = (List) risp.getOggettiTrasferimento().get(0);

            System.out.println(messaggioFirmato.get(0));



        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            clientSocket.close();
        }

    }
*/


    }

}
