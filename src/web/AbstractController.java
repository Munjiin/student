package web;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


public abstract class AbstractController extends HttpServlet {

    public abstract String getBasic();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("service..................");

        String path = req.getRequestURI().substring(getBasic().length());

        String way = req.getMethod(); //get���� post���� �޴� �ڵ�

        System.out.println(path + " : " + way); //Ȯ��

        String methodName = path + way; // writeGET, listGET, writePOST

        Class clz = this.getClass(); // ��� �ν��Ͻ��� �ڽ��� Ŭ������ �� �� �ִ�.

        try {
            // Ŭ������ ����� �޼ҵ带 ã�´�. (�޼ҵ� �̸�, �Ķ����1,�Ķ����2)
            System.out.println("methodName: " + methodName);
            Method method = clz.getDeclaredMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            System.out.println("method: " + method);

            Object result = method.invoke(this,req,resp); //�޼ҵ带 �����Ѵ�. ����� object�� ���´�.

            String returnURL = (String)result;

            System.out.println("RETURN: " + returnURL);

            if(returnURL.startsWith("redirect")){
                resp.sendRedirect(returnURL.substring(9));
                return;
            }
            req.getRequestDispatcher("/WEB-INF/" + returnURL + ".jsp").forward(req,resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
