package pl.javastart.couponscalc;

public class CouponSelection {

    private Coupon coupon;
    private double totalPriceWithCoupon;

    public CouponSelection(Coupon coupon, double totalPriceWithCoupon) {
        this.coupon = coupon;
        this.totalPriceWithCoupon = totalPriceWithCoupon;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public double getTotalPriceWithCoupon() {
        return totalPriceWithCoupon;
    }

    @Override
    public String toString() {
        return "Z kuponem: "+coupon.getCategory()+" placisz za zakupy: "+totalPriceWithCoupon;
    }
}
