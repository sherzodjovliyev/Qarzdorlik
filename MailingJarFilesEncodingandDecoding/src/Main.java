import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            // SMTP sozlamalari
            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.port", "587");

            // Foydalanuvchi ma'lumotlari
            String username = "sherzodjovliyev16@gmail.com";
            String password = "xbnj thpb lhmi swsi"; // Gmail App Password

            // Sessiya yaratish
            Session session = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            // Xabar obyekti
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("@gmail.com"));
            message.setSubject("HTML va rasm bilan xabar");

            // Multipart "related" obyekti yaratish
            MimeMultipart multipart = new MimeMultipart("related");

            // HTML qismini qo'shish
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<h1>Rasim korinayaptimi</h1>" +
                    "<p>Bu emailda rasm mavjud:</p>" +
                    "<img src=\"cid:image1\" alt=\"Rasm\" style=\"width:300px;\">";
            htmlPart.setContent(htmlContent, "text/html; charset=utf-8");
            multipart.addBodyPart(htmlPart);

            // Rasm faylini qo'shish
            MimeBodyPart imagePart = new MimeBodyPart();
            String filePath = "C:\\Users\\GHGHGH\\Desktop\\rasm.jpg"; // O'zingizning rasm yo'lingizni kiriting
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                throw new IllegalArgumentException("Fayl topilmadi yoki noto'g'ri: " + filePath);
            }
            FileDataSource fileDataSource = new FileDataSource(file);
            imagePart.setDataHandler(new DataHandler(fileDataSource));
            imagePart.setContentID("<image1>"); // HTMLdagi "cid:image1" bilan bog'lanadi
            imagePart.setFileName(fileDataSource.getName());
            imagePart.setDisposition(MimeBodyPart.INLINE); // Inline ko'rinish uchun
            multipart.addBodyPart(imagePart);

            // Xabarga multipart kontentni o'rnatish
            message.setContent(multipart);

            // Xabar yuborish
            Transport.send(message);
            System.out.println("Xabar muvaffaqiyatli yuborildi!");
        } catch (MessagingException e) {
            System.err.println("Xabar yuborishda xatolik: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Fayl bilan bog'liq xatolik: " + e.getMessage());
        }
    }
}