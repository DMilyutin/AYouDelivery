package ru.youdelivery.ControllersFX;

import ru.youdelivery.Helperss.Custors;
import ru.youdelivery.Helperss.Doc;
import ru.youdelivery.Helperss.Resp;
import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;

import java.io.IOException;


import static org.apache.http.entity.ContentType.APPLICATION_JSON;

public class StartOrderControllerFX  extends javafx.event.ActionEvent{

   private  String appKey = "c312b5a7b1794220a85b89079250e64e";
   private  String cliKey = "aec9813472954766897c74a55815d4e1";
   private  String accKey = "ec67c7fce9fb4f63a234d2d708f3a9c6";

   private   String COLLECTION_FOR_WORK_BALASHIHA = "for_work_balashiha";
   private   String COLLECTOIN_WORK_BALASHIHA="work_balashiha";

    private String sesId;
    private String idCustomer;
    private String login;
    private int coastOneAddress = 120;

    private Gson GSON = new Gson();

    private ObservableList<Custors> userData = FXCollections.observableArrayList();



    @FXML
    private TextField edNameCustomer;
    @FXML
    private TextField edNameOrder;
    @FXML
    private TextField edNumberCustomer;
    @FXML
    private TextField edAddressCustomer;
    @FXML
    private TextField etCoastCustomer;
    @FXML
    private Label tvRecomendCoastOrder;
    @FXML
    private TextField edMatcAddress;
    @FXML
    private CheckBox chTimeNow;
    @FXML
    private TextField edTime;
    @FXML
    private TableView tableCustomers;
    @FXML
    private TableColumn<Custors, String> tableCulumnNum;
    @FXML
    private TableColumn<Custors, String> tableCulumnAddress;
    @FXML
    private TableColumn<Custors, String> tableCulumnPhone;


    private void initialize(){
            tableCustomers.setVisible(true);

            tableCulumnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            tableCulumnAddress.setCellFactory(TextFieldTableCell.forTableColumn());
            tableCulumnAddress.setMinWidth(200);

            tableCulumnAddress.setOnEditCommit((CellEditEvent<Custors, String> eventAddress) -> {
                TablePosition<Custors, String> posAddress = eventAddress.getTablePosition();

                String newAddress = eventAddress.getNewValue();

                int rowAddress = posAddress.getRow();
                Custors custors = eventAddress.getTableView().getItems().get(rowAddress);

                custors.setAddress(newAddress);
                userData.set(rowAddress, custors);
            });

        tableCulumnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tableCulumnPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        tableCulumnPhone.setMinWidth(150);
            tableCulumnPhone.setOnEditCommit((CellEditEvent<Custors, String> eventPhone) -> {
                TablePosition<Custors, String> posPhone = eventPhone.getTablePosition();

                String newPhone = eventPhone.getNewValue();

                int rowPhone = posPhone.getRow();
                Custors custorsPhone = eventPhone.getTableView().getItems().get(rowPhone);

                custorsPhone.setPhone(newPhone);
                userData.set(rowPhone, custorsPhone);
            });

       tableCulumnNum.setCellValueFactory(new PropertyValueFactory<>("number"));


            userData.add(new Custors("1", "",""));
            userData.add(new Custors("2", "",""));
            userData.add(new Custors("3", "",""));
            userData.add(new Custors("4", "",""));
            userData.add(new Custors("5", "",""));
            userData.add(new Custors("6", "",""));
            userData.add(new Custors("7", "",""));
            userData.add(new Custors("8", "",""));
            userData.add(new Custors("9", "",""));

            tableCustomers.setItems(userData);

        }

    private Doc prepearNewDoc(){
        String addressCustomer = edAddressCustomer.getText();
        if (addressCustomer.equals("")||addressCustomer.isEmpty()){showAlertError("Укажите ваш адрес");
            return null;}

        String nameCustomer = edNameCustomer.getText();
        if (nameCustomer.equals("")||nameCustomer.isEmpty()){showAlertError("Укажите ваше название");
            return null;}

        String phoneCustomer = edNumberCustomer.getText();
        if (phoneCustomer.equals("")||phoneCustomer.isEmpty()){showAlertError("Укажите ваш номер");
            return null;}

        String numAdress = edMatcAddress.getText();
        int mumAddress =-1;

        if (numAdress.equals("")||numAdress.isEmpty()) {
            showAlertError("Укажите кол-во адресов");
        return null;}
        else { mumAddress = Integer.parseInt(numAdress);}

        String adresses= "";
        String phones = "";
        if(mumAddress>1){
            for(int i = 0; i< mumAddress; i++ ){
                adresses += userData.get(i).getAddress();
                phones +=userData.get(i).getPhone();
                if(i+1 != mumAddress) {
                    adresses +=";";
                    phones +=";";
                }
            }
        }
        else if (mumAddress ==1){
            adresses=userData.get(0).address;
            phones = userData.get(0).getPhone();
        }
        else{ adresses = "Не указано";
                phones = "Не указано";
        }

        String timeFil;
        if(chTimeNow.isSelected()) timeFil = "Ближайшее время";
        else {
            timeFil = edTime.getText();
            if (timeFil.isEmpty()||timeFil.equals("")){showAlertError("Укажите время прибытия курьера");
            return null;}


        }

        String coastOrder = etCoastCustomer.getText();
        if(coastOrder.equals("")||coastOrder.isEmpty()){
            coastOrder= String.valueOf(coastOneAddress*mumAddress);
        }

        Doc doc = new Doc(addressCustomer, adresses, coastOrder, idCustomer, nameCustomer
                , "Не указано", numAdress, phoneCustomer,
                phones, "Поиск курьера",
                timeFil, edNameOrder.getText());


        return doc;
    }


    public void sendOrder(javafx.event.ActionEvent actionEvent) {

        Doc doc = prepearNewDoc();
        J j = new J(appKey, cliKey, accKey, sesId, COLLECTION_FOR_WORK_BALASHIHA, doc);
        if (doc != null){}
         addNewOrderOnServer(j, 1, doc);

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
                    J j2 = new J(appKey, cliKey, accKey, sesId, COLLECTOIN_WORK_BALASHIHA, doc);
                    addNewOrderOnServer(j2, 2, doc);
                }
                if(i == 2){
                    clearForms();
                    showAlertWithoutHeaderText("Заказ успешно создан");
                    return;

                }

                System.out.println(result);

            } else {showAlertError("Код ошибки " + code);}

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showAlertWithoutHeaderText(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Формирование заказа");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    private void showAlertError(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Формирование заказа");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }


    private void clearTable(){
        tableCustomers.setItems(getList());
    }

    private ObservableList<Custors> getList() {
        Custors c1 = new Custors("1","","");
        Custors c2 = new Custors("2","","");
        Custors c3 = new Custors("3","","");
        Custors c4 = new Custors("4","","");
        Custors c5 = new Custors("5","","");
        Custors c6 = new Custors("6","","");
        Custors c7 = new Custors("7","","");
        Custors c8 = new Custors("8","","");
        Custors c9 = new Custors("9","","");
        ObservableList<Custors> list = FXCollections.observableArrayList(c1, c2, c3, c4,c5,c6,c7,c8,c9);



        return list;
    }

    void setSess(String ses, String login, String idCustomer) {
        System.out.println(ses);
        this.idCustomer = idCustomer;
        sesId = ses;
        this.login = login;
        edNumberCustomer.setText(login);
        initialize();
    }

    private void clearForms(){
        edMatcAddress.setText("");
        edTime.setText("");
        etCoastCustomer.setText("");
        chTimeNow.setSelected(false);
        clearTable();

    }
}
    // Классы для формирования GSON





