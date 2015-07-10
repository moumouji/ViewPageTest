package hp.viewpagetest;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class MainActivity extends Activity {

    MyViewPage myview;
    ViewPager vp;
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
        initView();
        vp =(ViewPager) findViewById(R.id.myViewPage);
        vp.setAdapter(new PagerAdapter() {

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView tmp = imageList[position];
                container.addView(tmp);
                return tmp;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(imageList[position]);
            }

            @Override
            public int getCount() {
                return imageList.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }
        });

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                isChange = true;
                if(position > ListEnd){
                    mposition = ListStart;
                }
                else if(position < ListStart){
                    mposition = ListEnd;
                }
                else {
                    mposition = position;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if(ViewPager.SCROLL_STATE_IDLE == state){
                    if(isChange){
                        isChange = false;
                        vp.setCurrentItem(mposition , false);
                    }
                }
            }
        });
    }

    public void initView(){
        ImageView tmp;
        tmp = new ImageView(getApplicationContext());
        tmp.setImageResource(picture[picture.length - 1]);
        imageList[0] = tmp;

        for(int i = 0; i < picture.length; i++){
            tmp = new ImageView(getApplicationContext());
            tmp.setImageResource(picture[i]);
            imageList[i + 1] = tmp;
        }

        tmp = new ImageView(getApplicationContext());
        tmp.setImageResource(picture[0]);
        imageList[imageList.length - 1] = tmp;
    }


}
