# Summer Shop (Web-store of summer clothes)

Link to site to view: https://summer-shop-v1.vercel.app/

Web application built using microserver architecture. The backend is written in **Spring** (**Java**), and the frontend is 
written in **React** (**TypeScript**).

# Functional

User capabilities:

* Create your own account
* Search for products, sort and filter them
* Adding items to cart, editing this cart
* View selection history
* View recommendations

Administrator features:

* Add, edit and delete products
* View statistics on views and purchases of products based on certain characteristics
# Implementation Details

The project is built on a microserver architecture. The backend is written in the **Spring framework**, the **Java 17** 
programming language. The following technologies were used: **Spring Web**, **Spring Security**, **Spring Data JPA** 
(**Hibernate**). How RDBMS was used **PostgreSQL**.

The backend is built as a **RESTful API**, that is, it receives HTTP requests from the frontend server and sends a 
response. Authentication of the implementation using JWT tokens, which are created by the backend server and must be 
added to the headers of all requests that require authorization.

Viewing products is also available to an unauthorized user, but to operate the shopping cart or select recommendations, 
you must register/log in. To create an account, you will need to indicate the email address to which the letter will 
be sent to confirm the data. Distribution is implemented using **Java Mail Sender**.

![image](https://drive.google.com/uc?export=view&id=1wd2fktkwLkXkbLp-_DGVa4baJneTxpEe)

You can sort and filter products. Implemented using queries with many predicates to the RDBMS using the **Criteria API** 
built into **Hibernate**.

![image](https://drive.google.com/uc?export=view&id=1uLJjjzwdnZzmr0tn68hhpz-Ry4T9zvdZ)

The database also stores the browsing history for a specific user and, based on this information, makes recommendations 
for the user using the K8 algorithm.

![image](https://drive.google.com/uc?export=view&id=1kKFeQwfNilfgRejcq_pB63XlZozVjwnk)

The product you like can be added to the cart for further purchase. In the cart, you can review information about the 
selected products, as well as change the quantity (if there is enough product in stock), sizes (if they are in stock) 
and delete unnecessary products.

![image](https://drive.google.com/uc?export=view&id=1c0v0_oLd9E7w_dJVm7ChUsiFs8UibWC2)

If the user is authorized with the administrator role, he will be given access to the administrative panel, where he 
can view purchase statistics, orders placed, and also edit information about products.

![image](https://drive.google.com/uc?export=view&id=1nkypmbI42QXE18vbxpdangITVjLXd2-t)

# How to Build

The backend:

* Download the project from package `backend`
* Fill in empty fields in the `application.properties` file; These are data about the database and the mailing domain
* Build maven using `mvnw.cmd` or `mvnw`
* Run the project

The frontend:

* Download the project from package `frontend`
* Open directory on console
* Write first `npm i` and after that `npm run dev`

# Pre Requisites

* Local server for the *PostgreSQL* DBMS
* Local domain for sending e-mails


