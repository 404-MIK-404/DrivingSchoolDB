package Controllers;

import sample.DB.OracleDB;
import sample.User.User;
import sample.DisplayTables.DisplayTableDB;
import sample.Message.MessageForm;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.lang.instrument.Instrumentation;



public class Controller extends MessageForm {


    private String Access;
    private String SQLAvailTable;
    private String SQLDelete;
    private String SQLChange;
    private String SQLAdd;

    private Instrumentation instrumentation;



    @FXML
    private Button ButtAvto,ButtAdd,ButtChange,ButtDel;

    @FXML
    private ComboBox<String> SwitchTable;

    @FXML
    private TableView DBTable;

    @FXML
    private ScrollPane ScrollTable;

    @FXML
    private Label LoginLabel;

    @FXML
    private TextField InputOne,InputTwo,InputThree,InputFour,InputFive,InputSix;

    @FXML
    private TextField[] TextVvod = {InputOne,InputTwo,InputThree,InputFour,InputFive,InputSix};

    @FXML
    private Label outputName1,outputName2,outputName3,outputName4,outputName5,outputName6;

    @FXML
    private Label outputValue1,outputValue2,outputValue3, outputValue4, outputValue5, outputValue6;

    @FXML
    private Label[] textOutputName = {outputName1,outputName2,outputName3,outputName4,outputName5,outputName6};

    @FXML
    private Label[] textOutputeValue = {outputValue1,outputValue2,outputValue3, outputValue4, outputValue5, outputValue6};



    private DisplayTableDB tableDB = new DisplayTableDB();

    private OracleDB oracleDB = OracleDB.createOracleDB();


    @FXML
    private void DelClick(ActionEvent event) throws Exception
    {
        String sqlDel = getAccessSqlDel();
        if (!isEmptyInputs() && !sqlDel.isEmpty()) {
            try {
                if (oracleDB.getResultQueryDelete(sqlDel,TextVvod,SwitchTable.getSelectionModel().getSelectedIndex())) {
                    MessageAllDoneSQL("Данные удалены");
                    prepareUpdateTable();
                } else {
                    throw new Exception("Результат выполнения вывело ошибку");
                }
            } catch (Exception ex){
                ErrorMessage("Попытка удалить данные прошло не успешно",
                        "Произошла ошибка при удалений данных, проверьте правильность заполненых данных \n Конкретная ошибка: " + ex);
                prepareUpdateTable();
            }
        }
        else
        {
            ErrorMessage("Попытка удалить данные",
                    "Вашего уровня доступа недостаточно для удаление данных,пожалуйста обратитесь в тех службу");
            prepareUpdateTable();
        }
    }

    @FXML
    private void ChangeClick(ActionEvent event) throws Exception
    {
        String sqlUpdate = getAccessSqlUpdate();
        if (!isEmptyInputs() && !sqlUpdate.isEmpty()) {
            try {
                if (oracleDB.getResultQueryUpdate(sqlUpdate, TextVvod,SwitchTable.getSelectionModel().getSelectedIndex())){
                    MessageAllDoneSQL("Данные обновлены");
                    prepareUpdateTable();
                }
                else {
                    throw new Exception("Результат выполнения вывело ошибку");
                }
            } catch (Exception ex){
                ErrorMessage("Попытка обновить данные прошло не успешно",
                        "Произошла ошибка при обновлений данных, проверьте правильность заполненых данных \n Конкретная ошибка: " + ex);
                prepareUpdateTable();
            }
        }
        else
        {
            ErrorMessage("Попытка обновить данные прошло не успешно",
                    "Вашего уровня доступа недостаточно для удаление данных,пожалуйста обратитесь в тех службу");
            prepareUpdateTable();
        }
    }


    @FXML
    private void AddClick(ActionEvent event) throws Exception
    {
        String sqlAdd = getAccessSqlAdd();
        if (!isEmptyInputs() && !sqlAdd.isEmpty()) {
            try {
                if (oracleDB.getResultQueryAdd(sqlAdd,TextVvod)){
                    MessageAllDoneSQL("Данные добавлены");
                    prepareUpdateTable();
                }
                else {
                    throw new Exception("Результат выполнения вывело ошибку");
                }

            } catch (Exception ex){
                ErrorMessage("Попытка добавить данные прошло не успешно",
                        "Произошла ошибка при добавлений данных, проверьте правильность заполненых данных \n Конкретная ошибка: " + ex);
                prepareUpdateTable();
            }
        }
        else
        {
            ErrorMessage("Попытка добавить данные прошло не успешно",
                    "Вашего уровня доступа недостаточно для удаление данных,пожалуйста обратитесь в тех службу");
            prepareUpdateTable();
        }
    }

    private String getAccessSqlDel(){
        switch (User.getCurrentUser().getAccessUser()){
            case "Автоинструктор":
                return tableDB.getInstructorAccess().getSqlDeleteNow();

            case "Админ":
                return tableDB.getAdminAccess().getSqlDeleteNow();

            case "Ученик":
                return tableDB.getStudentAccess().getSqlDeleteNow();

            case "Препод":
                return tableDB.getTeacherAccess().getSqlDeleteNow();

            default:
                return "";
        }
    }

    private String getAccessSqlUpdate(){
        switch (User.getCurrentUser().getAccessUser()){
            case "Автоинструктор":
                return tableDB.getInstructorAccess().getSqlUpdateNow();

            case "Админ":
                return tableDB.getAdminAccess().getSqlUpdateNow();

            case "Ученик":
                return tableDB.getStudentAccess().getSqlUpdateNow();

            case "Препод":
                return tableDB.getTeacherAccess().getSqlUpdateNow();

            default:
                return "";
        }
    }

