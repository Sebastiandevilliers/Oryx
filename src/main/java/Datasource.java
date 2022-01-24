import javax.management.Query;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.UIManager.getInt;

public class Datasource<COLUMN_LASTNAME> {

    public static final String DB_NAME = "Oryx.db";


    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\fpsde\\Documents\\Database\\" + DB_NAME;

    //  Employee Table
    public static final String TABLE_EMPLOYEES = "EMPLOYEES";
    public static final String COLUMN_EMPNUM = "Employee Number";
    public static final String COLUMN_FIRSTNAME = "FIRSTNAME";
    public static final String COLUMN_LASTNAME = "LASTNAME";
    public static final String COLUMN_ETHNICITY = "ETHNICITY";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_DISABLED = "disabled";
    public static final String COLUMN_POSITION = "Position";
   // public static final int COLUMN_EMPNUM = "empnum";
    public static final String COlUMN_AGE = "age";
    public static final int INDEX_FIRSTNAME = 1;
    public static final int INDEX_LASTNAME = 2;
    public static final int INDEX_ETHNICITY = 3;
    public static final int INDEX_SEX = 4;
    public static final int INDEX_DISABLED = 5;
    public static final int INDEX_POSITION = 6;
    public static final int INDEX_AGE = 7;
    public static final int INDEX_EMPNUM = 8;

    //Complaints
    public static final String TABLE_COMPLAINTS = "complaints";
    public static final String COLUMN_EMPLOYEE_NUM = "employeeNum";
    public static final String COLUMN_COMPLAINT_NUM = "complaintNum";
    public static final String COLUMN_COMPLAINT_LINK = "complaintLink";
    public static final int INDEX_EMPLOYEE_NUM = 1;
    public static final int INDEX_COMPLAINT_NUM = 2;
    public static final int INDEX_COMPLAINT_LINK = 3;





    public static final String INSERT_EMPLOYEE = "INSERT INTO " + TABLE_EMPLOYEES +
            '(' + COLUMN_FIRSTNAME  + ") VALUES ( ? )";



         /*   COLUMN_ETHNICITY +
            COLUMN_SEX + ", " + COLUMN_DISABLED + ", " + COLUMN_POSITION +
            COlUMN_AGE + ", " + COLUMN_EMPNUM + " ) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");*/

  //  public void addEmployee(){
  //      Statement.execute("INSERT INTO EMPLOYEES (FIRSTNAME, LASTNAME,  ")
 //   }



    public static final String QUERY_EMPLOYEES = "SELECT " + COLUMN_FIRSTNAME + " FROM " +
            TABLE_EMPLOYEES + " WHERE " + COlUMN_AGE + " = ?";
    public static final String INSERT_COMPLAINT = "INSERT INTO " + TABLE_COMPLAINTS +
            '(' + COLUMN_COMPLAINT_NUM + COLUMN_EMPLOYEE_NUM + COLUMN_COMPLAINT_LINK + ") VALUES(?,? ,?)";



    Connection conn;
    private PreparedStatement insertIntoEmployees;
    private PreparedStatement queryEmployees;
    private PreparedStatement insertComplaint;



    public boolean open() {
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS EMPLOYEES " +
                    " (FIRSTNAME TEXT, LASTNAME TEXT, ETHNICITY TEXT, SEX TEXT, DISABLED TEXT, POSITION TEXT, AGE INT, EMPNUM INT)");
            statement.execute("CREATE TABLE IF NOT EXISTS COMPLAINTS " +
                    " (EmployeeNum INT, ComplaintNum INT, ComplaintLink)");
                 insertIntoEmployees = conn.prepareStatement(INSERT_EMPLOYEE);
                 queryEmployees = conn.prepareStatement(QUERY_EMPLOYEES);


