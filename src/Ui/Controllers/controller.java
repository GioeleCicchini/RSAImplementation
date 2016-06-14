package Ui.Controllers;

import Ui.Chat.MessaggiChat;
import Util.*;
import Util.Attack.Frazione;
import Util.Attack.FrazioneContinua;
import Util.Attack.TestoChiaroCorto;
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
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.URL;
import java.security.Key;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Map<String,BigInteger> chiavePub = new HashMap<>();

        chiavePub.put("E",new BigInteger("323815174542919"));
        chiavePub.put("N", new BigInteger("1966981193543797"));

        KeyGenerator.getSingletonInstance().setChiavePubblica(chiavePub);

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

    public void attacca(Event event) throws IOException {

        FrazioneContinua frazione = new FrazioneContinua();


        frazione.calcolaFrazioneContinua(KeyGenerator.getChiavePubblica().get("E"),KeyGenerator.getChiavePubblica().get("N"));

        List<BigInteger> frazionecontinuaCifre = frazione.getFrazioneContinuaCifre();

        for(int i=2 ; i<frazionecontinuaCifre.size();i++) {
            Frazione frazioneIniziale = new Frazione(BigInteger.ONE, frazionecontinuaCifre.get(i));

            frazione.OttieniFrazione(i, frazioneIniziale);

            BigInteger parziale= KeyGenerator.getChiavePubblica().get("E").multiply(frazione.getFrazionecercata().get_denominatore()).subtract(BigInteger.ONE);

            BigDecimal num = new BigDecimal(parziale.toString());
            BigDecimal den = new BigDecimal(frazione.getFrazionecercata().get_numeratore());

            if(den.compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal C = num.divide(den,50, RoundingMode.UP);
                System.out.println(C);
            }


        }


    }
}
