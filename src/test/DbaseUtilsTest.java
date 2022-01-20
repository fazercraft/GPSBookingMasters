package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tpgps.model.Reserva;
import tpgps.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class DbaseUtilsTest {

    public static User aluno;
    public static ArrayList<Reserva> reservas;

    @BeforeEach
    void setUp() {
        aluno = null;
        reservas = null;
    }

    @Test
    void logInUser() {

        String mail="a21230461@isec.pt";
        User aluno2 = new User("0","empty","empty","empty","empty");

        try {
            File myObj = new File("src/basesdados/BDisec.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                aluno2.setId(scan.nextLine());
                aluno2.setEmail(scan.nextLine());
                aluno2.setNameUser(scan.nextLine());
                aluno2.setNumUser(scan.nextLine());
                aluno2.setPwdUser(scan.nextLine());

                // verificar
                if(aluno2.getEmail().equalsIgnoreCase(mail))
                    aluno = aluno2;
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

      assert aluno.equals(aluno2);

    }

    @Test
    void getLugares() {

        String disciplina = "IP";
        ArrayList<String> lugares = new ArrayList<>();
        for(int i = 0; i< 20;i++)
            lugares.add("0");


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
                    break;
                }
            }

            scan.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        assert listaLugares.equals(lugares);
    }

    @Test
    void reservaLugar() {

        String disciplina = "IP";
        ArrayList<String> lugares = new ArrayList<>();
        for(int i = 0; i< 20;i++)
            lugares.add("0");


        StringBuilder tmp = new StringBuilder();
        for (String str : lugares) {
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

        // confirmar
        String dic;
        String tmp2;
        ArrayList<String> lista = new ArrayList<>();
        try {
            File myObj = new File("src/basesdados/DBbookinMastersCourses.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                dic = scan.nextLine();
                int i=0;
                if(dic.equalsIgnoreCase(disciplina)){
                    while(scan.hasNext() && i < 20){
                        tmp2 = scan.next();
                        lista.add(tmp2);
                        i++;
                    }
                    break;
                }
            }
            scan.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        assert lista.equals(lugares);
    }

    @Test
    void saveToFileReserva() {
        Reserva novaRes = new Reserva("Jaffar AKbar","disciplinaTest","teste","0");
        reservas = new ArrayList<>();

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



        try {
            File myObj = new File("src/basesdados/reservas.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                Reserva resTmp = new Reserva("Jaffar AKbar", "tmp","tmp","0");
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

        int flag = 0;
        for (Reserva res: reservas) {
            if(res.getNomeUser().equals(novaRes.getNomeUser())
                    && res.getDisciplinaUser().equals(novaRes.getDisciplinaUser())
                    && res.getData().equals(novaRes.getData())
                    && res.getAtivo().equals(novaRes.getAtivo())){
                flag = 1;
                break;
            }
        }

        assert flag == 1;

    }


    @Test
    void removeLugar() {

        String disciplina = "SO UM";
        String alunoId = "1";

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
                    break;
                }
            }

            scan.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        int flag = 0;

        for(int i = 0; i< listaLugares.size();i++)
            if(listaLugares.get(i).contains(alunoId))
                listaLugares.set(i,"0");

        for (String listaLugare : listaLugares)
            if (listaLugare.contains(alunoId)) {
                flag = 1;
                break;
            }
        assert flag == 0;
            // agora chama o reserva lugar e passa a lista sem o id e isso já foi testado e funciona

    }

    @Test
    void mostraHistorico(){
        reservas = new ArrayList<>();

        try {
            File myObj = new File("src/basesdados/reservas.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                Reserva resTmp = new Reserva("tmp", "tmp","tmp","0");
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
        assert reservas!=null;
    }

}