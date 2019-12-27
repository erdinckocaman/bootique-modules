package com.tamplan.bootique.examples.services;

import com.tamplan.bootique.simplejavamail.MailerRepository;
import com.tamplan.bootique.threadpool.ThreadPoolRepository;
import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;

import java.util.Objects;
import java.util.concurrent.ExecutorService;

public class SampleService {

    private final MailerRepository mailerRepository;
    private final ThreadPoolRepository threadPoolRepository;

    public SampleService(MailerRepository mailerRepository, ThreadPoolRepository threadPoolRepository) {
        Objects.requireNonNull(mailerRepository);
        Objects.requireNonNull(threadPoolRepository);

        this.mailerRepository = mailerRepository;
        this.threadPoolRepository = threadPoolRepository;
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

    public void addTaskToThreadPool() {
        ExecutorService testThreadPool = threadPoolRepository.getExecutor("test");

        testThreadPool.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("Task submitted!");
            }
        });

    }
}
