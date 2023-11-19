package pj21.conectado_bbdd;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;

public class PJ21CONECTADO_BBDD {
    
    public static void main(String[] args) {
        // TODO code application logic here
        
//OJO..tengo que importar el jar mysql (me permite conectar a la BBDD) ir Libraries//clic derecho ADD JAR. seleccionar fichero mysql
    try{
            Class.forName("com.mysql.jdbc.Driver"); // uso del driver mysql
            //Ahora establezco la conexion, utilizando los datos  del servidor como BD, usuario y contraseña
            Connection conexion = DriverManager.getConnection("jdbc:mysql://127.0.0.1/cursos_java", "user_curso", "Curso1234*");
            //Preparo una peticion a la base de datos
            Statement peticion = conexion.createStatement();
            //Ejecuto la peticion
            ResultSet resultado = peticion.executeQuery("SELECT * FROM Cursos");
            while(resultado.next())
            {
            System.out.println(resultado.getString(3));
            
            
//////////////////////////////////////////////////////////
// mostraremos los resultados en imagenes
        int ancho = 800;                                                        //ANCHO DE LA IMAGEN CREADA
        int alto = 400;                                                         //ALTO DE LA IMAGEN CREADA
        
                                              
        BufferedImage imagencacheada = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_RGB); //CREO LA IMAGEN CON LOS DATOS DECLARADOS ANTES
        Graphics2D graficos = imagencacheada.createGraphics();                  //DIGO QUE DENTRO DE LA IMAGEN VOY A PINTAR COSAS
        /////////////PUEDES PINTAR////////////////////////////
        graficos.setColor(Color.white);                                         //SE ESTABLECE LA IMAGEN DE COLOR BLANCO
        graficos.fillRect(0,0,ancho,alto);                                      //SE ESTABLECE LAS MEDIDAS DE LA IMAGEN QUE TENDRA EL COLOR BLANCO    
              
        
        graficos.setColor(Color.black);                                         //DIGO EL COLOR DE LO PRIXIMO QUE SE DIBUJARA
        graficos.setFont(new Font("Arial",Font.PLAIN,30));
        graficos.drawString(resultado.getString(3),300,200);                    //INCLUYO UN TEXTO EN LA IMAGEN    
                
        BufferedImage imagen = null;  
        imagen = ImageIO.read(new File("logos/"+resultado.getString(7)));                                              
        graficos.drawImage(imagen,0,0,400,400,null); 
        
        graficos.setColor(Color.white);                                         //DIGO QUE LO PINTARE CON COLOR ROJO
        graficos.fillRect(390,0,20,400);                                        //PINTO UN RECTAGUNLO
        
        /////////////PUEDES PINTAR////////////////////////////
        graficos.dispose();                                                     //LIBERO EL RECURSO    
        
        File archivo = new File("guardado/"+resultado.getString(7)+".png");     //APUNTO A UN NUEVO ARCHIVO
        ImageIO.write(imagencacheada,"png",archivo);
        
        
    //////////////////////////////////////////////////////////////////    
            }
        }catch(Exception e){ //Captura de error
                e.printStackTrace(); //imprime en mensaje de error en caso de existir     
        
    }
    
}
}
