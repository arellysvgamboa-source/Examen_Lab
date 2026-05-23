# Sistema de Compras con Patrones de Diseño

Proyecto desarrollado en Java que implementa un sistema de compras aplicando múltiples Design Patterns, con el objetivo de lograr un código limpio, modular y escalable.

---

## Patrones de Diseño utilizados

- Strategy Pattern → Manejo de diferentes tipos de descuento
- Adapter Pattern → Integración de métodos de pago
- Observer Pattern → Notificaciones automáticas

---

## Estructura del Proyecto

```plaintext
src/
│
├── model/
│   ├── Product.java                        (Clase)
│   └── Cart.java                           (Clase)
│
├── strategy/
│   ├── DiscountStrategy.java               (Interfaz)
│   ├── NoDiscountStrategy.java             (Clase)
│   ├── PercentageDiscountStrategy.java     (Clase)
│   └── FixedAmountDiscountStrategy.java    (Clase)
│
├── adapter/
│   ├── PaymentProcessor.java               (Interfaz)
│   ├── ExternalPayPalService.java          (Clase)
│   ├── PayPalAdapter.java                  (Clase)
│   ├── CreditCardPaymentProcessor.java     (Clase)
│   └── YapePaymentProcessor.java           (Clase)
│
├── observer/
│   ├── OrderObserver.java                  (Interfaz)
│   ├── EmailNotificationObserver.java      (Clase)
│   ├── InventoryObserver.java              (Clase)
│   └── AdminNotificationObserver.java      (Clase)
│
├── service/
│   └── OrderService.java                   (Clase)
│
└── Principal.java                          (Clase)
```
---

## ¿Cómo funciona?

El sistema permite:

1. Seleccionar un tipo de descuento (Strategy)
2. Elegir un método de pago (Adapter)
3. Registrar observadores (Observer)
4. Procesar la compra mediante `OrderService`

---
## Explicación del Sistema

### Product

Representa los productos disponibles en la tienda virtual.
Cada producto contiene:

- nombre
- precio

---

### Cart

Administra los productos agregados por el usuario.

Sus funciones principales son:

- agregar productos
- calcular el total de la compra

---

### OrderService

Es la clase principal del sistema y actúa como
el núcleo de la aplicación.

Se encarga de:

- aplicar descuentos
- procesar pagos
- enviar notificaciones

Además, integra los tres patrones de diseño:

- Strategy
- Adapter
- Observer

---

## Flujo del Sistema

El sistema funciona siguiendo este flujo:

```plaintext
Cart → Strategy → Adapter → Observer
```

1. El carrito calcula el total de la compra
2. Strategy aplica el descuento seleccionado
3. Adapter procesa el método de pago
4. Observer notifica automáticamente la compra

---

## Menú 

El sistema utiliza un menú por consola
que permite al usuario:

- agregar productos al carrito
- seleccionar descuentos
- elegir métodos de pago
- confirmar compras

---
## Autor

Alumno: Villena Gamboa Arellys Judith

ID: 000292454