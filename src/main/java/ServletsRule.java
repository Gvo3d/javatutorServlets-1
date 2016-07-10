import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

public class ServletsRule extends HttpServlet {
    int i = 0; // "постоянство" сервлета
    Properties props = new Properties();
    private final String propfile = "counter.properties";

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.print("<HEAD><TITLE>");
        out.print("A server-side strategy");
        out.print("</TITLE></HEAD><BODY>");
        out.print("<h1>Servlets Rule! " + i++);
        out.print("</h1></BODY>");
        out.close();
    }

    @Override
    public void init() throws ServletException {
        i=Integer.parseInt(getCounter());
    }

    @Override
    public void destroy(){
        setCounter();
    }

    private String getCounter() {
        FileInputStream fis;
        String counter="0";

        try {
            fis = new FileInputStream(propfile);
            props.load(fis);

            counter = props.getProperty("number");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");

        }
        return counter;
    }

    private void setCounter(){
        FileOutputStream fos;

        try{
            fos = new FileOutputStream(propfile);
            props.setProperty("number", Integer.toString(i));
            props.store(fos, "");
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
} // /:~