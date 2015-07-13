package hp.viewpagetest;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

    MyViewPage myview;
    ViewPager vp;
    ListView lv;
    static int[] picture =new int[] {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7};
    ImageView[] imageList = new ImageView[picture.length + 2];
    private static  final int ListStart = 1;
    private static  final int ListEnd = picture.length ;
    private int mposition = 1;
    private boolean isChange = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myview = (MyViewPage) findViewById(R.id.myViewPage);
        lv = (ListView) findViewById(R.id.listview);
        lv.setAdapter(new MyListAdapter(getApplicationContext()));
    }

}
