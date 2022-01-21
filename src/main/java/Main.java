import java.util.List;

public class Main {

    public static void main(String[] args){
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

        datasource.close();

    }
}
