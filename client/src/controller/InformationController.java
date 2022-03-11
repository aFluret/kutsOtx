package controller;

import collections.CollectionsOtx;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Otx;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class InformationController {
    public TableView<Otx> tableCredit;
    public TableColumn<Otx, String> nameOtx;
    public TableColumn<Otx, String> typeOtx;
    public TableColumn<Otx, String> classOtx;
    public TableColumn<Otx, Integer> countOtx;
    public Label userName;

    private CollectionsOtx collectionsOtx = new CollectionsOtx();
    public void setInformation() {

        nameOtx.setCellValueFactory(new PropertyValueFactory<Otx, String>("nameOtx"));
        typeOtx.setCellValueFactory(new PropertyValueFactory<Otx, String>("typeOtx"));
        classOtx.setCellValueFactory(new PropertyValueFactory<Otx, String>("classOtx"));
        countOtx.setCellValueFactory(new PropertyValueFactory<Otx, Integer>("countOtx"));
        collectionsOtx.fillData();
        tableCredit.setItems(collectionsOtx.getOtxList());
        tableCredit.refresh();

    }

    public void ActionCancel(ActionEvent actionEvent) {
        userName.getScene().getWindow().hide();
    }


    public void ActionOK(ActionEvent actionEvent) {saveInformationInFileOtx(tableCredit.getItems());}

    private void saveInformationInFileOtx(ObservableList<Otx> items) {
        int count = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("InformationOtx.txt", true))) {
            for (Otx client : items) {
                count++;
                String out = count + "название:" + client.getNameOtx() + ",\nтип отхода: " + client.getTypeOtx() + ",\nкласс отхода: " + client.getClassOtx() + ",\nколичество: " + client.getCountOtx() + " \n";
                writer.append(out);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
