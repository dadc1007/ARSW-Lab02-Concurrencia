# 🧵 Laboratorio 2 - Programación concurrente, condiciones de carrera y sincronización de hilos.
**Escuela Colombiana de Ingeniería Julio Garavito**  
**Curso:** Arquitectura de Software (ARSW)

---

## 👥 Integrantes del grupo
- Vicente Garzón Ríos
- Daniel Alejandro Díaz Camelo

---

## 📌 Descripción
Este laboratorio introduce los conceptos de **programación concurrente** en Java, abordando la creación y coordinación de hilos, así como la identificación y solución de **condiciones de carrera** mediante mecanismos de **sincronización** aplicados a casos prácticos.


---

## 📂 Parte I - Creación, puesta en marcha y coordinación de hilos.

## 📌 Punto 1: Ejecución con un solo hilo

En la primera versión, el programa resuelve el problema utilizando **un único hilo**.  
La observación con **VisualVM** muestra:

- **Uso de CPU:** ~13% en promedio.
- **Threads activos (live peak):** alrededor de 18, de los cuales solo **1 corresponde al cálculo de primos** (los demás son internos de la JVM).

📷 *Evidencia (VisualVM)*
<p align="center">
  <img src="img/media/parte1/image1.png" alt="Uso con un hilo" width="400"/>
</p>


---

## 📌 Punto 2: Ejecución con tres hilos

En la segunda versión, el programa fue modificado para resolver el mismo problema dividiéndolo en **tres partes**, asignando cada una a un **hilo independiente**.

Con esta modificación, la observación con **VisualVM** muestra:

- **Uso de CPU:** ~30% en promedio.
- **Threads activos (live peak):** hasta 21, debido a la creación de los 3 `PrimeFinderThread` más los hilos internos de la JVM.

📷 *Evidencia (VisualVM)*  
<p align="center">
  <img src="img/media/parte1/image2.png" alt="Uso con tres hilo" width="400"/>
</p>



---

## 📌 Punto 3: Ejecución con control de pausa y reanudación

En esta versión, se agregó un **mecanismo de control** mediante la clase `Controller`, que permite **pausar y reanudar la ejecución de los hilos**.

El funcionamiento es el siguiente:
- A los **5 segundos** de ejecución, se detienen todos los hilos (`pause()`), mostrando el número de primos encontrados hasta ese momento.
- El programa queda en **espera de que el usuario presione ENTER**.
- Una vez presionado, se invoca a `resume()` y los hilos continúan desde donde quedaron.

Con esta implementación, la observación con **VisualVM** muestra:

- **Uso de CPU:** se observa un consumo normal durante los primeros 5 segundos, que luego cae a casi **0%** durante la pausa.
- **Threads activos (live peak):** alrededor de 21, los mismos que en el punto anterior, pero en **estado WAITING** mientras están pausados.

📷 *Evidencia (VisualVM)*
<p align="center">
  <img src="img/media/parte1/image3.png" alt="Uso con pausa y reanudación" width="400"/>
</p>
