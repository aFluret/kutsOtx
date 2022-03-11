package controller;

import config.DialogManager;
import connection.Connection;
import connection.FXMLLoad;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Otx;
import model.User;


public class OtxController {
    public TextField nameOtx;
    public TextField countOtx;
    public Button cardAdd;
    private User user;
    private Otx otx;
    public ChoiceBox<String> typeOtx;
    public ChoiceBox<String> classOtx;

    public void setUser(User user){
        this.user = user;
    }
    public void initialize() {
        typeOtx.setItems(FXCollections.observableArrayList("возвратные", "безвозвратные"));
        classOtx.setItems(FXCollections.observableArrayList("класс1", "класс2", "класс3","класс4"));
    }
    public void CardRegister(ActionEvent actionEvent) {
        if (!nameOtx.getText().equals("") && typeOtx.getValue()!=null &&classOtx.getValue()!=null && !countOtx.getText().equals("")) {
            Connection.getInstance().post("sighCard " +user.getName()  + " " + nameOtx.getText().trim() + " " + typeOtx.getValue().trim() + " " + classOtx.getValue().trim() + " "+ countOtx.getText().trim() + " " + user.getId().toString());
            nameOtx.getScene().getWindow().hide();
            FXMLLoad.getInstance().openWithParamUser("/view/client.fxml", user);

        } else DialogManager.showErrorDialog("Заполните все поля!");
    }

    public void ActionBack(ActionEvent actionEvent) {
        nameOtx.getScene().getWindow().hide();
        FXMLLoad.getInstance().openWithParamUser("/view/client.fxml", user);
    }

}
