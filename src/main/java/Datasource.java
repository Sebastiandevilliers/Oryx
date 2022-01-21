import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datasource {

    public static final String DB_NAME = "Oryx.db";


    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\fpsde\\Documents\\Database\\" + DB_NAME;

    //  public void createTable


    public static final String TABLE_EMPLOYEES = "Employees";
    public static final String COLUMN_EMPNUM ="Employee Number";
    public static final String COLUMN_FIRSTNAME = "First Name";
    public static final String COLUMN_LASTNAME = "Last Name";
    public static final String COLUMN_ETHNICITY = "Ethnicity";
    public static final String COLUMN_SEX = "sex";
    public static final String COLUMN_DISABLED = "disabled";
    public static final String COLUMN_POSITION = "Position";
    public static final String COlUMN_AGE = "age";
    public static final int INDEX_EMPLOYEES = 0;
    public static final int INDEX_EMPNUM = 8;
    public static final int INDEX_FIRSTNAME = 1;
    public static final int INDEX_LASTNAME = 2;
    public static final int INDEX_ETHNICITY = 3;
    public static final int INDEX_SEX = 4;
    public static final int INDEX_DISABLED = 5;
    public static final int INDEX_POSITION = 6;
    public static final int INDEX_AGE = 7;





    public static final String INSERT_EMPLOYEE = "INSERT INTO " + TABLE_EMPLOYEES +
            '(' + COLUMN_FIRSTNAME + ", " + COLUMN_LASTNAME + ", " + COLUMN_ETHNICITY +
            '(' + COLUMN_SEX + ", " + COLUMN_DISABLED + ", " + COLUMN_POSITION +
            '(' + COlUMN_AGE + ", " + COLUMN_EMPNUM + ") VALUES(?, ?, ?, ?, ?, ?, ?, ?)";


    public static final String QUERY_EMPLOYEES = "SELECT " + COLUMN_FIRSTNAME + "FROM " +
            TABLE_EMPLOYEES + " WHERE " + COlUMN_AGE + " = ?";


    Connection conn;
    private PreparedStatement insertIntoEmployees;
    private PreparedStatement queryEmployees;



    public boolean open() {
        try{
            conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS EMPLOYEES " +
                    " (FIRSTNAME TEXT, LASTNAME TEXT, ETHNICITY TEXT, SEX TEXT, DISABLED TEXT, POSITION TEXT, AGE INT, EMPNUM INT)");
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

    public List<Employees> queryEmployees(){

        try(Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM " + TABLE_EMPLOYEES)){

            List<Employees> employee = new ArrayList<>();
            while(results.next()){
                Employees employees = new Employees();
                employees.setFirstName(results.getString(INDEX_FIRSTNAME));
                employees.setLastName(results.getString(INDEX_LASTNAME));
                employee.add(employees);

            }
            return employee;

        }catch (SQLException e){
            System.out.println("Query failed: " + e.getMessage());
            return null;
        }
    }

    public void insertEmployee(String firstName, String lastName, String ethnicity, String sex, boolean disabality,
                               String position, int age) {
        try{
            conn.setAutoCommit(false);
            queryEmployees.setString(1,firstName);
            queryEmployees.setString(2, lastName);
            queryEmployees.setString(3, ethnicity);
            queryEmployees.setString(4, sex);
            queryEmployees.setBoolean(5, disabality);
            queryEmployees.setString(6, position);
            queryEmployees.setInt(7, age);
            insertIntoEmployees.setString(1, firstName);
            insertIntoEmployees.setString(2, lastName);
            insertIntoEmployees.setString(3, ethnicity);
            insertIntoEmployees.setString(4, sex);
            insertIntoEmployees.setBoolean(5, disabality);
            insertIntoEmployees.setString(6, position);
            insertIntoEmployees.setInt(7, age);


            int affectedRows = insertIntoEmployees.executeUpdate();
            if(affectedRows == 1){
                conn.commit();
            }else {
                throw new SQLException("Employee insert failed");
            }

            ResultSet results = queryEmployees.executeQuery();

            //insert employee

        } catch(SQLException e){
            System.out.println("Employee insert exception: " + e.getMessage());
            try{
                System.out.println("Performing rollback");
                conn.rollback();
            }catch (SQLException e2){
                System.out.println("Error: " + e2.getMessage());
            }
            finally {
                try{
                    System.out.println("Resetting default commit behavior");
                    conn.setAutoCommit(true);
                }catch (SQLException e3){
                    System.out.println("Couldn't reset auto-commit" + e.getMessage());
                }

            }
        }


    }
}
