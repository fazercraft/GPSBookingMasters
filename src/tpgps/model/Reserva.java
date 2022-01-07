package tpgps.model;

public class Reserva {
    String nomeUser;
    String disciplinaUser;
    String data;
    String ativo;

    public Reserva(String nomeUser, String disciplinaUser, String data,String ativo) {
        this.nomeUser = nomeUser;
        this.disciplinaUser = disciplinaUser;
        this.data = data;
        this.ativo = ativo;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public String getNomeUser() {
        return nomeUser;
    }

    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }

    public String getDisciplinaUser() {
        return disciplinaUser;
    }

    public void setDisciplinaUser(String disciplinaUser) {
        this.disciplinaUser = disciplinaUser;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Reserva: " + nomeUser + " | " +  disciplinaUser + " | " + data +  " --> " + ativo   +'\n';
    }
}
