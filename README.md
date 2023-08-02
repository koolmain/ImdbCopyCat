
## The Details
This assessment is based on the popular website [IMDb](https://www.imdb.com/)
which offers movie and TV show information. They have kindly made their dataset
publicly available at [IMDb Datasets](https://www.imdb.com/interfaces/). Your
mission, should you choose to accept it, is to write a web application that can
fulfil the following requirements:

### Requirement #1 (easy):

IMDb copycat: Present the user with endpoint for allowing them to search by
movie’s primary title or original title. The outcome should be related
information to that title, including cast and crew.

### Requirement #2 (easy):

Top rated movies: Given a query by the user, you must provide what are the top
rated movies for a genre (If the user searches horror, then it should show a
list of top rated horror movies).

### Requirement #3 (difficult):

[Six degrees of Kevin
Bacon](https://en.wikipedia.org/wiki/Six_Degrees_of_Kevin_Bacon): Given a query
by the user, you must provide what’s the degree of separation between the person
(e.g. actor or actress) the user has entered and Kevin Bacon. 

## Setup

To speed up the development, we have provided a `docker-compose` file that
will run a [PostgreSQL](https://www.postgresql.org/) instance,
which you can start with the following command: `docker-compose up`. You can connect to
this instance from your application with the following values:

```
JDBC URL = jdbc:postgresql://localhost:5432/koolmain_imdb
Username = postgres
Password = postgres
```

The schema is defined within the file `schema.cql` in the `database-init` folder. There
you'll find what each column represents on every table, this is taken from
[IMDb interfaces](https://www.imdb.com/interfaces/).
