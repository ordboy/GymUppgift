/*

 */
package gymuppgift;

import java.util.*;

import java.time.*;

import java.io.IOException;
import javax.swing.JOptionPane;

public class GymUppgift {

    public static void goProgram() {
        UserFinder u = new UserFinder();

        String user = JOptionPane.showInputDialog("Ange ett Namn eller ett personnummer");
        user = user.trim();

        try {
            user = u.hasName(user);
            if (user == null) {
                JOptionPane.showMessageDialog(null, "Obehörig användare/input!");
                System.exit(0);
            }
            if (u.validatePayment(user.trim())) {
                JOptionPane.showMessageDialog(null, "Behörig användare");
            } else {
                JOptionPane.showMessageDialog(null, "Medlemskapet utgått");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

 

    public static void main(String[] args) throws IOException {

        goProgram();

    }

}
