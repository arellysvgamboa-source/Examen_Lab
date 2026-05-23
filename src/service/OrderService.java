package service;

import adapter.PaymentProcessor;
import observer.OrderObserver;
import strategy.DiscountStrategy;

import java.util.*;

public class OrderService {

    private DiscountStrategy discountStrategy;
    private PaymentProcessor paymentProcessor;
    private List<OrderObserver> observers;

    public OrderService() {
        observers = new ArrayList<>();
    }

    // STRATEGY - Cambia descuento sin tocar el sistema
    public void setDiscountStrategy(DiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    // ADAPTER - Permite usar distintos metodos de pago en la misma interfaz
    public void setPaymentProcessor(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    // OBSERVER - Permite modificar los objetos cuando la compra esta activa
    public void addObserver(OrderObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {

        for (OrderObserver observer : observers) {
            observer.update("Compra realizada");
        }
    }

    // PROCESAR ORDEN
    public void processOrder(double total) {
        double finalTotal = discountStrategy.applyDiscount(total);
        System.out.println("Total con descuento: S/. " + finalTotal);
        paymentProcessor.pay(finalTotal);
        System.out.println("Compra confirmada por S/ " + finalTotal);
        notifyObservers();
    }
}