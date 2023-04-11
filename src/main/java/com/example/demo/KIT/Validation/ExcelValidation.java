package com.example.demo.kit.Validation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;

public class ExcelValidation {
    public static boolean trackCellNotBlank(Cell cell) {
        return (cell.getCellType() != CellType.BLANK) ? true : false;
    }

}
