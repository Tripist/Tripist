package com.example.tripist;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;

public class DatabaseAccessObject extends AppCompatActivity {

    private static SQLiteDatabase database;

    public boolean deleteFromMylocations(int position) {
        try {
            System.out.println("Delete işlemi başarılı değil");
            String sil = "DELETE FROM my_locations Where  id = position ";
            System.out.println("Delete işlemi başarılı değil");
            /*SQLiteStatement sqLiteStatement = database.compileStatement(sil);
            System.out.println("Delete işlemi başarılı değil");
            sqLiteStatement.execute();
            System.out.println("Delete işlemi başarılı değil");*/
            return true;

        }

        catch (Exception e)
        {
            e.printStackTrace();
            return false;

        }



    }
}
