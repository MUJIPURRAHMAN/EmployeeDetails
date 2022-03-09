package com.example.employeedetails.Services;

import com.example.employeedetails.Entities.Employee;
import com.example.employeedetails.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.el.ELException;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class NotificationService {
    @Autowired
    EmployeeRepository employeeRepository;


    public Employee getEmployeeById(Long id){
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ELException("Employee id found"));
        return employee;
    }
/*
    public Boolean mailToEmployee(String email)throws Exception{
        Boolean status=sendMail(email);
        System.out.println(status.booleanValue()+"}}}}}}");
        return status;
    }*/

    public void sendMail(){
        String to="mujiburrahmanar97@gmail.com";
        String username="mujiburinfo13@gmail.com";
        String from="mu**@gmail.com";
        String password="**************";
        String host = "smtp.gmail.com";
        String port ="587";

        System.out.println(to+"+++++++++++");
        Properties props=new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.port", port);

        Session session=Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username,password);
            }
        });

        try {
            System.out.println("hello)))");
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Example");
            message.setText("Hi");
            System.out.println(message.getSubject()+"||||||");

            Transport.send(message);
            System.out.println("message sent successfully...");
//            return Boolean.TRUE;
        } catch (MessagingException e) {
            System.out.println("catch"+"<<<<<<<<");
//            throw new RuntimeException(e);
            System.out.println("Failed to send Email:"+e);
        }

    }


}
