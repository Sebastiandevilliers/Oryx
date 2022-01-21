public class Employees {

    private String firstName;
    private String lastName;
    private String ethnicity;
    private String sex;
    private boolean disabled;
    private String position;
    private int age;
    private int empNumber;

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setEmpNumber(int empNumber) {
        this.empNumber = empNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public String getSex() {
        return sex;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public String getPosition() {
        return position;
    }

    public int getAge() {
        return age;
    }

    public int getEmpNumber() {
        return empNumber;
    }

    public void add(Employees employees) {
    }
}
