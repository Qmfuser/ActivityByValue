package activity.my.hsd.com.myapplication106;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {

    private String classJsonStr = "{classname:'数学',class_time:'2016-09-28'}";

    StringBuffer sb = new StringBuffer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn);
        final TextView mTv = (TextView) findViewById(R.id.tv);

//        Uri uri = Uri.parse("http://112.124.38.1:12345/login");
//        Intent it = new Intent(Intent.ACTION_VIEW,uri);
//        startActivity(it);
//


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gson gson = new Gson();
                StudentClassModel stuClass = gson.fromJson(classJsonStr, StudentClassModel.class);
                Log.d("-----", stuClass.getClassname() + "-----" + stuClass.getClass_time());

            }
        });

        OkHttpClient mOkhttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .url("http://112.124.38.1:12345/index")
                .build();

        Call call = mOkhttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String res = response.body().string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTv.setText(res);

                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(res);
                            System.out.println(obj);
                            System.out.println(obj.get("adv"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });


//        try {
//            URL url = new URL("http://112.124.38.1:6666/login");
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            InputStream inStream = conn.getInputStream();
//           // String jsonStr=DataUtil.Stream2string(inStream);
//            int b;
//            //顺序读取文件text里的内容并赋值给整型变量b,直到文件结束为止。
//            StringBuffer sb=new StringBuffer();
//            while((b=inStream.read())!=-1 ){
//                if((char)b=='\n'){
//                    sb.append("回车");
//                }else{
//                    sb.append((char)b);
//                }
//            }
//            String jsonStr = sb.toString();
//            JSONObject jsonobject = new JSONObject(jsonStr);
////            JSONObject json=jsonobject.getJSONObject("");
//            int mphone = jsonobject.getInt("phone");
//            String mpassword = jsonobject.getString("password");
//            String mdevice_id = jsonobject.getString("device_id");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }

    }
}
