# MULTITIER ENTERPRISE APPLICATION 

## Overview

This multitier java enterprise application provides "Login" and "Admin Panel" functionalities. It is composed of two modules: enterprise (EJB) module and web module (REST). EJB module contains "Customer" entity that was mapped by JPA from Derby database. The entity is manipulated by abstract generic model that performs CRUD operations. The model is extended bycustomer façade in form of "Session Bean", which exposes all CRUD operations provided my abstract service model to the web module.

Web module contains a managed bean that exploits and utilizes all services provided by EJB module through customer façade. Customer managed bean serves a role of key controller that manages most of the operations and actions that take place inside the "Admin Panel". In order to combine the functionalities of EJB module with REST module, another entity was deployed.

Student entity was used to perform ORM procedure via JPA. Its task is to expose its data and properties via Restful services. Student entity is served through the abstract model class that is extended by Restful resource. In order to process XML data fetched via REST service a Restful client was employed. Student Rest Client processes all data obtained or posted from Rest resource. All Restful resources all configured in "ApplicationConfig" class, which describes the resources.

All data is presented by JSF 2.1 framework and powered by PrimeFaces library for more advanced and powerful UI styling. Theapplication follows MVC model, where view is presented by two .xhtml files "login.xhtml" and "admin.xhtml". The first file is used to provide view functionalities for login operations while the second one presents all data available in adminpanel. Login authorization and filtering are controlled by Phase Listener class that processes all requests and prevents page jumps and unauthorized access.

The file ("admin.xhtml") presents the data in admin panel. It is designed so that it provides "West", "North", "East" and "Center" layout decorated by PrimeFaces utilities such as "Calender", "Server Time", "Dock" and "Gallery". Business logic operations are performed asynchronously through AJAX, which allows to update specific components without page refreshing.
Customer table is presented in the "Center" layout unit. Data is provided by EJB module. CRUD operations are carried out inan elegant and asynchronous way. For better interactivity and artificial intelligence most operations are accompanied by dynamic and live messages, that create a sense of live communication between user and server. All user inputs are processedby interactive dialogs with grid layouts.

Restful services can be tested within the REST block menu. User can retrieve all objects from Student database via REST and
show the output table in the dialog. CRUD operations are processed by REST client. To demonstrate the Restful GET requests few commands were implemented and presented in REST menu. User can GET all students and display them in a native XML format. Student can be retrieved by ID and presented in XML format. Finally issuing "Count" command returns the number of students present in the database.

## Technologies

The following technologies, properties and languages were used in this project:
* Enterprise Java Beans
* Enterprise Session Beans
* Java Persistence API
* Java Persistence Unit
* Java Server Faces
* Prime Faces
* Java Managed Beans
* Restful Services
* Restful Clients
* Restful Configuration Files
* Abstract Java Classes
* Java Façade Classes
* Data Table Models
* Cascading Style Sheets
* JavaScript
* Images and Icons
* XML and JSON mechanisms
* Supplementary Utilities and Listeners
* AJAX

## Extra Details

Login functionality works as follow: if user tries to request “secret” page it will be redirected to login page by Phase Listener in case if user is unauthorized. User will get authorized only if he passes the authentication and session is created. If session is missing user is handled appropriately by navigation rules. When user logs out session is invalidated and user is redirected to login page. This mechanism protects against page jumps.

Other aspects that should be clarified are Generic Sorter and Table Data Models. Generic Sorter is used as a utility to sort the content of the output table. The content can be sorted by columns. The functionality of this utility is demonstrated on Customer’s Table, where table data can be sorted by each column criteria.

Finally, Data Table Model is needed for sorting and selection properties. In my case I extended library’s LazyDataModel and overrode necessary methods. Those methods are required for exposing the selection listeners and sorting properties.

## Demonstration

The demonstration of this project is available in demo folder.

## Author

**Yury Shukhrov**

## License

Copyright © 2019, [Yury Shukhrov](https://github.com/yuryshukhrov).
Released under the [MIT License](LICENSE).


