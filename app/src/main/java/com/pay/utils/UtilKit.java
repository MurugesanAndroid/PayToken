package com.pay.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatDrawableManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pay.R;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class UtilKit {

    public static Dialog dialog;

    public static Dialog exploredialog;

    public static Handler mProgressHandler;

    public static Handler mProgressExploreHandler;

    public static String exploremessage;

    public static SharedPreferences purplepathPref;

    public static AlertDialog alertDialog;

    public static boolean dialogActive = false;

    public static String message;

    public static final String ERRORCODE = "300";

    public static final String SUCCESSCODE = "200";

    private static boolean explorecancelable = true;

    private static boolean cancelable = true;

    private static Context context;

    public static Runnable mShowCustomSpinnerDialog = new Runnable() {

        public void run() {
            try {
                if (message != null)
                    showSpinner(context, message);
                else
                    showSpinner(context);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        /**
         * @param ctx
         */
        private void showSpinner(Context ctx) {
            try {
                if (isDialogShown()) {
                    dismisssSpinnerDialog();
                }
                dialog = new Dialog(ctx, android.R.style.Theme_Translucent);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(UtilKit.cancelable);
                dialog.setContentView(R.layout.process_spinner);
                dialog.show();
            } catch (Exception e) {
                dialog = null;
            }
        }

        private void showSpinner(Context ctx, String msg) {
            try {
                if (isDialogShown()) {
                    dismisssSpinnerDialog();
                }
                dialog = new Dialog(ctx, android.R.style.Theme_Translucent);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setCancelable(UtilKit.cancelable);
                dialog.setContentView(R.layout.process_spinner);
                TextView text = dialog.findViewById(R.id.textView1);
                text.setText(msg);
                dialog.show();
            } catch (Exception e) {
                dialog = null;
            }
        }
    };
    // srini
    public static Runnable mShowCustomSpinnerDialogForExplore = new Runnable() {

        public void run() {
            try {
                if (exploremessage != null)
                    showSpinner(context, exploremessage);
                else
                    showSpinner(context);
            } catch (Exception e) {
                e.printStackTrace();

            }
        }

        /**
         * @param ctx
         */
        private void showSpinner(Context ctx) {
            try {
                if (isExploreDialogShown()) {
                    dismisssExploreSpinnerDialog();
                }
                exploredialog = new Dialog(ctx, android.R.style.Theme_Translucent);
                exploredialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                exploredialog.setCancelable(UtilKit.explorecancelable);
                exploredialog.setContentView(R.layout.process_spinner);
                exploredialog.show();
            } catch (Exception e) {
                exploredialog = null;
            }
        }

        private void showSpinner(Context ctx, String msg) {
            try {
                if (isExploreDialogShown()) {
                    dismisssExploreSpinnerDialog();
                }
                exploredialog = new Dialog(ctx, android.R.style.Theme_Translucent);
                exploredialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                exploredialog.setCancelable(UtilKit.explorecancelable);
                exploredialog.setContentView(R.layout.process_spinner);
                TextView text = exploredialog.findViewById(R.id.textView1);
                text.setText(msg);
                exploredialog.show();
            } catch (Exception e) {
                exploredialog = null;
            }
        }
    };

    /**
     * @param context
     * @param cancelable
     */
    public static void showSpinnerDialog(Context context, boolean cancelable) {

        try {
            UtilKit.context = context;
            message = null;
            mProgressHandler = new Handler();
            mProgressHandler.post(mShowCustomSpinnerDialog);
            UtilKit.cancelable = cancelable;
        } catch (Exception e) {
            dialog = null;
        }
    }

    public static void showToastShort(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    // srini
    public static void showSpinnerDialogForExplore(Context context, boolean cancelable) {

        try {
            UtilKit.context = context;
            exploremessage = null;
            mProgressExploreHandler = new Handler();
            mProgressExploreHandler.post(mShowCustomSpinnerDialogForExplore);
            UtilKit.explorecancelable = cancelable;
        } catch (Exception e) {
            exploredialog = null;
        }
    }

    public static void showSpinnerDialog(Context context, String msg,
                                         boolean cancelable) {
        try {
            UtilKit.context = context;
            message = msg;
            mProgressHandler = new Handler();
            mProgressHandler.post(mShowCustomSpinnerDialog);
            UtilKit.cancelable = cancelable;
        } catch (Exception e) {
            dialog = null;
        }
    }

    public static void alertRetrofitExceptionDialog(Context context, Throwable t) {
        try {
            if (t instanceof SocketTimeoutException) {
                alertRetrofitExceptionalert("Network Time out. Please try again.", context);
            } else if (t instanceof ConnectException) {
                alertRetrofitExceptionalert("Server Time out. Please try again.", context);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void alertRetrofitExceptionalert(String message, final Context context) {
        LayoutInflater inflater;
        View dialogView;
        inflater = LayoutInflater.from(UtilKit.context);
        dialogView = inflater.inflate(R.layout.alert_message_layout, null);
        alertDialog = new AlertDialog.Builder(UtilKit.context).create();
        alertDialog.setView(dialogView);
        TextView erroreMessage = dialogView.findViewById(R.id.textViewDilog);

        Log.i("", "intitializeAlertDialog " + message);
        erroreMessage.setText(message);
        dialogView.findViewById(R.id.yes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    ActivityCompat.finishAffinity((Activity) context);
                } else
                    ((Activity) context).finish();

            }
        });

        if (!alertDialog.isShowing()) {
            alertDialog.show();
        }

    }

    public static boolean isExploreDialogShown() {
        return exploredialog != null && exploredialog.isShowing();
    }


    public static boolean isDialogShown() {
        return dialog != null && dialog.isShowing();
    }


    public static void dismisssExploreSpinnerDialog() {
        try {
            if (exploredialog != null && exploredialog.isShowing()) {
                exploredialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exploredialog = null;
        }
    }


    /**
     *
     */
    public static void dismisssSpinnerDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dialog = null;
        }
    }

    public static void createSharedPreference(Context context, String userID) {
        purplepathPref = context.getSharedPreferences("useridPref", Context.MODE_PRIVATE);
        persistingPurplePathPref("user_id", userID);
    }

    public static void persistingPurplePathPref(String key, String valString) {
        purplepathPref.edit().putString(key, valString).commit();
    }

    public static boolean isNetworkAvailable(Context context) {

        boolean networkAvailability = false;
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo[] = manager.getAllNetworkInfo();

        for (int j = 0; j < netInfo.length; j++) {
            networkAvailability = (networkAvailability || netInfo[j].isConnected());
        }

        return networkAvailability;
    }

    public static void setSvgImageviewDrawable(ImageView imageview, Context mContext, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            imageview.setImageResource(id);
        } else {
            imageview.setImageDrawable(vectorToBitmapDrawable(mContext, id));
        }
    }


    public static BitmapDrawable vectorToBitmapDrawable(Context mContext, @DrawableRes int resVector) {
        return new BitmapDrawable(mContext.getResources(), vectorToBitmap(mContext, resVector));
    }

    public static Bitmap vectorToBitmap(Context mContext, @DrawableRes int resVector) {
        @SuppressLint("RestrictedApi") Drawable drawable = AppCompatDrawableManager.get().getDrawable(mContext, resVector);
        Bitmap b = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        drawable.setBounds(0, 0, c.getWidth(), c.getHeight());
        drawable.draw(c);
        return b;
    }

}
