package ControllersFX;

import Helperss.Resp;
import com.google.gson.Gson;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;



import org.apache.http.impl.client.HttpClients;

import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


import java.io.*;




public class LoginControllerFX {

    private static final String appKey = "c312b5a7b1794220a85b89079250e64e";
    private static final String cliKey = "aec9813472954766897c74a55815d4e1";



    private String login;
    private String sesId;
    private String idCustomer;

    private static final Gson GSON = new Gson();


    @FXML
    private TextField edLogin;


    public void logIn(ActionEvent actionEvent) {
        Stage stageIn = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        login = edLogin.getText();


      Boolean authorization = authorization(login);
      if(authorization){
          try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start_order.fxml"));
              Parent root = loader.load();
              stageIn.setScene(new Scene(root, 750, 550));
              StartOrderControllerFX controllerFX = loader.getController();
              controllerFX.setSess(sesId, login, idCustomer);
              stageIn.show();
          }  catch (Exception e) {
              e.printStackTrace();
          }
      }

    }




    private Boolean authorization(String login) {
        Boolean b = false;
        String j = "{\n" +
                "\"app\":\""+appKey+"\",\n" +
                "\"cli\":\""+cliKey+"\",\n" +
                "\"email\":\""+ login + "\",\n" +
                "\"password\":\"1\"\n" +
                "}";


        HttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.scorocode.ru/api/v1/login";
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

        StringEntity stringEntity = new StringEntity(j, org.apache.http.entity.ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);


        try {
                HttpResponse response = httpClient.execute(httpPost);
                int code = response.getStatusLine().getStatusCode();


                if(code == 200){
                    HttpEntity respEntity = response.getEntity();
                    String result = EntityUtils.toString(respEntity);

                    Resp resp = GSON.fromJson(result, Resp.class);

                    if(!resp.getError()&&!resp.getResult().getUser().getIsBlocked()) {
                        sesId = resp.getResult().getSessionId();
                        idCustomer = resp.getResult().getUser().getIdCustomer();
                        System.out.println(idCustomer);
                        b = true;
                    }

                }

            else return false;

        } catch (ClientProtocolException e) {
            e.printStackTrace();
            b =  false;
        } catch (IOException e) {
            e.printStackTrace();
            b =  false;
        }
        return b;
    }

}







