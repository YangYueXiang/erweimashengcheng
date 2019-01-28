package com.xyd.erweimashengcheng;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        imageView = findViewById(R.id.imageView);

    }

    /**
     * 简单的二维码,加载默认二维码扫码界面
     * @param view
     */

    public void ZXing(View view) {
        Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
        startActivityForResult(intent, 0);
    }

    /**
     * 生成带logo二维码
     * @param view
     */
    public void logoma(View view) {
        String textContent = editText.getText().toString();
        if (TextUtils.isEmpty(textContent)) {
            Toast.makeText(MainActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        editText.setText("");
        Bitmap mBitmap = CodeUtils.createImage(textContent, 400, 400, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        imageView.setImageBitmap(mBitmap);
    }

    /**
     * 生成不带logo二维码
     * @param view
     */
    public void NOlogoma(View view){
        String textContent = editText.getText().toString();
        if (TextUtils.isEmpty(textContent)) {
            Toast.makeText(MainActivity.this, "您的输入为空!", Toast.LENGTH_SHORT).show();
            return;
        }
        editText.setText("");
        Bitmap mBitmap = CodeUtils.createImage(textContent, 400, 400, null);
        imageView.setImageBitmap(mBitmap);
    }

    /**
     * 测试生成二维码图片
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }

    }
}
