package Controllers;

import sample.DB.OracleDB;
import sample.Message.MessageForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControllerChangePass extends MessageForm {

    @FXML
    private TextField OldLog;

    @FXML
    private PasswordField NewPass,RepeatPass;

    @FXML
    private Button ChangePass;


    private OracleDB oracleDB = OracleDB.createOracleDB();


    @FXML
    private void setChangePass(ActionEvent event)
    {
        try {
            if (NewPass.getText().equals(RepeatPass.getText()) && !OldLog.getText().isEmpty())
            {
                if (oracleDB.getResultChangePassword(OldLog.getText(),NewPass.getText()))
                {
                    MessageAllDoneSQL("Пароль изменён");
                    Stage stage = (Stage) ChangePass.getScene().getWindow();
                    stage.close();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../Forms/sample.fxml"));
                    Parent root = loader.load();
                    Controller controller = loader.getController();
                    controller.setTextLabel(OldLog.getText());
                    Stage stage1 = new Stage();
                    stage1.setResizable(false);
                    stage1.setScene(new Scene(root));
                    stage1.show();
                }
            }
            else
            {
                errorClearInput("Невозможно изменить пароли ","Пароли которые были введены не одинаковы");
            }
        }
        catch (Exception ex)
        {
            errorClearInput("Невозможно изменить пароли ","Пароли которые были введены не одинаковы");
        }
    }


    private void errorClearInput(String headerMessage,String contentMessage){
        ErrorMessage(headerMessage,contentMessage);
        NewPass.setText("");
        RepeatPass.setText("");
    }

}
