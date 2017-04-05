package com.example.simple_soul.mylearningdemo.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.WallpaperManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.simple_soul.mylearningdemo.R;

import java.io.IOException;

/**
 * Created by simple_soul on 2017/3/31.
 */

public class WallpaperActivity extends BaseActivity implements View.OnClickListener
{
    private Button confirm, select;
    private ImageView image;
    private Bitmap bitmap;
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 200;

    @Override
    public View initView()
    {
        View view = View.inflate(this, R.layout.activity_wallpaper, null);

        confirm = (Button) view.findViewById(R.id.wallpaper_btn_confirm);
        select = (Button) view.findViewById(R.id.wallpaper_btn_select);
        image = (ImageView) view.findViewById(R.id.wallpaper_image);

        confirm.setOnClickListener(this);
        select.setOnClickListener(this);

        return view;
    }

    @Override
    public void initData()
    {
        // 版本判断。当手机系统大于 23 时，才有必要去判断权限是否获取.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED)
            {

                    Log.i("main", "requestPermissions");
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            REQUEST_CODE_ASK_PERMISSIONS);


            }

        }
    }

    private void showMessage(String message,
                             DialogInterface.OnClickListener okListener)
    {
        new AlertDialog.Builder(this).setMessage(message)
                .setPositiveButton("OK", okListener).create().show();
    }

    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.wallpaper_btn_confirm:
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(
                        WallpaperActivity.this);
                try
                {
                    wallpaperManager.setBitmap(bitmap);
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.wallpaper_btn_select:
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, 1);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && null != data)
        {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();
            Log.i("main", "picturePath" + picturePath);
            bitmap = BitmapFactory.decodeFile(picturePath);
            image.setImageBitmap(bitmap);

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case REQUEST_CODE_ASK_PERMISSIONS:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "已获取权限", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(this, "未获取权限", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }
}