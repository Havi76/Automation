package framwork;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import org.apache.log4j.Layout;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.net.SMTPAppender;
import org.apache.log4j.spi.LoggingEvent;

public class SecureSMTPAppender extends SMTPAppender {
    protected Session session;
    public SecureSMTPAppender() {
    }
    protected Session createSession() {
        Properties props = new Properties();
        props.put("mail.smtps.host", this.getSMTPHost());
        props.put("mail.smtps.auth", "true");
        Authenticator auth = null;
        if (this.getSMTPPassword() != null && this.getSMTPUsername() != null) {
            auth = new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SecureSMTPAppender.this.getSMTPUsername(), SecureSMTPAppender.this.getSMTPPassword());
                }
            };
        }
        this.session = Session.getInstance(props, auth);
        if (this.getSMTPDebug()) {
            this.session.setDebug(this.getSMTPDebug());
        }
        return this.session;
    }
    protected void sendBuffer() {
        try {
            MimeBodyPart part = new MimeBodyPart();
            StringBuffer sbuf = new StringBuffer();
            String t = this.layout.getHeader();
            if (t != null) {
                sbuf.append(t);
            }
            int len = this.cb.length();
            for(int i = 0; i < len; ++i) {
                LoggingEvent event = this.cb.get();
                sbuf.append(this.layout.format(event));
                if (this.layout.ignoresThrowable()) {
                    String[] s = event.getThrowableStrRep();
                    if (s != null) {
                        for(int j = 0; j < s.length; ++j) {
                            sbuf.append(s[j]);
                            sbuf.append(Layout.LINE_SEP);
                        }
                    }
                }
            }
            t = this.layout.getFooter();
            if (t != null) {
                sbuf.append(t);
            }
            part.setContent(sbuf.toString(), this.layout.getContentType());
            Multipart mp = new MimeMultipart();
            mp.addBodyPart(part);
            this.msg.setContent(mp);
            this.msg.setSentDate(new Date());
            this.send(this.msg);
        } catch (Exception var9) {
            LogLog.error("Error occured while sending e-mail notification.", var9);
        }
    }
    protected void send(Message msg) throws MessagingException {
        SMTPTransport t = (SMTPTransport)this.session.getTransport("smtps");
        try {
            t.connect(this.getSMTPHost(), this.getSMTPUsername(), this.getSMTPPassword());
            t.sendMessage(msg, msg.getAllRecipients());
        } finally {
            System.out.println("Response: " + t.getLastServerResponse());
            t.close();
        }
    }
}
