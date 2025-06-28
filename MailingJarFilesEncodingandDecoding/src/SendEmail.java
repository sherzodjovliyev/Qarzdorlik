import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SendEmail {
    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        final  String username = "sherzodjovliyev16@gmail.com";
        final  String password = "xbnj thpb lhmi swsi";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sherzodjovliyev16@gmail.com"));
            message.setSubject("sallom");

            MimeBodyPart textpart = new MimeBodyPart();
            textpart.setContent("<h1> salom zormian </h1>","text/html");

            MimeBodyPart filepart = new MimeBodyPart();
            filepart.attachFile(new File("C:\\Users\\GHGHGH\\IdeaProjects\\5-modul\\MailingJarFilesEncodingandDecoding\\myCV.txt"));


            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(textpart);
            multipart.addBodyPart(filepart);

            message.setContent(multipart);

            Transport.send(message);
//            message.setText("zormisan");
//            Transport.send(message);
        }catch (MessagingException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}