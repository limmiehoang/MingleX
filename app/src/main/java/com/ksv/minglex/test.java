import java.security.SecureRandom;

public class test {
    public static void main(String[] args) {
        SecureRandom random = new SecureRandom();
        String s = random.getAlgorithm();
        System.out.println(s);
    }
}
