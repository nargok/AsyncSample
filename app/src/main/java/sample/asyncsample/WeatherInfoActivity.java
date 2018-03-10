package sample.asyncsample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeatherInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);

        // 画面部品ListViewを取得
        ListView lvCityList = findViewById(R.id.lvCityList);
        // SimpleAdapterで使用するListオブジェクトを用意
        List<Map<String, String>> cityList = new ArrayList<>();
        // 都市データを格納するMapオブジェクトの用意とcitylistへのデータ登録
        String[] names = new String[5];
        names[0] = "大阪";
        names[1] = "神戸";
        names[2] = "豊岡";
        names[3] = "京都";
        names[4] = "舞鶴";
        String[] ids = new String[5];
        ids[0] = "270000";
        ids[1] = "280010";
        ids[2] = "290020";
        ids[3] = "300030";
        ids[4] = "300040";

        Map<String, String> city = new HashMap<>();
        for(int i = 0; i < names.length; i++) {
            city = new HashMap<>();
            city.put("name", names[i]);
            city.put("id", ids[i]);
            cityList.add(city);
        }
        // SimpleAdapterで使用するfrom-to用変数の用意
        String[] from = {"name"};
        int[] to = {android.R.id.text1};
        // SimpleAdapterを生成
        SimpleAdapter adapter = new SimpleAdapter(WeatherInfoActivity.this, cityList,
                android.R.layout.simple_expandable_list_item_1, from, to);
        // ListViewにSimpleAdapterを設定
        lvCityList.setAdapter(adapter);
        // ListViewにリスナを設定
        lvCityList.setOnItemClickListener(new ListItemClickListener());
    }

    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // ListViewでタップされた行の都市名と都市IDを取得
            Map<String, String> item = (Map<String, String>) parent.getItemAtPosition(position);
            String cityName = item.get("name");
            String cityId = item.get("id");
            // 取得した都市名をtvCityNameに設定
            TextView tvCityName = findViewById(R.id.tvCityName);
            tvCityName.setText(cityName + "の天気: ");
            // 天気情報を表示するTextViewを取得
            TextView tvWeatherTelop = findViewById(R.id.tvWeatherTelop);
            // 天気詳細情報を表示するTextViewを取得
            TextView tvWeatherDesc = findViewById(R.id.tvWeatherDesc);
            // WeatherInfoReceiverをnew。引数として上で取得したTextViewを渡す。
            WeatherInfoReceiver receiver = new WeatherInfoReceiver(tvWeatherTelop, tvWeatherDesc);
            // WeatherInfoReceiverを実行
            receiver.execute(cityId);
        }
    }

    // AsyncTaskの引数
    // 1:execute(), doInBackgroundの引数の型
    // 2: publishProgress(), onProgressUpdate(O)の引数の型
    // 3: doInBackground, onPostExecute, onCancelledの引数の型
    private class WeatherInfoReceiver extends AsyncTask<String, String, String> {
        // 現在の天気を表示する画面部品フィールド
        private TextView _tvWeatherTelop;
        // 天気の詳細を表示する画面部品フィールド
        private TextView _tvWeatherDesc;

        // コンストラクタ 記述しておくことでnewの時の引数で画面描画部分を取得できるから便利
        public WeatherInfoReceiver(TextView tvWeatherTelop, TextView tvWeatherDesc) {
            _tvWeatherTelop = tvWeatherTelop;
            _tvWeatherDesc = tvWeatherDesc;
        }

        @Override
        public String doInBackground(String... params) {
            // 可変長引数の1個目(インデックス0)を取得。これが都市ID
            String id = params[0];
            //　都市IDを使って接続URLを作成
            String urlStr = "http://weather.livedoor.com/forecast/webservise/json/v1?city=" + id;
            // 天気情報サービスから取得したJSON文字列。天気情報が格納されている。
            String result = "";

            // ここに上記URLに接続してJSON文字列を取得する処理を記述

            // JSON文字列を返す
            return result;
        }

        @Override
        public void onPostExecute(String result) {
            // 天気情報用文字列変数を用意
            String telop = "";
            String desc = "";

            // ここに天気情報文字列を解析する処理を記述

            // 的情報用文字列をTextViewにセット
            _tvWeatherTelop.setText(telop);
            _tvWeatherDesc.setText(desc);
        }
    }
}

