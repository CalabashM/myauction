package com.shop.utils;

public class AuctionPriceException extends Exception{
    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AuctionPriceException(String message) {

        this.message = message;
    }

    private String message;
}
