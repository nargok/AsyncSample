package sample.asyncsample;

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

        }
    }
}
