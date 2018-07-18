package ru.youdelivery.DataLayers;

import ru.youdelivery.Helperss.Resp;

import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class LoginCL {

    private HttpResponse response;

   private String appKey = "c312b5a7b1794220a85b89079250e64e";
   private String cliKey = "aec9813472954766897c74a55815d4e1";

   private Gson GSON = new Gson();



    public Resp aurorization(String login, String pass) {



        final String j = "{\n" +
                "\"app\":\"" + appKey + "\",\n" +
                "\"cli\":\"" + cliKey + "\",\n" +
                "\"email\":\"" + login + "\",\n" +
                "\"password\":\"1\"\n" +
                "}";

        HttpClient httpClient = HttpClients.createDefault();


        String url = "https://api.scorocode.ru/api/v1/login";
        HttpPost httpPost = new HttpPost(url);

        httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");

        StringEntity stringEntity = new StringEntity(j, org.apache.http.entity.ContentType.APPLICATION_JSON);
        httpPost.setEntity(stringEntity);
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpPost);
            int code = response.getStatusLine().getStatusCode();
            if (code == 200) {
                HttpEntity respEntity = response.getEntity();
                String result = EntityUtils.toString(respEntity);

                Resp resp = GSON.fromJson(result, Resp.class);
                System.out.println(result);
                if (!resp.getError() && !resp.getResult().getUser().getIsBlocked()) {
                    return resp;
                }
            }



        } catch (IOException e) {
            e.printStackTrace();
        }



        setResponse(response);


    return null;
    }


    public void setResponse(HttpResponse response) {
        this.response = response;
    }
}

