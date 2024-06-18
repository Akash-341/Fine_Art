package com.ort.fineart.Utils;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

public class DownloadUtil {

    public static void downloadFile(Context context, String url) {
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);

        // Extract the order ID from the URL
        String orderId = url.substring(url.lastIndexOf('/') + 1);
        String fileName = "invoice_" + orderId + ".pdf";

        request.setTitle("Downloading "+fileName+"...");
        request.setDescription("Downloading invoice from The Fine Art Hub");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);

        downloadManager.enqueue(request);
        Toast.makeText(context, "Download Started", Toast.LENGTH_SHORT).show();
    }

}
