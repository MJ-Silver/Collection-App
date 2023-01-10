package com.iqcollections;
/*
    Code Attribution 1:
    Source: YouTube
    Source URL link: https://youtu.be/9JdbgoYgCyA?list=PLHQRWugvckFr9H2Mo4hyre1wQHglSRake
    Title Page/Video: How to Insert Data in Firebase Realtime Database | Android Firebase Part 3
    Author name/tag/channel: CodingZest
    Author channel/profile url link: https://www.youtube.com/c/CodingZest
 */

// this class stores all the constructors as well as the get and set methods for the wishlist function
public class wishClass {
    private String wishId;
    private String wishName;
    private String wishDesc;
    private String wishPrice;


    public wishClass() {

    }

    public wishClass(String wishId, String wishName, String wishDesc, String wishPrice) {
        this.wishId = wishId;
        this.wishName = wishName;
        this.wishDesc = wishDesc;
        this.wishPrice = wishPrice;
    }

    public String getWishId() { return wishId; }

    public void setWishId(String wishId) { this.wishId = wishId; }

    public String getWishName() {
        return wishName;
    }

    public void setWishName(String wishName) {
        this.wishName = wishName;
    }

    public String getWishDesc() {
        return wishDesc;
    }

    public void setWishDesc(String wishDesc) {
        this.wishDesc = wishDesc;
    }

    public String getWishPrice() {
        return wishPrice;
    }

    public void setWishPrice(String wishPrice) {
        this.wishPrice = wishPrice;
    }


}
