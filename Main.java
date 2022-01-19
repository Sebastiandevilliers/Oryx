package Finance;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws SQLException {

        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        List<Employees> employees = datasource.queryEmployees();
        if(employees == null){
            System.out.println("No Employees");
            return;
        }

        for(Employees employee : employees){
            System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        }
     //   datasource.insertEmployee("Koos", "Look","White","Male",false,"Top",50);
        datasource.close();
	// write your code here

  /*      Scanner scanner = new Scanner(System.in);
        List<Integer> employeeNum = new ArrayList<>();
        Random rand = new Random(); //instance of a random class
        int upperbound = 10000;
        int int_random = rand.nextInt(upperbound);
        employeeNum.add(2);


        System.out.println(int_random + " Random number");

        for(int i = 0; i<employeeNum.size();i++){
            if (employeeNum.get(0).equals(int_random)) {
                employeeNum.add(int_random + int_random);
                System.out.println(int_random + " was ");
                break;
            }else{
                employeeNum.add(int_random);
                break;
            }
        }


        for(int b= 0; b<employeeNum.size();b++){
            System.out.println("Employee num: " + employeeNum.get(b));
     */   }


    }

