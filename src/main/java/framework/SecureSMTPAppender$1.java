package framework;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

class SecureSMTPAppender$1 extends Authenticator {
    private final SecureSMTPAppender this$0;

    SecureSMTPAppender$1(SecureSMTPAppender var1) {
        this.this$0 = var1;
    }

    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(this.this$0.getSMTPUsername(), this.this$0.getSMTPPassword());
    }
}
