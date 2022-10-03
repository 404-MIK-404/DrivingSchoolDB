package sample.DB;

import javafx.scene.control.TextField;
import sample.User.User;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class OracleDB implements ConnectionsDB {


    private Connection oracleConnection;
    private Statement statementOracle;

    private static OracleDB oracleDB;


    private final String DRIVER_ORACLE = "oracle.jdbc.driver.OracleDriver";
    private final String URL_DB = "jdbc:oracle:thin:@localhost:1521:xe";
    private final String USER_DB = "NIK";
    private final String PASSWORD_DB = "KOLOBOK";

    private PreparedStatement statementQuery;

    private SqlQuery sqlQuery = new SqlQuery();

    private OracleDB() throws Exception {
        createConnection();
    }

    public Statement getStatementOracle() {
        return statementOracle;
    }

    public static OracleDB createOracleDB(){
        try {
            if (oracleDB == null){
                oracleDB = new OracleDB();
            }
            return oracleDB;
        } catch (Exception ex){
            return oracleDB;
        }
    }

    public OracleDB getOracleDB(){
        return oracleDB;
    }



    @Override
    public void createConnection() throws Exception {
        Class.forName(DRIVER_ORACLE);
        oracleConnection = DriverManager.getConnection(URL_DB,USER_DB,PASSWORD_DB);
        statementOracle = oracleConnection.createStatement();
    }

    @Override
    public void createCursor(String sqlQuery) throws Exception {
        statementQuery = oracleConnection.prepareStatement(sqlQuery);
    }


    public boolean getResultQueryUpdate(String sqlUpdate, TextField[] TextVvod,int column) throws Exception {
        ArrayList<String> array = new ArrayList<>();
        for (int i = 0; i < TextVvod.length;i++){
            if (!TextVvod[i].isDisable()){
                array.add(TextVvod[i].getText());
            }
        }
        if (column == 5){
            array.add(array.get(0));
            array.add(array.get(1));
            array.remove(0);
            array.remove(1);
        } else {
            array.add(array.get(0));
            array.remove(0);
        }

        return sqlQuery.queryUpdate(sqlUpdate,array);
    }

    public boolean getResultQueryAdd(String sqlAdd,TextField[] TextVvod) throws Exception{
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < TextVvod.length;i++){
            if (!TextVvod[i].isDisable()){
                arrayList.add(TextVvod[i].getText());
            }
        }
        return sqlQuery.queryAdd(sqlAdd,arrayList);
    }

    public boolean getResultQueryDelete(String sqlDelete,TextField[] TextVvod, int column) throws Exception {
        ArrayList<String> array = new ArrayList<>();
        array.add(TextVvod[0].getText());
        if (column == 5){
            array.add(TextVvod[1].getText());
        }
        return sqlQuery.queryDelete(sqlDelete,array);
    }


    public String getResultLogPass(String login,String password) throws Exception{
        return sqlQuery.queryLogPass(login,password);
    }

    public boolean getResultChangePassword(String currentUser, String newPassword){
        return sqlQuery.ChangePasswordCurrentUser(currentUser,newPassword);
    }




    class SqlQuery {

        final private static String DATE_FORMAT = "yyyy-MM-dd";



        private String queryLogPass(String login,String password) throws Exception{
            String result = "";
            ResultSet resultQuery = statementOracle.executeQuery("SELECT * FROM ENTER_SYSTEM_DB WHERE LOGIN='"+ login + "' AND PASSWORD='"+ password +"'");
            while (resultQuery.next()){
                result = resultQuery.getString("ACCESS_ENTER");
            }
            if (result.isEmpty()){
                return "empty";
            }
            return result;
        }

        private boolean queryUpdate(String sqlUpdate,ArrayList<String> data) throws Exception {
            statementQuery = oracleConnection.prepareStatement(sqlUpdate);
            addData(data);
            statementQuery.executeUpdate();
            return true;
        }

        private boolean queryAdd(String sqlAdd,ArrayList<String> data) throws Exception {
            statementQuery = oracleConnection.prepareStatement(sqlAdd);
            addData(data);
            statementQuery.executeUpdate();
            return true;
        }

        private boolean queryDelete(String sqlDelete,ArrayList<String> data) throws Exception{
            statementQuery = oracleConnection.prepareStatement(sqlDelete);
            addData(data);
            statementQuery.executeUpdate();
            return true;
        }



        private void addData(ArrayList<String> data) throws Exception{
            for (int i = 0; i < data.size();i++){
                if (isInt(data.get(i))) statementQuery.setInt(i+1,Integer.parseInt(data.get(i)));
                else if (isDateValid(data.get(i))){
                    DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
                    java.util.Date date = dateFormat.parse(data.get(i));
                    java.sql.Date sqlFromLong = new java.sql.Date(date.getTime());
                    statementQuery.setDate(i+1,sqlFromLong);
                }
                else if ("NULL".equals(data.get(i))) statementQuery.setNull(i+1,Types.NULL);
                else statementQuery.setString(i+1,data.get(i));
            }

        }

        private boolean ChangePasswordCurrentUser(String currentUser, String newPassword) {
            try {
                statementQuery = oracleConnection.prepareStatement("UPDATE ENTER_SYSTEM_DB SET PASSWORD='"+newPassword+"' WHERE LOGIN='"+currentUser+"'");
                statementQuery.executeUpdate();
                if (User.getCurrentUser() != null){
                    User.getCurrentUser().setPasswordUser(newPassword);
                }
                else {
                    String access = queryLogPass(currentUser,newPassword);
                    User.createUser(currentUser,newPassword,access);
                }

            } catch (Exception ex){
                System.out.println(ex);
                return false;
            }
            return true;
        }


        private boolean isInt(String str)
        {
            for (char c : str.toCharArray())
            {
                if (!Character.isDigit(c)) return false;
            }
            return true;
        }

        private boolean isDateValid(String date)
        {
            try {
                DateFormat df = new SimpleDateFormat(DATE_FORMAT);
                df.parse(date.toString());
                return true;
            } catch (ParseException e) {

                return false;
            }
        }


    }
}
