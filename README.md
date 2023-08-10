# 🚀 Java Technical Test 🚀

We have a small Spring boot application that currently fetches Tweets from a file via a REST HTTP endpoint.

## 🏗️ Running and building the code
The code is built with Java 11 (but if there's features you like in later versions, then you could ugprade this!) and can be built and run using Maven.


```./mvnw spring-boot:run```

**Testing the response:**

```curl localhost:8080/tweets```


## 📄 What we would like you to do?
First, take some time to familiarise yourself with this application.

We would then like you improve some parts of this, based on your skills and experiences. 

**You don't need to spend a long time on this. One coding session (2-3 hours) is plenty, and our reviewers are understanding that not everything may be completed!**  

### 📕 Connect the application to a database
You probably would have spotted that the application is using a file as way of reading tweets - which you'd agree isn't ideal!

Choose a database technology of your choosing, and load the tweets into it on start up. The endpoint should then be configured to read from this database.

Given the time constraints, something like an embedded SQL database like [H2 or HSQL](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#features.sql.datasource.embedded) would suffice. 

But feel free to try one of the storage technologies we use: 
- PostgresSQL 
- MongoDB 

### 🧠 Add some features to the API 
We regularly have to add new features to our APIs, which we look to do with good API design. Feel free to implement some features below, or get creative and come up with your own!

Example features:
- The ability to insert a Tweet into the database via a POST request.
- Adding the ability to specify a time range in the `GET /tweets` request

### 🔧 Add some test coverage
Writing good integration tests is a key part of our development routine, as we want certainty when we are pushing to production.

We don't expect you to add full test coverage of this application - but see if you can write an integration test that verifies some behaviour.

### 🚢 Get this production ready
We run our services with Docker on Kubernetes, and so our build process outputs Docker images that are then run. 

See if you can get this web application building into Docker images that are then runnable 🐳 


## 📫 Submitting the code

We take pride in keeping our Git history organised and easy to follow for the benefit of our code reviewers, so please organise your commits to reflect this.

Once complete, zip up your directory (including any .git files!) and sent it to the recruiter you've been in contact with, including a summary of what you have done. 

## ✅ How will my work be evaluated?

Rome wasn't built in a day, and we are fair in our review given the time constraints. But some of the things we are looking for:
- Is the code clean, well organised and reusable
- Have you used appropriate techniques, libraries and tools. Whilst the bleeding edge might be exciting, we want our applications to stand the test of time. 

Should you proceed to the next stage of the process, we often discuss the approach taken and what you may have done when given more time. 

Good luck! 


