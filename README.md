# Bookie
By Alexandr Khan

## Description:
Application for sport bets, providing functionality of betting on different outcomes of the match

## Features:
* Patterns: Singleton, Command, Proxy, Factory
* Servlet
* JSP/JSTL/JS/CSS
* MYSQL
* Maven
* Localization: RU/EN
* Email sender via SMTP
* Connection pool
* XSS protection
* Double validation
* Scheduled tasks
* Custom tags
* 3 user roles
* 4 user status

## User status notes:
* After registration user status becomes NOT ACTIVATED
* Once he verifies his account via email status changes to ACTIVATED
* After that user must provide passport scan for admin to verify account
* Until account is verified user can't cash in & place bets 

Function | ADMIN | USER | GUEST
---------| --------------|----------------|---------------
Log in |   |   | *
Register |   |   | *
Log out | * | * |
Change locale | * | * | * |
User list | * |   |   
Match list | * | * | * |
Block/Unblock/Verify user | * |
Add new match | * |
Update match time | * |
Upload passport scan | * | * |
Place bet | * | * |
Personal betting history | * | * |
Personal messages | * | * |
Cash in | * | * |
Activate account |   |   | *
