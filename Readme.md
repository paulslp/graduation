### 1. Information about database and application

| Database name     | Database type  | Application context|URL after launch tomcat without cargo-maven-plugin|
| :-------------    | :----------:   |:----------:        |:----------:                                      |
| graduation        | HSQLDB         |/graduation          |http://localhost:8080/graduation/rest/all/welcome |

### 2. Information about database structure and access to tables through services

| Table name     | Table type  | Field name list                              | Foreign key field names | Unique index field names         |Admin rights      | User rights                               |
| :------------- | :----------:| -----------:                                 | -----------:            | -----------:                     | -----------:     |-----------:                               |
| meals          | reference   | id, name                                     | no key                  | name                             | editing, viewing | no rights                                 |
| restaurants    | reference   | id, name                                     | no key                  | name                             | editing, viewing | no rights                                 |
| users          | reference   | id, name, email, password,registered,enabled | no key                  | email                            | editing, viewing | no rights                                 |
| user_roles     | table       | user_id,role                                 | user_id                 |no index                          | editing, viewing |no rights                                  |
| menu           | table       | id, restaurant_id, meal_id, date_menu, price | meal_id, restaurant_id  |restaurant_id, meal_id, date_menu | editing, viewing | viewing                                   |
| voting         | table       | id, user_id, restaurant_id, date_voting      | user_id, restaurant_id  |user_id, date_voting              | deleting,viewing |creating, updating before 11:00 and viewing|

The date_list table is needed to display reports (for example, displaying voting results for a period) and is filled in by the administrator using the sql-queries for database.

### 3.Service Information
#### 3.1. Meal service

| Method name |Parameters   |Http request type| URL                             | Definition       |Admin rights|User rights |
| :-----------| :----------:|:----------:     | :----------                     | :-----------     |:--------:  |:---------: |
| getAll      |no parameters|       GET       | /rest/admin/meal               |Get all meals     |      +     |      -     |                                
| get         |id           |       GET       | /rest/admin/meal/{id}          |Get meal by id    |      +     |      -     |    
| getByName   |name         |       GET       | /rest/admin/meal/filter?name={name}|Get meal by name  |      +     |      -     |    
| create      |meal         |       POST      | /rest/admin/meal               |Create new meal   |      +     |      -     |    
| update      |meal,id      |       PUT       | /rest/admin/meal/{id}          |Update meal by id |      +     |      -     |    
| delete      |id           |      DELETE     | /rest/admin/meal/{id}          |Delete meal by id |      +     |      -     |    

#### 3.2. Restaurant service

| Method name                     |Parameters       |Http request type| URL                                                                  | Definition                                                 |Admin rights|User rights |
| :-----------                    | :----------:    |:----------:     | :----------                                                          | :-----------                                               |:--------:  |:---------: |
| getAll                          |no parameters    |       GET       | /rest/admin/restaurant                                              |Get all restaurants                                         |      +     |      -     |                                
| get                             |id               |       GET       | /rest/admin/restaurant/{id}                                         |Get restaurant by id                                        |      +     |      -     |    
| getByName                       |name             |       GET       | /rest/admin/restaurant/filter/{name}                                |Get restaurant by name                                      |      +     |      -     |    
| create                          |restaurant       |       POST      | /rest/admin/restaurant                                              |Create new restaurant                                       |      +     |      -     |    
| update                          |restaurant,id    |       PUT       | /rest/admin/restaurant/{id}                                         |Update restaurant by id                                     |      +     |      -     |    
| delete                          |id               |      DELETE     | /rest/admin/restaurant                                              |Delete restaurant by id                                     |      +     |      -     |
| getRestaurantListWithMenuOnDate |startDate,endDate|       GET       | /rest/user/restaurant/filter?startDate={startDate}&endDate={endDate}|Get a list of restaurants that have a menu for a given date |      +     |      +     |

#### 3.3. User service

