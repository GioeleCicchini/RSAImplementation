package Ui.Controllers;

import Ui.Chat.MessaggiChat;
import Util.*;
import Util.Network.Configuration;
import Util.Network.TransferObject.DTO;
import Util.Network.TransferObject.DTOMaker;
import Util.Network.client;
import Util.Network.Server.server;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Created by Gioele on 11/06/2016.
 */
public class controller implements Initializable {


    public TextField nomeUtente;
    public TextField indirizzoIp;
    public TextField portaInvio;
    public TextField portaRicezione;
    public TextArea messaggioBox;
    public VBox chat;

    private server server = null;
    private client client = new client();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MessaggiChat messaggiChat = new MessaggiChat();

        messaggiChat.setControllerinterfaccia(this);

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


            String messaggio = messaggioBox.getText();

            this.aggiungiMessaggio(messaggio,true);

            List<BigInteger> messaggioBlocchiCriptato = RSACripterDecripter.getSingletonInstance().Cripta(KeyGenerator.getSingletonInstance().getChiavePubblicaInterlocutore(),messaggio);


            DTO dto = DTOMaker.getSingletonInstance().inviaMessaggio(messaggioBlocchiCriptato);

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
        Configuration.getSingletonInstance().setIndirizzoIp(indirizzoIp.getText());
        server = new server();

        server.StartServer();


    }


    public void aggiungiMessaggio(String messaggio,boolean utente){

        Label label = new Label(messaggio);

        FlowPane pane = new FlowPane();
        pane.setPrefHeight(20);
        label.setPadding(new Insets(5,20,5,20));
        label.setTextFill(Paint.valueOf("FFFFFF"));
        if(utente == true){
            pane.setAlignment(Pos.TOP_RIGHT);
            label.setStyle("-fx-background-color: green");
        }
        else {
            label.setStyle("-fx-background-color: lightseagreen");
        }
        pane.getChildren().add(label);
        pane.setMargin(label,new Insets(5,15,5,15));


        Platform.runLater(new Runnable(){

                              @Override
                              public void run() {
                                  chat.getChildren().add(pane);
                              }
        });

    }

}
