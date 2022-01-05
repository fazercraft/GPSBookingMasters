package tpgps.model;

public class User {

    private String numUser;
    private String pwdUser;
    private String nameUser;

    public User(String numUser, String pwdUser, String nameUser) {
        this.numUser = numUser;
        this.pwdUser = pwdUser;
        this.nameUser = nameUser;
    }

    public String getNumUser() {
        return numUser;
    }

    public void setNumUser(String numUser) {
        this.numUser = numUser;
    }

    public String getPwdUser() {
        return pwdUser;
    }

    public void setPwdUser(String pwdUser) {
        this.pwdUser = pwdUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
