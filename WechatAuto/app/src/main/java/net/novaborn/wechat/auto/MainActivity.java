package net.novaborn.wechat.auto;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import net.novaborn.wechat.auto.entity.AutoMessage;
import net.novaborn.wechat.auto.services.RabbitMQService;
import net.novaborn.wechat.auto.utils.ImageUtil;
import net.novaborn.wechat.auto.utils.WechatUtil;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

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

        startBtn.setOnClickListener(v -> {
            if (!WechatUtil.isAccessibilitySettingsOn(this)) {
                Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(accessibleIntent);

                Intent service = new Intent(this, RabbitMQService.class);
                startService(service);
            } else {
                Toast.makeText(this, "已经打开了!", Toast.LENGTH_SHORT).show();
            }
        });

        endBtn.setOnClickListener(v -> {
            Intent service = new Intent(this, RabbitMQService.class);
            stopService(service);

            if (WechatUtil.isAccessibilitySettingsOn(this)) {
                Intent accessibleIntent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
                startActivity(accessibleIntent);
            } else {
                Toast.makeText(this, "没有开启!", Toast.LENGTH_SHORT).show();
            }
        });

        openWechatBtn.setOnClickListener(v -> {
            if (!WechatUtil.isAccessibilitySettingsOn(this)) {
                Toast.makeText(this, "还没有自动化，先开启辅助功能!", Toast.LENGTH_SHORT).show();
                return;
            }

            AutoMessage autoMessage = new AutoMessage();
            autoMessage.setMessage("明天正常营业!！！！\n六一活动持续进行中!!\n小伙伴现在就可以下预约订单哦～\n预约订单不排队哦");
            autoMessage.setImgUrlList(Arrays.asList("https://admin.cxy.novaborn.net/upload/images/activity/dfc4b68932ba4c9f907624bc424c48f4.png", "https://admin.cxy.novaborn.net/upload/images/activity/5ee1589cb6a1453cbf65d38c93c479bc.png"));
            ControlService.autoMessage = autoMessage;

            WechatUtil.openWChart(this);
        });
    }
}
