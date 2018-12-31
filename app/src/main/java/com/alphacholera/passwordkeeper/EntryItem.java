package com.alphacholera.passwordkeeper;

public class EntryItem {
    private String date;
    private String websiteName;
    private String username;
    private String password;

    EntryItem(String date, String websiteName, String username, String password) {
        this.date = date;
        this.websiteName = websiteName;
        this.username = username;
        this.password = password;
    }

    String getDate() {
        return date;
    }

    String getWebsiteName() {
        return websiteName;
    }

    String getUsername() {
        return username;
    }

    String getPassword() {
        return password;
    }
}
