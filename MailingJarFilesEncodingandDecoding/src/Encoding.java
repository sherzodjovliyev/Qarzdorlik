import java.util.Base64;

public class Encoding {
    public static void main(String[] args) {
        String text = "Salom";
        byte[] bytes = text.getBytes();
        String encoder = Base64.getEncoder().encodeToString(bytes);
        System.out.println(encoder);

    }
}