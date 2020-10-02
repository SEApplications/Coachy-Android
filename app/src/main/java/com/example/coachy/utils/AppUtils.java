package com.example.coachy.utils;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Vibrator;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.text.TextUtilsCompat;
import androidx.core.view.ViewCompat;
import com.example.coachy.R;
import com.google.android.material.snackbar.Snackbar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AppUtils {

    public static float density = 1;

    public static String getCurrentTime(boolean withSeconds) {
        return new SimpleDateFormat(withSeconds ? "k:mm:ss" : "k:mm", Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    public static String getTodayDate() {
        return new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime());
    }

    public static void call(Context context, String number) {
        try {
            context.startActivity(new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel: " + number)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void vibrate(Context context, long duration) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            vibrator.vibrate(duration);
        }
    }

    public static void vibrate(Context context) {
        vibrate(context, 10);
    }

    public static void showToast(Context context, String message, int length) {
        if (context == null) return;
        Toast.makeText(context, message, length).show();
    }


    public static void showSnackBar(View view, String message) {
        Snackbar snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG);
        TextView tv = snackbar.getView().findViewById(R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        //snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();
    }


    public static void hideKeyboard(@Nullable View view) {
        hideKeyboard(view, false);
    }


    public static void hideKeyboard(@Nullable View view, boolean force) {
        if (view == null) return;
        if (force) {
            try {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && !imm.isActive()) return;
                if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            view.postDelayed(() -> {
                try {
                    InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null && !imm.isActive()) return;
                    if (imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }, 200);
        }
    }

    public static void showKeyboard(View view) {
        if (view == null) return;
        view.postDelayed(() -> {
            try {
                InputMethodManager imm = (InputMethodManager) view.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null && !imm.isActive()) return;
                if (imm != null) imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 200);
    }


    public static boolean isRTL() {
        return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == ViewCompat.LAYOUT_DIRECTION_RTL;
    }
}