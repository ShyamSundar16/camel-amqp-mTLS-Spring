# camel-amqp-mTLS-spring-example

#### RabbitMQ Server Configurations
RabbitMQ allows you to have a custom configuration file, which will be given priority over the default configurations. There are 2 mandatory steps to override the basic configurations.


1. The file name should be rabbitmq.conf
2. It should be present inside /etc/rabbitmq
3. Inside rabbitmq.conf, we can have the required configurations for rabbitmq server as well as management console.
4. Sample rabbitmq.conf which contains configurations for mTLS is given below.
```
loopback_users.guest = false
listeners.ssl.default = 5671
ssl_options.cacertfile = /path/to/ca.pem
ssl_options.certfile = /path/to/server.pem
ssl_options.fail_if_no_peer_cert = false
ssl_options.keyfile = /path/to/serverKey.pem
ssl_options.verify = verify_peer
management.ssl.port = 15671
management.ssl.cacertfile = /path/to/ca.pem
management.ssl.certfile = /path/to/server.pem
management.ssl.fail_if_no_peer_cert = false
management.ssl.keyfile = /path/to/serverKey.pem
management.ssl.verify = verify_peer
```
#### Generate Certificates for MTLS (Mutal Transport Layer Security)

Open the  [Certificate Generation Tool](generateCertificate.sh) and modify the [location of openssl.cnf](openssl.cnf) & location where your certificates need to be generated.

#### Configure Application Beans for MTLS
This example contains beans for both MTLS and non-SSL which is present in [applicationContext.xml](/src/main/resources/applicationContext.xml) & [applicationContext-Non-SSL.xml](/src/main/resources/applicationContext-Non-SSL.xml) respectively.

Provide the location of the generated certificates, and it's corresponding beans in [applicationContext.xml](/src/main/resources/applicationContext.xml) .

#### Docker for quick run (MTLS)
```
docker run --name docker-rabbit -p 5671:5671 -p 5672:5672 -p 5673:5673 -p 15671:15671 -p 15672:15672  -v /path/to/cert:/cert:Z -e RABBITMQ_SSL_CACERTFILE=/cert/ca.pem -e RABBITMQ_SSL_CERTFILE=/cert/server.pem -e RABBITMQ_SSL_KEYFILE=/cert/serverKey.pem -e RABBITMQ_SSL_VERIFY=verify_peer -e RABBITMQ_SSL_FAIL_IF_NO_PEER_CERT=true -e RABBITMQ_MANAGEMENT_SSL_CACERTFILE=/cert/ca.pem -e RABBITMQ_MANAGEMENT_SSL_CERTFILE=/cert/server.pem -e RABBITMQ_MANAGEMENT_SSL_KEYFILE=/cert/serverKey.pem -e RABBITMQ_MANAGEMENT_SSL_VERIFY=verify_peer -e RABBITMQ_MANAGEMENT_SSL_FAIL_IF_NO_PEER_CERT=true rabbitmq:3-management 
```

##### Help and contributions
If you hit any problem or have some feedback let me know at,
1. [LinkedIn Profile](https://www.linkedin.com/in/shaam-sundar-1405/)
2. [Gmail Account](shaamsundar16@gmail.com)
 