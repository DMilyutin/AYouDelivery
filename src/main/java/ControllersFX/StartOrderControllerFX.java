package ControllersFX;

import Helperss.Doc;
import Helperss.Resp;
import com.google.gson.Gson;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;


import java.io.IOException;

import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class StartOrderControllerFX  extends javafx.event.ActionEvent{

    private static final String appKey = "c312b5a7b1794220a85b89079250e64e";
    private static final String cliKey = "aec9813472954766897c74a55815d4e1";
    public static final String COLLECTION_FOR_WORK_BALASHIHA = "for_work_balashiha";
    public static final String COLLECTOIN_WORK_BALASHIHA="work_balashiha";

    private String sesId;
    private String idCustomer;
    private String login;

    private static final Gson GSON = new Gson();

    @FXML
    private TextField edNameCustomer;

    @FXML
    private TextField edNumberCustomer;

    @FXML
    private TextField edAddressCustomer;

    @FXML
    private Label tvCoastOrder;

    @FXML
    private TextField edMatcAddress;




    @FXML
    private CheckBox chTimeNow;

    @FXML
    private TextField edTime;



    public void sendOrder(javafx.event.ActionEvent actionEvent) {

        String numAdress = edMatcAddress.getText();
        String timeFil;
        if(chTimeNow.isSelected()) timeFil = "Ближайшее время";
        else timeFil = edTime.getText();
        String coastOrder = tvCoastOrder.getText();

        Doc o = new Doc(edAddressCustomer.getText(), "Не указано", coastOrder, idCustomer,
                edNameCustomer.getText(), "Не указано", numAdress, edNumberCustomer.getText(),
                "Не указано", "Поиск курьера",
                timeFil, "Без названия");
        J j = new J(appKey, cliKey, "ec67c7fce9fb4f63a234d2d708f3a9c6", sesId, COLLECTION_FOR_WORK_BALASHIHA, o);
        addNewOrderOnServer(j, 1, o);
        // Todo сбор всех данных с форм
        // создание класса J
        // отправка на сервер
        // очистка форм
    }

    private void addNewOrderOnServer(J j, int i, Doc doc) {
        String jForNewOrder = GSON.toJson(j);
        System.out.println(jForNewOrder);
        HttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.scorocode.ru/api/v1/data/insert";
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

        StringEntity stringEntity = new StringEntity(jForNewOrder, APPLICATION_JSON);
        httpPost.setEntity(stringEntity);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            int code = response.getStatusLine().getStatusCode();


            if (code == 200) {
                HttpEntity respEntity = response.getEntity();
                String result = EntityUtils.toString(respEntity);
                if(i == 1){
                    Resp resp = GSON.fromJson(result, Resp.class);
                    String ss = resp.getResult().get_id();
                    doc.setIdForWorkBalashiha(ss);
                    J j2 = new J(appKey, cliKey, "ec67c7fce9fb4f63a234d2d708f3a9c6", sesId, COLLECTOIN_WORK_BALASHIHA, doc);
                    addNewOrderOnServer(j2, 2, doc);
                }
                if(i == 2){
                    clearForms();
                    return;
                    // завершение
                }

                System.out.println(result);






            } else System.out.println(code);

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void setSess(String ses, String login, String idCustomer) {
        System.out.println(ses);
        this.idCustomer = idCustomer;
        sesId = ses;
        this.login = login;
        edNumberCustomer.setText(login);
    }

    private void clearForms(){
        edMatcAddress.setText("");
        edTime.setText("");
        chTimeNow.setSelected(false);
    }


}



    // Классы для формирования GSON


    class J{

        private String app;
        private String cli;
        private String acc;
        private String sess;
        private String coll;
        private Doc doc;

        public J(String app, String cli, String acc, String sess, String coll, Doc doc) {
            this.app = app;
            this.cli = cli;
            this.acc = acc;
            this.sess = sess;
            this.coll = coll;
            this.doc = doc;
        }

        public String getApp() {
            return app;
        }

        public void setApp(String app) {
            this.app = app;
        }

        public String getCli() {
            return cli;
        }

        public void setCli(String cli) {
            this.cli = cli;
        }

        public String getAcc() {
            return acc;
        }

        public void setAcc(String acc) {
            this.acc = acc;
        }

        public String getSess() {
            return sess;
        }

        public void setSess(String sess) {
            this.sess = sess;
        }

        public String getColl() {
            return coll;
        }

        public void setColl(String coll) {
            this.coll = coll;
        }

        public Doc getDoc() {
            return doc;
        }

        public void setDoc(Doc doc) {
            this.doc = doc;
        }
    }





