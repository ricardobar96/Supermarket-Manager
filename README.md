# Supermarket-Manager

**Front-end** made with **React** and **Typescript** that offers a GUI with which users can manage orders for existing products in a supermarket.
<br>
<br>
**Back-end** made with **Java** using the **Spring Framework**.
<br>
<br>
To manage its **MySQL database** I use the GUI-based application **phpMyAdmin**, inside which are stored the tables that handle information about the clients, products and orders of a supermarket.

<br>

| [:camera: Screenshots](#screenshots) | [📖 Features](#features) | [🔑 Relationships](#relationships) |
| -------- | ----------- | ----------- |

<br>

## Screenshots

![super3](https://github.com/ricardobar96/Supermarket-Manager/assets/73242474/e9d5b9ae-652a-4609-a99f-21393f1e4315)


![super1](https://github.com/ricardobar96/Supermarket-Manager/assets/73242474/d77f6111-fc01-4685-9ee2-ff4f9298c0ad)


![catalog](https://github.com/ricardobar96/Supermarket-Manager/assets/73242474/4b9df867-1d51-4a08-965c-b472ff20cff9)

<br>

## Features

* **Client Management:** Keeps a record of all clients and their personal information.
<br>
 
* **Product Management:** Offers a comprehensive list of all products available.
<br>

* **Place orders:** Allows the user to place an order based on the products available.
<br>

* **User recognition:** Only allows users to place an order, whilst non-users can view the products available.
<br>

<br>

## Relationships

The following tables populate the app's database:
<br>
<br>
* **Client**: Which stores client's personal information.
<br>

* **Product:** Which stores product's data.
<br>

* **Orders:** Which stores order's data.
<br>

* **Orders_detail:** It references the order and product on which it is based thanks to both product and order foreign keys.
<br>
