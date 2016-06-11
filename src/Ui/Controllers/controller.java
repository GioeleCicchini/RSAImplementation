package Ui.Controllers;

import Util.KeyGenerator;
import Util.Network.Configuration;
import Util.Network.TransferObject.DTO;
import Util.Network.TransferObject.DTOMaker;
import Util.Network.client;
import Util.Network.Server.server;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Gioele on 11/06/2016.
 */
public class controller implements Initializable {


    public TextField nomeUtente;
    public TextField indirizzoIp;
    public TextField portaInvio;
    public TextField portaRicezione;

    private server server = null;
    private client client = new client();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        KeyGenerator GeneratoreChiavi = KeyGenerator.getSingletonInstance();
        if(new File("ChiavePrivata.bin").exists()){
            try {
                GeneratoreChiavi.caricaChiaveDaFile("ChiavePrivata");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(new File("ChiavePubblica.bin").exists()){
            try {
                GeneratoreChiavi.caricaChiaveDaFile("ChiavePubblica");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        if(!new File("ChiavePubblica.bin").exists()){
            if(!new File("ChiavePrivata.bin").exists()){
                GeneratoreChiavi.generaChiavi();
                GeneratoreChiavi.salvaChiaviSuFile();
            }
        }


    }


    public void invia(Event event) throws IOException {


        if(KeyGenerator.getSingletonInstance().getChiavePubblicaInterlocutore() != null){

            DTO dto = new DTO();

            client.inviaAlServer(dto);
        }
        else {
            client.richiediChiavePubblica();

            System.out.println("Ho prelevato N: "+KeyGenerator.getSingletonInstance().getChiavePubblicaInterlocutore().getChiavePubblicaInterlocutore().get("N"));
            System.out.println("Ho prelevato E: "+KeyGenerator.getSingletonInstance().getChiavePubblicaInterlocutore().getChiavePubblicaInterlocutore().get("E"));

        }



    }

    public void aggiorna(Event event) {


        Configuration.getSingletonInstance().setPortaClient(portaInvio.getText());
        Configuration.getSingletonInstance().setPortaServer(portaRicezione.getText());
        server = new server();

        server.StartServer();


    }
}
