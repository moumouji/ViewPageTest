package hp.viewpagetest;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by hp on 2015/7/10.
 */
public class MyViewPage extends ViewPager {

    static int[] picture =new int[] {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7};
    String[] txt = new String[]{"啤酒","红酒","果汁饮料","鸡尾酒","橙汁","香槟","咖啡饮料","啤酒","红酒"};
    ImageView[] imageList = new ImageView[picture.length + 2];
    private static  final int ListStart = 1;
    private static  final int ListEnd = picture.length ;
    private int mposition = 1;
    private boolean isChange = false;
    private Context context;

    public MyViewPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init();
    }

    public MyViewPage(Context context) {
        super(context);
        this.context = context;
        init();
    }

    private void init(){

        //将要用到的ImageView准备好
        addImages();

        //添加adapter
        this.setAdapter(new PagerAdapter() {

            //生成一个view
            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                final int i = position;
                ImageView tmp = imageList[position];
                tmp.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, txt[i], Toast.LENGTH_SHORT).show();
                    }
                });
                container.addView(tmp);
                return tmp;
            }

            //当图片多于3个时会用到
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

        //为了实现伪循环，需要监听当前位置
        this.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //正常的内容是1～picture.length，我们在0位置放上最后一张图片，在ImageView最后放上第一张
                //当检测到当前位置是0，则需要跳到picture.length
                //若当前位置大于picture.length，则跳到1号位置
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
                //设置当前显示哪个view
                if(ViewPager.SCROLL_STATE_IDLE == state){
                    if(isChange){
                        isChange = false;
                        setCurrentItem(mposition , false);
                    }
                }
            }
        });

        //因为0号并不是我们需要的“第一张图片”，而是为了循环准备的最末尾的图片
        // 因此需要跳到我们的“第一张图片”，即1号位置
        this.setCurrentItem(mposition, false);
    }

    private void addImages(){
        ImageView tmp;
        tmp = new ImageView(context);
        tmp.setImageResource(picture[picture.length - 1]);
        imageList[0] = tmp;

        for(int i = 0; i < picture.length; i++){
            tmp = new ImageView(context);
            tmp.setImageResource(picture[i]);
            imageList[i + 1] = tmp;
        }

        tmp = new ImageView(context);
        tmp.setImageResource(picture[0]);
        imageList[imageList.length - 1] = tmp;
    }
}
