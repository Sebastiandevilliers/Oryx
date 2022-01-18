/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oryx;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 *
 * @author fpsde
 */
public class Oryx {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        List<Employees> companyName = new ArrayList<>();
        
        companyName.add(new Employees("Frank", "de Villiers", 23/2/2098,"white","Male",34));
      
        companyName.add(new Employees("Yvonne", "de Villiers", 4, "White", "Female",32));
        companyName.add(new Employees("Vonne", "de Villiers", 5, "White", "Female",32));
        
        System.out.println(companyName);
        
    }
    
}
