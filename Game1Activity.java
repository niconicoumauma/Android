/*省略*/


public class Game1Activity extends AppCompatActivity {

    //蛇口関連
    int rotate_count=0;
    private ImageView iv_jaguchi;
    private Bitmap bm_jaguchi;
    //蛇口のサイズ取得
    private int image_height;
    private int image_width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game1);

        Button bt_rotate = (Button)findViewById(R.id.bt_rotate);
        bt_rotate.setOnClickListener(new rotateListener());

        iv_jaguchi = (ImageView)findViewById(R.id.iv_jaguchi);
        bm_jaguchi = BitmapFactory.decodeResource(getResources(), R.drawable.jaguti);
        iv_jaguchi.setImageBitmap(bm_jaguchi);


    }

    class rotateListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            image_height = bm_jaguchi.getHeight();
            image_width = bm_jaguchi.getWidth();
            Matrix matrix = new Matrix();
            matrix.setRotate(-15*rotate_count, 270, 270);
            Bitmap bm_rotated = Bitmap.createBitmap(bm_jaguchi, 0, 0, image_width, image_height, matrix, true);
            iv_jaguchi.setImageBitmap(bm_rotated);
            rotate_count++;
        }
    }

}
