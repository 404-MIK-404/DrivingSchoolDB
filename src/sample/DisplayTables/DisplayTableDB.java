package sample.DisplayTables;

import sample.Access.AccessToTable;
import sample.Message.MessageForm;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.sql.Statement;

public class DisplayTableDB extends MessageForm {

    //Наименование колонок на русском
    final private String[][] RateTableRus = {
            {"ID Должность","Наименование","Оклад","Образование"},
            {"ID Персонала","ID Должность","ФИО","Паспорт","Образование","Телефон"},
            {"ID Экзамена","Номер автомобиля","ID Персонала","Дата экзамена","ID Тарифа"},
            {"Номер автомобиля","Марка","Модель","Цвет","Год выпуска","Дата регистраций в ГАИ"},
            {"ID Тарифа","Наименование","Размер скидки","Цена без скидки","Финальная цена"},
            {"ID Ученика","ID Экзамена","Оценка"},{"ID Ученика","ФИО","Паспорт","Место проживания","Дата рождения","ID Группы"},
            {"ID Группы","Наименование","Код программы","Дата начала обучения","Дата окончания обучения"},
            {"ID Расписания","ID Группы","ID Предмета","Номер кабинета","ID Персонала","День недели"},
            {"ID Предмета","Наименование","Кол-во часов"}
    };

    private ObservableList<ObservableList> TableViewData;


    private AccessToTable.Admin adminAccess;
    private AccessToTable.Student studentAccess;
    private AccessToTable.Instructor instructorAccess;
    private AccessToTable.Teacher teacherAccess;

    public AccessToTable.Admin getAdminAccess() {
        return adminAccess;
    }

    public AccessToTable.Student getStudentAccess() {
        return studentAccess;
    }

    public AccessToTable.Instructor getInstructorAccess() {
        return instructorAccess;
    }

    public AccessToTable.Teacher getTeacherAccess() {
        return teacherAccess;
    }

    private void AddColumnTable(ResultSet rs, TableView TableData, int j, TextField[] TextInputTable,Label[] outputName,Label[] outputValue) throws Exception
    {
        for (int i = 0; i < rs.getMetaData().getColumnCount(); i++)
        {
            final int k = i;
            TableColumn col = new TableColumn(RateTableRus[j][i]);
            setEventColumnTable(col,k);
            outputName[i].setText(col.getText() + ":" );
            TextInputTable[i].setPromptText(col.getText());
            TextInputTable[i].setDisable(false);
            TableData.getColumns().addAll(col);
        }

        for (int i = rs.getMetaData().getColumnCount(); i < outputName.length; i++){
            outputName[i].setVisible(false);
            outputValue[i].setVisible(false);
        }

    }

    private void setEventColumnTable(TableColumn col, int k)
    {
        col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>()
        {
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                return new SimpleStringProperty(param.getValue().get(k).toString());
            }
        });
        col.setCellFactory(TextFieldTableCell.forTableColumn());
        col.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                TableViewData.get(event.getTablePosition().getRow()).set(event.getTablePosition().getColumn(),event.getNewValue());
            }
        });

    }

    private void AddEventTableDB(TableView TableData,Label[] outputValue)
    {
        TableData.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if(TableData.getSelectionModel().getSelectedItem() != null)
                {
                    Object selectedItems = TableData.getSelectionModel().getSelectedItems().get(0);
                    for (int i = 0; i < TableData.getColumns().size();i++){
                        outputValue[i].setText(selectedItems.toString().split(",|]")[i].substring(1));
                    }
                }
            }
        });
    }

    private void AddRowsTable(ResultSet rs) throws Exception
    {
        ObservableList<String> row;
        while (rs.next())
        {
            row = FXCollections.observableArrayList();
            for (int i = 1; i <= rs.getMetaData().getColumnCount();i++)
            {
                row.add(rs.getString(i));
            }
            TableViewData.add(row);
        }
    }

    public void availTable(String SQL_Query, TableView TableData, int j, TextField[] TextInputTable, Statement statementSql, ScrollPane ScrollTable, Label[] outputName,Label[] outputValue) throws Exception{
        TableViewData = FXCollections.observableArrayList();
        ResultSet rs = statementSql.executeQuery(SQL_Query);
        AddColumnTable(rs,TableData,j,TextInputTable,outputName,outputValue);
        AddRowsTable(rs);
        AddEventTableDB(TableData,outputValue);
        TableData.getItems().setAll(TableViewData);
        ScrollTable.setContent(TableData);
    }


    public int prepareAvailAdmin(String switchValue){
        switch(switchValue){

            case "Должность":
                adminAccess = AccessToTable.Admin.JOB_TITLE;
                return 0;

            case "Персонал":
                adminAccess = AccessToTable.Admin.STAFF;
                return 0;

            case "Тариф":
                adminAccess = AccessToTable.Admin.RATE;
                return 0;

            case "Результаты экзаменов":
                adminAccess = AccessToTable.Admin.EXAM_RESULTS;
                return 0;

            case "Ученики":
                adminAccess = AccessToTable.Admin.STUDENTS;
                return 0;

            case "Группа":
                adminAccess = AccessToTable.Admin.GROUP;
                return 0;

            case "Расписание":
                adminAccess = AccessToTable.Admin.TIMETABLE;
                return 0;

            default:
                ErrorMessage("Произошла попытка просмотреть данные","Уровень доступа на просмотр данных низок");
                return -1;
        }


    }

    public int prepareAvailInstructor(String switchValue){
        switch(switchValue){

            case "Экзамены":
                instructorAccess = AccessToTable.Instructor.EXAMS;
                return 0;

            case "Автомобили":
                instructorAccess = AccessToTable.Instructor.CARS;
                return 0;


            case "Результаты экзаменов":
                instructorAccess = AccessToTable.Instructor.EXAM_RESULTS;
                return 0;

            default:
                ErrorMessage("Произошла попытка просмотреть данные","Уровень доступа на просмотр данных низок");
                return -1;

        }
    }

    public int prepareAvailStudent(String switchValue){
        switch(switchValue){

            case "Экзамены":
                studentAccess = AccessToTable.Student.EXAMS;
                return 0;

            case "Результаты экзаменов":
                studentAccess = AccessToTable.Student.EXAM_RESULTS;
                return 0;

            case "Группа":
                studentAccess = AccessToTable.Student.GROUP;
                return 0;

            case "Расписание":
                studentAccess = AccessToTable.Student.TIMETABLE;
                return 0;

            case "Предметы":
                studentAccess = AccessToTable.Student.ITEMS;
                return 0;

            default:
                ErrorMessage("Произошла попытка просмотреть данные","Уровень доступа на просмотр данных низок");
                return -1;
        }
    }

    public int prepareAvailTeacher(String switchValue){
        switch(switchValue){

            case "Экзамены":
                teacherAccess = AccessToTable.Teacher.EXAMS;
                return 0;

            case "Результаты экзаменов":
                teacherAccess = AccessToTable.Teacher.EXAM_RESULTS;
                return 0;

            case "Предметы":
                teacherAccess = AccessToTable.Teacher.ITEMS;
                return 0;

            case "Группа":
                teacherAccess = AccessToTable.Teacher.GROUP;
                return 0;

            case "Расписание":
                teacherAccess = AccessToTable.Teacher.TIMETABLE;
                return 0;


            default:
                ErrorMessage("Произошла попытка просмотреть данные","Уровень доступа на просмотр данных низок");
                return -1;
        }
    }




}
