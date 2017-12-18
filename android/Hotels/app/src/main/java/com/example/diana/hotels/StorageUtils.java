package garmin.com.academyshop;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import garmin.com.academyshop.model.Product;

public class StorageUtils {

    private static final String PRODUCTS_FILE_NAME = "products.txt";

    public static void saveProductsListToExternalStorage(List<Product> products, File saveDir, final Context context) {
        // Check if external storage is mounted
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState(saveDir))) {
            if (products != null && saveDir != null && saveDir.exists()) {
                // Create target file
                final File productsFile = new File(saveDir.getAbsolutePath() + File.separator + PRODUCTS_FILE_NAME);

                // Write data to file
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(productsFile);

                    pw.println(new Date().toString());
                    for (int i = 0; i < products.size(); i++) {
                        pw.println(products.get(i).getProductName());
                    }
                    pw.flush();

                    // TODO Replace Handler with broadcast to update UI
                    Handler h = new Handler(Looper.getMainLooper());
                    h.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(context, "File saved: " + productsFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (FileNotFoundException e) {
                    Toast.makeText(context, "Couldn't save file: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                } finally {
                    if (pw != null) {
                        pw.close();
                    }
                }
            }
        }
    }

}
