package tema4;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//formulario en WebPages/Tema4
@WebServlet(name="FormularioServlet", urlPatterns={"/FormularioServlet"})
public class FormulariotServlet extends HttpServlet {
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        //el parametro es el nombre del select
        String edad = request.getParameter("edad");
        //Cuando queremos recoger mas de un parametro de un formulario
        //utilizamos el metodo getParameterValues--Ver formularioHobbies.html
        String[] hobbies = request.getParameterValues("hobbies");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet que procesa un formulario basico</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>" + "Hola " + nombre + " " + apellidos+  "</h1>");
            out.println("Tu Franja de edad es " + edad + " y tus hobbies son:");

            out.println("<ul>");
            //recorro el String
            for (String hobby : hobbies) {
                out.println("<li>" + hobby + "</li>");
            }
            out.println("</ul>");
            out.println("Este formulario ha sido invocado con Los siguientes parametros:<br/>");
            out.println(request.getQueryString());

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
