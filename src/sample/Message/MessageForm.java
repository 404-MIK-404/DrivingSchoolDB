package sample.Message;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MessageForm implements Message{

    @Override
    public void ErrorMessage(String HeaderText, String ContentText) {
        Alert ErrorMess = new Alert(Alert.AlertType.ERROR);
        ErrorMess.setTitle("");
        ErrorMess .setHeaderText(HeaderText);
        ErrorMess .setContentText(ContentText);
        Optional<ButtonType> result = ErrorMess .showAndWait();
    }

    @Override
    public void InformationMessage(String HeaderText, String ContentText) {
        Alert InfoMessage = new Alert(Alert.AlertType.INFORMATION);
        InfoMessage.setTitle("");
        InfoMessage .setHeaderText(HeaderText);
        InfoMessage.setContentText(ContentText);
        Optional<ButtonType> result = InfoMessage.showAndWait();
    }

    public void MessageAllDoneSQL(String HeaderText)
    {
        Alert errorAlertEnterToApp = new Alert(Alert.AlertType.INFORMATION);
        errorAlertEnterToApp.setTitle("");
        errorAlertEnterToApp .setHeaderText(HeaderText);
        errorAlertEnterToApp .showAndWait();
    }


}
