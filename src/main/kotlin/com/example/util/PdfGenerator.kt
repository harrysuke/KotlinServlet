package com.example.util

import com.example.model.UserAccount
import com.lowagie.text.Document
import com.lowagie.text.Element
import com.lowagie.text.FontFactory
import com.lowagie.text.PageSize
import com.lowagie.text.Paragraph
import com.lowagie.text.Phrase
import com.lowagie.text.pdf.PdfPCell
import com.lowagie.text.pdf.PdfPTable
import com.lowagie.text.pdf.PdfWriter
import java.io.OutputStream
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object PdfGenerator {

    /**
     * Optional utility function if you want to abstract your PDF generation logic completely 
     * away from the PdfReportServlet class file in the future.
     */
    fun generateUserReport(users: List<UserAccount>, startDate: String, endDate: String, outputStream: OutputStream) {
        val document = Document(PageSize.A4, 36f, 36f, 54f, 54f)
        val writer = PdfWriter.getInstance(document, outputStream)
        
        writer.pageEvent = HeaderFooterPageEvent()
        document.open()

        // Document Title
        val title = Paragraph("User Report", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18f))
        title.alignment = Element.ALIGN_CENTER
        document.add(title)

        // Metadata Subtitle
        val dateRange = Paragraph("From: $startDate To: $endDate", FontFactory.getFont(FontFactory.HELVETICA, 12f))
        dateRange.alignment = Element.ALIGN_CENTER
        document.add(dateRange)
        
        document.add(Paragraph("Generated on: ${LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))}"))
        document.add(Paragraph(" ")) // Spacing line

        // Layout Grid Table
        val table = PdfPTable(6)
        table.widthPercentage = 100f
        table.setSpacingBefore(10f)

        // Generate Header Grid Block Row Elements
        arrayOf("Name", "Username", "Email", "Mobile", "Country", "Reg Date").forEach { header ->
            val cell = PdfPCell(Phrase(header, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12f)))
            cell.horizontalAlignment = Element.ALIGN_CENTER
            table.addCell(cell)
        }

        // Generate Row Iteration Lists Elements
        for (user in users) {
            table.addCell(user.fullName)
            table.addCell(user.username)
            table.addCell(user.emailId)
            table.addCell(user.mobileNumber)
            table.addCell(user.country)
            
            val formattedDate = user.regDate?.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ?: "N/A"
            table.addCell(formattedDate)
        }

        document.add(table)
        document.close()
    }
}
