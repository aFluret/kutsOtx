package collections;

import connection.Connection;
import connection.ServerMessage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Otx;

import java.util.List;

public class CollectionsOtx {
    private ObservableList<Otx> otxList = FXCollections.observableArrayList();

    public javafx.collections.ObservableList<Otx> getOtxList() {
        return otxList;
    }

    public void fillData() {
        Connection.getInstance().post("cardsTable ");
        otxList.clear();
        List<Otx> otxs = (List<Otx>) ServerMessage.get();
        otxList.addAll(otxs);
    }
}
