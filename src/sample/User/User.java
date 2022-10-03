package sample.User;

public class User {

    private String loginUser;
    private String passwordUser;
    private String accessUser;

    private static User currentUser;


    private User(String loginUser, String passwordUser, String accessUser) {
        this.loginUser = loginUser;
        this.passwordUser = passwordUser;
        this.accessUser = accessUser;
    }


    public static User createUser(String loginUser, String passwordUser, String accessUser){
        if (currentUser == null){
            currentUser = new User(loginUser,passwordUser,accessUser);
        }
        return currentUser;
    }

    public static User getCurrentUser(){
        return currentUser;
    }


    public String getLoginUser() {
        return loginUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public String getAccessUser() {
        return accessUser;
    }

}
