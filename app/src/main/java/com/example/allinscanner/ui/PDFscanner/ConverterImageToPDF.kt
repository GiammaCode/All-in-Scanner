package com.example.allinscanner.ui.PDFscanner

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.RectF
import android.net.Uri
import com.pspdfkit.document.processor.NewPage
import com.pspdfkit.document.processor.PageImage
import com.pspdfkit.document.processor.PdfProcessorTask
import com.pspdfkit.utils.Size

/**
 * This creates a `PdfProcessorTask` that will create a single-page document using the supplied image as the page background.
 */
fun createPdfFromImageTask(imageUri: Uri, context: Context) : PdfProcessorTask {
    // First obtain the size of the image.
    val options = BitmapFactory.Options().apply {
        // By setting this, we won't actually load the image but only figure out the size.
        inJustDecodeBounds = true
    }
    BitmapFactory.decodeStream(context.contentResolver.openInputStream(imageUri), null, options)
    val imageHeight: Int = options.outHeight
    val imageWidth: Int = options.outWidth

    // We take A4 as a baseline and alter the page's aspect ratio based on the given bitmap.
    val pageSize: Size = if (imageWidth <= imageHeight) {
        Size(NewPage.PAGE_SIZE_A4.width, imageHeight * (NewPage.PAGE_SIZE_A4.width / imageWidth))
    } else {
        Size(NewPage.PAGE_SIZE_A4.height, imageHeight * NewPage.PAGE_SIZE_A4.height / imageWidth)
    }

    // Now that we know the desired size, we can create a `PdfProcessorTask` that will create a document containing a single page.
    return PdfProcessorTask.newPage(
        NewPage.emptyPage(pageSize)
        // We initialize our new page using the passed-in image URI and calculated page size.
        .withPageItem(PageImage(context, imageUri, RectF(0f, pageSize.height, pageSize.width, 0f)))
        .build())
}
