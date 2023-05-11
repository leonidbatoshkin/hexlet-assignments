package exercise.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

import static javax.swing.UIManager.get;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List getUsers() throws JsonProcessingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.<List<Map<String, String>>>readValue(new File("src/main/resources/users.json"),
                new TypeReference<>() {
                });
    }

    private void showUsers(HttpServletRequest request,
                           HttpServletResponse response)
            throws IOException {
        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            StringBuilder body = new StringBuilder();
            body.append("""
                    <!DOCTYPE html>
                    <html lang=\"ru\">
                        <head>
                            <meta charset=\"UTF-8\">
                        </head>
                        <body>
                            <table>
                                <tr>
                    """);
            ObjectMapper mapper = new ObjectMapper();
            for (Object user : getUsers()) {
                Map map = mapper.convertValue(user, Map.class);
                body.append("<td>" + map.get("id") + "</td>");
                body.append("""
                        <td>
                           <a href=\"/users/""");
                body.append(map.get("id") + "\">" + map.get("firstName") + " " + map.get("lastName") + "</a>");
                body.append("</td>");
            }
            body.append("""
                                </tr>
                            </table>
                        </body>
                    </html>
                    """);

            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(body.toString());
        }
    }

    private void showUser(HttpServletRequest request,
                          HttpServletResponse response,
                          String id)
            throws IOException {

        StringBuilder body = new StringBuilder();
        body.append("""
                <!DOCTYPE html>
                <html lang=\"ru\">
                    <head>
                        <meta charset=\"UTF-8\">
                    </head>
                    <body>
                        <table>
                            <tr>
                """);
        ObjectMapper mapper = new ObjectMapper();

        for (Object user : getUsers()) {
            Map map1 = mapper.convertValue(user, Map.class);
            if (map1.get("id").equals(id)) {
                body.append("<td>" + map1.get("id") + "</td>");
                body.append("<td>" + map1.get("firstName") + "</td>");
                body.append("<td>" + map1.get("lastName") + "</td>");
                body.append("<td>" + map1.get("email") + "</td>");
                body.append("""
                                    </tr>
                                </table>
                            </body>
                        </html>
                        """);
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.write(body.toString());
                return;
            }
        }
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
    }
}
