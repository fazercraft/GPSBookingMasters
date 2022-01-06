package tpgps.model;

import java.util.ArrayList;

public class User {

    private String id;
    private String email;
    private String numUser;
    private String pwdUser;
    private String nameUser;
    private ArrayList<String> listaReservas;

    public User(String id, String email,String numUser, String pwdUser, String nameUser) {
        this.id = id;
        this.email = email;
        this.numUser = numUser;
        this.pwdUser = pwdUser;
        this.nameUser = nameUser;
        listaReservas = new ArrayList<>();
    }


    // GETS
    public String getId() { return id; }
    public String getEmail(){return email;}
    public String getNumUser() {
        return numUser;
    }
    public String getPwdUser(){ return pwdUser;}
    public String getNameUser() {
        return nameUser;
    }
    public ArrayList<String> getListaReservas(){return listaReservas;}

    // SETS
    public void setId(String id) { this.id = id; }
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
    public void addReserva(String res){this.listaReservas.add(res);}
    public void rmReserva(String res){this.listaReservas.remove(res);}

    @Override
    public String toString() {

        String alumni="";
        alumni +="\nID: " + this.getId();
        alumni +="\nEMAIL: " + this.getEmail();
        alumni +="\nNome: " + this.getNameUser();
        alumni +="\nNUM: " + this.getNumUser();
        alumni +="\nPASS: " + this.getPwdUser();

       return alumni;


    }
}