| Method name |Parameters   |Http request type| URL                                  | Definition       |Admin rights|User rights |
| :-----------| :----------:|:----------:     | :----------                          | :-----------     |:--------:  |:---------: |
| getAll      |no parameters|       GET       | /rest/admin/user                     |Get all users     |      +     |      -     |                                |
| get         |id           |       GET       | /rest/admin/user/{id}                |Get user by id    |      +     |      -     |    
| getByMail   |email        |       GET       | /rest/admin/user/filter?email={email}|Get user by email |      +     |      -     |    
| create      |user         |       POST      | /rest/admin/user                     |Create new user   |      +     |      -     |    
| update      |user,id      |       PUT       | /rest/admin/user/{id}                |Update user by id |      +     |      -     |    
| delete      |id           |      DELETE     | /rest/admin/user                     |Delete user by id |      +     |      -     |

Anonymous users can register by /profile/register URL 

#### 3.4. Menu service

| Method name             |Parameters                    |Http request type| URL                                                                                     | Definition                                           |Admin rights|User rights |
| :-----------            | :----------:                 |:----------:     | :----------                                                                             | :-----------                                         |:--------:  |:---------: |
| getBetween              |startDate,endDate             |       GET       | /rest/admin/menu/filter?startDate={startDate}&endDate={endDate}                         |Get a list of menus for a given period                |      +     |      -     |                                
| get                     |id                            |       GET       | /rest/admin/menu/{id}                                                                   |Get menu by id                                        |      +     |      -     |       
| create                  |menu                          |       POST      | /rest/admin/menu                                                                        |Create new menu                                       |      +     |      -     |    
| update                  |menu,id                       |       PUT       | /rest/admin/menu/{id}                                                                   |Update menu by id                                     |      +     |      -     |    
| delete                  |id                            |      DELETE     | /rest/admin/menu/{id}                                                                   |Delete menu by id                                     |      +     |      -     |
| getBetweenForRestaurant |restaurantId,startDate,endDate|       GET       | /rest/user/menu/filter/{restaurantId}?startDate={startDate}&endDate={endDate}|Get a list of menus for a given period for restaurant |      +     |      +     |

#### 3.5. Voting service

| Method name             |Parameters                    |Http request type| URL                                                                                   | Definition                                                                                      |Admin rights|User rights |
| :-----------            | :----------:                 |:----------:     | :----------                                                                           | :-----------                                                                                    |:--------:  |:---------: |
| get                     |id                            |       GET       | /rest/user/voting/{id}                                                                |Get voting by id                                                                                 |      +     |      +     |       
| create                  |restaurantId                  |       POST      | /rest/user/voting/{restaurantId}                                                      |Create new voting record                                                                         |      +     |      +     |    
| update                  |restaurantId,id               |       PUT       | /rest/user/voting/{restaurantId}/{id}                                                 |Update voting record by id with new restaurantId (if it is after 11:00 return error message)     |      +     |      +     |    
| delete                  |id                            |      DELETE     | /rest/admin/voting/{id}                                                               |Delete voting record by id                                                                       |      +     |      -     |
| deleteFromUser          |no parameters                 |      DELETE     | /rest/user/voting                                                                     |Delete voting record for current User on current Date (if it is after 11:00 return error message)|      +     |      +     |
| getBetween              |startDate,endDate             |       GET       | /rest/admin/voting/filter?startDate={startDate}&endDate={endDate}                     |Get a list of voting for a given period                                                          |      +     |      -     |
| getBetweenForCurrentUser|startDate,endDate             |       GET       | /rest/user/voting/filter?startDate={startDate}&endDate={endDate}                      |Get a list of voting for a given period for current User                                         |      +     |      +     |                                
| getBetweenForUser       |userId,startDate,endDate      |       GET       | /rest/admin/voting/filter/{userId}?startDate={startDate}&endDate={endDate} |Get a list of voting for a given period for specific user                                        |      +     |      -     |
| getVotingResult         |startDate,endDate             |       GET       | /rest/user/voting/result?startDate={startDate}&endDate={endDate}                         |Get a voting results for a given period for every restaurant                                     |      +     |      +     |


### 4.Typical scenarios
#### 4.1. Voting of user

