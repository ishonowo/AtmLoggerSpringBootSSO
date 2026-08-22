# Export the LIVE cert straight from the running LDAP server and re-import it
$certFile = "$env:TEMP\ldap-cert-latest.crt"

# Requires openssl on PATH, or run from Git Bash instead
& openssl s_client -connect localhost:10636 -showcerts 2>$null | `
    & openssl x509 -outform PEM > $certFile

keytool -delete -alias ldap-current -keystore src\main\resources\ldap-truststore2.jks -storepass changeit -noprompt 2>$null
keytool -import -alias ldap-current -file $certFile -keystore src\main\resources\ldap-truststore2.jks -storepass changeit -noprompt

Write-Host "LDAP truststore synced with current cert."