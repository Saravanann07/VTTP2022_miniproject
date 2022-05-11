package vttp2022.project.Stock.models;

import java.util.Date;

public class Transaction {
    
    private Integer transactionId;
    private Date purchaseDate;
    private String ticker;
    private String companyName;
    private Integer quantity;
    private Float sharePrice;
    private Float totalPrice;
    public Integer getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(Integer transactionId) {
        this.transactionId = transactionId;
    }
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public String getTicker() {
        return ticker;
    }
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Float getSharePrice() {
        return sharePrice;
    }
    public void setSharePrice(Float sharePrice) {
        this.sharePrice = sharePrice;
    }
    public Float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    
}
