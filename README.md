# mountain-problem
Application structure:

water-problem - parent pom with dependency management
water-problem-service-api - API for SolverService, our main service with incapsulated algorithm
water-problem-service - EJB module with SolverService itself
water-problem-presentation - Application Presentation layer, consists of 1 REST Service which calls incapsulates SolverService
water-problem-ear - Application assembly. Deployable to Enterprise Container

Structure could seem a bit redundant, but from my perspective it is not true. In the future, when our APP will grow, such approach simpilfys
technical releases, and provides nice decoupling which could be useful under certain circumstances (e.g make some remote calls). 

Application was tested on WildFly 9.0.2.Final and on WildFly 8.2.0.Final

HOW TO USE:
When deployed, APP will provide REST interface for you. getWaterVolume GET method which requires "surface" request parameter - a coma separated string. Example of usage:

http://localhost:8080/solver-presentation/api/solver/getWaterVolume?surface=2,0,1,2,4

If string will have wrong format you will get 400 HTTP Status Code.

Test Approoach:

Application has 2 sets of tests based on Arquillian and Arquillian-Warp, they use Arquillian Embedded WildFly 9.0.2.Final.
First Test covers algorithm correctness. Second covers REST Contorller. 
Tests not claim to cover all and to be comprehensive. They are more for demonstration. But, completely useful.
Test for Algorithm correctness could be easily substituted with JUnit (+ Mockito if needed).

Algorithm itself is a very lightweight and has complexity O(n).
Regarding memory(Approximatelly):
5 int variables * 4byte = 20
Integer[] (4byte + 16byte object overhead + 8byte reference overhead + 4byte object padding) * N_elements = 32byte * N_elements

It's based on two pointers which scanning our array from both sides and meet each other.

To be honest - this is not my implementation. 
I've had this exercise solved a year ago and my variant had O(2n) approach, but later i've found such solution. 
