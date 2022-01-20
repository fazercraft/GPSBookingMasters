package tpgps;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tpgps.model.Reserva;
import tpgps.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;


public class DbaseUtils {
    public static User aluno = null;
    public static ArrayList<Reserva> reservas = null;

    // DB ISEC
    public static  User getAlumni(){ return aluno; }
    public static void logInUser(ActionEvent event, String mail, String passAl) {
        // ler ficheiro de texto com users e pass
        aluno  = findUser(mail);
        reservas = findReservas();


        if( aluno == null){
            System.out.println("user not found in database");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha no Login");
            alert.setContentText("User not found");
            alert.show();
        }else{
            if(!findPass(passAl)){
                System.out.println("pass did not match: " + passAl);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Password incorrect");
                alert.show();
            }else{
                // salta para o MENU Scene
                try {
                    Parent root = FXMLLoader.load(Objects.requireNonNull(DbaseUtils.class.getResource("views/menu.fxml")));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Booking Masters");
                    stage.setScene(new Scene(root,800,600));
                    stage.show();
                    System.out.println("Login Successful");

                } catch (IOException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
    private static Boolean findPass(String pwd) {
        return aluno.getPwdUser().equals(pwd);
    }
    private static User findUser(String mail) {

       aluno = new User("0","empty","empty","empty","empty");

        try {
            File myObj = new File("src/basesdados/BDisec.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                aluno.setId(scan.nextLine());
                aluno.setEmail(scan.nextLine());
                aluno.setNameUser(scan.nextLine());
                aluno.setNumUser(scan.nextLine());
                aluno.setPwdUser(scan.nextLine());

                // verificar
                if(aluno.getEmail().equalsIgnoreCase(mail))
                    return aluno;
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return aluno;
    }


    // DB BOOKINGMASTER
    private static ArrayList<Reserva> findReservas() {
        reservas = new ArrayList<>();

        try {
            File myObj = new File("src/basesdados/reservas.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                Reserva resTmp = new Reserva(aluno.getNameUser(), "tmp","tmp","0");
                resTmp.setNomeUser(scan.nextLine());
                resTmp.setDisciplinaUser(scan.nextLine());
                resTmp.setData(scan.nextLine());
                resTmp.setAtivo(scan.nextLine());
                reservas.add(resTmp);

            }
            scan.close();
        } catch (IOException e) {
            System.out.println("ERRO LOAD RESERVAS.");
            e.printStackTrace();
        }
        return reservas;
    }
    public static ArrayList<Reserva> getReservas(){return reservas;}
    public static ArrayList<String> getDisciplinas() {

        ArrayList<String> disciplinas = new ArrayList<>();
        String id;
        String fim;

        try {
            File myObj = new File("src/basesdados/BDbookinMasters.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
               id = scan.nextLine();

               if(id.equalsIgnoreCase(aluno.getId())){
                   while(scan.hasNextLine()){
                       fim = scan.nextLine();
                       if(fim.equalsIgnoreCase("fim"))
                           return disciplinas;
                       disciplinas.add(fim);
                   }
               }
            }

            scan.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return disciplinas;

    }
    public static ArrayList<String> getLugares(String disciplina) {
        ArrayList<String> listaLugares = new ArrayList<>();
        String dic;
        String tmp;
        try {
            File myObj = new File("src/basesdados/DBbookinMastersCourses.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                dic = scan.nextLine();
                int i=0;
                if(dic.equalsIgnoreCase(disciplina)){
                    while(scan.hasNext() && i < 20){
                        tmp = scan.next();
                        listaLugares.add(tmp);
                        i++;
                    }
                    return listaLugares;
                }
            }

            scan.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return listaLugares;
    }
    public static void reservaLugar(String disciplina, ArrayList<String> listaLugares) {

        StringBuilder tmp = new StringBuilder();
        for (String str : listaLugares) {
            tmp.append(str).append(" ");
        }

        try {
            FileReader reader = new FileReader("src/basesdados/DBbookinMastersCourses.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
            FileWriter writer = new FileWriter("src/basesdados/savetmp.txt");
            String line;
            while ((line = bufferedReader.readLine())!=null){
                if(!line.equalsIgnoreCase(disciplina)){
                    writer.write(line + "\n");
                }else{
                    writer.write(line + "\n");
                    writer.write(tmp + "\n");
                    bufferedReader.readLine();
                }
            }
            reader.close();
            writer.close();

            FileReader readSave = new FileReader("src/basesdados/savetmp.txt");
            BufferedReader BuffSave = new BufferedReader(readSave);
            FileWriter writerSave = new FileWriter("src/basesdados/DBbookinMastersCourses.txt");
            String jardel;
            while ((jardel = BuffSave.readLine())!=null){
                writerSave.write(jardel + "\n");
            }
            readSave.close();
            writerSave.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public static void saveToFileReserva(Reserva novaRes) {

            try {
                FileWriter writer = new FileWriter("src/basesdados/reservas.txt",true);
                writer.write(novaRes.getNomeUser() + "\n");
                writer.write(novaRes.getDisciplinaUser()+ "\n");
                writer.write(novaRes.getData()+ "\n");
                writer.write(novaRes.getAtivo()+"\n");
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred writing to reservas.txt");
                e.printStackTrace();
            }

    }
    public static void UpdateFileReservas() {
        // apagar reservas.txt e escrever para lá a lista de revs como está
        try {
            FileWriter writer = new FileWriter("src/basesdados/reservas.txt",false);
            for (Reserva res:reservas) {
                writer.write(  res.getNomeUser() + "\n");
                writer.write(res.getDisciplinaUser()+ "\n");
                writer.write(res.getData()+ "\n");
                writer.write(res.getAtivo()+"\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred writing to reservas.txt");
            e.printStackTrace();
        }


    }
    public static void removeLugar(String disciplina) {
        // atualizar no ficheiro bookingMastersCourses na disciplina o lugar para 0
        // onde o lugar for igual ao id do user

        ArrayList<String> listaLugares = getLugares(disciplina);
        for(int i = 0; i< listaLugares.size();i++)
            if(listaLugares.get(i).contains(aluno.getId()))
                listaLugares.set(i,"0");

        reservaLugar(disciplina,listaLugares);

    }
}
