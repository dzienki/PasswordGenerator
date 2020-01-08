package mailSender;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
public class sendMail {
    public static void sendEmail(String email, String text){
        String to= "";
        if(email.contains("@")) to = email;
        else return;
        String from = "senderEmail@gmail.pl";
        final String username = "username";//change accordingly
        final String password = "passwoerd";//change accordingly

        // Assuming you are sending email through relay.jangosmtp.net
        //String host = "relay.jangosmtp.net";

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.mailtrap.io");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Generated Password");

            // Now set the actual message
            message.setText("Hello, there are your generated passwords:\n" +
                    text);

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
