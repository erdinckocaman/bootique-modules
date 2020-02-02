package com.tamplan.bootique.jdbi;

public class JdbiProperties {

    private boolean autoCommit;

    public JdbiProperties() {
        autoCommit = true;
    }

    @Override
    public String toString() {
        return "JdbiProperties{" +
                "autoCommit=" + autoCommit +
                '}';
    }

    public void setAutoCommit(boolean autoCommit) {
        this.autoCommit = autoCommit;
    }

    public boolean isAutoCommit() {
        return autoCommit;
    }
}
