package cargotransportations.servlet;

import cargotransportations.entity.Driver;
import cargotransportations.service.DriverService;
import cargotransportations.service.DriverServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DriversViewer extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        DriverService driverService = new DriverServiceImpl();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<body>");
        //out.println("<p>Hello, World!</p>");
        for (Driver driver : driverService.getAllDrivers()) {
            String rowData = String.format(
                    "<p>%d, %s, %s, %d, %s</p>",
                    driver.getId(),
                    driver.getFirstName(),
                    driver.getLastName(),
                    driver.getHours(),
                    driver.getCity());
            out.println(rowData);
        }
        out.println("</body>");
        out.println("</html>");
    }
}
