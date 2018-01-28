package pl.javastart.couponscalc;


import java.util.*;

public class PriceCalculator {

    private static double round(double price){ //szukalem gotowych metod, ale ciagle cos nie chcialo grac, stad takie moje urywane zaokraglanie

        String number = String.valueOf(price);
        String[] split = number.split("\\.");
        String beforeDot = split[0];
        String afterDot = split[1];
        if(afterDot.length() > 2) {
            afterDot = afterDot.substring(0, 2);
        }
        String result = beforeDot+"."+afterDot;
        return Double.parseDouble(result);
    }


    public double calculatePrice(List<Product> pr, List<Coupon> coupons) {
        if (pr == null){
            return 0;
        }
        else if (coupons == null){
            double result = 0;
            for (Product product : pr) {
                result+=product.getPrice();
            }
            return result;
        }
        List<CouponSelection> resultList = new ArrayList<>();
        List<Product> products = pr;
        double priceToCompare = 0;
        for (Product product : products) {
            priceToCompare+=product.getPrice();
        }

        for (Coupon coupon : coupons) {
            if (coupon.getCategory() == null){
                double priceAbsoluteCoupon = useAbsoluteCopon(products, coupon);
                resultList.add(new CouponSelection(coupon,priceAbsoluteCoupon));
            }
            else {
                double priceAfterCoupon = 0;
                for (Product product : products) {
                    if (coupon.getCategory() == product.getCategory()) {
                        priceAfterCoupon += useCoupon(product, coupon);
                    }
                    if (coupon.getCategory() != product.getCategory()){
                        priceAfterCoupon+= product.getPrice();
                    }
                }
                resultList.add(new CouponSelection(coupon,priceAfterCoupon));
            }


        }
        double result = priceToCompare;
        for (CouponSelection couponSelection : resultList) {
            if (couponSelection.getTotalPriceWithCoupon() < result){
                result = couponSelection.getTotalPriceWithCoupon();
            }
        }

        return result;
    }

    private double useCoupon(Product product, Coupon coupon){
        double result = 0;
        result+= product.getPrice()-(product.getPrice()*coupon.getDiscountValueInPercents()/100);
        result = round(result);
        return result;
    }

    private double useAbsoluteCopon(List<Product> pr,Coupon coupon){
        double result = 0;
        for (Product product : pr) {
            result+= product.getPrice()-(product.getPrice()*coupon.getDiscountValueInPercents()/100);
        }
        result = round(result);
        return result;
    }

}
