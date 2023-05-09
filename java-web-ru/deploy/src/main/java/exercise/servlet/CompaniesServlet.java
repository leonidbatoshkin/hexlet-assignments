package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        PrintWriter out = response.getWriter();

        if (request.getQueryString() == null) {
            getCompanies().forEach(out::println);
        } else if (request.getQueryString().contains("search")) {
            if (getCompanies().stream().anyMatch(name -> name.contains(request.getParameter("search")))) {
                getCompanies().stream()
                        .filter(name -> name.contains(request.getParameter("search")))
                        .forEach(out::println);
            } else {
                out.println("Companies not found");
            }
        }
    }
}