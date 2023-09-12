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

![super1](https://github.com/ricardobar96/Supermarket-Manager/assets/73242474/2a4d2708-e3e1-42bf-a069-27c65a555098)


![catalog](https://github.com/ricardobar96/Supermarket-Manager/assets/73242474/efce19d7-442a-4e75-96e1-2bb1901a9b4b)


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
