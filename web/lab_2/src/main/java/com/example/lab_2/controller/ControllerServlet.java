package com.example.lab_2.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
@WebServlet(name = "ControllerServlet",value="/ControllerServlet")
public class ControllerServlet extends HttpServlet {
    double[] rArray = {1, 1.5, 2, 2.5, 3};

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String x = req.getParameter("X");
        String y = req.getParameter("Y");
        String r = req.getParameter("R");
        if (x != null && y != null && r != null
                && validateCoordinates(Double.parseDouble(x), Double.parseDouble(y), Double.parseDouble(r))) {
            getServletContext().getNamedDispatcher("AreaCheckServlet").forward(req, resp);
        }
    }

    private boolean validateCoordinates(double x, double y, double r) {
        boolean validX = -4 <= x && x <= 4;
        boolean validY = -3 <= y && y <= 3;
        boolean validR = false;
        for (int i = 0; i < rArray.length; i++) {
            if (rArray[i] == r) {
                validR = true;
                break;
            }
        }
        return validX && validY && validR;
    }
}