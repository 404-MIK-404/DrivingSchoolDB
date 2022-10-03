package sample.Message;

public interface Message{

    void ErrorMessage(String HeaderText,String ContentText);


    void InformationMessage(String HeaderText,String ContentText);


    //void MessageDoneSQLQuery(String HeaderText);

}
