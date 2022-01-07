package com.example.employeedetails.Services;

import com.example.employeedetails.Entities.Employee;
import com.example.employeedetails.Repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileUploadService {

    @Autowired
    EmployeeRepository employeeRepository;


    public static final String FILE_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static String INVALID_FILE_TYPE = "File type should be .xlsx";
    private static DataFormatter formatter = new DataFormatter();


    public byte[] getExcelTemplate() throws IOException {
        return downloadTemplate();
    }

    public byte[] downloadTemplate() throws IOException {
        String[] header = {"Name", "DOB", "DOJ", "MOBILENO", "SALARY"};

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
            Sheet sheet = workbook.createSheet("Employee");

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setColor(IndexedColors.BLUE.getIndex());

            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

            // Row for Header
            Row headerRow = (Row) sheet.createRow(0);

            // Header
            for (int col = 0; col < header.length; col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(header[col].toLowerCase());
                cell.setCellStyle(headerCellStyle);
                sheet.autoSizeColumn(col);
            }

            workbook.write(out);
            workbook.close();

            return out.toByteArray();
        }
    }


    /////////////////////////////////////////


    public void save(MultipartFile multipartFile) throws IOException {
        List<Employee> employees = uploadFile(multipartFile);
        employeeRepository.saveAll(employees);
    }


    public static boolean hasCorrectFormat(MultipartFile file){
        if (!FILE_TYPE.equals(file.getContentType())){
            return false;
        }
        return true;
    }


    public static List<Employee> uploadFile(MultipartFile multipartFile) throws IOException {
        try (Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream()); ByteArrayOutputStream out = new ByteArrayOutputStream();){
            Sheet sheet = workbook.getSheetAt(0);
            List<Employee> employeeList = new ArrayList<>();

            for (int i = 1; i<=sheet.getLastRowNum(); i++){
                Row row = sheet.getRow(i);
                Employee employee = new Employee();
                for (int j = 0; j<= row.getLastCellNum(); j++){
                    Cell currentCell = row.getCell(j);
                    switch (j){
                        case 0:
                            if (currentCell != null) {
                                employee.setName(formatter.formatCellValue(currentCell));
                            }
                            break;
                        case 1:
                            if (currentCell != null){
                                employee.setDob(formatter.formatCellValue(currentCell));
                            }
                            break;
                        case 2:
                            if (currentCell != null){
                                employee.setDoj(formatter.formatCellValue(currentCell));
                            }
                            break;
                        case 3:
                            if (currentCell != null){
                                employee.setMobileno(formatter.formatCellValue(currentCell));
                            }
                            break;
                        case 4:
                            if (currentCell != null){
                                employee.setSalary(Long.valueOf(formatter.formatCellValue(currentCell)));
                            }
                            break;

                        default:
                            break;
                    }
                }
                employeeList.add(employee);
            }
            workbook.close();
            return employeeList;
        }
    }

}
