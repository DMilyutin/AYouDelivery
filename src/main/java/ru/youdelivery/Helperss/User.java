package ru.youdelivery.Helperss;

public class User {

    private  String _id;
    private  String email;
    private  Boolean isBlocked;
    private  String username;
    private  String idCustomer;

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public User(String id, String email, Boolean isBlocked, String username, String idCustomer) {
        this._id = id;
        this.email = email;
        this.isBlocked = isBlocked;
        this.username = username;
        this.idCustomer = idCustomer;

    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Boolean isBlocked) {
        this.isBlocked = isBlocked;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
