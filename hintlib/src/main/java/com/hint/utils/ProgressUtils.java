package com.hint.utils;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.hint.R;
import com.hint.views.ScaleProgressView;

public class ProgressUtils {
    private static HintLoadingDialog loadingDialog = null;

    public static void showLoading(Context context) {
        loadingDialog = new HintLoadingDialog(context, 0, "");
        if (null != loadingDialog && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public static void showLoading(Context context, String msg) {
        loadingDialog = new HintLoadingDialog(context, 1, msg);
        if (null != loadingDialog && !loadingDialog.isShowing()) {
            loadingDialog.show();
        }
    }

    public static void dismissLoading() {
        if (null != loadingDialog) {
            if (loadingDialog.isShowing()) {
                loadingDialog.cancel();
                loadingDialog.dismiss();
            }
            loadingDialog = null;
        }
    }

    private static Dialog progressdialog = null;

    public static void showProgressDialog(Context context) {
        dismissProgressDialog();
        if (null == progressdialog) {
            progressdialog = new Dialog(context, R.style.Loading);
            progressdialog.setCanceledOnTouchOutside(false);
            progressdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress, null);
            progressdialog.setContentView(view);
        }
        if (null != progressdialog && !progressdialog.isShowing()) {
            progressdialog.show();
            Window window = progressdialog.getWindow();
            window.setGravity(Gravity.CENTER);
        }
    }

    public static void showProgressDialog(Context context, String msg) {
        dismissProgressDialog();
        if (null == progressdialog) {
            progressdialog = new Dialog(context, R.style.Loading);
            progressdialog.setCanceledOnTouchOutside(false);
            progressdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress_msg, null);
            TextView tipsTv = view.findViewById(R.id.tv_tips);
            progressdialog.setContentView(view);
            tipsTv.setText(msg);
        }
        if (null != progressdialog && !progressdialog.isShowing()) {
            progressdialog.show();
            Window window = progressdialog.getWindow();
            window.setGravity(Gravity.CENTER);
        }
    }

    public static void dismissProgressDialog() {
        if (null != progressdialog) {
            if (progressdialog.isShowing()) {
                progressdialog.cancel();
                progressdialog.dismiss();
            }
            progressdialog = null;
        }
    }

    private static Dialog progressFish = null;
    public final static int FISH_ONE = 0;
    public final static int FISH_TWO = 1;

    public static void showProgressFish(Context context) {
        showProgressFish(context, FISH_ONE);
    }

    public static void showProgressFish(Context context, int fish) {
        dismissProgressFish();
        if (null == progressFish) {
            progressFish = new Dialog(context, R.style.Loading);
            progressFish.setCanceledOnTouchOutside(false);
            progressFish.requestWindowFeature(Window.FEATURE_NO_TITLE);
            View view = null;
            if (FISH_TWO == fish) {
                view = LayoutInflater.from(context).inflate(R.layout.dialog_progress_two_fish, null);
            } else {
                view = LayoutInflater.from(context).inflate(R.layout.dialog_progress_fish, null);
            }
            progressFish.setContentView(view);
        }
        if (null != progressFish && !progressFish.isShowing()) {
            progressFish.show();
            Window window = progressFish.getWindow();
            window.setGravity(Gravity.CENTER);
        }
    }

    public static void dismissProgressFish() {
        if (null != progressFish) {
            if (progressFish.isShowing()) {
                progressFish.cancel();
                progressFish.dismiss();
            }
            progressFish = null;
        }
    }

    private static Dialog progressScale = null;

    public static void showProgressScale(Context context, int progress) {
        dismissProgressScale();
        if (null == progressScale) {
            progressScale = new Dialog(context, R.style.Loading);
            progressScale.setCanceledOnTouchOutside(false);
            progressScale.requestWindowFeature(Window.FEATURE_NO_TITLE);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_progress_scale, null);
            ScaleProgressView mProgressView = view.findViewById(R.id.progress_view);
            mProgressView.setProgress(progress);

            progressScale.setContentView(view);
        }
        if (null != progressScale && !progressScale.isShowing()) {
            progressScale.show();
            Window window = progressScale.getWindow();
            window.setGravity(Gravity.CENTER);
        }
    }

    public static void dismissProgressScale() {
        if (null != progressScale) {
            if (progressScale.isShowing()) {
                progressScale.cancel();
                progressScale.dismiss();
            }
            progressScale = null;
        }
    }
}
