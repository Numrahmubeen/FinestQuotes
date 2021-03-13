package com.dailyinspirationalquotes.lifequotes.adapters;

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
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.dailyinspirationalquotes.lifequotes.R;
import com.dailyinspirationalquotes.lifequotes.models.Quotes;
import com.github.clans.fab.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuotesAdapter extends RecyclerView.Adapter {
    private List<Quotes> quotesList = new ArrayList<Quotes>();
    private Context context;
    private int myColor;

    public QuotesAdapter(Context context) {
        this.context = context;
    }

    public void addQuotes(List<Quotes> quotesList) {
        this.quotesList = quotesList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public QuoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cust_quote, parent, false);
        return new QuoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder vholder, int position) {
        Quotes result = quotesList.get(position);
        QuoteViewHolder holder = (QuoteViewHolder) vholder;


        holder.textQuote_tv.setText(result.getText());
        holder.author_tv.setText(String.valueOf(result.getAuthor()));

        //Change color on quote click
        holder.textQuote_tv.setOnClickListener(v -> {
            int randomColor = getRandomColor();
            holder.textQuote_tv.setTextColor(randomColor);
            holder.author_tv.setTextColor(randomColor);
            holder.quote_iv.setColorFilter(randomColor);
        });

        //Change background color
        holder.screenShot_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.screenShot_rl.setBackgroundColor(getRandomColor());
            }
        });

        //Copy fab click listener
        holder.copy_fab.setOnClickListener(v -> {
            String text = holder.textQuote_tv.getText().toString();
            ClipboardManager clipboard;
            clipboard = (ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("label", text);
            if (clipboard == null || clip == null)
                return;
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "Copied text Successfully", Toast.LENGTH_SHORT).show();
        });

        //Share on whats app fab
        holder.whatsapp_fab.setOnClickListener(v -> {
            String text = holder.textQuote_tv.getText().toString();
            Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
            whatsappIntent.setType("text/plain");
            whatsappIntent.setPackage("com.whatsapp");
            whatsappIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            whatsappIntent.putExtra(Intent.EXTRA_TEXT, text);
            try {
                context.startActivity(whatsappIntent);
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(context, "Whatsapp have not been installed.", Toast.LENGTH_SHORT).show();
            }
        });
        //Share fab click listener
        holder.share_fab.setOnClickListener(v -> {
            String text = holder.textQuote_tv.getText().toString();
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            sendIntent.putExtra(Intent.EXTRA_TEXT, text);
            sendIntent.setType("text/plain");
            context.startActivity(sendIntent);
        });

        //save fab click listener
        holder.save_fab.setOnClickListener(v -> {
            //for greater than lolipop versions we need the permissions asked on runtime
            //so if the permission is not available user will go to the screen to allow storage permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(context,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]
                        {Manifest.permission.WRITE_EXTERNAL_STORAGE}, 101);
                return;
            }
            Bitmap bitmap = getScreenShot(holder.screenShot_rl, myColor);
            try {
                saveImage(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private Bitmap getScreenShot(View view, int color) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();

        if (bgDrawable != null)
            bgDrawable.draw(canvas);
        else {
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

        TextView textQuote_tv, author_tv;
        FloatingActionButton whatsapp_fab, save_fab, copy_fab, share_fab;
        ProgressBar mProgress;
        RelativeLayout screenShot_rl;
        RelativeLayout relativeLayout;
        ImageView quote_iv;

        public QuoteViewHolder(View itemView) {
            super(itemView);

            textQuote_tv = (TextView) itemView.findViewById(R.id.quote_tv);
            author_tv = itemView.findViewById(R.id.quote_author_tv);
            whatsapp_fab = itemView.findViewById(R.id.quote_fab_whatsapp);
            save_fab = itemView.findViewById(R.id.quote_fab_save);
            copy_fab = itemView.findViewById(R.id.quote_fab_copy);
            share_fab = itemView.findViewById(R.id.quote_fab_share);
            quote_iv = itemView.findViewById(R.id.quote_iv);

            mProgress = (ProgressBar) itemView.findViewById(R.id.quote_pb);
            relativeLayout = itemView.findViewById(R.id.quote_rl);
            screenShot_rl = itemView.findViewById(R.id.quote_screenShot_rl);
        }
    }


    private final Random random = new Random();

    private int getRandomColor() {
        int randomRed = random.nextInt(255);
        int randomGreen = random.nextInt(255);
        int randomBlue = random.nextInt(255);

        int finalColor = Color.rgb(randomRed, randomGreen, randomBlue);
        return finalColor;
    }
}

