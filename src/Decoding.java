import java.util.Base64;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Decoding {
    public static void main(String[] args) {
        String decode = "U2Fsb20sIEphdmEgZHVueW9zaSE=";
        byte[] bytes = Base64.getDecoder().decode(decode);
        String text = new String(bytes);

        System.out.println(text);
    }
}
