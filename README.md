# CodeFellowship

## Overview

Building an app that allows users to create their profile on CodeFellowship.

## Lab: 16 - Spring Auth and Lab: 17 - Spring Authorization

- Cnfig contains :
  - UserDetailServiceImpl class
  - WebSecurityConfig calss


- Domain contains :
  - ApplicationUser class
  - Post class

 
- Infrastructure contains :

  - AppUserRepository interface
  - PostRepository interface

- Web contains
  - ApplicationController
  - HomeController


- Routes : 

  - http://localhost:8080/signup
  - http://localhost:8080/login
  - http://localhost:8080/users
  - http://localhost:8080/myProfile
  - http://localhost:8080/users/{id}
  - http://localhost:8080/post
