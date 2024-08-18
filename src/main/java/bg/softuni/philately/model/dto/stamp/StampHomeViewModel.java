package bg.softuni.philately.model.dto.stamp;

import java.util.List;

public class StampHomeViewModel {
    private List<MyStampsDTO> myStamps;
    private List<OfferedStampsDTO> offeredStamps;
    private List<MyPurchasesDTO> myPurchases;
    private List<MyWishlistDTO> myWishlist;

    public StampHomeViewModel(List<MyStampsDTO> myStamps,
                              List<OfferedStampsDTO> offeredStamps,
                              List<MyPurchasesDTO> myPurchases,
                              List<MyWishlistDTO> myWishlist) {
        this.myStamps = myStamps;
        this.offeredStamps = offeredStamps;
        this.myPurchases = myPurchases;
        this.myWishlist = myWishlist;
    }

    public List<MyStampsDTO> getMyStamps() {
        return myStamps;
    }

    public void setMyStamps(List<MyStampsDTO> myStamps) {
        this.myStamps = myStamps;
    }

    public List<OfferedStampsDTO> getOfferedStamps() {
        return offeredStamps;
    }

    public void setOfferedStamps(List<OfferedStampsDTO> offeredStamps) {
        this.offeredStamps = offeredStamps;
    }

    public List<MyPurchasesDTO> getMyPurchases() {
        return myPurchases;
    }

    public void setMyPurchases(List<MyPurchasesDTO> myPurchases) {
        this.myPurchases = myPurchases;
    }

    public List<MyWishlistDTO> getMyWishlist() {
        return myWishlist;
    }

    public void setMyWishlist(List<MyWishlistDTO> myWishlist) {
        this.myWishlist = myWishlist;
    }
}
