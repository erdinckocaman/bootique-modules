package com.tamplan.bootique.simplejavamail;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MailerRepository {

    private Map<String, Mailer> mailers;

    public MailerRepository() {
        mailers = new HashMap<>();
    }

    public void addMailer(String name, MailerAccount mailerAccount) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(mailerAccount);

        if (mailers.containsKey(name)) {
            throw new IllegalStateException("Mailer with name '" + name + "' already exists!");
        }

        Mailer mailer = MailerBuilder.withTransportStrategy(mailerAccount.getTransportStrategy()).
                withSMTPServer(
                        mailerAccount.getServerAddress(),
                        mailerAccount.getServerPort(),
                        mailerAccount.getUsername(),
                        mailerAccount.getPassword()).
                buildMailer();

        mailers.put(name, mailer);
    }

    public Mailer getMailer(String name) {
        return mailers.get(name);
    }
}
