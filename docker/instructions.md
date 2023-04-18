### Anleitung zum erstellen von Docker Stacks

Je Datenbanktyp wird ein Docker Stack erstellt, welcher die Datenbank (DB) und ein passendes Administrationstool wie phpmyAdmin oder Adminer enthält.

Für jede DB gibt es einen USER = root und das PASSWORT = geheim.
Somit müssen wir uns immer nur diese Zugangsdaten merken.

## Anleitung: Erstellen der Stacks

1. Stelle sicher, das Docker Desktop auf deinem Rechner installiert ist und auch läuft!

    2. Öffne das Terminal

        3. Navigiere in dieses Verzeichnis ../generic_database_client/docker

            4. Navigieren in das Unterverzeichnis, welches den Namen der gewünschten DB enthält

                5. Gib im Terminal folgenden Befehl ein:
    
                    $ docker-compose up -d

Die benötigten Dateien werden heruntergeladen und installiert. Nach der Installation siehst du den Stack in Docker Desktop und kannst diesen aufklapen, um die einzelnen Container zu sehen.
Je nach DB ist ein anderer Port für den Zugriff ausgewählt. In einem Webbrowser deiner Wahl kannst du bei den laufenden Containern mit den folgenden URLs auf die Administrationstools zugreifen:

    MySQL:      http://localhost:8080
    MariaDB:    http://localhost:8081
    Postgres:   http://localhost:8082

## Einlesen der Test-Daten

In diesem Verzeichnis ist eine Datei "test-data-500.sql" abgespeichert. Diese enthält eine Tabelle mit 9 Spalten und 500 Zeilen.

Diese kann beliebig oft über die Administrationstools in die unterschiedlichen DBs importiert werden.