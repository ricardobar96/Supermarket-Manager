# Supermarket-Manager

Front-end made with typescript that offers to the users a GUI with which they can manage orders for existing products in a supermarket.

<br>

| [:camera: Screenshots](#screenshots) | [📖 Features](#features) | [🔑 Relationships](#relationships) |
| -------- | ----------- | ----------- |

<br>

## Screenshots

Coming soon.

<br>

## Features

* **Client Management:** Maintain a database of clients, storing their personal information.
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

* **Client**: Which stores client's personal information.
<br>

* **Product:** Which stores product's data.
<br>

* **Orders:** Which stores order's data.
<br>

* **Orders_detail:** It references the order and product on which is it based thanks to both product and order foreign keys.
<br>