            return true;
        } catch (SQLException e){
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close(){
        try{
            if(conn != null){
                conn.close();
            }
            if(insertIntoEmployees != null ){
                insertIntoEmployees.close();
            }
            if(queryEmployees != null){
                queryEmployees.close();
            }
        } catch (SQLException e){
            System.out.println("Couldn't close connection: " + e.getMessage() );
        }
    }

    public List<Employees> queryEmpNum(){
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_EMPLOYEES)){

            List<Employees> employeeNum = new ArrayList<>();
            while(results.next()){
                Employees employees = new Employees();
                employees.setFirstName(results.getString(INDEX_FIRSTNAME));
                employees.setLastName(results.getString(INDEX_LASTNAME));
                employees.setEmpNumber(results.getInt(INDEX_EMPNUM));
                employeeNum.add(employees);

            }
            return employeeNum;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public List<Employees> numVerify(){
        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_EMPLOYEES)){

            List<Employees> employeeNumVerify = new ArrayList<>();
            while(results.next()){
                Employees employees = new Employees();
                employees.setFirstName(results.getString(INDEX_FIRSTNAME));
                employeeNumVerify.add(employees);

            }
            return employeeNumVerify;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
    //Search Employee With number
    public List<Employees> searchEmployees(){
        try(Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_EMPLOYEES  )){
            List<Employees> empNum = new ArrayList<>();
            while (result.next()){
                Employees emp = new Employees();
                emp.setFirstName(result.getString(INDEX_FIRSTNAME));
                emp.setLastName(result.getString(INDEX_LASTNAME));
                emp.setEmpNumber(result.getInt(INDEX_EMPNUM));
                empNum.add(emp);

            }
            return empNum;
        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
    //Print list of employees
    public List<Employees> queryEmployees(){

        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_EMPLOYEES)){

            List<Employees> employee = new ArrayList<>();
            while(results.next()){
                Employees employees = new Employees();
                employees.setFirstName(results.getString(INDEX_FIRSTNAME));
                employees.setLastName(results.getString(INDEX_LASTNAME));
                employees.setEthnicity(results.getString(INDEX_ETHNICITY));
                employees.setSex(results.getString(INDEX_SEX));
                employees.setDisabled(results.getBoolean(INDEX_DISABLED));
                employees.setPosition(results.getString(INDEX_POSITION));
                employees.setAge(results.getInt(INDEX_AGE));
                employees.setEmpNumber(results.getInt(INDEX_EMPNUM));
                employee.add(employees);

            }
            return employee;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }
    //Adding Employee Name

    public void insertEmployeeName(String firstName, String lastName, String ethnicity, String sex, boolean disabled, String position, int age,int num){
        String sql = "INSERT INTO EMPLOYEES(FIRSTNAME, LASTNAME, ETHNICITY, sex, disabled, Position, age, empnum  ) VALUES (?,?,?,?,?,?,?,?)";
        try{

            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(INDEX_FIRSTNAME,firstName);
            preparedStatement.setString(INDEX_LASTNAME,lastName);
            preparedStatement.setString(INDEX_ETHNICITY,ethnicity);
            preparedStatement.setString(INDEX_SEX,sex);
            preparedStatement.setBoolean(INDEX_DISABLED,disabled);
            preparedStatement.setString(INDEX_POSITION,position);
            preparedStatement.setInt(INDEX_AGE,age);
            preparedStatement.setInt(INDEX_EMPNUM,num);
            preparedStatement.executeUpdate();



        }catch (SQLException e){
            System.out.println("Could not add firstName" + e.getMessage());
        }
    }

    //Add New Complaint
    public void insertEmployeeComplaint(int employeeNum, int complaintNum, String complaintLink) {
        String sql = "INSERT INTO COMPLAINTS(EmployeeNum, ComplaintNum, ComplaintLink ) VALUES (?,?,?)";
        try {
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(INDEX_EMPLOYEE_NUM, employeeNum);
            preparedStatement.setInt(INDEX_COMPLAINT_NUM, complaintNum);
            preparedStatement.setString(INDEX_COMPLAINT_LINK, complaintLink);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Could not add Complaint" + e.getMessage());

        }
    }
        //Delete Employee
    public void deleteEmployee(int numb) {
            String sql = "DELETE FROM  EMPLOYEES  WHERE empnum  = ?";


            try {

                Connection conn = DriverManager.getConnection(CONNECTION_STRING);
                PreparedStatement pstmt =conn.prepareStatement(sql);
                pstmt.setInt(1, numb);
                pstmt.executeUpdate();


                }catch(SQLException e){
                System.out.println("Could not Delete " + e.getMessage());
            }

    }

}
