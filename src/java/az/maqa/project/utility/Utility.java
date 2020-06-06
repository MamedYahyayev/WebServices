package az.maqa.project.utility;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Utility {

    public static boolean sendMail(String to, String subject, String text) {

        boolean result = false;

        final String username = "heri.men1995@gmail.com";
        final String password = "herimen1995";

        Properties p = new Properties();
        p.put("mail.smtp.auth", "true");
        p.put("mail.smtp.starttls.enable", "true");
        p.put("mail.smtp.host", "smtp.gmail.com");
        p.put("mail.smtp.port", "587");


        Session session = Session.getInstance(p,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("heri.men1995@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse("heri.men1995@gmail.com"));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;

    }
}
