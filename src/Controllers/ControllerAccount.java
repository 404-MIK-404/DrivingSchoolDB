package Controllers;

import sample.DB.OracleDB;
import sample.User.User;
import sample.Message.MessageForm;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ControllerAccount extends MessageForm {

    @FXML
    private TextField LoginText;

    @FXML
    private PasswordField PasswordText;

    @FXML
    private Button EnterToSystem;

    @FXML
    private Hyperlink ForgetPassword;


    private OracleDB oracleDB;


    @FXML
    public void CheckLoginTest(ActionEvent event) throws Exception
    {
        if (!LoginText.getText().isEmpty() && !PasswordText.getText().isEmpty())
        {
            oracleDB = OracleDB.createOracleDB();
            String resultQuery = oracleDB.getResultLogPass(LoginText.getText(),PasswordText.getText());
            if (!"empty".equals(resultQuery))
            {
                User.createUser(LoginText.getText(),PasswordText.getText(),resultQuery);
                InformationMessage("Вход в систему выполнен успешно ","Логин и пароль потверждён ");
                Stage stage = (Stage) EnterToSystem.getScene().getWindow();
                stage.close();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Forms/sample.fxml"));
                Parent root = loader.load();
                Controller controller = loader.getController();
                controller.setTextLabel(LoginText.getText());
                Stage stage1 = new Stage();
                stage1.setResizable(false);
                stage1.setScene(new Scene(root));
                stage1.show();
            }
            else
            {
                ErrorMessage("Попытка зайти в профиль","Данные которые были введены неверны");
            }
        }
        else
        {
            ErrorMessage("Попытка зайти в профиль","Данные не введены");
        }
    }


    @FXML
    private void ForgetPasswordUser(ActionEvent event) throws Exception
    {
        Stage stage = (Stage) ForgetPassword.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Forms/ChangePassword.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        stage  = new Stage();
        stage.setResizable(false);
        stage.setTitle("Форма входа программы");
        stage.setScene(new Scene(root1));
        stage.show();
    }


}
