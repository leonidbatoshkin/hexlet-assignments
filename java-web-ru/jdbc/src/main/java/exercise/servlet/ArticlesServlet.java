package exercise.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletContext;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.ArrayUtils;

import exercise.TemplateEngineUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.Objects;


public class ArticlesServlet extends HttpServlet {

    List<Map<String, String>> articles = new ArrayList<>();

    private String getId(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return null;
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 1, null);
    }

    private String getAction(HttpServletRequest request) {
        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            return "list";
        }
        String[] pathParts = pathInfo.split("/");
        return ArrayUtils.get(pathParts, 2, getId(request));
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        String action = getAction(request);

        switch (action) {
            case "list":
                showArticles(request, response);
                break;
            default:
                showArticle(request, response);
                break;
        }
    }

    private void showArticles(HttpServletRequest request,
                              HttpServletResponse response)
            throws IOException, ServletException {


        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        var page = request.getParameter("page") == null ? 1 : Integer.parseInt(request.getParameter("page"));
        String query = "SELECT id, title, body FROM articles ORDER BY id ASC LIMIT ? OFFSET ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, 11);
            statement.setInt(2, page == 1 ? 0 : 9);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                articles.add(Map.of(
                                "id", rs.getString("id"),
                                "title", rs.getString("title"),
                                "body", rs.getString("body")
                        )
                );
            }

        } catch (SQLException e) {
            // Если произошла ошибка, устанавливаем код ответа 500
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        request.setAttribute("page", page);
        request.setAttribute("articles", articles);
        TemplateEngineUtil.render("articles/index.html", request, response);
        articles.clear();
    }


    private Map<String, String> findArticle(String id) {

        return articles
                .stream()
                .filter(u -> u.get("id").equals(id))
                .findAny()
                .orElse(null);
    }

    private void showArticle(HttpServletRequest request,
                             HttpServletResponse response)
            throws IOException, ServletException {

        Map<String, String> article = new HashMap<>();
        int id = Integer.parseInt(Objects.requireNonNull(getId(request)));

        ServletContext context = request.getServletContext();
        Connection connection = (Connection) context.getAttribute("dbConnection");
        String query = "SELECT id, title, body FROM articles WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                article.putAll(Map.of(
                                "id", rs.getString("id"),
                                "title", rs.getString("title"),
                                "body", rs.getString("body")
                        )
                );
            }

        } catch (SQLException e) {
            // Если произошла ошибка, устанавливаем код ответа 500
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        if (article.get("title") == null || article.get("body") == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        request.setAttribute("title", article.get("title"));
        request.setAttribute("body", article.get("body"));
        TemplateEngineUtil.render("articles/show.html", request, response);
    }
}
