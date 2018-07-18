package ru.youdelivery.ControllersFX;

import ru.youdelivery.Helperss.Resp;
import ru.youdelivery.DataLayers.LoginCL;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginControllerFX {

   private LoginCL loginCL = new LoginCL();

    @FXML
    private TextField edLogin;

    public void logIn(ActionEvent actionEvent) {
        Stage stageIn = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        String login = edLogin.getText();
        Resp authorization = loginCL.aurorization(login, "1");


      if(!(authorization==null)){
          try {
              String sesId = authorization.getResult().getSessionId();
              String idCustomer = authorization.getResult().getIdCustomer();
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/start_order.fxml"));
              Parent root = loader.load();
              stageIn.setScene(new Scene(root, 850, 450));
              StartOrderControllerFX controllerFX = loader.getController();
              controllerFX.setSess(sesId, login, idCustomer);
              stageIn.show();

          }  catch (Exception e) {
              e.printStackTrace();
          }
      }
      else
          showAlertError("Ошибка авторизации!");

    }


    private void showAlertError(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Авторизация");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}







