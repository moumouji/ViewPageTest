package hp.viewpagetest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hp on 2015/7/13.
 */
public class MyListAdapter extends BaseAdapter {

    static int[] picture =new int[] {R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7,R.drawable.b1, R.drawable.b2, R.drawable.b3, R.drawable.b4, R.drawable.b5, R.drawable.b6, R.drawable.b7};
    String[] txt = new String[]{"禽兽","饮料","思考","吃面","你特么逗我","莱雪条","抱胸","禽兽2","饮料2","思考2","吃面2","你特么逗我2","莱雪条2","抱胸2"};
    LayoutInflater layoutInflater;
    Context context;

    public MyListAdapter(Context context){
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return picture.length;
    }

    @Override
    public Object getItem(int position) {
        return txt[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.listitem, null, false);
            viewHolder.iv = (ImageView) convertView.findViewById(R.id.headphoto);
            viewHolder.tv = (TextView) convertView.findViewById(R.id.tv);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.iv.setImageResource(picture[position]);
        viewHolder.tv.setText(txt[position]);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, txt[position], Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }
}

class ViewHolder{
    TextView tv;
    ImageView iv;
}
