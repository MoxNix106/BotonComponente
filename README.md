Explicacion General de Componentes
 
1. La clase Captura se extiende de JPanel (componente gráfico de Java) y 
   implementa ActionListener para que esta clase pueda responder a eventos,
   específicamente al clic del botón de captura.
Se declaran constantes como DIRECTORIO_CAPTURAS (directorio donde se
   almacenarán las capturas) y LIMITE_CAPTURAS (para limitar el número de 
   capturas).
   
![image](https://github.com/user-attachments/assets/fbaf5f86-078a-4bcf-bb61-a5602e93425b)


2. Creación del directorio: Al instanciar Captura, se revisa si existe el directorio 
capturas. Si no existe, se crea. Esto asegura que tengamos un lugar para 
guardar las imágenes. 
El botón se inicializa con el texto "Capturar 
Ventana". Al agregar el ActionListener, al hacer clic llama al método actionPerformed, que realiza la captura.
Se añade el botón al JPanel, para que 
aparezca en la interfaz.

![image](https://github.com/user-attachments/assets/1ce1ff81-5bc6-42c3-80ee-0b130ba32886)

3. Llama al método capturarVentana() para hacer una captura de pantalla. 
Llama a verificarLimiteCapturas() para revisar si el número de capturas 
excede el límite si sobrepasa elimina las más antiguas. 

![image](https://github.com/user-attachments/assets/6c313743-6b81-4f07-a522-f249c6ca217a)


4. El método usa SwingUtilities.getWindowAncestor(this) 
sirve para encontrar el JFrame que contiene el panel. 
Utilizando Robot, se captura la imagen de la ventana.
La captura se guarda en un archivo PNG en el 
directorio capturas con un nombre único que esta basado en la hora de la captura para evitar duplicados

![image](https://github.com/user-attachments/assets/bb5d3b93-a43b-4bf8-8f4d-64c62be00970)


5. Verifica cuántas capturas existen en el directorio.
Si el número de capturas excede el límite, se 
seleccionan las más antiguas para eliminar, basándose en la fecha de 
creación obtenida por el método obtenerTiempoCreacionArchivo.

![image](https://github.com/user-attachments/assets/8700c45b-56c0-4a60-9c84-03e066c0d529)


6. Devuelve la fecha de 
creación en milisegundos. Esto ayuda a ordenar los archivos en 
verificarLimiteCapturas, permitiendo identificar los más antiguos.

![image](https://github.com/user-attachments/assets/eccb58e4-6496-45a7-92b5-ad3d8f79aa1a)


7. Borra el archivo en la ruta especificada. Esto se usa en 
verificarLimiteCapturas para eliminar las capturas antiguas.

![image](https://github.com/user-attachments/assets/b4e7a2b8-7cbd-4922-b92e-53907e0cd9e8)

8. Crea un JFrame principal con título, tamaño y centrado en pantalla.
Se agrega al JFrame para que el botón sea visible.
Finalmente, la ventana se hace visible, permitiendo al usuario hacer capturas.

![image](https://github.com/user-attachments/assets/c32f119a-3b6f-4809-9f01-9dad9b0fb90d)


