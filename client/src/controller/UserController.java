package controller;

import connection.Connection;
import connection.FXMLLoad;
import connection.ServerMessage;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Otx;
import model.User;

import java.util.List;

public class UserController {
    public TextField account;
    public Label helloLabel;
    public TableView<Otx> tableOtx;
    public TableColumn<Otx, String> typeOtx;
    public TableColumn<Otx, String> classOtx;
    public TableColumn<Otx, String> nameOtx;
    public TableColumn<Otx, Integer> countOtx;
    public Button btnSum;


    private Otx otx;
    private User user;

    @FXML
    public void initialize(){
        tableOtx.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    otx = tableOtx.getSelectionModel().getSelectedItem();
                    btnSum.setDisable(false);
                }
            }
        });
    }

    public void ActionLogout(ActionEvent actionEvent) {
        helloLabel.getScene().getWindow().hide();
        FXMLLoad.getInstance().open("/view/main.fxml");
    }



    public void setInformation(User user){
        nameOtx.setCellValueFactory(new PropertyValueFactory<Otx, String>("nameOtx"));
        typeOtx.setCellValueFactory(new PropertyValueFactory<Otx, String>("typeOtx"));
        classOtx.setCellValueFactory(new PropertyValueFactory<Otx, String>("classOtx"));
        countOtx.setCellValueFactory(new PropertyValueFactory<Otx, Integer>("countOtx"));

        Connection.getInstance().post("allOtx " + user.getId() );

        tableOtx.setItems(FXCollections.observableArrayList((List<Otx>) ServerMessage.get()));
        tableOtx.refresh();

        this.user = user;
        helloLabel.setText("Здравствуйте: " + user.getName());
    }

    public void ActionCard(ActionEvent actionEvent) {
        helloLabel.getScene().getWindow().hide();
        FXMLLoad.getInstance().openWithOtx("/view/otx_add.fxml", user);

    }
}
