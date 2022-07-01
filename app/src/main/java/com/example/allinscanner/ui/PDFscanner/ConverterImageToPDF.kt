package com.example.allinscanner.ui.PDFscanner

import android.content.Context
import android.graphics.RectF
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.pspdfkit.document.processor.NewPage
import com.pspdfkit.document.processor.PageImage
import com.pspdfkit.document.processor.PdfProcessor
import com.pspdfkit.document.processor.PdfProcessorTask
import com.pspdfkit.utils.Size
import java.io.File

const val MARGIN = 20f
/**
 * This creates a `PdfProcessorTask` that will create a single-page document using the supplied image as the page background.
 */
fun createPdfFromImageTask(imageUri: Uri, context: Context) : PdfProcessorTask {
    val pageSize = Size(NewPage.PAGE_SIZE_A4.width, NewPage.PAGE_SIZE_A4.height )

    // Now that we know the desired size, we can create a `PdfProcessorTask` that will create a document containing a single page.
    return PdfProcessorTask.newPage(
        NewPage.emptyPage(pageSize)
        // We initialize our new page using the passed-in image URI and calculated page size.
        .withPageItem(
            PageImage(context, imageUri,
                RectF(MARGIN , pageSize.height-MARGIN,pageSize.width-MARGIN, MARGIN)))
        .build())
}

fun processPdf(context: Context, imageUri: Uri, pdfName: String,  outputDirectory: File){
    val saveOutNamePDF = "$pdfName.pdf"
    val task = createPdfFromImageTask(imageUri,context)
    val outputPath = outputDirectory.resolve("MYPDF/" + saveOutNamePDF )
    // Process the document.
    PdfProcessor.processDocument(task, outputPath)
    Log.d("ProcessPDF", outputPath.toString())
    Toast.makeText(context, "$saveOutNamePDF saved", Toast.LENGTH_SHORT).show()
}