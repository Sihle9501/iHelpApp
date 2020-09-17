# Spark Java Postgres setup

## Fork & clone this app

Fork and clone this app locally.

You can use this as a starting point for your Hackathon application.

You can remove the current remote Git repository using this command.

```
git remote remove origin
```

You can then link this to your own GitHub repository.

Ensure to add all your team members as collaborators on your projects GitHub repository.

## Run the app

Run the app in IntelliJ after running this command:

```
mvn clean install
```

This will install the application dependencies.

The tests will fail initially - you will need to configure the database following the instructions below.

### Database setup

Setup a local database using the instructions below.


Create  the database like this:

```
createdb spark_hbs_jdbi;
```

Or like this if you are on **Ubuntu**:

```
sudo -u postgres createdb spark_hbs_jdbi;
```

Ensure your local user is a postgres user and have access to the database.

Create a postgres user for your local user.

> **Note:** If your username is not **coder** use the appropriate local username instead.

```
sudo -u postgres createuser coder -P;
```

Ensure this user has access to the database you are using the postgres user:

```
sudo -u postgres psql;
```

Grant access like this:

```
grant all privileges on database spark_hbs_jdbi to coder;
```

Exit out of `psql` using:

```
\q
```

Now connect to the database like this:

```
psql spark_hbs_jdbi;
```

Create the users table like this:

```
CREATE TABLE IF NOT EXISTS users (
  id SERIAL PRIMARY KEY NOT NULL,
  first_name TEXT,
  last_name TEXT,
  email TEXT
);
```

## Heroku deployment

Deploy your application using Heroku.

Create a Heroku app for your web application

```
heroku create
```

Rename your Heroku application

```
heroku apps:rename <your-app-name-here>
```

Deploy your application to Heroku using this command:

```
mvn clean heroku:deploy
```

> **Note:**  ensure you setup the Heroku database as per the instructions below.

## Database setup

Create the database on Heroku using this command:

```
heroku addons:create heroku-postgresql:hobby-dev
```

Connect to the Heroku database using this command:

```
heroku pg:psql
```

Run the `./sql/database_script` on the Heroku database.

## Rename your database

Once this app is up and running it proves your local configuration is working.

You should now rename the database and application name. 
And make sure it's working with your own configuration.