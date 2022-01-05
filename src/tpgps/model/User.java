package tpgps.model;

public class User {

    private int id;
    private String numUser;
    private String pwdUser;
    private String nameUser;

    public User(int id, String numUser, String pwdUser, String nameUser) {
        this.id = id;
        this.numUser = numUser;
        this.pwdUser = pwdUser;
        this.nameUser = nameUser;
    }


    // GETS
    public int getId() { return id; }
    public String getNumUser() {
        return numUser;
    }
    private String getPwdUser(){ return pwdUser;}
    public String getNameUser() {
        return nameUser;
    }

    // SETS
    public void setId(int id) { this.id = id; }
    public void setNumUser(String numUser) {
        this.numUser = numUser;
    }
    public void setPwdUser(String pwdUser) {
        this.pwdUser = pwdUser;
    }
    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