    private String getAccessSqlAdd(){
        switch (User.getCurrentUser().getAccessUser()){
            case "Автоинструктор":
                return tableDB.getInstructorAccess().getSqlAddNow();

            case "Админ":
                return tableDB.getAdminAccess().getSqlAddNow();

            case "Ученик":
                return tableDB.getStudentAccess().getSqlAddNow();

            case "Препод":
                return tableDB.getTeacherAccess().getSqlAddNow();

            default:
                return "";
        }
    }



    private boolean isEmptyInputs(){
        for (int i = 0; i < TextVvod.length;i++){
            if (!TextVvod[i].isDisable() && TextVvod[i].getText().isEmpty()){
                return true;
            }
        }

        return false;
    }

    private void FalseDisableButton(boolean res)
    {
        ButtAdd.setDisable(res);
        ButtDel.setDisable(res);
        ButtChange.setDisable(res);
    }

    private void ClearTextInput()
    {
        for (int i =0; i < TextVvod.length;i++)
        {
            TextVvod[i].setPromptText("");
            TextVvod[i].setText("");
            textOutputName[i].setText("Значение: ");
            textOutputeValue[i].setText("Значение отсутствует");
        }
    }

    private void setEnable()
    {
        for (int i =0; i < TextVvod.length;i++)
        {
            TextVvod[i].setDisable(true);
            textOutputName[i].setVisible(true);
            textOutputeValue[i].setVisible(true);
        }
    }

    private void clearTableDB(){
        DBTable.getColumns().clear();
        DBTable.getItems().clear();
        DBTable.setDisable(false);
        DBTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    @FXML
    private void UpdateTables(ActionEvent event) throws Exception
    {
        prepareUpdateTable();
    }

    private void prepareUpdateTable() throws Exception {
        if (User.getCurrentUser() != null){
            clearTableDB();
            ClearTextInput();
            setEnable();
            FalseDisableButton(false);
            int selectIndexSwitch = SwitchTable.getSelectionModel().getSelectedIndex();
            switch (User.getCurrentUser().getAccessUser()){
                case "Автоинструктор":
                    if (tableDB.prepareAvailInstructor(SwitchTable.getValue()) != -1)
                        tableDB.availTable(tableDB.getInstructorAccess().getSqlSelectNow(),DBTable,selectIndexSwitch,
                                TextVvod,oracleDB.getOracleDB().getStatementOracle(),ScrollTable,textOutputName,textOutputeValue);
                    break;

                case "Админ":
                    if (tableDB.prepareAvailAdmin(SwitchTable.getValue()) != -1)
                        tableDB.availTable(tableDB.getAdminAccess().getSqlSelectNow(),DBTable,selectIndexSwitch,
                                TextVvod,oracleDB.getOracleDB().getStatementOracle(),ScrollTable,textOutputName,textOutputeValue);
                    break;

                case "Ученик":
                    if (tableDB.prepareAvailStudent(SwitchTable.getValue()) != -1)
                        tableDB.availTable(tableDB.getStudentAccess().getSqlSelectNow(),DBTable,selectIndexSwitch,
                                TextVvod,oracleDB.getOracleDB().getStatementOracle(),ScrollTable,textOutputName,textOutputeValue);
                    break;

                case "Препод":
                    if (tableDB.prepareAvailTeacher(SwitchTable.getValue()) != -1)
                        tableDB.availTable(tableDB.getTeacherAccess().getSqlSelectNow(),DBTable,selectIndexSwitch,
                                TextVvod,oracleDB.getOracleDB().getStatementOracle(),ScrollTable,textOutputName,textOutputeValue);
                    break;
            }
        } else {
            ErrorMessage("Невозможно просмотреть данные","Необходимо зарегистрироваться для того чтобы начать просмотр таблиц");
        }
    }


    public void setTextLabel(String value)
    {
        LoginLabel.setText("Пользователь: " + value );
        SwitchTable.setDisable(false);
    }


    private void DisableTextInput(boolean res)
    {
        for (int i =0; i < TextVvod.length;i++)
        {
            TextVvod[i].setDisable(res);
        }
    }

    @FXML
    public void initialize() throws Exception {
        SwitchTable.setDisable(false);
        SwitchTable.setItems(FXCollections.observableArrayList("Должность" ,"Персонал" ,"Экзамены" ,"Автомобили" ,"Тариф" ,"Результаты экзаменов" ,"Ученики" ,"Группа" ,"Расписание","Предметы"));
        FalseDisableButton(true);
        ScrollTable.setFitToHeight(true);
        ScrollTable.setFitToWidth(true);
        if (User.getCurrentUser() != null){
            LoginLabel.setText("Пользователь: " + User.getCurrentUser().getLoginUser());
        }
        else {
            LoginLabel.setText("Пользователь: Гость");
        }
        TextVvod = new TextField[] {InputOne,InputTwo,InputThree,InputFour,InputFive,InputSix};
        textOutputName = new Label[] {outputName1,outputName2,outputName3,outputName4,outputName5,outputName6};
        textOutputeValue = new Label[] {outputValue1,outputValue2,outputValue3, outputValue4, outputValue5, outputValue6};
        DBTable.setDisable(true);
        DisableTextInput(true);
    }




    private void ChangeForm(String FilePathFXML) throws Exception
    {
        Stage stage = (Stage) ButtAvto.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FilePathFXML));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage1  = new Stage();
        stage1.setResizable(false);
        stage1.setTitle("Форма входа программы");
        stage1.setScene(new Scene(root1));
        stage1.show();
    }

    @FXML
    private void RegisterUser(ActionEvent event) throws Exception
    {
        ChangeForm("../Forms/Account.fxml");
    }




}
