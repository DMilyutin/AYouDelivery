package Classes;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class StartOrder {

    private String appKey = "c312b5a7b1794220a85b89079250e64e";
    private String cliKey = "aec9813472954766897c74a55815d4e1";


    Stage stage;
    String sesId;
    String login;

    //@FXML
    //private TextField edLogin;

    @FXML
    TextField edLogin;

    public StartOrder(Stage stage, String sesId, String login) throws Exception{
        this.stage = stage;
        this.sesId = sesId;
        this.login = login;
        System.out.println(sesId);
        System.out.println(login);

        initCustomer();

        System.out.println(sesId);
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/start_order.fxml"));
        stage.setScene(new Scene(root, 600, 400));
        stage.show();




    }

    private void initCustomer() {

        String js2 = "{\n" +
                "\"app   \":\""+appKey+"\",\n" +
                "\"cli   \":\""+cliKey+"\",\n" +
                "\"acc\":\"\",\n" +
                "\"sess  \":\"" +sesId+ "\",\n"+
                "\"coll\":\"customer_balashiha\",\n" +
                "\"query\": {\n"+
                    "\"loginCustomer\": {\n"+
                        "\"$eq\": \""+login+"\"\n" +
                            "}\n" +
                    "},\n" +
                "\"sort\": {\n" +
                        "\"updatedAt\":" + 1 +"\n" +
                    "},\n" +
                "\"fields\": [],\n" +
                "\"limit\":"+  50 + ",\n" +
                "\"skip\":" +  0 + ",\n" +
                "\"pool\": " + "{}" + "}";

        HttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.scorocode.ru/api/v1/data/find";
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

        StringEntity stringEntity = new StringEntity(js2, org.apache.http.entity.ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);

        try {
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity respEntity = response.getEntity();
            int code = response.getStatusLine().getStatusCode();
            System.out.println(code);
            String result = EntityUtils.toString(respEntity);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}



