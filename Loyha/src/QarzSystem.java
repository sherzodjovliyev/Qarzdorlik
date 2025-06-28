import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class QarzSystem {
    private List<User> users = new ArrayList<>();
    private List<Market> markets = new ArrayList<>();
    private List<Qarz> qarzlar = new ArrayList<>();
    private Long userIdCounter = 1L;
    private Long marketIdCounter = 1L;
    private Long qarzIdCounter = 1L;

    void registerUser(String fistName, String lastName, String phoneNumber, String password){
        for(User user : users){
            if(user.getPhoneNumber().equals(phoneNumber)){
                System.out.println("Bu telefon raqami allqachon ro'yhatga olingan");
                return;
            }
        }
        User user = new User(userIdCounter++,fistName,lastName,phoneNumber,password,"USER");
        users.add(user);
        System.out.println("Foydalanuvchi royhatdan o'tdi: " + fistName + " " + lastName + " (ID: " + user.getId() + ")");
    }

    public User login(String phoneNumber, String password){
        for (User user : users){
            if (user.getPhoneNumber().equals(phoneNumber) && user.getPassword().equals(password)){
                System.out.println("Tizimga kirdingiz: " + user.getFirstName() + " (" +user.getRole() + ") ");
                return user;
            }
        }
        System.out.println("Telefon raqam yok parol xato!");
        return null;
    }
    public  void makeAdmin(User currentUser, Long targetUserId){
        if(!currentUser.getRole().equals("ADMIN")){
            System.out.println("Faqat admin boshqa foydalanuvchini admin qila oladi!!");
            return;
        }
        for (User user : users){
            if (user.getId().equals(targetUserId)){
                user.setRole("ADMIN");
                System.out.println(user.getFirstName() + " endi admin!!");
                return;
            }
        }
    }
    public void makeCashier(User currentUser, Long targetUserId, Long marketId) {
        if (!currentUser.getRole().equals("ADMIN")) {
            System.out.println("Faqat adminlar kassir tayinlay oladi!");
            return;
        }
        boolean marketExists = false;
        for (Market market : markets) {
            if (market.getId().equals(marketId)) {
                marketExists = true;
                break;
            }
        }
        if (!marketExists) {
            System.out.println("Do'kon topilmadi!");
            return;
        }
        for (User user : users) {
            if (user.getId().equals(targetUserId)) {
                user.setRole("CASHIER");
                user.setMarketId(marketId);
                System.out.println(user.getFirstName() + " endi kassir, do'kon ID: " + marketId);
                return;
            }
        }
        System.out.println("Foydalanuvchi topilmadi!");
    }


    public void addMarket(User currentUser, String name, String address) {
        if (!currentUser.getRole().equals("ADMIN")) {
            System.out.println("Faqat adminlar do'kon qo'sha oladi!");
            return;
        }
        Market market = new Market(marketIdCounter++, name, address);
        markets.add(market);
        System.out.println("Do'kon qo'shildi: " + name + " (ID: " + market.getId() + ")");
    }
    public void addDebt(User currentUser, Long userId, Long marketId, double amount) {
        if (!currentUser.getRole().equals("CASHIER")) {
            System.out.println("Faqat kassirlar qarz qo'sha oladi!");
            return;
        }
        if (!currentUser.getMarketId().equals(marketId)) {
            System.out.println("Siz faqat o'zingiz ishlaydigan do'konda qarz qo'sha olasiz!");
            return;
        }
        boolean userExists = false;
        for (User user : users) {
            if (user.getId().equals(userId)) {
                userExists = true;
                break;
            }
        }
        if (!userExists) {
            System.out.println("Foydalanuvchi topilmadi!");
            return;
        }
        boolean marketExists = false;
        for (Market market : markets) {
            if (market.getId().equals(marketId)) {
                marketExists = true;
                break;
            }
        }
        if (!marketExists) {
            System.out.println("Do'kon topilmadi!");
            return;
        }
        Qarz qarz = new Qarz(qarzIdCounter++, userId, marketId, amount, LocalDateTime.now());
        qarzlar.add(qarz);
        System.out.println("Qarz qo'shildi: " + amount + " (Foydalanuvchi ID: " + userId + ", Do'kon ID: " + marketId + ")");
    }
    public void viewDebts(User currentUser) {
        if (!currentUser.getRole().equals("USER")) {
            System.out.println("Faqat oddiy foydalanuvchilar o'z qarzlarini ko'ra oladi!");
            return;
        }
        System.out.println("Sizning qarzlaringiz:");
        boolean hasDebts = false;
        for (Qarz qarz : qarzlar) {
            if (qarz.getUserId().equals(currentUser.getId())) {
                hasDebts = true;
                Market market = null;
                for (Market m : markets) {
                    if (m.getId().equals(qarz.getMarketId())) {
                        market = m;
                        break;
                    }
                }
                if (market != null) {
                    System.out.println("Do'kon: " + market.getName() + ", Qarz: " + qarz.getAmount() + ", Sana: " + qarz.getCreatedAt());
                } else {
                    System.out.println("Do'kon topilmadi (ID: " + qarz.getMarketId() + "), Qarz: " + qarz.getAmount() + ", Sana: " + qarz.getCreatedAt());
                }
            }
        }
        if (!hasDebts) {
            System.out.println("Sizda qarzlar yo'q.");
        }
    }

}
