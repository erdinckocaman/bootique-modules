package com.tamplan.bootique.examples.services;

import com.tamplan.bootique.simplejavamail.MailerRepository;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

import java.util.Objects;

public class SampleService {

    private final MailerRepository mailerRepository;

    public SampleService(MailerRepository mailerRepository) {
        Objects.requireNonNull(mailerRepository);

        this.mailerRepository = mailerRepository;
    }

    public void getSomething() {
        System.out.println("SampleService#getSomething called");
    }

    public void sendMail() {
        System.out.println("SampleService#sendMail called");

        Mailer mailer = mailerRepository.getMailer("test");

        Email email = EmailBuilder.startingBlank().from("CronMaker", "cronmaker@gmail.com").to("kocamane@hotmail.com").withSubject("Deneme").withPlainText("Icerik").buildEmail();

        //mailer.sendMail(email);

    }
}
