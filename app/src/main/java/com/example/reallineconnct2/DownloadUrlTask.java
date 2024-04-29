package com.example.reallineconnct2;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadUrlTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {
        try {
            // สร้าง URL object
            URL url = new URL(urls[0]);

            // เปิดการเชื่อมต่อโดยใช้ HttpURLConnection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            // ตั้งค่าค่าเมื่อเรียกใช้งาน HttpURLConnection
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(15000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);

            // เริ่มการเชื่อมต่อ
            urlConnection.connect();

            // อ่านข้อมูลที่ได้จาก URL
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            // ปิดการเชื่อมต่อ
            urlConnection.disconnect();

            // ส่งข้อมูลที่ได้กลับไปยัง onPostExecute
            return stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        // นำผลลัพธ์ไปใช้งานต่อได้ตามต้องการ
        if (result != null) {
            // ตัวอย่างการใช้งานผลลัพธ์
            Log.d("DownloadedData", result);
        } else {
            Log.e("DownloadError", "Failed to download data");
        }
    }
}
