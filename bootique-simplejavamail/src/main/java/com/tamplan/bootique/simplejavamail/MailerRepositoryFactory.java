package com.tamplan.bootique.simplejavamail;

import java.util.Map;

public class MailerRepositoryFactory {

    private Map<String, MailerAccount> accounts;

    public void setAccounts(Map<String, MailerAccount> accounts) {
        this.accounts = accounts;
    }

    public MailerRepository createMailerRepository() {
        MailerRepository mailerRepository = new MailerRepository();

        accounts.forEach(mailerRepository::addMailer);

        return mailerRepository;
    }

}
