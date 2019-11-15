# Database Primer

This project serves as an introduction to interacting with a database in an enterprise environment. As such, we use tools such as Maven and Liquibase.

## Getting Started

These instructions will get you up and running on your local PC. Sometimes we take our tool chains for granted but once in place they allow us to be quite productive. In this project we introduce a handful of tools that professional developers keep in their "tool kit."

### Prerequisites

Since this is about Structured Query Language (SQL), one of the first things we will need access to is a database. In the "old" days we would have to either install a developer edition of a database server or convince a Database Administrator to let us use one of theirs! With the advent of containerization, we can bring a server of our own up almost immediately! All without having to install a bunch of dependencies on our PC.

#### Docker

In this case, we are using Docker and a [PostgreSQL image](https://hub.docker.com/_/postgres?tab=description). Docker is fairly easy to install but for more guidance we have provided information in the [Docker section](https://github.com/gkhays/db-primer/wiki/Docker-for-Windows) of the [wiki](https://github.com/gkhays/db-primer/wiki). So be sure to check it out.

We prefer `docker-compose` because it is suitable for orchestration and reduces the number of parameters and options at invocation time. Using it, bring up an instance of PostgreSQL.

```bash
docker-compose up -d db
```

For the curious, a recipe is located in the `docker-compose.yml` located in the root of this project.

**Optional**: For those desiring more control, the same outcome can be achieved by using Docker directly.

```bash
docker pull postgres:10.5-alpine
docker run -d -p 5432:5432 postgres:10.5-alpine
```

#### Java

The project requires a Java JDK so that Maven may function correctly. We recommend the [Zulu Community OpenJDK](https://www.azul.com/downloads/zulu-community/?&architecture=x86-64-bit&package=jdk) Java 8 (LTS) version.

#### Maven

Maven is an imperative build tool that enforces a certain structure on Java software projects. It is favored by enterprise software development in order to facilitate repeatable, predictable builds. It also features a rich ecosystem of plugins, several of which we employ in this project. Detailed instruction for installing it are under the [Maven section](https://github.com/gkhays/db-primer/wiki/Maven) in the [wiki](https://github.com/gkhays/db-primer/wiki).

Create the database. If the database already exists it will be dropped. Then the tables are generated using [Liquibase](https://www.liquibase.org/) changesets.

### Installing

The first step is to install the database. This is done using the following Maven invocation from a command line prompt.

```bash
mvn clean install -Ddb.create
```

There are many ways to interact with a database in the capacity of an administrator. We will briefly explore two: the first using the pgAdmin 4 graphical user interface; the second using the `usql` command line interface.

Bring up an instance of `pgAdmin 4`.

```bash
docker-compose up -d pgadmin
```

Connect to the database.

![Create Server](doc/images/create-server.png)

Insert data into the `accesspoint` table.

```sql
INSERT INTO accesspoint (essid, bssid, vendor, channel)
VALUES ('HoneyPot', 'c4:6e:1f:0c:82:03', 'TP-LINK TECHNOLOGIES CO. LTD.', 4);

INSERT INTO accesspoint (essid, bssid, vendor, channel)
VALUES ('Demo', '80:2a:a8:5a:fb:2a', 'Ubiquiti Networks Inc.', 11);

INSERT INTO accesspoint (essid, bssid, vendor, channel)
VALUES ('BELL456', '44:e9:dd:4f:c2:7a', 'Sagemcom Broadband SAS', 6);
```

Display all the rows in the table.

```sql
SELECT * FROM accesspoint;
```

In addition to pgAdmin, I am using [usql](https://github.com/xo/usql) to illustrate a command line based approach.

```bash
usql postgres://postgres@localhost:5432/devices?sslmode=disable
```

```sql
pg:postgres@localhost:5432/devices=> select * from accesspoint;
 id | essid    | bssid             | vendor                        | channel
----+----------+-------------------+-------------------------------+---------
  1 | HoneyPot | c4:6e:1f:0c:82:03 | TP-LINK TECHNOLOGIES CO. LTD. |       4
  2 | Demo     | 80:2a:a8:5a:fb:2a | Ubiquiti Networks Inc.        |      11
  3 | BELL456  | 44:e9:dd:4f:c2:7a | Sagemcom Broadband SAS        |       6
(3 rows)
```
