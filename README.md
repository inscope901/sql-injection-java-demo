Open folder sql-injection-demo by IntelliJ IDEA and Run.

Open endpoint http://localhost:8080/insecure/1 in Browser.
In database have SQL query: "select * from employee where id = " + id (id here is 1).
Response return : 'Jonathan: Developer'

When inject id: 1 OR 1=1 will SQL query is select * from employee where id = 1 OR 1=1 ,return all records employee in database
http://localhost:8080/insecure/1%20OR%201=1

Response:

```
Jonathan: Developer
Lee: Team Leader
Phuong: Developer
Alice: Tester
```

Open secure endpoint: http://localhost:8080/secure/1
That endpoint uses Spring Data JPA extracted data from JpaRepository

'Jonathan: Developer'

However,when inject 1 OR 1=1

http://localhost:8080/secure/1%20OR%201=1

Response contain error:

Whitelabel Error Page
This application has no explicit mapping for /error, so you are seeing this as a fallback.
