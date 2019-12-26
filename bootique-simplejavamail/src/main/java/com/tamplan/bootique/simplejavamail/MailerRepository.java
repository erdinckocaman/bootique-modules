package com.tamplan.bootique.simplejavamail;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MailerRepository {
    
    private static Logger logger = LoggerFactory.getLogger(MailerRepository.class);

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

        logger.info("Adding mailer with account, details={}", mailerAccount);

        Mailer mailer = MailerBuilder.withTransportStrategy(mailerAccount.getTransportStrategy()).
                withSMTPServer(
                        mailerAccount.getServerAddress(),
                        mailerAccount.getServerPort(),
                        mailerAccount.getUsername(),
                        mailerAccount.getPassword()).
                withDebugLogging(mailerAccount.isDebugMode()).
                buildMailer();

        mailers.put(name, mailer);
    }

    public Mailer getMailer(String name) {
        return mailers.get(name);
    }
}
