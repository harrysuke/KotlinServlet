package com.example.servlet

import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import com.example.doa.UserDAO
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.streaming.SXSSFWorkbook

@WebServlet("/excel-report")
class ExcelReportServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val startParam = req.getParameter("startDate")
        val endParam = req.getParameter("endDate")

        if (startParam.isNullOrBlank() || endParam.isNullOrBlank()) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing date parameters")
            return
        }

        val startDate = LocalDate.parse(startParam, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val endDate = LocalDate.parse(endParam, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        
        if (startDate.isAfter(endDate)) {
            res.sendError(HttpServletResponse.SC_BAD_REQUEST, "Start date cannot be after end date")
            return
        }

        val users = UserDAO().getUserByDateRange(
            startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), 
            endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        )

        res.contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
        res.setHeader("Content-Disposition", "attachment; filename=user_report_${startDate}_to_${endDate}.xlsx")
        
        val workBook = SXSSFWorkbook()
        val sheet = workBook.createSheet("User Report")

        // FIXED: Enable trackAllColumnsForAutoSizing so SXSSF remembers widths before flushing rows to disk
        sheet.trackAllColumnsForAutoSizing()

        val headerFont = workBook.createCellStyle()
        val headerFontName = workBook.createFont()
        headerFontName.bold = true
        headerFontName.fontHeightInPoints = 14.toShort() 
        headerFontName.color = IndexedColors.WHITE.getIndex() // FIXED: Use getIndex() instead of .index for safety
        
        // FIXED: Removed Font.SS_SUB to keep the header text centered cleanly on the text baseline
        headerFontName.typeOffset = Font.SS_NONE 
        headerFont.setFont(headerFontName) 
        
        headerFont.fillForegroundColor = IndexedColors.BLUE.getIndex() // FIXED: Use getIndex()
        headerFont.fillPattern = FillPatternType.SOLID_FOREGROUND
        headerFont.alignment = HorizontalAlignment.CENTER
        headerFont.borderBottom = BorderStyle.THIN
        headerFont.borderTop = BorderStyle.THIN
        headerFont.borderLeft = BorderStyle.THIN
        headerFont.borderRight = BorderStyle.THIN

        val headers = arrayOf("ID", "Name", "Email", "Mobile", "Country", "Reg Date")
        val headerRow = sheet.createRow(0)
        headers.forEachIndexed { index, header -> 
            val cell = headerRow.createCell(index)
            cell.setCellValue(header)
            cell.cellStyle = headerFont
        }

        var rowIndex = 1
        val dataFont = workBook.createCellStyle()
        dataFont.borderBottom = BorderStyle.THIN
        dataFont.borderTop = BorderStyle.THIN
        dataFont.borderLeft = BorderStyle.THIN
        dataFont.borderRight = BorderStyle.THIN

        users.forEach { user ->
            val row = sheet.createRow(rowIndex++)
            
            row.createCell(0).apply { setCellValue(user.id.toDouble()); cellStyle = dataFont }
            row.createCell(1).apply { setCellValue(user.fullName); cellStyle = dataFont }
            row.createCell(2).apply { setCellValue(user.emailId); cellStyle = dataFont }
            row.createCell(3).apply { setCellValue(user.mobileNumber); cellStyle = dataFont }
            row.createCell(4).apply { setCellValue(user.country); cellStyle = dataFont }
            row.createCell(5).apply { setCellValue(user.regDate); cellStyle = dataFont }
        }

        for (i in 0 until headers.size) {
            sheet.autoSizeColumn(i)
        }

        workBook.write(res.outputStream)
        workBook.close()
        workBook.dispose() // FIXED: Clean up streaming OS cache temporary files
    } 
}
