/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oryx;

import java.time.LocalDate;

/**
 *
 * @author fpsde
 */
public class Employees {
    
    private String firstName;
    private String lastName;
    private int started;
    private String ethnicity;
    private String sex;
    private int age;
  
    public Employees(String firstName, String lastName, int started,
            String ethnicity, String sex, int age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.started = started;
        this.ethnicity = ethnicity;
        this.sex = sex;
        this.age = age;
        
    }
    
    public String getName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public int getStartDate(){
        return started;
    }
    
    public String getEthnicity(){
        return ethnicity;
    }
    
    public String getSex(){
        return sex;
    }
    
    public int getAge(){
        return age;
    }
    
    public String toString(){
        return "Employee name: " + firstName + " " + lastName + "\n" +
                "Started Employement: " + started + "\n" +
                "Age: " + age + "\n" +
                "Sex: " + sex + "\n" +
                "Ethnicity: " + ethnicity + "\n";
    }
    
}
