# Performance Demo

Bauen und Starten

    mvn test

## JDBC Demo

Dieser Test verbindet sich mit einer MySQL DB.
- In Variante 1 wird das Connections-Objekt mit jeder Verbindung neu erzeugt.
- In Variante 2 wird das Connections-Objekt initial erzeugt und mehrmals benutzt.

Resultat:

    Reusing connection. Ran 10000 queries with 259150 ns per query.
    Recreating connection. Ran 10000 queries with 1956817 ns per query.


Der Test `JdbcDemoTest` geht davon aus, dass eine MySQL DB unter jdbc:mysql://localhost:3306/jpademo erreichbar ist.
Diese DB wurde mit dem Projekt https://github.com/juventusHF/spring-data-jpa aufgesetzt.
