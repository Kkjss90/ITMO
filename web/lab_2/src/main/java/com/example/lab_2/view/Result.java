package com.example.lab_2.view;

import com.example.lab_2.model.Data;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class Result {
    public static void renderView(HttpServletResponse response, String ctx, Data data) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        final PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"en\">");
        out.println("<head>");
        out.println("    <meta http-equiv=\"Content-Type\" content=\"text/html\" charset=\"UTF-8\" />");
        out.println("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("    <link rel=\"stylesheet\" href=\"" + ctx + "/styles/style.css\">");
        out.println("    <title>Результаты</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("    <div id=\"header\" class=\"blured-container round-container margin\">");
        out.println("        <h1>Результат: " + (data.isRes() ? "Попадание" : "Промах") + "</h1>");
        out.println("    </div>");
        out.println("    <div id=\"result-table-container\" class=\"blured-container margin\">");
        out.println("        <table>");
        out.println("            <tr>");
        out.println("                <th>Параметр</th>");
        out.println("                <th>Полученные значения</th>");
        out.println("            </tr>");
        out.println("            <tr>");
        out.println("                <td>X</td>");
        out.println("                <td>" + data.getX() + "</td>");
        out.println("            </tr>");
        out.println("            <tr>");
        out.println("                <td>Y</td>");
        out.println("                <td>" + data.getY() + "</td>");
        out.println("            </tr>");
        out.println("            <tr>");
        out.println("                <td>R</td>");
        out.println("                <td>" + data.getR() + "</td>");
        out.println("            </tr>");
        out.println("        </table>");
        out.println("    </div>");
        out.println("    <div class=\"blured-container round-container fit-content-container margin\">"); // Здесь подставьте результат вычислений
        out.println("        <p><a href=\"" + ctx + "\\controller\">Вернуться назад</a></p>");
        out.println("    </div>");
        out.println("</body>");
        out.println("</html>");

        out.close();

    }
}
