package tpgps;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import tpgps.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class DbaseUtils {


    // DB ISEC
    public static User aluno = null;
    public static  User getAlumni(){ return aluno; }
    public static void logInUser(ActionEvent event, String mail, String passAl) {
        System.out.println("login pressed");

        // ler ficheiro de texto com users e pass
        aluno  = findUser(mail);

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
                    Parent root = FXMLLoader.load(DbaseUtils.class.getResource("views/menu.fxml"));
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.setTitle("Booking Masters");
                    stage.setScene(new Scene(root,600,400));
                    stage.show();

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

        return null;
    }



    // DB BOOKINGMASTER
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

                if(dic.equalsIgnoreCase(disciplina)){
                    while(scan.hasNext()){
                        tmp = scan.next();
                        if(!tmp.equalsIgnoreCase("0") && !tmp.equalsIgnoreCase("1"))
                            return listaLugares;
                        listaLugares.add(tmp);
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

        String tmp = "";
        for (String str : listaLugares) {
            tmp += str + " ";
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
}
