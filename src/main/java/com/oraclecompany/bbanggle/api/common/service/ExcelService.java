package com.oraclecompany.bbanggle.api.common.service;

import com.oraclecompany.bbanggle.api.common.dto.ExcelDataDto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelService {

    /**
     * 엑셀 파일 읽기
     * 번호 | 이름    | 이메일
     * 1   | 홍길동  | test@gmail.com
     * 2   | 김길동  | test@gmail.com
     * 3   | 이길동  | test@gmail.com
     */
    public void readExcel(MultipartFile file) throws IOException {

        List<ExcelDataDto> dataList = new ArrayList<>();

        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }

        Workbook workbook = null;

        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }

        Sheet worksheet = workbook.getSheetAt(0);

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {

            Row row = worksheet.getRow(i);

            ExcelDataDto data = new ExcelDataDto();

            data.setNum((int) row.getCell(0).getNumericCellValue());
            data.setName(row.getCell(1).getStringCellValue());
            data.setEmail(row.getCell(2).getStringCellValue());

            dataList.add(data);
        }

        for (ExcelDataDto data : dataList) {
            System.out.println("============================");
            System.out.println(data.getNum());
            System.out.println(data.getName());
            System.out.println(data.getEmail());
        }

        //TODO :  data DB 저장
//        storeService.save(dataList);

    }
}
