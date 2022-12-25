# Restaurant Mate (Restaurant take-out management software)

## What will the application do?
The application will provide customers a menu to order take-out from and when they order and pick a time a new order will show up in the restaurant's internal food service application approx 10-15 minutes before the pick-up time. The order will also be specified that it is a take-out order and will also list the relevant details of the orders, such as, the item to be prepped, the time to pick-up, and the name of the customer. Once done the restaurant staff can check off that the item has been prepared.

On the customer side the customer will receive a summary of what they ordered and the sum total of the order. Moreover, on the customer side their will be a visual to display the current status of their order. Ultimately, once the food is done and bagged the application will send the customer a notification to pick up the food.

## Who will use it?
The primary user base for this application will be the restaurant industry and the people they serve. More specifically the people who will use it are:
- Managers and Shift leaders
- Kitchen staff
- Front of house staff
- Customers (viewing the status of their order)

## Why is this project of interest to you?

The reasoning for this project stems from my experience working in the restaurant industry, where I worked at the popular and fast-paced **_Cactus Club_** restaurant group. Although our software for managing orders in-house was good I felt that our internal take-out software needed improvements to improve the coordination between the kitchen and expo staff. I experienced this first hand as an **Expo**, where it was my job to coordinate all orders coming into the restaurant and I found that our software made it difficult to identify and prioritize sit down orders versus takeout orders. Therefore, my goal is to improve the experiences of guests and the make my former colleagues jobs easier. 


## User Stories

In the context of a restaurant management application: 

Restaurant 
- As a user I want to be able to create and remove orders from the current queue of orders for my restaurant
- As a user I want to be able to customize my menu
- As a user I want to be able to prioritize certain orders
- As a user I want to be able to modify my orders (items ordered and allergies)
- As a user I want to be able to know when the food is ready
- As a user I want to know how much my bill is for the food I ordered
- As a user I want to save the state of my restaurant
  - name
  - cuisine
  - Menu
  - Order queue 
- As a user I want to load my restaurant from file and continue using my restaurant with my saved data
  - name
  - cuisine
  - Menu
  - Order queue 

## Reflection
1. Refactoring Restaurant GUI
   - I would increase my restaurant gui class's cohesion by separating my GUI into its individual components, separating into different classes the visual components for different parts of my application such as the menu class, menu item class, order class, and restaurant class. Furthermore, I would make use of the Java Swing visibility feature instead of super-implanting a new GUI frame on-top of my existing frame.
2. Refactoring Event messages
   - I would decrease the coupling of my code by creating a single event message class that I could use in place of hard-coding in messages for new events as currently I am simply typing in the message that should be outputed instead of calling a method that would return a message to be inputted to the event log, this increases coupling as in the future if I wanted to change the format of the message I would have to change the message everywhere in my code.
3. Refactoring OrderForRestaurant
   - Currently, my OrderForRestaurant class has an association with my menu item class as it uses menu items as the input for its list of food ordered, however, this creates problems as menu items that aren't in the restaurant's menu can theoretically be added to an order which could create problems for my program. Therefore, given more time I would make OrderForRestaurant have an association with MenuForRestaurant class or with the Restaurant class and utilize the restaurant's menu to pick the menu items that can be inputed, thus, eliminating the problem of possibly choosing a menu item that does not exist. 

# UML Diagram
<img width="584" alt="Screen Shot 2022-12-02 at 9 24 37 AM" src="https://media.github.students.cs.ubc.ca/user/19552/files/e3a703eb-5eb6-400a-aa6d-419b259c1617">

