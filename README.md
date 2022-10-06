# API

How to start the API application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/ea-agile-sprint-api-1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

## Generate RSA keys

1. `ssh-keygen -b 2048 -t rsa -f /path/to/repo/ea-agile-sprint-api/secret-key -C "enter comment here"`
1. the comment is not necessary
2. enter a passphrase (make sure to remember it!) - or don't
3. export PRIVATE_KEY=`cat secret-key` (make sure the backticks are around the 'cat [...] ')

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