| Service    |Method                         |Definition                                                             |CURL request
| :----------| :----------                   |----------:                                                            | :---------- 
| Menu       |getBetween                     |User receives information about the current menu of each restaurant    |`curl -s "http://localhost:8080/graduation/rest/user/menu/filter?startDate=2019-07-24&endDate=2019-08-25" --user user@yandex.ru:password`|
| Restaurant |getRestaurantListWithMenuOnDate|User get restaurant list on current date (set period)                  |`curl -s "http://localhost:8080/graduation/rest/user/restaurant/filter?startDate=2019-07-24&endDate=2019-08-25" --user user@yandex.ru:password`|
| Voting     |getBetweenForCurrentUser       |User receives voting data                                              |`curl -s "http://localhost:8080/graduation/rest/user/voting/filter?startDate=2019-07-24&endDate=2019-08-25" --user user@yandex.ru:password`|
| Voting     |create                         |User votes for a restaurant from a previously received list            |`curl -s -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/user/voting/100003 --user user@yandex.ru:password`|
| Voting     |getBetweenForCurrentUser       |User receives voting data                                              |`curl -s "http://localhost:8080/graduation/rest/user/voting/filter?startDate=2019-07-24&endDate=2019-08-25" --user user@yandex.ru:password`|
| Voting     |update                         |User votes for another restaurant                                      |`curl -s -X PUT -d '{"restaurantId":100004}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/user/voting/100003/100047 --user user@yandex.ru:password`|
| Voting     |getBetweenForCurrentUser       |User receives voting data                                              |`curl -s "http://localhost:8080/graduation/rest/user/voting/filter?startDate=2019-07-24&endDate=2019-08-25" --user user@yandex.ru:password`|

#### 4.2. Get voting results

| Service    |Method                         |Definition                                                             |CURL request
| :----------| :----------                   |----------:                                                            | :---------- 
| Voting     |getVotingResult                |User receives voting result for a period    |`curl -s "http://localhost:8080/graduation/rest/user/voting/result?startDate=2019-08-01&endDate=2019-08-25" --user user@yandex.ru:password`|



### 5. Curl samples for each service method(application deployed in application context `graduation`).
> For windows use `Git Bash`

#### get All Users
`curl -s http://localhost:8080/graduation/rest/admin/user --user admin@gmail.com:admin`

#### get User 100001
`curl -s http://localhost:8080/graduation/rest/admin/user/100001 --user admin@gmail.com:admin`

#### create User
`curl -s -X POST -d '{"name":"Admin4","email":"admin4@gmail.com","password":"12345","enabled":true,"roles":["ROLE_USER","ROLE_ADMIN"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/admin/user --user admin@gmail.com:admin`

#### register User
`curl -s -X POST -d '{"name":"user4","email":"user4@gmail.com","password":"12345"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/profile/register`

#### update User
`curl -s -X PUT -d '{"name":"Admin5","email":"admin5@gmail.com","password":"12345","enabled":true,"roles":["ROLE_USER","ROLE_ADMIN"]}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/admin/user/100002 --user admin@gmail.com:admin`

#### delete User
`curl -s -X DELETE http://localhost:8080/graduation/rest/admin/user/100002 --user admin@gmail.com:admin`

#### get User by email
`curl -s http://localhost:8080/graduation/rest/admin/user/filter?email=user@yandex.ru --user admin@gmail.com:admin`



#### get All Meals
`curl -s http://localhost:8080/graduation/rest/admin/meal --user admin@gmail.com:admin`

#### get Meal 100010
`curl -s http://localhost:8080/graduation/rest/admin/meal/100010 --user admin@gmail.com:admin`

#### get Meal not found
`curl -s http://localhost:8080/graduation/rest/admin/meal/0 --user admin@gmail.com:admin`

#### create Meal
`curl -s -X POST -d '{"name":"soup0"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/admin/meal --user admin@gmail.com:admin`

#### update Meal
`curl -s -X PUT -d '{"name":"tea0"}' -H 'Content-Type: application/json' http://localhost:8080/graduation/rest/admin/meal/100024 --user admin@gmail.com:admin`

#### get Meal by name
`curl -s http://localhost:8080/graduation/rest/admin/meal/filter?name=tea0 --user admin@gmail.com:admin`

#### delete Meal
`curl -s -X DELETE http://localhost:8080/graduation/rest/admin/meal/100024 --user admin@gmail.com:admin`


