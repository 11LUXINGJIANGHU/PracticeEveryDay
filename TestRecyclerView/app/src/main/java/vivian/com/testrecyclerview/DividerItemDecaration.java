package vivian.com.testrecyclerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by asus1 on 2017/7/31.
 */

public class DividerItemDecaration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };
    public  static  final  int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;
    public  static  final  int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable divider;
    private int orientation;

    public DividerItemDecaration(Context context,int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        divider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);

    }

    public void setOrientation(int orientation){
        if(orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST){
            throw  new IllegalArgumentException("invalid orientation");

        }

        this.orientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if(orientation == VERTICAL_LIST){
            drawVertical(c,parent);
        }else {
            drawHorizontal(c,parent);
        }

    }

    public void drawVertical(Canvas c,RecyclerView parent){
        final  int left = parent.getPaddingLeft();
        final  int riht  = parent.getWidth()-parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for(int i  = 0;i<childCount;i++){
            final View child = parent.getChildAt(i);

            final  RecyclerView.LayoutParams params = (RecyclerView.LayoutParams)
                                       child.getLayoutParams();
            final int top  = child.getBottom()+params.bottomMargin;
            final  int bottom = top +divider.getIntrinsicHeight();
            divider.setBounds(left,top,riht,bottom);
            divider.draw(c);
            Log.d("wnw",String.valueOf(top)+"  "+String.valueOf(bottom)+"  "+String.valueOf(left)+
                    "   "+String.valueOf(riht) );

        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + divider.getIntrinsicHeight();
            divider.setBounds(left, top, right, bottom);
            divider.draw(c);
            Log.d("wnw",String.valueOf(top)+"  "+String.valueOf(bottom)+"  "+String.valueOf(left)+
            "   "+String.valueOf(right) +"  "+String.valueOf(i));
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        if(orientation == VERTICAL_LIST){
            outRect.set(0,0,0,divider.getIntrinsicHeight());
        }else {
            outRect.set(0,0,divider.getIntrinsicWidth(),0);
        }
        Log.d("offset","ooooooooooooo") ;
    }
}
