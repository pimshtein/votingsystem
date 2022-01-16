Restaurant Voting system
=
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.  

The task is:  

Build a voting system for deciding where to have lunch.

*  2 types of users: admin and regular users
*  Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
*  Menu changes each day (admins do the updates)
*  Users can vote on which restaurant they want to have lunch at
*  Only one vote counted per user
*  If user votes again the same day:
    * If it is before 11:00 we assume that he changed his mind.
    * If it is after 11:00 then it is too late, vote can't be changed
*  Each restaurant provides a new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it (better - Swagger).

P.S.: Make sure everything works with latest version that is on github :)
P.P.S.: Assume that your API will be used by a frontend developer to build frontend on top of that.

### Realization:  
### [Demo](https://pimshtein-votingsystem.herokuapp.com/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config) (heroku)

Authorize:  
* admin:  
admin@mail.com admin
* user:  
user@mail.com password  

### Usecases:  
1. Admin can add the restaurant with the menu (`POST /api/v1/restaurants/`)  
2. Admin can edit the restaurant with the menu (`PUT /api/v1/restaurants/`)  
3. Admin can delete the restaurant with the menu (`DELETE /api/v1/restaurants/`)  
4. User can vote at the restaurant (one vote of the day `POST /api/v1/votes/`) User can vote a lot of the time, if the time is before 11.00 on the day.  
5. User and Admin can view votes to the restaurants (`GET /api/v1/votes/by-restaurant/`)  
6. User and Admin can view all restaurants with menus (`GET /api/v1/restaurants/`) with pagination  
7. Admin can edit users (`CRUD /api/v1/admin/users/`)