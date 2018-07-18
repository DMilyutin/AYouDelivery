package ru.youdelivery.Helperss;


public class Doc {

    private String _id;
    private String addressCustomer;
    private String addressForDriver;
    private String coastOrder;
    private String idCustomer;
    private String nameCustomer;
    private String nameForDriver;
    private String numberOfAddresses;
    private String phoneCustomer;
    private String phoneForDriver;
    private String statusOrder;
    private String timeFilingCustomer;
    private String nameOrder;
    private String idForWorkBalashiha;



    public Doc(){}

    public Doc(String addressCustomer, String addressForDriver, String coastOrder, String idCustomer,
               String nameCustomer, String nameForDriver, String numberOfAddresses, String phoneCustomer,
               String phoneForDriver, String statusOrder, String timeFilingCustomer, String nameOrder) {
        this.addressCustomer = addressCustomer;
        this.addressForDriver = addressForDriver;
        this.coastOrder = coastOrder;
        this.idCustomer = idCustomer;
        this.nameCustomer = nameCustomer;
        this.nameForDriver = nameForDriver;
        this.numberOfAddresses = numberOfAddresses;
        this.phoneCustomer = phoneCustomer;
        this.phoneForDriver = phoneForDriver;
        this.statusOrder = statusOrder;
        this.timeFilingCustomer = timeFilingCustomer;
        this.nameOrder = nameOrder;


    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    public String getAddressCustomer() {
        return addressCustomer;
    }

    public void setAddressCustomer(String addressCustomer) {
        this.addressCustomer = addressCustomer;
    }

    public String getAddressForDriver() {
        return addressForDriver;
    }

    public void setAddressForDriver(String addressForDriver) {
        this.addressForDriver = addressForDriver;
    }

    public String getCoastOrder() {
        return coastOrder;
    }

    public void setCoastOrder(String coastOrder) {
        this.coastOrder = coastOrder;
    }

    public String getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(String idCustomer) {
        this.idCustomer = idCustomer;
    }

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getNameForDriver() {
        return nameForDriver;
    }

    public void setNameForDriver(String nameForDriver) {
        this.nameForDriver = nameForDriver;
    }

    public String getNumberOfAddresses() {
        return numberOfAddresses;
    }

    public void setNumberOfAddresses(String numberOfAddresses) {
        this.numberOfAddresses = numberOfAddresses;
    }

    public String getPhoneCustomer() {
        return phoneCustomer;
    }

    public void setPhoneCustomer(String phoneCustomer) {
        this.phoneCustomer = phoneCustomer;
    }

    public String getPhoneForDriver() {
        return phoneForDriver;
    }

    public void setPhoneForDriver(String phoneForDriver) {
        this.phoneForDriver = phoneForDriver;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    public String getTimeFilingCustomer() {
        return timeFilingCustomer;
    }

    public void setTimeFilingCustomer(String timeFilingCustomer) {
        this.timeFilingCustomer = timeFilingCustomer;
    }

    public String getIdForWorkBalashiha() {
        return idForWorkBalashiha;
    }

    public void setIdForWorkBalashiha(String idForWorkBalashiha) {
        this.idForWorkBalashiha = idForWorkBalashiha;
    }
}

