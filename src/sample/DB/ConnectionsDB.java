package sample.DB;

public interface ConnectionsDB {
    void createConnection() throws Exception;
    void createCursor(String sqlQuery) throws Exception;
}
