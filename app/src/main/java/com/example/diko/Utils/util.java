package com.example.diko.Utils;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.AlertDialog;
import android.app.ProgressDialog;


import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;



public class util {

    private static final String TAG = util.class.getSimpleName();

    static ProgressDialog mProgressDialog;




    public static Toast showToast(Context ctx, CharSequence msg) {
        return showToast(ctx, msg, Toast.LENGTH_LONG);
    }



    public static boolean isInternetAvailable(Context ctx) {
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        Toast.makeText(ctx,"Check Your Internet Connectivity...",Toast.LENGTH_SHORT).show();
        return false;
    }



    public static boolean Log(Context ctx,String tag,String msg)
    {
        Log.e(tag,msg);
        return true;
    }
public  static  boolean Toast(Context ctx,CharSequence msg)
{
    Toast.makeText(ctx,msg,Toast.LENGTH_SHORT).show();

    return true;
}

    public static Toast showToast(Context ctx, CharSequence msg, int duration) {
        Toast toast = Toast.makeText(ctx, msg, Toast.LENGTH_SHORT);
        toast.setDuration(duration);
        toast.show();
        return toast;
    }


    /*
    public static boolean isSdCardMounted() {
        String status = Environment.getExternalStorageState();
        if (status != null && status.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
    public static void showAlertDialog(Context context, @StringRes int titleResId, @StringRes int bodyResId) {
        showAlertDialog(context, context.getString(titleResId),
                context.getString(bodyResId), null);
    }

    public static void showAlertDialog(Context context, String title, String body) {
        showAlertDialog(context, title, body, null);
    }
    public static void showAlertDialog(Context context, String title, String body, DialogInterface.OnClickListener okListener) {

        if (okListener == null) {
            okListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            };
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context)
                .setMessage(body).setPositiveButton("OK", okListener);

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }

        builder.show();
    }

    public static String toBase64(Bitmap bitmap) {

        if (bitmap == null) {
            throw new NullPointerException("Bitmap cannot be null");
        }

        String base64Bitmap = null;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] imageBitmap = stream.toByteArray();
        base64Bitmap = Base64.encodeToString(imageBitmap, Base64.DEFAULT);

        return base64Bitmap;
    }


    public static Bitmap drawableToBitmap(Drawable drawable) {

        if (drawable == null) {
            throw new NullPointerException("Drawable to convert should NOT be null");
        }

        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        if (drawable.getIntrinsicWidth() <= 0 && drawable.getIntrinsicHeight() <= 0) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }


    public static InputStream bitmapToInputStream(Bitmap bitmap) throws NullPointerException {

        if (bitmap == null) {
            throw new NullPointerException("Bitmap cannot be null");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        InputStream inputstream = new ByteArrayInputStream(baos.toByteArray());

        return inputstream;
    }


    public static void showProgressDialog(Context ctx, String title, String body, boolean isCancellable) {
        showProgressDialog(ctx, title, body, null, isCancellable);
    }


    public static void showProgressDialog(Context ctx, String title, String body, Drawable icon, boolean isCancellable) {

        if (ctx instanceof Activity) {
            if (!((Activity) ctx).isFinishing()) {
                mProgressDialog = ProgressDialog.show(ctx, title, body, true);
                mProgressDialog.setIcon(icon);
                mProgressDialog.setCancelable(isCancellable);
            }
        }
    }


    public static boolean isProgressDialogVisible() {
        return (mProgressDialog != null);
    }


    public static void dismissProgressDialog() {

        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }

        mProgressDialog = null;
    }


    public static float getDensityMultiplier(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }


    public static int getDip(int px, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (px * scale + 0.5f);
    }


    public static void showConfirmDialog(Context ctx, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener) {
        showConfirmDialog(ctx, message, yesListener, noListener, "Yes", "No");
    }


    public static void showConfirmDialog(Context ctx, String message, DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener, String yesLabel, String noLabel) {

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        if (yesListener == null) {
            yesListener = new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        if (noListener == null) {
            noListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            };
        }

        builder.setMessage(message).setPositiveButton(yesLabel,yesListener).setNegativeButton(noLabel, noListener).show();
    }


    public static void showDialog(Context ctx, String message, String positiveBtnLabel, String negativeBtnLabel, DialogInterface.OnClickListener dialogClickListener) {

        if (dialogClickListener == null) {
            throw new NullPointerException("Action listener cannot be null");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setMessage(message).setPositiveButton(positiveBtnLabel, dialogClickListener).setNegativeButton(negativeBtnLabel, dialogClickListener).show();
    }

    public static String getApplicationVersionNumber(Context context) {

        String versionName = null;

        if (context == null) {
            return versionName;
        }

        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionName;
    }


    public static int getApplicationVersionCode(Context ctx) {

        int versionCode = 0;

        try {
            versionCode = ctx.getPackageManager().getPackageInfo(ctx.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }


    public static String getOsVersion() {
        return Build.VERSION.RELEASE;
    }


    public static boolean isServiceRunning(Context ctx, String serviceName) {

        if (serviceName == null) {
            throw new NullPointerException("Service name cannot be null");
        }

        // use application level context to avoid unnecessary leaks.
        ActivityManager manager = (ActivityManager) ctx.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (service.service.getClassName().equals(serviceName)) {
                return true;
            }
        }

        return false;
    }

    public static void share(Context ctx, String sharingMsg, String emailSubject, String title) {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);

        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, sharingMsg);
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, emailSubject);

        ctx.startActivity(Intent.createChooser(sharingIntent, title));
    }


    public static int getDataConnectionType(Context ctx) {

        // use application level context to avoid unnecessary leaks.
        ConnectivityManager connectivityManager = (ConnectivityManager) ctx.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE) != null) {
            if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).isConnected()) {
                return ConnectivityManager.TYPE_MOBILE;
            } else if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnected()) {
                return ConnectivityManager.TYPE_WIFI;
            } else
                return -1;
        } else
            return -1;
    }


    public static boolean isValidEmail(String email) {

        if (email == null) {
            return false;
        }

        final String emailPattern = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Matcher matcher;
        Pattern pattern = Pattern.compile(emailPattern);

        matcher = pattern.matcher(email);

        if (matcher != null) {
            return matcher.matches();
        } else {
            return false;
        }
    }


    public static String capitalizeString(String string) {

        if (string == null) {
            return null;
        }

        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '\'') { // You
                // can add other
                // chars here
                found = false;
            }
        } // end for

        return String.valueOf(chars);
    }


    public static boolean isDatabasePresent(String packageName, String dbName) {
        SQLiteDatabase sqLiteDatabase = null;
        try {
            sqLiteDatabase = SQLiteDatabase.openDatabase("/data/data/" + packageName + "/databases/" + dbName, null, SQLiteDatabase.OPEN_READONLY);
            sqLiteDatabase.close();
        } catch (SQLiteException e) {
            // database doesn't exist yet.
            e.printStackTrace();
            Log.e(TAG, "The database does not exist." + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "Exception " + e.getMessage());
        }

        return (sqLiteDatabase != null);
    }


    public static String getPathForMediaUri(Context context, Uri mediaContentUri) {

        Cursor cur = null;
        String path = null;

        try {
            String[] projection = {MediaColumns.DATA};
            cur = context.getContentResolver().query(mediaContentUri, projection, null, null, null);

            if (cur != null && cur.getCount() != 0) {
                cur.moveToFirst();
                path = cur.getString(cur.getColumnIndexOrThrow(MediaColumns.DATA));
            }

            // Log.v( TAG, "#getRealPathFromURI Path: " + path );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cur != null && !cur.isClosed())
                cur.close();
        }

        return path;
    }

    public static ArrayList<String> toStringArray(JSONArray jsonArr) {

        if (jsonArr == null || jsonArr.length() == 0) {
            return null;
        }

        ArrayList<String> stringArray = new ArrayList<String>();

        for (int i = 0, count = jsonArr.length(); i < count; i++) {
            try {
                String str = jsonArr.getString(i);
                stringArray.add(str);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return stringArray;
    }

    public static JSONArray toJSONArray(ArrayList<String> stringArr) {
        JSONArray jsonArr = new JSONArray();

        for (int i = 0; i < stringArr.size(); i++) {
            String value = stringArr.get(i);
            jsonArr.put(value);
        }

        return jsonArr;
    }


    public static File getStorageDirectory(Context ctx, String dirName) {

        if (TextUtils.isEmpty(dirName)) {
            dirName = "atemp";
        }

        File f = null;

        String state = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(state)) {
            f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/" + dirName);
        } else {
            // media is removed, unmounted etc
            // Store image in
            // /data/data/<package-name>/cache/atemp/photograph.jpeg
            f = new File(ctx.getCacheDir() + "/" + dirName);
        }

        if (!f.exists()) {
            f.mkdirs();
        }

        return f;
    }


    public static File getFile(Context ctx, String fileName) {
        File dir = getStorageDirectory(ctx, null);
        File f = new File(dir, fileName);
        return f;
    }

    public static String writeImage(Context ctx, byte[] imageData) {

        final String FILE_NAME = "photograph.jpeg";
        File dir = null;
        String filePath = null;
        OutputStream imageFileOS;

        dir = getStorageDirectory(ctx, null);
        File f = new File(dir, FILE_NAME);

        try {
            imageFileOS = new FileOutputStream(f);
            imageFileOS.write(imageData);
            imageFileOS.flush();
            imageFileOS.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        filePath = f.getAbsolutePath();

        return filePath;
    }

    public static String writeImageToMedia(Context ctx, Bitmap image, String title, String description) {
        // TODO: move to MediaUtils
        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        return Media.insertImage(ctx.getContentResolver(), image, title, description);
    }


    public static String getApplicationName(Context ctx) throws NameNotFoundException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        final PackageManager packageMgr = ctx.getPackageManager();
        ApplicationInfo appInfo = null;

        try {
            appInfo = packageMgr.getApplicationInfo(ctx.getPackageName(), PackageManager.SIGNATURE_MATCH);
        } catch (final NameNotFoundException e) {
            throw new NameNotFoundException(e.getMessage());
        }

        final String applicationName = (String) (appInfo != null ? packageMgr.getApplicationLabel(appInfo) : "UNKNOWN");

        return applicationName;
    }

    public static URL getPathFromUrl(URL url) {

        if (url != null) {
            String urlStr = url.toString();
            String urlWithoutQueryString = urlStr.split("\\?")[0];
            try {
                return new URL(urlWithoutQueryString);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static String fromCalendar(final Calendar calendar) {
        // TODO: move this method to DateUtils
        Date date = calendar.getTime();
        String formatted = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").format(date);
        return formatted.substring(0, 22) + ":" + formatted.substring(22);
    }


    public static String now() {
        // TODO: move this method to DateUtils
        return fromCalendar(GregorianCalendar.getInstance());
    }

    public static Calendar toCalendar(final String iso8601string) throws ParseException {
        // TODO: move this method to DateUtils
        Calendar calendar = GregorianCalendar.getInstance();
        String s = iso8601string.replace("Z", "+00:00");
        try {
            s = s.substring(0, 22) + s.substring(23);
        } catch (IndexOutOfBoundsException e) {
            // throw new org.apache.http.ParseException();
            e.printStackTrace();
        }

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").parse(s);
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
        calendar.setTime(date);
        return calendar;
    }



    public static void setMockLocation(Context ctx, double longitude, double latitude) {
        // use application level context to avoid unnecessary leaks.
        LocationManager locationManager = (LocationManager) ctx.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);

        locationManager.addTestProvider(LocationManager.GPS_PROVIDER, "requiresNetwork" == "", "requiresSatellite" == "", "requiresCell" == "", "hasMonetaryCost" == "", "supportsAltitude" == "", "supportsSpeed" == "", "supportsBearing" == "",

                android.location.Criteria.POWER_LOW, android.location.Criteria.ACCURACY_FINE);

        Location newLocation = new Location(LocationManager.GPS_PROVIDER);

        newLocation.setLongitude(longitude);
        newLocation.setLatitude(latitude);
        newLocation.setTime(new Date().getTime());

        newLocation.setAccuracy(500);

        locationManager.setTestProviderEnabled(LocationManager.GPS_PROVIDER, true);

        locationManager.setTestProviderStatus(LocationManager.GPS_PROVIDER, LocationProvider.AVAILABLE, null, System.currentTimeMillis());

        // http://jgrasstechtips.blogspot.it/2012/12/android-incomplete-location-object.html
        makeLocationObjectComplete(newLocation);

        locationManager.setTestProviderLocation(LocationManager.GPS_PROVIDER, newLocation);
    }

    private static void makeLocationObjectComplete(Location newLocation) {
        Method locationJellyBeanFixMethod = null;
        try {
            locationJellyBeanFixMethod = Location.class.getMethod("makeComplete");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        if (locationJellyBeanFixMethod != null) {
            try {
                locationJellyBeanFixMethod.invoke(newLocation);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }


    public static int getRandomColor() {
        Random random = new Random();
        int red = random.nextInt(255);
        int green = random.nextInt(255);
        int blue = random.nextInt(255);

        return Color.argb(255, red, green, blue);
    }

    public static byte[] toBytes(Bitmap bmp) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }

    public static Bitmap resizeImage(Bitmap sourceBitmap, int newWidth, int newHeight, boolean filter) {
        // TODO: move this method to ImageUtils
        if (sourceBitmap == null) {
            throw new NullPointerException("Bitmap to be resized cannot be null");
        }

        Bitmap resized = null;

        if (sourceBitmap.getWidth() < sourceBitmap.getHeight()) {
            // image is portrait
            resized = Bitmap.createScaledBitmap(sourceBitmap, newHeight, newWidth, true);
        } else {
            // image is landscape
            resized = Bitmap.createScaledBitmap(sourceBitmap, newWidth, newHeight, true);
        }

        resized = Bitmap.createScaledBitmap(sourceBitmap, newWidth, newHeight, true);

        return resized;
    }


    public static Bitmap compressImage(Bitmap sourceBitmap, int compressionFactor) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inPreferredConfig = Config.ARGB_8888;
        opts.inSampleSize = compressionFactor;

        if (Build.VERSION.SDK_INT >= 10) {
            opts.inPreferQualityOverSpeed = true;
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        sourceBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length, opts);

        return image;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }





    public Bitmap resizeImageByHeight(int height, Bitmap sourceImage) {
        // TODO: move this method to ImageUtils
        int widthO = 0; // original width
        int heightO = 0; // original height
        int widthNew = 0;
        int heightNew = 0;

        widthO = sourceImage.getWidth();
        heightO = sourceImage.getHeight();
        heightNew = height;

        // Maintain the aspect ratio
        // of the original banner image.
        widthNew = (heightNew * widthO) / heightO;

        return Bitmap.createScaledBitmap(sourceImage, widthNew, heightNew, true);
    }




    public static String getMediaType(String contentType) {
        if (isMedia(contentType)) {
            if (isVideo(contentType)) {
                return "video";
            } else if (isAudio(contentType)) {
                return "audio";
            } else if (isImage(contentType)) {
                return "image";
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    public static boolean isMedia(String mimeType) {
        boolean isMedia = false;

        if (mimeType != null) {
            if (mimeType.startsWith("image/") || mimeType.startsWith("video/") || mimeType.startsWith("audio/")) {
                isMedia = true;
            }
        } else {
            isMedia = false;
        }

        return isMedia;
    }

    public static String removeUriFragment(String url) {

        if (url == null || url.length() == 0) {
            return null;
        }

        String[] arr = url.split("#");

        if (arr.length == 2) {
            return arr[0];
        } else {
            return url;
        }
    }


    public static String removeQueryParameters(Uri uri) {
        assert (uri.getAuthority() != null);
        assert (uri.getPath() != null);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme(uri.getScheme());
        builder.encodedAuthority(uri.getAuthority());
        builder.encodedPath(uri.getPath());
        return builder.build().toString();
    }

    public static boolean isImage(String mimeType) {
        if (mimeType != null) {
            if (mimeType.startsWith("image/")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public static boolean isAudio(String mimeType) {
        if (mimeType != null) {
            if (mimeType.startsWith("audio/")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean isVideo(String mimeType) {
        if (mimeType != null) {
            if (mimeType.startsWith("video/")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public static byte[] getMediaData(Context context, Uri uri) {
        if (uri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        Cursor cursor = context.getContentResolver().query(uri, new String[]{Media.DATA}, null, null, null);
        byte[] data = null;

        try {
            if (cursor != null && cursor.getCount() > 0) {
                if (cursor.moveToNext()) {
                    String path = cursor.getString(cursor.getColumnIndex(Media.DATA));

                    try {
                        File file = new File(path);
                        FileInputStream fileInputStream = new FileInputStream(file);
                        data = readStreamToBytes(fileInputStream);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    // Log.v( TAG, "#getVideoData byte.size: " + data.length );
                } // end while
            } else {
                Log.e(TAG, "#getMediaData cur is null or blank");
            }
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return data;
    }


    public static byte[] readStreamToBytes(InputStream inputStream) {

        if (inputStream == null) {
            throw new NullPointerException("InputStream is null");
        }

        byte[] bytesData = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();

            int nRead;
            byte[] data = new byte[16384];

            while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
                buffer.write(data, 0, nRead);
            }

            buffer.flush();

            bytesData = buffer.toByteArray();

            // Log.d( TAG, "#readStream data: " + data );
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (reader != null) {
                try {
                    reader.close();

                    if (inputStream != null)
                        inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }    // finally

        return bytesData;
    }


    public static long getMediaSize(Context context, Uri mediaUri) {
        Cursor cur = context.getContentResolver().query(mediaUri, new String[]{Media.SIZE}, null, null, null);
        long size = -1;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    size = cur.getLong(cur.getColumnIndex(Media.SIZE));

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getSize byte.size: " + size );

                    if (size == 0)
                        Log.w(TAG, "#getSize The media size was found to be 0. Reason: UNKNOWN");

                } // end while
            } else if (cur.getCount() == 0) {
                Log.e(TAG, "#getMediaSize cur size is 0. File may not exist");
            } else {
                Log.e(TAG, "#getMediaSize cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return size;
    }

    public static String getMediaFileName(Context ctx, Uri mediaUri) {
        String colName = MediaColumns.DISPLAY_NAME;
        Cursor cur = ctx.getContentResolver().query(mediaUri, new String[]{colName}, null, null, null);
        String dispName = null;

        try {
            if (cur != null && cur.getCount() > 0) {
                while (cur.moveToNext()) {
                    dispName = cur.getString(cur.getColumnIndex(colName));

                    // for unknown reason, the image size for image was found to
                    // be 0
                    // Log.v( TAG, "#getMediaFileName byte.size: " + size );

                    if (TextUtils.isEmpty(colName)) {
                        Log.w(TAG, "#getMediaFileName The file name is blank or null. Reason: UNKNOWN");
                    }

                } // end while
            } else if (cur != null && cur.getCount() == 0) {
                Log.e(TAG, "#getMediaFileName File may not exist");
            } else {
                Log.e(TAG, "#getMediaFileName cur is null");
            }
        } finally {
            if (cur != null && !cur.isClosed()) {
                cur.close();
            }
        }

        return dispName;
    }


    public static String getMediaType(Uri uri) {
        if (uri == null) {
            return null;
        }

        String uriStr = uri.toString();

        if (uriStr.contains("video")) {
            return "video";
        } else if (uriStr.contains("audio")) {
            return "audio";
        } else if (uriStr.contains("image")) {
            return "image";
        } else {
            return null;
        }
    }


    public static SpannableStringBuilder toBold(String sourceText) {

        if (sourceText == null) {
            throw new NullPointerException("String to convert cannot be bold");
        }

        final SpannableStringBuilder sb = new SpannableStringBuilder(sourceText);

        // Span to set text color to some RGB value
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);

        // set text bold
        sb.setSpan(bss, 0, sb.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        return sb;
    }


    public static SpannableStringBuilder toBold(String string, String subString) {
        if (TextUtils.isEmpty(string)) {
            return new SpannableStringBuilder("");
        }

        SpannableStringBuilder spannableBuilder = new SpannableStringBuilder(string);

        StyleSpan bss = new StyleSpan(Typeface.BOLD);
        if (subString != null) {
            int substringNameStart = string.toLowerCase().indexOf(subString);
            if (substringNameStart > -1) {
                spannableBuilder.setSpan(bss, substringNameStart, substringNameStart + subString.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        } else {
            // set entire text to bold
            spannableBuilder.setSpan(bss, 0, spannableBuilder.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        return spannableBuilder;
    }


    public static String formatSize(long size) {

        if (size <= 0) return "0";

        final String[] units = new String[]{"B", "KB", "MB", "GB", "TB"};
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }


    public static String formatSize(long bytes, boolean si) {
        int unit = si ? 1000 : 1024;

        if (bytes < unit) {
            return bytes + " B";
        }

        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String pre = (si ? "kMGTPE" : "KMGTPE").charAt(exp - 1) + (si ? "" : "i");
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }

    public static Uri createUri(Context ctx) {
        File root = getStorageDirectory(ctx, null);
        root.mkdirs();
        File file = new File(root, Long.toString(new Date().getTime()));
        Uri uri = Uri.fromFile(file);

        return uri;
    }

    public static Intent createTakeVideoIntent(Activity ctx, Uri savingUri, int durationInSeconds) {

        if (savingUri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        final PackageManager packageManager = ctx.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri);
            intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, durationInSeconds);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("video/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));

        return chooserIntent;
    }

    public static Intent createTakePictureIntent(Activity ctx, Uri savingUri) {

        if (savingUri == null) {
            throw new NullPointerException("Uri cannot be null");
        }

        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = ctx.getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final String packageName = res.activityInfo.packageName;
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            intent.setPackage(packageName);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, savingUri);
            cameraIntents.add(intent);
        }

        // Filesystem.
        final Intent galleryIntent = new Intent();
        galleryIntent.setType("image/*");
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);

        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select Source");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[]{}));

        return chooserIntent;
    }


    public static Uri createImageUri(Context ctx) throws IOException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        Uri imageUri = null;

        ContentValues values = new ContentValues();
        values.put(MediaColumns.TITLE, "");
        values.put(ImageColumns.DESCRIPTION, "");
        imageUri = ctx.getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, values);

        return imageUri;
    }


    public static Uri createVideoUri(Context ctx) throws IOException {

        if (ctx == null) {
            throw new NullPointerException("Context cannot be null");
        }

        Uri imageUri;

        ContentValues values = new ContentValues();
        values.put(MediaColumns.TITLE, "");
        values.put(ImageColumns.DESCRIPTION, "");
        imageUri = ctx.getContentResolver().insert(Video.Media.EXTERNAL_CONTENT_URI, values);

        return imageUri;
    }

    public static String getName(String firstName, String lastName) {
        if (!TextUtils.isEmpty(firstName) && !TextUtils.isEmpty(lastName)) {
            return firstName + " " + lastName;
        } else if (!TextUtils.isEmpty(firstName)) {
            return firstName;
        } else if (!TextUtils.isEmpty(lastName)) {
            return lastName;
        } else {
            return null;
        }
    }

    public static Bitmap roundBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;
        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            sbmp = Bitmap.createScaledBitmap(bmp, radius, radius, false);
        } else {
            sbmp = bmp;
        }

        Bitmap output = Bitmap.createBitmap(sbmp.getWidth(), sbmp.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, sbmp.getWidth(), sbmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(Color.parseColor("#BAB399"));
        canvas.drawCircle(sbmp.getWidth() / 2 + 0.7f, sbmp.getHeight() / 2 + 0.7f, sbmp.getWidth() / 2 + 0.1f, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }


    public static final boolean isRelativeUrl(String url) {

        if (TextUtils.isEmpty(url)) {
            return false;
        }

        Uri uri = Uri.parse(url);

        return uri.getScheme() == null;
    }


    public static boolean isContentUri(Uri uri) {
        if (!uri.toString().contains("content://")) {
            return false;
        } else {
            return true;
        }
    }

    public static void hideKeyboard(Context context) {
        try {
            // use application level context to avoid unnecessary leaks.
            InputMethodManager inputManager = (InputMethodManager) context.getApplicationContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            inputManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (Exception e) {
            Log.e(TAG, "Sigh, cant even hide keyboard " + e.getMessage());
        }
    }

    public static boolean isBuildBelow(int buildVersion) {
        if (Build.VERSION.SDK_INT < buildVersion) return true;
        else return false;
    }


    public static void setTextValues(@NonNull TextView textView, @Nullable String firstValue, @Nullable String secondValue) {
        String nullEmptyCheckedVal = getNullEmptyCheckedValue(firstValue, secondValue, null);
        textView.setText(nullEmptyCheckedVal);
    }


    public static String getNullEmptyCheckedValue(@Nullable String firstValue, @Nullable String secondValue,
                                                  @Nullable String delimiter, String... moreValues) {
        if (TextUtils.isEmpty(delimiter)) {
            delimiter = " ";
        }

        StringBuilder builder = new StringBuilder();
        if (!TextUtils.isEmpty(firstValue) && !firstValue.equalsIgnoreCase("null")
                && secondValue != null && !secondValue.equalsIgnoreCase("null")) {
            builder.append(firstValue);
            builder.append(delimiter);
            builder.append(secondValue);
        } else if (!TextUtils.isEmpty(firstValue) && !firstValue.equalsIgnoreCase("null")) {
            builder.append(firstValue);
        } else if (!TextUtils.isEmpty(secondValue) && !secondValue.equalsIgnoreCase("null")) {
            builder.append(secondValue);
        }

        if (moreValues != null) {
            for (String value : moreValues) {
                if (!TextUtils.isEmpty(value) && !value.equalsIgnoreCase("null")) {
                    builder.append(delimiter);
                    builder.append(value);
                }
            }
        }

        return builder.toString();
    }

    public static String capitalizeString(String string, int start, int offset) {
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        String formattedString = string.substring(start, offset).toUpperCase() + string.substring(offset, string.length());
        return formattedString;
    }


    public static String getSha512Hash(String stringToHash) {
        if (stringToHash == null) {
            return null;
        } else {
            return getSha512Hash(stringToHash.getBytes());
        }
    }


    public static String getSha512Hash(byte[] dataToHash) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if (md != null) {
            md.update(dataToHash);
            byte byteData[] = md.digest();
            String base64 = Base64.encodeToString(byteData, Base64.DEFAULT);

            return base64;
        }
        return null;
    }


    public static String getExtension(File file) {
        String ext = null;
        String s = file.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }

        return ext;
    }
    */
}
