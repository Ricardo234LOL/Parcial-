import matplotlib.pyplot as plt

tamanios = [100, 500, 1000, 10000, 20000]
tiempos_merge = [1.696271131093611, 4.321845898880389, 2.1615275099325917, 57.67228993479342, 263.5509199325235]
tiempos_quick = [1.0007141774596835, 2.787508810377182, 9.284704696995815, 46.356770251134385, 218.00025732221303]

plt.figure(figsize=(10, 6))
plt.plot(tamanios, tiempos_merge, marker='o', label="Merge Sort (Java)")
plt.plot(tamanios, tiempos_quick, marker='s', label="Quick Sort (Java)")
plt.xlabel("Tamaño del archivo")
plt.ylabel("Tiempo de ejecución (segundos)")
plt.title("Comparación de tiempos: Merge Sort vs Quick Sort (Java)")
plt.legend()
plt.grid(True)
plt.savefig("Comparacion_Sorts_Java.png")
plt.show()
