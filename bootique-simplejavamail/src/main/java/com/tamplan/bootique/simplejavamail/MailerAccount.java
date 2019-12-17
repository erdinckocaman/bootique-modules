package com.tamplan.bootique.simplejavamail;

import org.simplejavamail.mailer.config.TransportStrategy;

public class MailerAccount {

    private String serverAddress;
    private Integer serverPort;
    private String username;
    private String password;
    private TransportStrategy transportStrategy;

    public MailerAccount() {
        transportStrategy = TransportStrategy.SMTP;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public Integer getServerPort() {
        return serverPort;
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public TransportStrategy getTransportStrategy() {
        return transportStrategy;
    }
}
