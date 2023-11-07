package com.example.lab_2.controller;

import com.example.lab_2.model.Data;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import static com.example.lab_2.view.Result.renderView;

@WebServlet(name = "AreaCheckServlet", value="/AreaCheckServlet")
public class AreaCheckServlet extends HttpServlet {
    public List<Data> getResultList() {
        return resultList;
    }

    private List<Data> resultList;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String ctx = this.getServletContext().getContextPath();
        final long start = System.nanoTime();
        final String x = req.getParameter("X");
        final String y = req.getParameter("Y");
        final String r = req.getParameter("R");
        final int dx;
        final double dy;
        final double dr;
        try {
            dx = Integer.parseInt(x);
            dy = Double.parseDouble(y);
            dr = Double.parseDouble(r);
        } catch (NumberFormatException | NullPointerException e) {
            resp.sendError(400);
            return;
        }

        final boolean res = checkArea(dx, dy, dr);
        final long end = System.nanoTime();
        final long execTime = end - start;
        final LocalDateTime execAt = LocalDateTime.now();

        final Data data = new Data();
        data.setX(dx);
        data.setY(dy);
        data.setR(dr);
        data.setRes(res);
        data.setCalculationTime(execTime);
        data.setCalculatedAt(execAt);

        synchronized (resultList) {
            resultList.add(data);
        }

        renderView(resp, ctx, data);
    }
    public boolean checkSquare(int x, double y, double r) {
        return (x <= 0 && y >= 0 && Math.abs(x) <= r && y <= r);
    }

    public boolean checkCircle(int x, double y, double r) {
        return (x >= 0 && y >= 0 && (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2)));
    }

    public boolean checkTriangle(int x, double y, double r) {
        return (x >= 0 && y <= 0 && Math.abs(x) + Math.abs(y) <= r);
    }

    public boolean checkArea(int x, double y, double r) {
        return checkCircle(x, y, r) || checkTriangle(x, y, r) || checkSquare(x, y, r);
    }

    @Override
    public void init() throws ServletException {
        resultList = new LinkedList<>();
        getServletContext().setAttribute("resultList", resultList);
    }

}
