package controller;

import connection.Connection;
import connection.ServerMessage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Otx;
import model.User;

import java.util.List;

public class OtxInfoController {

    public TableView<Otx> tableCard;
    public TableColumn<Otx, String> nameOtx;
    public TableColumn<Otx, String> nameColumn;
    public TableColumn<Otx, String> typeOtx;
    public TableColumn<Otx, String> classOtx;
    public TableColumn<Otx, Integer> idUserColumn;
    public TableColumn<Otx, Integer> countColumn;
    private User user;
    public Label userName;

    public void setInformation(User user) {
        this.user = user;
        userName.setText(user.getName());

        nameOtx.setCellValueFactory(new PropertyValueFactory<Otx, String>("nameOtx"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<Otx, String>("name"));
        classOtx.setCellValueFactory(new PropertyValueFactory<Otx, String>("classOtx"));
        typeOtx.setCellValueFactory(new PropertyValueFactory<Otx, String>("typeOtx"));
        idUserColumn.setCellValueFactory(new PropertyValueFactory<Otx, Integer>("id_user"));
        countColumn.setCellValueFactory(new PropertyValueFactory<Otx, Integer>("countOtx"));

        Connection.getInstance().post("allOtx "+user.getId());

        tableCard.setItems(FXCollections.observableArrayList((List<Otx>) ServerMessage.get()));
        tableCard.refresh();
    }


    public void ActionCancel(ActionEvent actionEvent) {
        userName.getScene().getWindow().hide();
    }
}
