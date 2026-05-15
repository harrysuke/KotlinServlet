package com.example.util

import com.lowagie.text.Document
import com.lowagie.text.Element
import com.lowagie.text.FontFactory
import com.lowagie.text.Phrase
import com.lowagie.text.pdf.ColumnText
import com.lowagie.text.pdf.PdfPageEventHelper
import com.lowagie.text.pdf.PdfWriter

class HeaderFooterPageEvent : PdfPageEventHelper() {

    private val fontHeader = FontFactory.getFont(FontFactory.HELVETICA, 9f)
    private val fontFooter = FontFactory.getFont(FontFactory.HELVETICA, 10f)

    // Triggers automatically when a new page is started
    override fun onStartPage(writer: PdfWriter, document: Document) {
        val headerPhrase = Phrase("Internal Systems - Confidential User Report", fontHeader)
        
        // Render top running header aligned to the right margin
        ColumnText.showTextAligned(
            writer.directContent,
            Element.ALIGN_RIGHT,
            headerPhrase,
            document.right(),
            document.top() + 10f,
            0f
        )
    }

    // Triggers automatically right before a page finishes rendering
    override fun onEndPage(writer: PdfWriter, document: Document) {
        val pageNumberPhrase = Phrase("Page ${writer.pageNumber}", fontFooter)
        
        // Render centered footer at the bottom margin of the page
        ColumnText.showTextAligned(
            writer.directContent,
            Element.ALIGN_CENTER,
            pageNumberPhrase,
            (document.left() + document.right()) / 2,
            document.bottom() - 20f,
            0f
        )
    }
}
