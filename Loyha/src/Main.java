import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QarzSystem system = new QarzSystem();
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;


        system.registerUser("Admin", "User", "123456789", "admin123");
        User admin = system.login("123456789", "admin123");
        if (admin != null) {
            admin.setRole("ADMIN");
        }

        do {
            System.out.println("\n=== Qarz Tizimi ===");
            if (currentUser == null) {
                System.out.println("1. Ro'yxatdan o'tish");
                System.out.println("2. Tizimga kirish");
            } else {
                System.out.println("Foydalanuvchi: " + currentUser.getFirstName() + " (" + currentUser.getRole() + ")");
                if (currentUser.getRole().equals("ADMIN")) {
                    System.out.println("3. Foydalanuvchini admin qilish");
                    System.out.println("4. Foydalanuvchini kassir qilish");
                    System.out.println("5. Do'kon qo'shish");
                } else if (currentUser.getRole().equals("CASHIER")) {
                    System.out.println("6. Qarz qo'shish");
                } else if (currentUser.getRole().equals("USER")) {
                    System.out.println("7. Qarzlarni ko'rish");
                }
                System.out.println("8. Tizimdan chiqish (boshqa foydalanuvchi sifatida kirish)");
            }
            System.out.println("9. Dasturdan chiqish");
            System.out.print("Tanlov: ");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Clear buffer
            } catch (Exception e) {
                System.out.println("Noto'g'ri tanlov! Iltimos, raqam kiriting.");
                scanner.nextLine();
                continue;
            }

            if (choice == 1 && currentUser == null) {
                System.out.print("Ism: ");
                String firstName = scanner.nextLine();
                System.out.print("Familya: ");
                String lastName = scanner.nextLine();
                System.out.print("Telefon: ");
                String phone = scanner.nextLine();
                System.out.print("Parol: ");
                String password = scanner.nextLine();
                system.registerUser(firstName, lastName, phone, password);
            } else if (choice == 2 && currentUser == null) {
                System.out.print("Telefon: ");
                String phone = scanner.nextLine();
                System.out.print("Parol: ");
                String password = scanner.nextLine();
                currentUser = system.login(phone, password);
            } else if (choice == 3 && currentUser != null && currentUser.getRole().equals("ADMIN")) {
                System.out.print("Admin qilmoqchi bo'lgan foydalanuvchi ID: ");
                try {
                    Long targetUserId = scanner.nextLong();
                    system.makeAdmin(currentUser, targetUserId);
                } catch (Exception e) {
                    System.out.println("Noto'g'ri ID kiritildi!");
                }
            } else if (choice == 4 && currentUser != null && currentUser.getRole().equals("ADMIN")) {
                System.out.print("Kassir qilmoqchi bo'lgan foydalanuvchi ID: ");
                try {
                    Long targetUserId = scanner.nextLong();
                    System.out.print("Do'kon ID: ");
                    Long marketId = scanner.nextLong();
                    system.makeCashier(currentUser, targetUserId, marketId);
                } catch (Exception e) {
                    System.out.println("Noto'g'ri ID kiritildi!");
                }
            } else if (choice == 5 && currentUser != null && currentUser.getRole().equals("ADMIN")) {
                System.out.print("Do'kon nomi: ");
                String name = scanner.nextLine();
                System.out.print("Do'kon manzili: ");
                String address = scanner.nextLine();
                system.addMarket(currentUser, name, address);
            } else if (choice == 6 && currentUser != null && currentUser.getRole().equals("CASHIER")) {
                try {
                    System.out.print("Foydalanuvchi ID: ");
                    Long userId = scanner.nextLong();
                    System.out.print("Do'kon ID: ");
                    Long marketId = scanner.nextLong();
                    System.out.print("Qarz summasi: ");
                    double amount = scanner.nextDouble();
                    system.addDebt(currentUser, userId, marketId, amount);
                } catch (Exception e) {
                    System.out.println("Noto'g'ri ma'lumot kiritildi!");
                }
            } else if (choice == 7 && currentUser != null && currentUser.getRole().equals("USER")) {
                system.viewDebts(currentUser);
            } else if (choice == 8 && currentUser != null) {
                currentUser = null;
                System.out.println("Tizimdan chiqdingiz. Yangi foydalanuvchi sifatida kiring.");
            } else if (choice == 9) {
                System.out.println("Dastur yakunlandi.");
                break;
            } else {
                System.out.println("Noto'g'ri tanlov yoki ruxsat yo'q!");
            }
        } while (true);

        scanner.close();
    }
}