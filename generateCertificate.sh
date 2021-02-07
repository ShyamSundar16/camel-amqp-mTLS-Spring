export workingDir=/home/administrator/Deployment/Certificates/IP
export OPENSSL=/etc/pki/tls
openssl genrsa -out "$workingDir/ca.key" 1024
openssl req -new -config "$OPENSSL/openssl.cnf" -key "$workingDir/ca.key" -out "$workingDir/ca.csr"
openssl x509 -req -days 365 -in "$workingDir/ca.csr" -signkey "$workingDir/ca.key" -out "$workingDir/ca.crt"

openssl genrsa -out "$workingDir/server.key" 1024
openssl req -new -config "$OPENSSL/openssl.cnf" -key "$workingDir/server.key" -out "$workingDir/server.csr"
openssl x509 -req -days 365 -CA "$workingDir/ca.crt" -CAkey "$workingDir/ca.key" -CAcreateserial -in "$workingDir/server.csr" -out "$workingDir/server.crt" -extensions v3_req -extfile "$OPENSSL/openssl.cnf"
#openssl x509 -req -days 3650 -in "$workingDir/server.csr" -signkey "$workingDir/server.key" -out "$workingDir/server.crt" -extensions v3_req -extfile "$OPENSSL/openssl.cnf"
openssl pkcs12 -export -name localhost -in "$workingDir/server.crt" -inkey "$workingDir/server.key" -CAfile "$workingDir/ca.crt" -out "$workingDir/server.p12"
#volanteServer

openssl genrsa -out "$workingDir/client.key" 1024
openssl req -new -config "$OPENSSL/openssl.cnf" -key "$workingDir/client.key" -out "$workingDir/client.csr"
openssl x509 -req -days 365 -CA "$workingDir/ca.crt" -CAkey "$workingDir/ca.key" -CAcreateserial -in "$workingDir/client.csr" -out "$workingDir/client.crt"
openssl pkcs12 -export -in "$workingDir/client.crt" -inkey "$workingDir/client.key" -CAfile "$workingDir/ca.crt" -out "$workingDir/client.p12"

cd /usr/java/jdk1.8.0_131/bin
./keytool -import -alias client -keystore "$workingDir/client.jks" -file "$workingDir/client.crt"
#openssl pkcs12 -export -name localhost -in "$workingDir/client.crt" -inkey "$workingDir/client.key" -CAfile "$workingDir/ca.crt" -out "$workingDir/client1.p12"
#volanteClient
./keytool -import -alias VolanteCA -keystore "$workingDir/volanteCA.jks" -file "$workingDir/ca.crt"
#volanteCA
./keytool -importkeystore -destkeystore "$workingDir/server.keystore.jks" -srckeystore "$workingDir/server.p12" -srcstoretype PKCS12 -alias localhost
#volanteServerKeystore
./keytool -keystore "$workingDir/server.truststore.jks" -alias CARoot -import -file "$workingDir/ca.crt"
#volanteServerTrustStore
