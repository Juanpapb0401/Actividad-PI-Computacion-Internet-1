# Actividad-PI-Computacion-Internet-1



Estimación de π mediante el Método de Monte Carlo en un Sistema Distribuido
Este proyecto implementa un sistema distribuido para estimar el valor de π (pi) utilizando el método de Monte Carlo. La solución distribuye las tareas entre varios nodos (trabajadores) coordinados por un nodo maestro, permitiendo calcular π con alta precisión al incrementar el número de puntos generados. La comunicación entre las entidades (Cliente, Maestro y Trabajadores) se realiza mediante ZeroC ICE (Internet Communications Engine), un middleware para desarrollo distribuido en Java.

Descripción del Proyecto
Método de Monte Carlo para Estimación de π
El método de Monte Carlo utiliza la generación de puntos aleatorios en un cuadrado y cuenta cuántos de estos caen dentro del círculo inscrito. La proporción entre los puntos dentro del círculo y el total de puntos es aproximadamente π/4, lo que permite calcular el valor de π.

Arquitectura del Sistema
El sistema sigue un modelo Cliente-Maestro-Trabajadores:

Cliente: Envia una solicitud al Maestro para calcular π con un número especificado de puntos y trabajadores.
Maestro: Coordina el trabajo distribuyendo la carga entre los Trabajadores y consolidando los resultados.
Trabajadores: Generan puntos aleatorios y cuentan cuántos caen dentro del círculo. Envían sus resultados al Maestro.


Requisitos
Java Development Kit (JDK) 8 o superior.
Gradle como herramienta de compilación.
ZeroC ICE 3.7.2 o superior (configurado como dependencia en build.gradle).
Configuración de Red: Asegúrate de que los dispositivos (si son múltiples) puedan comunicarse en la red y están configurados con direcciones IP adecuadas.


Configuración e Instalación
Clona el Repositorio:
cd <CARPETA_DEL_PROYECTO>

Genera las Clases a partir de PiEstimation.ice: Usa el compilador slice2java para generar los proxies de las interfaces:
slice2java PiEstimation.ice
Esto generará archivos como MasterPrx.java y WorkerPrx.java en los directorios correspondientes.

Compila el Proyecto con Gradle: Ejecuta el siguiente comando para compilar el proyecto, cada vez que se quiera correr una terminal:
./gradlew build

Ejecución del Proyecto

Inicia los Trabajadores: Cada trabajador se ejecuta así:
java -cp worker/build/libs/worker.jar WorkerServer

Inicia el Maestro: Usa el siguiente comando:
java -cp master/build/libs/master.jar MasterServer

Ejecuta el Cliente: Una vez que el Maestro y los Trabajadores estén en ejecución, ejecuta el Cliente para enviar una solicitud de cálculo de π.
java -cp client/build/libs/client.jar Client

El Cliente pedirá al Maestro que coordine una estimación de π con el número de puntos y trabajadores especificados en su configuración.

Prueba de Rendimiento (Opcional): Puedes ejecutar pruebas de rendimiento con diferentes configuraciones de puntos y trabajadores utilizando PerformanceTest.java.
./gradlew runPerformanceTest




