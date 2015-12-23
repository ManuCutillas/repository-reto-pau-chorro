package reto.android.chorro.pau.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by pauchorroyanguas on 23/12/15.
 */
public class CoverInfo extends View {

    private Paint textoPaint = new Paint();

    public CoverInfo(Context context) {
        super(context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Dibujar aquí
        Paint pincel = new Paint();
        pincel.setColor(Color.BLUE);
        pincel.setStrokeWidth(8);
        pincel.setStyle(Paint.Style.STROKE);
        canvas.drawText("Hola", 0, 0, pincel);

    }
}
