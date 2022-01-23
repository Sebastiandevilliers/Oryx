import java.util.List;

public class Main {

    public static void main(String[] args){
        Datasource datasource = new Datasource();
        if(!datasource.open()){
            System.out.println("Can't open datasource");
            return;
        }
        datasource.insertEmployeeName("drie", "jan", "white","Male",false,"Manager",67);
      //  datasource.insertEthnicity("White");





        List<Employees> employees = datasource.queryEmployees();
        if(employees == null){
            System.out.println("No Employees");
            return;
        }

        for(Employees employee : employees){
            System.out.println("Name: " + employee.getFirstName() + " " + employee.getLastName());
        }

        datasource.close();

    }
}
