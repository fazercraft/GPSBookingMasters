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

    //GETS
    public String getAtivo() { return ativo; }
    public String getNomeUser() { return nomeUser; }
    public String getDisciplinaUser() {
        return disciplinaUser;
    }
    public String getData() { return data; }
    //SETs
    public void setAtivo(String ativo) { this.ativo = ativo; }
    public void setNomeUser(String nomeUser) {
        this.nomeUser = nomeUser;
    }
    public void setDisciplinaUser(String disciplinaUser) {
        this.disciplinaUser = disciplinaUser;
    }
    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() { return "Reserva: " + nomeUser + " | " +  disciplinaUser + " | " + data +  " --> " + ativo   +'\n'; }
}
