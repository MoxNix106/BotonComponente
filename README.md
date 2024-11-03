Abraham Cano Luis


1. La clase Captura se extiende de JPanel y se implementa ActionListener para que esta clase pueda responder a eventos, específicamente al clic del botón de captura. Se declara DIRECTORIO_CAPTURAS (directorio donde se almacenarán las capturas) y LIMITE_CAPTURAS (para limitar el número de capturas) y botonCaptura es un botón que iniciará la captura de pantalla al hacer clic.

![image](https://github.com/user-attachments/assets/476ec785-dee4-4a4f-8a90-1803d26f8848)

2. Al iniciar Captura, se revisa si existe el directorio capturas. Si no existe, se crea. Esto asegura que tengamos un lugar para guardar las imágenes. se inicializa con el texto "Capturar Ventana". Al agregar el ActionListener, se le dice al botón que, al hacer clic, debe llamar al método actionPerformed, que realiza la captura, se añade el botón al panel actual, de modo que aparezca en la interfaz.

![image](https://github.com/user-attachments/assets/cdcb0261-8118-4602-8390-df383b840357)

3. Llama al método capturarVentana() para hacer una captura de pantalla. Llama a verificarLimiteCapturas() para revisar si el número de capturas excede el límite y, de ser así, eliminar las más antiguas.

![image](https://github.com/user-attachments/assets/7fcc8ba7-1222-488e-93dd-d787a2df42ac)
 
4. El método usa SwingUtilities.getWindowAncestor(this) para encontrar el JFrame que contiene el panel Captura, ya que ese es el área a capturar, se define un Rectangle usando la posición y tamaño del JFrame, cubriendo así toda la ventana. Utilizando Robot, se captura la imagen de la ventana dentro del área definida. La captura se guarda en un archivo PNG en el directorio capturas con un nombre único (basado en la hora actual para evitar duplicados).

![image](https://github.com/user-attachments/assets/dcbec48d-b703-49fa-9803-550815b17fb6)

5. Verifica cuántas capturas existen en el directorio. Si el número de capturas excede el límite, se seleccionan las más antiguas para eliminar, basándose en la fecha de creación obtenida por el método obtenerTiempoCreacionArchivo.

![image](https://github.com/user-attachments/assets/4aa7bec1-fa80-429d-a7ef-03ef03f3d75d)
 
6. Devuelve su fecha de creación. Esto ayuda a ordenar los archivos en verificarLimiteCapturas, permitiendo identificar los más antiguos.

![image](https://github.com/user-attachments/assets/5abeb34a-13bf-4726-82af-16ff62b55054)
 
7. Borra el archivo en la ruta especificada. Esto se usa en verificarLimiteCapturas para eliminar las capturas antiguas.

![image](https://github.com/user-attachments/assets/d4fce1ed-38be-4423-a40e-0e526344c01d)
 
8. Crea un JFrame principal con título, tamaño y centrado en pantalla. Se instancia Captura y se agrega al JFrame para que el botón sea visible. Finalmente, la ventana se hace visible, permitiendo al usuario hacer capturas.

 ![image](https://github.com/user-attachments/assets/eb754c6f-f724-494a-ae9a-476132e05788)



Video Presentacion Componente

https://github.com/user-attachments/assets/d6a678e1-6ac2-4787-aef4-8ef036c1b5b4

