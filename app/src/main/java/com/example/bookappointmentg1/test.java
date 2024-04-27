package com.example.bookappointmentg1;

public class test {
    private String username,password,emailaddress,isactive;
    public test(){
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

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getIsactive() {
        return isactive;
    }

    public void setIsactive(String isactive) {
        this.isactive = isactive;
    }

    public test(String username, String password, String emailaddress, String isactive){
        this.username=username;
        this.password=password;
        this.emailaddress=emailaddress;
        this.isactive=isactive;
    }
}
