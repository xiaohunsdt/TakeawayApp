package net.novaborn.wechat.auto;

import android.accessibilityservice.AccessibilityService;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Toast;

import java.util.List;

public class ControlService extends AccessibilityService {
    //微信首页
    public static final String WECHAT_CLASS_LAUNCHUI = "com.tencent.mm.ui.LauncherUI";
    //微信聊天页面
    public static final String WECHAT_CLASS_CHATUI = "com.tencent.mm.ui.chatting.ChattingUI";
    private static final String TAG = "ControlService";
    //微信包名
    private final static String WeChat_PNAME = "com.tencent.mm";

    private static boolean isSending = false;

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
                switch (className) {
                    case WECHAT_CLASS_LAUNCHUI:
                        autoSendMoment();
                        break;
                }
                break;
        }
    }

    @Override
    public void onInterrupt() {

    }

    private void autoSendMoment() {
        if (isSending) {
            return;
        }
        try {
            isSending = true;
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
            WechatUtils.pastContent(this, editInput, "test");
            Thread.sleep(2000);

            WechatUtils.findTextAndClick(this, "发表");
            Thread.sleep(1000);

            resetAndReturnApp();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isSending = false;
        }
    }

    /**
     * 微信界面，点击title的搜索按钮
     */
    private void handleFlow_clickSearch() {
        try {
            //如果没有名字，说明不是主动发送的，就没有必要搜索了
            if (TextUtils.isEmpty(WechatUtils.NAME)) return;

            //调起微信之后，不管在什么页面，先查找返回键并点击：防止在其他页面查找不到搜索按钮
            Thread.sleep(100);

            WechatUtils.findTextAndClick(this, "返回");

            Thread.sleep(500);

            WechatUtils.findTextAndClick(this, "搜索");

            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
}
