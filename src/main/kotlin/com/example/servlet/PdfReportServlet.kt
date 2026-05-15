package com.example.servlet

import com.example.dao.UserAccountDAO
import com.lowagie.text.Document
import com.lowagie.text.Element
import com.lowagie.text.FontFactory
import com.lowagie.text.PageSize
import com.lowagie.text.Paragraph  // FIX: Added missing Paragraph import
import com.lowagie.text.Phrase
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.pdf.PdfWriter
import com.example.util.HeaderFooterPageEvent // FIX: Added missing Event Handler import
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@WebServlet("/pdf-report")
class PdfReportServlet : HttpServlet() {

    private val userAccountDAO = UserAccountDAO()

    override fun doGet(req: HttpServletRequest, res: HttpServletResponse) {
        val startDate = req.getParameter("startDate")
        val endDate = req.getParameter("endDate")

        if (startDate.isNullOrEmpty() || endDate.isNullOrEmpty()) {
            res.sendError(400, "Start date and end date are required")
            return
        }

        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val users = userAccountDAO.getUsersByDateRange(
            LocalDate.parse(startDate, formatter), 
            LocalDate.parse(endDate, formatter)
        )

        res.contentType = "application/pdf"
        res.setHeader("Content-Disposition", "attachment; filename=user_report_${startDate}_to_${endDate}.pdf")

        val document = Document(PageSize.A4, 36f, 36f, 54f, 54f) // Margins: L, R, T, B
        val writer = PdfWriter.getInstance(document, res.outputStream)
        
        // --- REGISTER THE EVENT HANDLER HERE ---
        writer.pageEvent = HeaderFooterPageEvent()

        document.open()

        // Title
        val title = Paragraph("User Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18f))
        title.alignment = Element.ALIGN_CENTER
        document.add(title)

        val dateRange = Paragraph("From: $startDate To: $endDate", FontFactory.getFont(FontFactory.HELVETICA, 12f))
        dateRange.alignment = Element.ALIGN_CENTER
        document.add(dateRange)
        val generatedDate = Paragraph("Generated on: ${LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}")
        generatedDate.alignment = Element.ALIGN_CENTER
        document.add(generatedDate) // Spacer

        // Table (Note: You have 6 headers, so PdfPTable should be 6)
        val table = PdfPTable(6)
        table.widthPercentage = 100f
        table.setSpacingBefore(10f)

        // Headers
        arrayOf("Name", "Username", "Email", "Mobile", "Country", "Reg Date").forEach { header ->
            val cell = PdfPCell(Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12f)))
            cell.horizontalAlignment = Element.ALIGN_CENTER
            table.addCell(cell)
        }

        // Data Rows
        for (user in users) {
            table.addCell(user.fullName)
            table.addCell(user.username)
            table.addCell(user.emailId)
            table.addCell(user.mobileNumber)
            table.addCell(user.country)
            
            // FIX: Added safe-call (?.) and fallback value to avoid null compile exception
            val formattedDate = user.regDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "N/A"
            table.addCell(formattedDate)
        }

        document.add(table)
        document.close()
    }
}