#### get All Restaurants
`curl -s http://localhost:8080/graduation/rest/admin/restaurant --user admin@gmail.com:admin`

#### get Restaurant 100005
`curl -s http://localhost:8080/graduation/rest/admin/restaurant/100005 --user admin@gmail.com:admin`

#### get Restaurant not found
`curl -s http://localhost:8080/graduation/rest/admin/restaurant/0 --user admin@gmail.com:admin`

#### create Restaurant
`curl -s -X POST -d '{"name":"restaurant0"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/admin/restaurant --user admin@gmail.com:admin`

#### update Restaurant
`curl -s -X PUT -d '{"name":"restaurant_without_menu00"}' -H 'Content-Type: application/json' http://localhost:8080/graduation/rest/admin/restaurant/100009 --user admin@gmail.com:admin`

#### get Restaurant by name
`curl -s http://localhost:8080/graduation/rest/admin/restaurant/filter?name=restaurant_without_menu00 --user admin@gmail.com:admin`

#### delete Restaurant
`curl -s -X DELETE http://localhost:8080/graduation/rest/admin/restaurant/100009 --user admin@gmail.com:admin`

#### get RestaurantList with menu on date
`curl -s "http://localhost:8080/graduation/rest/user/restaurant/filter?startDate=2019-07-24&endDate=2019-08-25" --user user@yandex.ru:password`


#### get MenuItem
`curl -s "http://localhost:8080/graduation/rest/admin/menu/100027" --user admin@gmail.com:admin`

#### getBetween MenuItems
`curl -s "http://localhost:8080/graduation/rest/user/menu/filter?startDate=2019-07-24&endDate=2019-08-25" --user user@yandex.ru:password` 

#### getBetweenForRestaurant MenuItems
`curl -s "http://localhost:8080/graduation/rest/user/menu/filter/100003?startDate=2019-07-24&endDate=2019-08-25" --user user@yandex.ru:password`

#### delete MenuItem
`curl -s -X DELETE "http://localhost:8080/graduation/rest/admin/menu/100025" --user admin@gmail.com:admin`

#### create MenuItem
`curl -s -X POST -d '{"dateMenu":"2019-07-23","price":200,"meal":{"id":100019},"restaurant":{"id":100003}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/admin/menu --user admin@gmail.com:admin`

#### update MenuItem 
`curl -s -X PUT -d '{"dateMenu":"2019-07-22","price":100,"meal":{"id":100019},"restaurant":{"id":100003}}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/admin/menu/100027 --user admin@gmail.com:admin`



#### get VoteItem for CurrentUser
`curl -s "http://localhost:8080/graduation/rest/user/voting/100046" --user user@yandex.ru:password`

#### getBetweenForCurrentUser VoteItems
`curl -s "http://localhost:8080/graduation/rest/user/voting/filter?startDate=2019-07-24&endDate=2019-08-25" --user user@yandex.ru:password`

#### delete VoteItem
`curl -s -X DELETE "http://localhost:8080/graduation/rest/admin/voting/100045" --user admin@gmail.com:admin`

#### create VoteItem
`curl -s -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/user/voting/100003 --user user@yandex.ru:password`

#### update VoteItem (working only before 11:00)
`curl -s -X PUT -d '{"restaurantId":100003}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/user/voting/100004/100047 --user admin@gmail.com:admin`

#### delete VoteItem from User

`curl -s -X DELETE "http://localhost:8080/graduation/rest/user/voting" --user user@yandex.ru:password`

#### getBetween VoteItems for all users
`curl -s "http://localhost:8080/graduation/rest/admin/voting/filter?startDate=2019-07-24&endDate=2019-08-25" --user admin@gmail.com:admin` 

#### getBetweenForUser VoteItems
`curl -s "http://localhost:8080/graduation/rest/admin/voting/filter/100000?startDate=2019-07-24&endDate=2019-08-25" --user admin@gmail.com:admin`

#### get Voting Result
`curl -s "http://localhost:8080/graduation/rest/user/voting/result?startDate=2019-07-24&endDate=2019-08-25" --user user@yandex.ru:password`
