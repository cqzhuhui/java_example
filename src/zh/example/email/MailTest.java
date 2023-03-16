package zh.example.email;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailTest {


    public static String myEmailAccount = "";
    public static String myEmailPassword = "";
    public static String myEmailSMTPHost = "smtp.163.com";

    public static String receiveMailAccount = "";

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", myEmailSMTPHost);
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.port", "25");
        props.setProperty("mail.debug", "true");


        Session session = Session.getInstance(props);

        MimeMessage message = createMimeMessage(session, myEmailAccount, receiveMailAccount);

        Transport transport = session.getTransport();


        transport.connect(myEmailAccount, myEmailPassword);
        transport.sendMessage(message, message.getAllRecipients());
        System.out.println("mail send success");
        transport.close();
    }


    public static MimeMessage createMimeMessage(Session session, String sendMail, String receiveMail) throws Exception {
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendMail, "taobao", "UTF-8"));
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(receiveMail, "XX用户", "UTF-8"));
        message.setSubject("mail", "UTF-8");
        message.setContent("hello  shopping", "text/html;charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();
        return message;
    }
}
