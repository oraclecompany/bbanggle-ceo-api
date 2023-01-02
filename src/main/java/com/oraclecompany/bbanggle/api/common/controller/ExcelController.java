package com.oraclecompany.bbanggle.api.common.controller;

import com.oraclecompany.bbanggle.api.common.service.ExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@AllArgsConstructor
@Api(value = "Excel Upload", tags = "Excel Upload")
public class ExcelController {

    private final ExcelService excelService;

    @ApiOperation(value = "Excel Upload API", notes = "Excel Upload")
    @PostMapping("/excel/read")
    public String readExcel(@RequestPart("file") MultipartFile file) throws IOException {
        excelService.readExcel(file);
        return "상점이 정상적으로 등록되었습니다.";
    }
}
