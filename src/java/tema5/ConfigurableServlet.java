package tema5;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//Servlet con parametros de inicialización....
//Podria ser por ejemplo la cadena de conexion, la url
@WebServlet(name="ConfigurableServlet", urlPatterns={"/ConfigurableServlet"},
initParams={@WebInitParam(name="parametro1", value="Valor1"),
            @WebInitParam(name="parametro2", value="Valor2")})
public class ConfigurableServlet extends HttpServlet {
    //Este tipo de listas nos permiten asociar pares de datos
    // mediante una clave y su correspondiente valor
    //Enumeration no soporta borrados, adicion replace
    private Map<String,String> mapaDeParametrosDeConfiguracion =
            new ConcurrentHashMap<String, String>();

    @Override
    //Inicializacion de parametros
   public void init(ServletConfig config){
       //Enumeration es un interfaz simple que permite enumerar todos 
       //los elementos de cualquier conjunto de objetos de forma sincronizada
        Enumeration<String> nombresParametros = config.getInitParameterNames();
            while (nombresParametros.hasMoreElements()) {
                //extraemos los parametros de configuracion
                String nombreParametro = nombresParametros.nextElement();
              
mapaDeParametrosDeConfiguracion.put(nombreParametro,config.getInitParameter(nombreParametro));
                
            }

    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
 response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet que toma parametros de configuracion</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Parametros de configuracion:</h1>");
            out.println("<ul>");
            Set<String> lista= mapaDeParametrosDeConfiguracion.keySet();
            for (String nombre : lista) {
                out.println("<li>" + nombre + ": "+
                mapaDeParametrosDeConfiguracion.get(nombre) + "</li>");
            }
            out.println("</ul>");
            out.println("</body>");
            out.println("</html>");

        } finally { 
            out.close();
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

}
