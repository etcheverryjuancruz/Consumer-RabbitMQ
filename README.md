#  RabbitMQ - Basic Consumer

Este proyecto es una prueba de concepto para entender cómo funciona RabbitMQ. Basicamente, consiste en 2 ejemplos que leen mensajes de una cola.

# Instalacion 
- Clonar el proyecto :  `git clone https://github.com/etcheverryjuancruz/Consumer-RabbitMQ.git`
- Importar el proyecto como un 'Proyecto Maven'.
# Ejemplos

**Leer mensaje de una cola:**
Ejecutar como una Application Java el archivo `Consumer.java` En este caso, se lee el mensaje 'Hello world' de una cola.

**Leer mensajes en una cola de tareas:**
En este ejemplo, la idea es crear "workers" que consumen tareas que son publicadas en la cola de tarea. Para crear un worker se debe ejecutar la `Worker.java`
