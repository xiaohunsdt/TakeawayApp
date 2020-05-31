package net.novaborn.wechat.auto;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ControlService extends AccessibilityService {

    //微信首页
    public static final String WECHAT_CLASS_LAUNCHUI = "com.tencent.mm.ui.LauncherUI";
    private static final String TAG = "ControlService";
    //微信包名
    private final static String WeChat_PNAME = "com.tencent.mm";

    public static boolean isSend = false;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        String className = event.getClassName().toString();
        Log.i(TAG, "event >> TYPE:" + event.getEventType());
        Log.i(TAG, "event >> ClassName:" + className);

        switch (event.getEventType()) {
            case AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED:
                if (WeChat_PNAME.equals(event.getPackageName().toString())) {
                    sendNotifacationReply(event);
                }
                break;
            case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:
                if (WECHAT_CLASS_LAUNCHUI.equals(className)) {
                    autoSendMoment();
                }
                break;
        }
    }

    @Override
    public void onInterrupt() {
    }

    private void autoSendMoment(String message) {
        if (!isSend) {
            return;
        }
        try {
            //调起微信之后，不管在什么页面，先查找返回键并点击：防止在其他页面查找不到搜索按钮
            WechatUtils.findViewIdAndClick(this, "com.tencent.mm:id/dm");
            Thread.sleep(100);

            WechatUtils.findViewIdAndClick(this, "com.tencent.mm:id/cn_", 2);
            Thread.sleep(100);

            WechatUtils.findViewIdAndClick(this, "com.tencent.mm:id/b3b", 0);
            Thread.sleep(3000);

            AccessibilityNodeInfo cameraBtn = WechatUtils.findViewId(this, "com.tencent.mm:id/cj", 0);
//            if (cameraBtn == null) {
//                return;
//            }
            WechatUtils.performAction(cameraBtn, AccessibilityNodeInfo.ACTION_LONG_CLICK);
            Thread.sleep(3000);

            AccessibilityNodeInfo editInput = WechatUtils.findViewId(this, "com.tencent.mm:id/fms", 0);
//            if (editInput == null) {
//                return;
//            }
            WechatUtils.pastContent(this, editInput, message);
            Thread.sleep(2000);

            WechatUtils.findTextAndClick(this, "发表");
            Thread.sleep(1000);

            resetAndReturnApp();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isSend = false;
        }
    }

    private void autoSendMoment() {
        if (!isSend) {
            return;
        }
        try {
            WechatUtils.findViewIdAndClick(this, "com.tencent.mm:id/cn_", 2);
            Thread.sleep(100);

            WechatUtils.findViewIdAndClick(this, "com.tencent.mm:id/dpa", 0);
            Thread.sleep(3000);

            AccessibilityNodeInfo cameraBtn = WechatUtils.findViewId(this, "com.tencent.mm:id/cj", 0);
//            if (cameraBtn == null) {
//                return;
//            }
            WechatUtils.performAction(cameraBtn, AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(2000);

            WechatUtils.performAction(WechatUtils.findViewId(this, "com.tencent.mm:id/f40", 1), AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(3000);

            WechatUtils.performAction(WechatUtils.findViewId(this, "com.tencent.mm:id/dm0", 0), AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);

            WechatUtils.performAction(WechatUtils.findViewId(this, "com.tencent.mm:id/dm0", 1), AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);

            WechatUtils.performAction(WechatUtils.findViewId(this, "com.tencent.mm:id/dm0", 2), AccessibilityNodeInfo.ACTION_CLICK);
            Thread.sleep(1000);

            WechatUtils.performAction(WechatUtils.findViewId(this, "com.tencent.mm:id/ch", 0), AccessibilityNodeInfo.ACTION_CLICK);

            Thread.sleep(2500);

            AccessibilityNodeInfo editInput = WechatUtils.findViewId(this, "com.tencent.mm:id/fms", 0);
//            if (editInput == null) {
//                return;
//            }
            WechatUtils.pastContent(this, editInput, "messageaaaa");
            Thread.sleep(2000);

            WechatUtils.findViewIdAndClick(this, "com.tencent.mm:id/ch");
            Thread.sleep(3000);

            //返回键并点击：防止在其他页面查找不到搜索按钮
            WechatUtils.findViewIdAndClick(this, "com.tencent.mm:id/dm");
            Thread.sleep(1000);

            WechatUtils.findViewIdAndClick(this, "com.tencent.mm:id/cn_", 0);
            Thread.sleep(1000);

            resetAndReturnApp();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isSend = false;
        }
    }


    private void resetAndReturnApp() {
        ActivityManager activtyManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTaskInfos = activtyManager.getRunningTasks(3);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTaskInfos) {
            if (this.getPackageName().equals(runningTaskInfo.topActivity.getPackageName())) {
                activtyManager.moveTaskToFront(runningTaskInfo.id, ActivityManager.MOVE_TASK_WITH_HOME);
                return;
            }
        }
    }

    /**
     * 拉起微信界面
     *
     * @param event 服务事件
     */
    private void sendNotifacationReply(AccessibilityEvent event) {
        if (event.getParcelableData() != null && event.getParcelableData() instanceof Notification) {
            Notification notification = (Notification) event.getParcelableData();
            String content = notification.tickerText.toString();
            String[] cc = content.split(":");

            String receiveName = cc[0].trim();
            String receciveScontent = cc[1].trim();

            PendingIntent pendingIntent = notification.contentIntent;
            try {
                pendingIntent.send();
            } catch (PendingIntent.CanceledException e) {
                e.printStackTrace();
            }
            Toast.makeText(this, content, Toast.LENGTH_LONG).show();
        }
    }

    private boolean savePictrueToGallery(Context context, Bitmap bmp, String fileName) {
        // 首先保存图片
        String storePath = Environment.getExternalStorageDirectory()
                + File.separator + Environment.DIRECTORY_DCIM
                + File.separator + "Camera" + File.separator;
        File appDir = new File(storePath);
        if (!appDir.exists()) {
            appDir.mkdir();
        }
//        String fileName = System.currentTimeMillis() + ".jpg";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            //通过io流的方式来压缩保存图片
            boolean isSuccess = bmp.compress(Bitmap.CompressFormat.JPEG, 60, fos);
            fos.flush();
            fos.close();

            //把文件插入到系统图库
            //MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), fileName, null);

            //保存图片后发送广播通知更新数据库
            Uri uri = Uri.fromFile(file);
            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, uri));
            if (isSuccess) {
                return true;
            } else {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
