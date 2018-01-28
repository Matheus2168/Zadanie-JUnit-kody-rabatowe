package pl.javastart.couponscalc;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class PriceCalculatorTest {

    @Test
    public void shouldReturnZeroForNoProducts() {
        // given
        PriceCalculator priceCalculator = new PriceCalculator();

        // when
        double result = priceCalculator.calculatePrice(null, null);

        // then
        assertThat(result, is(0.));
    }

    @Test
    public void shouldReturnPriceForSingleProductAndNoCoupons() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        // when
        double result = priceCalculator.calculatePrice(products, null);

        // then
        assertThat(result, is(5.99));
    }

    @Test
    public void shouldReturnPriceForSingleProductAndOneCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 5.99, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(Category.FOOD, 20));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result, is(4.79));
    }

    @Test
    public void shouldPickFoodCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Masło", 10, Category.FOOD));
        products.add(new Product("Baton", 20, Category.FOOD));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(null, 20));
        coupons.add(new Coupon(Category.FOOD, 50));
        coupons.add(new Coupon(Category.CAR, 90));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result, is(15.0));
    }

    @Test
    public void shouldPickNullCoupon() {

        // given
        PriceCalculator priceCalculator = new PriceCalculator();
        List<Product> products = new ArrayList<>();
        products.add(new Product("Laptop", 2000, Category.ENTERTAINMENT));
        products.add(new Product("Zabawka", 50, Category.ENTERTAINMENT));
        products.add(new Product("Hot-Dog", 5, Category.FOOD));
        products.add(new Product("Cukierki", 10, Category.FOOD));
        products.add(new Product("Szyba", 380, Category.CAR));

        List<Coupon> coupons = new ArrayList<>();
        coupons.add(new Coupon(null, 20));
        coupons.add(new Coupon(Category.FOOD, 50));
        coupons.add(new Coupon(Category.CAR, 90));

        // when
        double result = priceCalculator.calculatePrice(products, coupons);

        // then
        assertThat(result, is(1956.0));
    }

}