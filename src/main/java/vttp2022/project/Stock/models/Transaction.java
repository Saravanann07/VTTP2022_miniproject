package vttp2022.project.Stock.models;

import java.util.Date;

public class Transaction {
    
    private Integer transactionId;
    private Date purchaseDate;
    private String symbol;
    private String companyName;
    private Integer quantity;
    private Double stockPrice;
    private Double totalPrice;
    private Double stockStatus;
   

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
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
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
    public Double getStockPrice() {
        return stockPrice;
    }
    public void setStockPrice(Double stockPrice) {
        this.stockPrice = stockPrice;
    }
    public Double getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getStockStatus() {
        return stockStatus;
    }
    public void setStockStatus(Double stockStatus) {
        this.stockStatus = stockStatus;
    }

    
}
