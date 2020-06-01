package net.novaborn.wechat.auto;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import net.novaborn.wechat.auto.entity.AutoMessage;
import net.novaborn.wechat.auto.utils.ImageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class MainActivity extends AppCompatActivity {
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, 0x12);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        verifyStoragePermissions(this);

        Button startBtn = findViewById(R.id.start);
        Button endBtn = findViewById(R.id.end);
        Button openWechatBtn = findViewById(R.id.openWechat);
        Button savePictureBtn = findViewById(R.id.savePicture);

        startBtn.setOnClickListener(v -> {
            if (!isAccessibilitySettingsOn(this)) {
                Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(accessibleIntent);
            } else {
                Toast.makeText(this, "已经打开了!", Toast.LENGTH_SHORT).show();
            }
        });

        endBtn.setOnClickListener(v -> {
            if (isAccessibilitySettingsOn(this)) {
                Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(accessibleIntent);
            } else {
                Toast.makeText(this, "没有开启!", Toast.LENGTH_SHORT).show();
            }
        });

        openWechatBtn.setOnClickListener(v -> {
            if (!isAccessibilitySettingsOn(this)) {
                Toast.makeText(this, "还没有自动化，先开启辅助功能!", Toast.LENGTH_SHORT).show();
                return;
            }

            AutoMessage autoMessage = new AutoMessage();
            autoMessage.setMessage("明天正常营业!！！！\n六一活动持续进行中!!\n小伙伴现在就可以下预约订单哦～\n预约订单不排队哦");
            autoMessage.setImgUrlList(Arrays.asList("https://admin.cxy.novaborn.net/upload/images/activity/dfc4b68932ba4c9f907624bc424c48f4.png","https://admin.cxy.novaborn.net/upload/images/activity/5ee1589cb6a1453cbf65d38c93c479bc.png"));
            ControlService.autoMessage = autoMessage;

            openWChart();
        });

        savePictureBtn.setOnClickListener(v -> {
            new Thread(() -> {
                ImageUtil.savePictrueToGallery(this, ImageUtil.getPicture("https://admin.cxy.novaborn.net/upload/images/c9c7edaab1c44bd3842cd942cc276db3.jpg"), "test.jpg");
                ImageUtil.deleteImage(this,"test.jpg");
            }).start();
        });
    }

    /**
     * 打开微信界面
     */
    private void openWChart() {
        Intent intent = new Intent(Intent.ACTION_MAIN, null);
        intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setClassName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI");
        startActivity(intent);
    }

    /**
     * 判断微信助手是否开启
     *
     * @param context
     * @return
     */
    public boolean isAccessibilitySettingsOn(Context context) {
        int accessibilityEnabled = 0;
        try {
            accessibilityEnabled = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.ACCESSIBILITY_ENABLED);
        } catch (Settings.SettingNotFoundException e) {
            Log.i("URL", "错误信息为：" + e.getMessage());
        }

        if (accessibilityEnabled == 1) {
            String services = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES);
            if (services != null) {
                return services.toLowerCase().contains(context.getPackageName().toLowerCase());
            }
        }
        return false;
    }
}
