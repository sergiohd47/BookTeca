FROM java:8

WORKDIR /app/

# Copiamos el JAR de nuestra aplicación a la imagen Docker
COPY target/ServicioInterno-0.0.1-SNAPSHOT.jar .

# Corremos el archivo JAR
CMD ["java", "-jar", "ServicioInterno-0.0.1-SNAPSHOT.jar"]