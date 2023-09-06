# Supermarket-Manager

Back-end made with Java using the Spring Framework. To manage its MySQL database I use the GUI-based application phpMyAdmin, inside which are stored the tables that manage information about the clients, products and orders of a supermarket.

<br>

| [:camera: Screenshots](#screenshots) | [📖 Features](#features) | [🔑 Relationships](#relationships) |
| -------- | ----------- | ----------- |

<br>

## Screenshots

Coming soon.

<br>

## Features

* **Client Management:** Maintain a database of clients, storing their personal information. 
* **Product Management:** Offers a comprehensive list of all products available.
* **Place orders:** Allows the user to place an order based on the products available.
* **User recognition:** Only allows users to place an order, whilst non-users can view the products available.

<br>

## Relationships

The following tables populate the app's database:

* **Client**: Which stores client's personal information.
* **Product:** Which stores product's data.
* **Orders:** Which stores order's data.
* **Orders_detail:** It references the order and product on which is it based thanks to both product and order foreign keys.
