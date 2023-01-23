package com.example.phuongnguyen.sqlinjectionjavademo;

import java.sql.*;
import java.util.Optional;

import com.example.phuongnguyen.sqlinjectionjavademo.Employee;
import com.example.phuongnguyen.sqlinjectionjavademo.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class DemoController {

    @Autowired
    EmployeeRepository employeeRepository;

    private Connection connection;

    Logger logger = LoggerFactory.getLogger(DemoController.class);

    /**
     * The /secure endpoint takes ID and looks up a employee.
     * @param id Employee ID
     * @return Response contain employee 's name and jobname
     */
    @RequestMapping(value = "/secure/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String secure(@PathVariable("id") int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get().name + ": " + employee.get().jobname;
        } else {
            return "No employee found!";
        }
    }

    /**
     * The /insecure endpoint takes  ID and looks up a employee,extract directly on databse,no defense method -> SQL injection
     * @param id Employee ID
     * @return Response contain employee's name and jobname
     */
    @RequestMapping(value = "/insecure/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String insecure(@PathVariable("id") String id) {
        try {
            // Connect to H2 database and execute query
            connection = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "password");
            Statement statement = connection.createStatement();
            logger.info("Executing: " + "select * from employee where id = " + id);
            ResultSet rs = statement.executeQuery("select * from employee where id = " + id);

            // Iterate through results
            StringBuilder sb = new StringBuilder();
            while (rs.next()) {

                // Build result
                int rID = rs.getInt("id"); // ID
                String rName = rs.getString("name"); // Name
                String rJobName = rs.getString("jobname"); // JobName
                if (sb.length() > 0) {
                    sb.append("<br />");
                }
                sb.append(rName + ": " + rJobName);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error connected to database!";
        }
    }
}