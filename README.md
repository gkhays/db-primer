# Database Primer

This project serves as an introduction to interacting with a database in an enterprise environment. As such, we use tools such as Maven and Liquibase.

## Getting Started

Bring up an instance of PostgreSQL.

```bash
docker-compose up -d db
```

Create the database. If the database already exists it will be dropped. Then the tables are generated using [Liquibase](https://www.liquibase.org/) changesets.

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
