package com.example.testimageviewbtn;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	private List<String> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.listView1);
		list = new ArrayList<>();
		ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
				android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);

		list.add("http://d.hiphotos.baidu.com/zhidao/pic/item/4b90f603738da97702307972b251f8198618e348.jpg");
		list.add("http://pic32.nipic.com/20130830/2668693_155750632000_2.jpg");
		list.add("http://imgsrc.baidu.com/baike/pic/item/dbf554ed4aa41c98b21cb101.jpg");
		list.add("http://imgsrc.baidu.com/baike/pic/item/034965f47206f595f3d38501.jpg");
		list.add("http://pic.nipic.com/2007-10-12/20071012214335993_2.jpg");
		list.add("http://h.hiphotos.baidu.com/zhidao/pic/item/cefc1e178a82b9013da57a2f738da9773912efa6.jpg");
		list.add("http://d.hiphotos.baidu.com/zhidao/pic/item/9f2f070828381f30ca0b4ec0a8014c086e06f04d.jpg");
		list.add("http://pic32.nipic.com/20130826/2668693_230218622000_2.jpg");
		list.add("http://pic32.nipic.com/20130827/2668693_221201857000_2.jpg");
		list.add("http://tupian.qqjay.com/u/2013/0117/57_185531_9.jpg");
		list.add("http://pic29.nipic.com/20130528/12813299_164744621170_2.jpg");
		list.add("http://pic10.nipic.com/20101007/5779577_115854325910_2.jpg");

		adapter.notifyDataSetChanged();

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startActivity(new Intent(MainActivity.this, ImageBrowserActivity.class));
				ImageBrowserActivity.mList = list;
				ImageBrowserActivity.position = position;
			}
		});
	}

}
