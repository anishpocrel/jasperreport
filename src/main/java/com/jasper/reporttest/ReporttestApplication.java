package com.jasper.reporttest;

import com.jasper.reporttest.domain.user;
import com.jasper.reporttest.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
public class ReporttestApplication {

    @Autowired
    private com.jasper.reporttest.repository.userrepository userrepository;

    @Autowired
    private ReportService reportService;

    @GetMapping("/getUsers")
    public List<user> getUsers(){
        return userrepository.findAll();
    }

    @GetMapping("/getUsers/{id}")
    public Optional<user> getUsersById(@PathVariable String id){
        System.out.println("**********************"+userrepository.findById(id).get().getFirstName());
        return userrepository.findById(id);

    }
    @GetMapping("/report")
    public String generateReport(){
        return reportService.exportReport();
    }
    public static void main(String[] args) {
        SpringApplication.run(ReporttestApplication.class, args);
    }



}
