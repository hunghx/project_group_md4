package ra.academy.config;

import org.mindrot.jbcrypt.BCrypt;

import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        String pass = "123456";
        String passBcrypt = BCrypt.hashpw(pass,BCrypt.gensalt(12));
        System.out.println(passBcrypt);
        // giải mã
        String p = new Scanner(System.in).nextLine();
        System.out.println(BCrypt.checkpw(p,passBcrypt));
    }
}
