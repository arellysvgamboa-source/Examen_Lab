import adapter.*;
import model.*;
import observer.*;
import service.*;
import strategy.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner teclado = new Scanner(System.in);

        Product producto1 = new Product("Laptop", 2500);
        Product producto2 = new Product("Mouse", 100);

        Product producto3 = new Product("Teclado", 150);

        Cart carrito = new Cart();
        int opcion;
        do {

            System.out.println("\n------ TIENDA VIRTUAL ------ ");

            System.out.println("\t1. Agregar Laptop");
            System.out.println("\t2. Agregar Mouse");
            System.out.println("\t3. Agregar Teclado");
            System.out.println("\t4. Finalizar compra");
            System.out.println("\t5. Salir");

            System.out.print("Seleccione una opcion: ");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1:

                    carrito.addProduct(producto1);
                    System.out.println("Laptop agregada al carrito ...");
                    break;

                case 2:
                    carrito.addProduct(producto2);
                    System.out.println("Mouse agregado al carrito ...");
                    break;

                case 3: carrito.addProduct(producto3);
                    System.out.println("Teclado agregado al carrito ...");break;

                case 4:
                    double total = carrito.calculateTotal();
                    System.out.println( "\nTotal original: S/ " + total);

                    OrderService orderService = new OrderService();

                    System.out.println("\nSeleccione descuento:");

                    System.out.println("1. Sin descuento");

                    System.out.println("2. Descuento 10%");

                    System.out.println("3. Descuento fijo S/20");

                    int opcionDescuento = teclado.nextInt();

                    DiscountStrategy strategy;

                    switch (opcionDescuento) {

                        case 1:
                            strategy = new NoDiscountStrategy();
                            break;

                        case 2:
                            strategy = new PercentageDiscountStrategy(10);
                            break;

                        case 3:
                            strategy = new FixedAmountDiscountStrategy(20);
                            break;

                        default:
                            strategy = new NoDiscountStrategy();
                    }

                    orderService.setDiscountStrategy(strategy);

                    System.out.println("\nSeleccione metodo de pago:");

                    System.out.println("1. PayPal");

                    System.out.println("2. Tarjeta");

                    System.out.println("3. Yape");

                    int opcionPago = teclado.nextInt();

                    PaymentProcessor paymentProcessor;

                    switch (opcionPago) {

                        case 1:
                            ExternalPayPalService payPalService = new ExternalPayPalService();
                            paymentProcessor = new PayPalAdapter(payPalService);
                            break;

                        case 2:
                            paymentProcessor = new CreditCardPaymentProcessor();
                            break;

                        case 3:
                            paymentProcessor = new YapePaymentProcessor();
                            break;

                        default:
                            paymentProcessor = new CreditCardPaymentProcessor();
                    }

                    orderService.setPaymentProcessor(paymentProcessor);

                    orderService.addObserver(new EmailNotificationObserver());
                    orderService.addObserver(new InventoryObserver());
                    orderService.addObserver(new AdminNotificationObserver());
                    orderService.processOrder(total);
                    break;

                case 5:
                    System.out.println("Gracias por usar la tienda <3");
                    break;

                default:
                    System.out.println("Opcion invalida");
            }

        } while (opcion != 5);

        teclado.close();
    }
}