import java.util.function.*;

public class FunctionalL {
    public static void main(String[] args) {
        Function<Integer,Integer> f1 = a -> a * a;
        System.out.println(f1.apply(12));

        Consumer<String> f2 = a -> System.out.println(a + "Salom");
        f2.accept("Sherzod");

        Predicate<Integer> f3 = a -> a > 5;
        System.out.println(f3.test(4));

        Supplier<Integer> f4 = () -> 28;
        System.out.println(f4.get());

        BiFunction<Integer,Integer,Integer> f5 = (a,b) -> a * b;
        System.out.println(f5.apply(4,5));
    }
}