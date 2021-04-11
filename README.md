# Bookie
By Alexandr Khan

## Description:
Application for sport bets, providing functionality of betting on different outcomes of the match

## How it works:
* Register
* Activate via email
* Wait for admin to verify you
* Cash in
* Place bet on match (3 outcomes: Home, Draw, Away)
* After match is over, if your bet has won you will get paid

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
* Once he activates his account via email status changes to ACTIVATED
* After that user must provide passport scan for admin to VERIFY account
* Until account is verified user can not cash in & place bets 

## Role functional:
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
