package com.jasper.reporttest.service;

import com.jasper.reporttest.domain.user;
import com.jasper.reporttest.repository.userrepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    private final String  users_template_path = "/users-report.jrxml";

    @Autowired
    private com.jasper.reporttest.repository.userrepository userrepository;

    public String exportReport() {
        List<user> users = userrepository.findAll();
        try {
            final InputStream reportInputStream = getClass().getResourceAsStream(users_template_path);

            JasperReport jasperReport = JasperCompileManager.compileReport(reportInputStream);

            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(users);

//            Map<String ,Object> parameters=new HashMap<>();
//            parameters.put("createdBy","JAVA TECH");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null,
                    jrBeanCollectionDataSource);

            JasperExportManager.exportReportToPdfFile(jasperPrint,  System.getProperty("user.dir")+"/userReport.pdf"+new Date().getTime());

        }catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return "Done:  "+System.getProperty("user.dir")+"/userReport.pdf";
    }
}
