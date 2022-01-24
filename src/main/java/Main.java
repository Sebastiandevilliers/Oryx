import java.util.*;

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    static int number = 1;


    public static void main(String[] args){
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }

        boolean flag = false;

        while (!flag){

            System.out.println("Please select option:");
            System.out.println("1.- List Employees");
            System.out.println("2.- Search Employees");
            System.out.println("3.- Add Employees");
            System.out.println("4.- Delete Employee");
            System.out.println("5.- Add Complaint");
            System.out.println("6.- Search Complaints");
            System.out.println("7.- Delete Complaint");
            System.out.println("8.- Exit");


            int n = scanner.nextInt();

            switch (n){

                case 1:
                    List<Employees> employees = datasource.queryEmployees();
                    if(employees == null){
                        System.out.println("No Employees");
                        return;
                    }

                    for(Employees employee : employees){
                        System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName() +
                                "\nEthnicity: " + employee.getEthnicity() + "\nSex: " + employee.getSex() +
                                "\nDisability: " + employee.isDisabled() + "\nPosition: " + employee.getPosition() +
                                "\nAge: " + employee.getAge() + "\nEmployee Number: " + employee.getEmpNumber());
                        System.out.println("--------------");
                    }

                    break;
                case 2:
                    System.out.println("Please enter Employee Number");
                    int employeeNumber = scanner.nextInt();
                    datasource.searchEmployees();
                        List<Employees> searchEmployees = datasource.searchEmployees();
                    if(searchEmployees == null){
                        System.out.println("No Employees");
                        return;
                    }
                    for(Employees emp : searchEmployees){
                        if(emp.getEmpNumber() == employeeNumber) {
                            System.out.println("Name: " + emp.getFirstName() + " " + emp.getLastName() +
                                    " Employee Number: " + emp.getEmpNumber());
                            System.out.println("---------");
                            break;
                        }

                    }
                    break;
                case 3:
                    inputEmp();
                    break;

                case 4:
                    System.out.println("Enter Employee Number");
                    int num = scanner.nextInt();
                    datasource.deleteEmployee(num);

                    break;

                case 5:
                    inputComplaint();
                    break;

                case 6:
                    //search complaints
                    break;

                case 7:
                    //Delete Complaint
                    break;

                case 8:
                    flag = true;
                }

    //   System.out.println(insertEmployeeNum() + " Employee");
      //  datasource.insertEmployeeName("LOP", "jan", "white","Male",false,"Manager",67,5);
      //  datasource.insertEthnicity("White");









        //inputEmp();
       // printEmployees();







     //  List<Employees> employeeNum = datasource.queryEmpNum();
     //   if(employeeNum == null){
     //       System.out.println("No Number");
    //        return;
     //   }
      //  for(Employees employee : employeeNum){
       //     System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName() + "\n" +  " EN: " + employee.getEmpNumber());
      //  }

    //    datasource.insertEmployeeName("Frank","de Villiers","white","male",false,"management",34,randomNum());



    }
        }


    public static Datasource datasource = new Datasource();

    public static int  randomNum(){
        Random random = new Random();
        for(int i = 0; i<1000; i++) {
            int empNum = random.nextInt(4000);
            int empNumTwo = random.nextInt(70000);
            int empNumThree = random.nextInt(600000);
            int empNumFour = random.nextInt(98374);
            number = empNum + empNumTwo + empNumThree;
            number =    number + empNumThree - empNumTwo + empNum;
            number = number + empNumThree - empNumFour + empNumTwo - empNum;


        }
        return number;

    }
    public static void inputEmp(){
        System.out.println("Please enter name");
        String name = scanner.next();
        System.out.println("Please enter Last Name");
        String lastName = scanner.next();
        System.out.println("Please enter Ethnicity");
        String ethnicity = scanner.next();
        System.out.println("Please enter Sex");
        String sex = scanner.next();
        System.out.println("Do you have a disability");
        String disabled = scanner.next();
        System.out.println("Please enter Position");
        String position = scanner.next();
        System.out.println("Please enter Age");
        int age = scanner.nextInt();
        int empNum = randomNum();
        Datasource datasource = new Datasource();
        datasource.insertEmployeeName(name,lastName,ethnicity,sex, Boolean.parseBoolean(disabled),position,age,empNum);
        datasource.close();
    }

    public static void inputComplaint(){
        System.out.println("Enter Employee number");
        int num = scanner.nextInt();
        System.out.println("Please enter link");
        String link = scanner.next();
        Datasource datasource = new Datasource();
        datasource.insertEmployeeComplaint(num,randomNum(),link);
        datasource.close();
    }


        }



