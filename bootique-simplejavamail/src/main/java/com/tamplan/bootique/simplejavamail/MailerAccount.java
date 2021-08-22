package com.tamplan.bootique.simplejavamail;

import org.simplejavamail.api.mailer.config.TransportStrategy;

public class MailerAccount {

    private String serverAddress;
    private Integer serverPort;
    private String username;
    private String password;
    private TransportStrategy transportStrategy;
    private boolean debugMode;

    public MailerAccount() {
        transportStrategy = TransportStrategy.SMTP;
    }

    @Override
    public String toString() {
        return "MailerAccount{" +
                "serverAddress='" + serverAddress + '\'' +
                ", serverPort=" + serverPort +
                ", username='" + username + '\'' +
                ", password='" + "*" + '\'' +
                ", transportStrategy=" + transportStrategy +
                '}';
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

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }

    public boolean isDebugMode() {
        return debugMode;
    }
}
