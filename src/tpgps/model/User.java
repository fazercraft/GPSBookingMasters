package tpgps.model;

public class User {

    private int id;
    private String email;
    private String numUser;
    private String pwdUser;
    private String nameUser;

    public User(int id, String email,String numUser, String pwdUser, String nameUser) {
        this.id = id;
        this.email = email;
        this.numUser = numUser;
        this.pwdUser = pwdUser;
        this.nameUser = nameUser;
    }


    // GETS
    public int getId() { return id; }
    public String getEmail(){return email;}
    public String getNumUser() {
        return numUser;
    }
    public String getPwdUser(){ return pwdUser;}
    public String getNameUser() {
        return nameUser;
    }

    // SETS
    public void setId(int id) { this.id = id; }
    public void setEmail(String email){this.email = email;}
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
