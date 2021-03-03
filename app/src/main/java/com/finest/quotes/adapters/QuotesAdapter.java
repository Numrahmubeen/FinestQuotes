package com.finest.quotes.adapters;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.finest.quotes.R;
import com.finest.quotes.models.Quotes;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuotesAdapter extends RecyclerView.Adapter  {
    private List<Quotes> quotesList= new ArrayList<Quotes>();
    private Context context;
    private Activity activity;
    private int myColor;

    public QuotesAdapter(List<Quotes> quotesList, Context context, Activity activity) {
        this.quotesList = quotesList;
        this.context = context;
        this.activity = activity;
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quotes_item_rv,parent,false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vholder, int position) {
        Quotes result = quotesList.get(position);
        QuoteViewHolder holder = (QuoteViewHolder) vholder;


        holder.textQuote_tv.setText(result.getText());
        holder.author_tv.setText(String.valueOf(result.getAuthor()));
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        holder.relativeLayout.setBackgroundColor(color);
        myColor = color;

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                holder.relativeLayout.setBackgroundColor(color);
                myColor = color;
            }
        });
        holder.textQuote_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random r = new Random();
                int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                holder.textQuote_tv.setTextColor(color);
                ImageViewCompat.setImageTintList(holder.quote_iv, ColorStateList.valueOf(color));
                holder.author_tv.setTextColor(color);
            }
        });
        holder.copy_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.textQuote_tv.getText().toString();
                ClipboardManager clipboard;
                clipboard = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", text);
                if (clipboard == null || clip == null)
                    return;
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Copied text Successfully", Toast.LENGTH_SHORT).show();
            }
        });
        holder.whatsapp_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.textQuote_tv.getText().toString();
                Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
                whatsappIntent.setType("text/plain");
                whatsappIntent.setPackage("com.whatsapp");
                whatsappIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                whatsappIntent.putExtra(Intent.EXTRA_TEXT, text);
                try {
                    context.startActivity(whatsappIntent);
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(context, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();                }
            }
        });
        holder.share_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = holder.textQuote_tv.getText().toString();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                sendIntent.putExtra(Intent.EXTRA_TEXT, text);
                sendIntent.setType("text/plain");
                context.startActivity(sendIntent);
            }
        });
        holder.save_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //for greater than lolipop versions we need the permissions asked on runtime
                //so if the permission is not available user will go to the screen to allow storage permission
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(context,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity, new String[]
                            {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                    return;
                }
            Bitmap bitmap = getScreenShot(holder.screenShot_rl,myColor) ;
                try {
                    saveImage(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private Bitmap  getScreenShot(View view, int color) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas =new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();

        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else
        {
            canvas.drawColor(color);
        }
        view.draw(canvas);
        return returnedBitmap;
    }


    @SuppressLint("MissingPermission")
    private void saveImage(Bitmap bitmap) throws IOException {
        OutputStream outputStream;
        boolean isSaved;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContentResolver resolver = context.getContentResolver();
            ContentValues contentValues = new ContentValues();
            contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis() + ".png");
            contentValues.put(MediaStore.MediaColumns.MIME_TYPE, "image/png");
            contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES);
            Uri uri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            outputStream = resolver.openOutputStream(uri);
        } else {
            String imagesDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
            File file = new File(imagesDir, System.currentTimeMillis() + ".png");
            outputStream = new FileOutputStream(file);
        }
        isSaved = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        outputStream.flush();
        outputStream.close();
        if (isSaved) {
            Toast.makeText(context, "Download Successful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }

    }




    @Override
    public int getItemCount() {
        return quotesList.size();
    }

    public class QuoteViewHolder extends RecyclerView.ViewHolder {

        private TextView textQuote_tv, author_tv, save_tv, copy_tv, share_tv, whatsapp_tv;
        private ProgressBar mProgress;
        private RelativeLayout screenShot_rl;
        private RelativeLayout  relativeLayout;
        private ImageView quote_iv;

        public QuoteViewHolder(View itemView) {
            super(itemView);

            textQuote_tv = (TextView) itemView.findViewById(R.id.quote_tv);
            author_tv = itemView.findViewById(R.id.quote_author_tv);
            save_tv = itemView.findViewById(R.id.rvItem_save_tv);
            share_tv = itemView.findViewById(R.id.rvItem_share_tv);
            whatsapp_tv = itemView.findViewById(R.id.rvItem_whatsapp_tv);
            copy_tv = itemView.findViewById(R.id.rvItem_copy_tv);
            quote_iv = itemView.findViewById(R.id.quote_iv);

            mProgress = (ProgressBar) itemView.findViewById(R.id.quote_pb);
            relativeLayout = itemView.findViewById(R.id.quote_rl);
            screenShot_rl = itemView.findViewById(R.id.screenShot_cl);
        }
    }


}

