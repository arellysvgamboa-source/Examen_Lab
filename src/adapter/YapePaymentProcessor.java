package adapter;

public class YapePaymentProcessor implements PaymentProcessor {

    @Override
    public void pay(double amount) {
        System.out.println("Pago con Yape: S/ " + amount);
    }
}
