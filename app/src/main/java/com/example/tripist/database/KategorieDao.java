package com.example.tripist.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.example.tripist.adapters.MyFavAdapter;
import com.example.tripist.models.Foods;
import com.example.tripist.models.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import static com.example.tripist.controller.navigation.SettingsFragment.appLanguage;
import static com.example.tripist.database.LocalizationHelper.EnLanguage;
import static com.example.tripist.database.LocalizationHelper.FoodsApp_language;
import static com.example.tripist.database.LocalizationHelper.MyLocApp_language;
import static com.example.tripist.database.LocalizationHelper.app_language;


public class KategorieDao {


    public ArrayList<Places> Mylocations(DatabaseHelper databaseHelper) {
        ArrayList<Places> lstPlaces = new ArrayList<>();
        lstPlaces.clear();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM my_locations", null);


        int nameIx = app_language();
        int latitudeIx = cursor.getColumnIndex("latitude");
        int longitudeIx = cursor.getColumnIndex("longitude");

        while (cursor.moveToNext()) {

            String nameFromDatabase = cursor.getString(nameIx);
            String latitudeFromDatabase = cursor.getString(latitudeIx);
            String longitudeFromDatabase = cursor.getString(longitudeIx);

            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);

            Places place = new Places(nameFromDatabase, latitude, longitude);


            lstPlaces.add(place);

        }

        cursor.close();
        return lstPlaces;
    }

    public ArrayList<Places> Historical_Places(DatabaseHelper databaseHelper) {

        ArrayList<Places> lstPlaces = new ArrayList<>();
        lstPlaces.clear();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM historical_places", null);


        int nameIx = app_language();
        int latitudeIx = cursor.getColumnIndex("latitude");
        int longitudeIx = cursor.getColumnIndex("longitude");
        int imageIX = cursor.getColumnIndex("image");

        while (cursor.moveToNext()) {

            String nameFromDatabase = cursor.getString(nameIx);
            String latitudeFromDatabase = cursor.getString(latitudeIx);
            String longitudeFromDatabase = cursor.getString(longitudeIx);
            String image = cursor.getString(imageIX);
            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);

            Places place = new Places(nameFromDatabase, latitude, longitude, image);


            lstPlaces.add(place);

        }

        cursor.close();
        return lstPlaces;
    }

    public ArrayList<Places> KategorieList(DatabaseHelper databaseHelper, String tablename) {

        ArrayList<Places> lstPlaces = new ArrayList<>();
        lstPlaces.clear();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + tablename + " ", null);

        int nameIx = app_language();
        int latitudeIx = cursor.getColumnIndex("latitude");
        int longitudeIx = cursor.getColumnIndex("longitude");
        int imageIX = cursor.getColumnIndex("image");

        while (cursor.moveToNext()) {

            String nameFromDatabase = cursor.getString(nameIx);
            String latitudeFromDatabase = cursor.getString(latitudeIx);
            String longitudeFromDatabase = cursor.getString(longitudeIx);
            String image = cursor.getString(imageIX);
            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);

            Places place = new Places(nameFromDatabase, latitude, longitude, image);


            lstPlaces.add(place);

        }

        cursor.close();
        return lstPlaces;
    }

    public ArrayList<Places> MyFavourites(DatabaseHelper databaseHelper) {

        ArrayList<Places> lstPlaces = new ArrayList<>();
        lstPlaces.clear();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM my_favourites", null);

        int nameIx = app_language();
        int latitudeIx = cursor.getColumnIndex("latitude");
        int longitudeIx = cursor.getColumnIndex("longitude");
        int imageIX = cursor.getColumnIndex("image");

        while (cursor.moveToNext()) {

            String nameFromDatabase = cursor.getString(nameIx);
            String latitudeFromDatabase = cursor.getString(latitudeIx);
            String longitudeFromDatabase = cursor.getString(longitudeIx);
            String image = cursor.getString(imageIX);
            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);

            Places place = new Places(nameFromDatabase, latitude, longitude, image);


            lstPlaces.add(place);

        }

        cursor.close();
        return lstPlaces;
    }

    public ArrayList<Foods> FoodsList(DatabaseHelper databaseHelper) {
        ArrayList<Foods> lstPlaces = new ArrayList<>();
        lstPlaces.clear();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM foods", null);


        int nameIx = FoodsApp_language();
        int imageIX = cursor.getColumnIndex("image");

        while (cursor.moveToNext()) {

            String nameFromDatabase = cursor.getString(nameIx);
            String image = cursor.getString(imageIX);


            Foods food = new Foods(nameFromDatabase, image);


            lstPlaces.add(food);

        }

        cursor.close();
        return lstPlaces;
    }


    public void addMylocations(DatabaseHelper databaseHelper, String name, Double latitude, Double longitude) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        values.put("isim", name);
        database.insertOrThrow("my_locations", null, values);
        database.close();
    }

    public void addMylocationsOthers(DatabaseHelper databaseHelper, String name, Double latitude, Double longitude) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", name);
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        values.put("isim", name);
        database.insertOrThrow("my_locations", null, values);
        database.close();
    }

    public void addMarker(DatabaseHelper databaseHelper, GoogleMap mMap, String tablename) {

        mMap.clear();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + tablename + " ", null);

        int nameIx = app_language();
        int latitudeIX = cursor.getColumnIndex("latitude");
        int longitudeIX = cursor.getColumnIndex("longitude");

        while (cursor.moveToNext()) {
            String nameFromDatabase = cursor.getString(nameIx);
            String latitudeFromDatabase = cursor.getString(latitudeIX);
            String longitudeFromDatabase = cursor.getString(longitudeIX);

            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);
            LatLng latLng = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title(nameFromDatabase));
        }
        cursor.close();

    }

    public void add_MyLocMarker(DatabaseHelper databaseHelper, GoogleMap mMap) {

        // mMap.clear();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM my_locations", null);

        int nameIx = app_language();
        int latitudeIX = cursor.getColumnIndex("latitude");
        int longitudeIX = cursor.getColumnIndex("longitude");

        while (cursor.moveToNext()) {
            String nameFromDatabase = cursor.getString(nameIx);
            String latitudeFromDatabase = cursor.getString(latitudeIX);
            String longitudeFromDatabase = cursor.getString(longitudeIX);

            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);
            LatLng latLng = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker
                    (BitmapDescriptorFactory.HUE_BLUE)).alpha(0.7f).position(latLng).title(nameFromDatabase));
        }
        cursor.close();

    }


    public void deletePlace(DatabaseHelper databaseHelper, String name) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        if (EnLanguage()) {
            db.delete("my_locations", "name=?", new String[]{name});
        } else {
            db.delete("my_locations", "isim=?", new String[]{name});
        }

        db.close();

    }


    public void updatePlace(DatabaseHelper databaseHelper, String name, String newName) {
        String newNamee = newName.substring(0, 1).toUpperCase() + newName.substring(1);
        SQLiteDatabase dbx = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put("name", newNamee);
        values.put("isim", newNamee);

        dbx.update("my_locations", values, "name=?", new String[]{name});
        dbx.close();

    }

    public void fav(DatabaseHelper databaseHelper, String name, String tablename) {

        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (EnLanguage()) {
            Cursor cursor = database.rawQuery("SELECT * FROM " + tablename + " where name=?", new String[]{name});
            int nameIx = cursor.getColumnIndex("name");
            int latitudeIx = cursor.getColumnIndex("latitude");
            int longitudeIx = cursor.getColumnIndex("longitude");
            int imageIX = cursor.getColumnIndex("image");
            int isimIx = cursor.getColumnIndex("isim");
            while (cursor.moveToNext()) {

                String nameFromDatabase = cursor.getString(nameIx);
                String latitudeFromDatabase = cursor.getString(latitudeIx);
                String longitudeFromDatabase = cursor.getString(longitudeIx);
                String isimFromDatabase = cursor.getString(isimIx);
                String image = cursor.getString(imageIX);

                values.put("name", nameFromDatabase);
                values.put("latitude", latitudeFromDatabase);
                values.put("longitude", longitudeFromDatabase);
                values.put("image", image);
                values.put("isim", isimFromDatabase);
                database.insertOrThrow("my_favourites", null, values);
            }
            cursor.close();
        } else {
            Cursor cursor = database.rawQuery("SELECT * FROM " + tablename + " where isim=?", new String[]{name});
            int nameIx = cursor.getColumnIndex("name");
            int latitudeIx = cursor.getColumnIndex("latitude");
            int longitudeIx = cursor.getColumnIndex("longitude");
            int imageIX = cursor.getColumnIndex("image");
            int isimIx = cursor.getColumnIndex("isim");
            while (cursor.moveToNext()) {

                String nameFromDatabase = cursor.getString(nameIx);
                String latitudeFromDatabase = cursor.getString(latitudeIx);
                String longitudeFromDatabase = cursor.getString(longitudeIx);
                String image = cursor.getString(imageIX);
                String isimFromDatabase = cursor.getString(isimIx);

                values.put("name", nameFromDatabase);
                values.put("latitude", latitudeFromDatabase);
                values.put("longitude", longitudeFromDatabase);
                values.put("image", image);
                values.put("isim", isimFromDatabase);
                database.insertOrThrow("my_favourites", null, values);
            }
            cursor.close();
        }
        database.close();
    }

    public void foodFav(DatabaseHelper databaseHelper, String name, String tablename) {

        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        if (EnLanguage()) {
            Cursor cursor = database.rawQuery("SELECT * FROM " + tablename + " where name=?", new String[]{name});
            int nameIx = cursor.getColumnIndex("name");
            int imageIX = cursor.getColumnIndex("image");
            int isimIx = cursor.getColumnIndex("isim");
            while (cursor.moveToNext()) {

                String nameFromDatabase = cursor.getString(nameIx);
                String latitudeFromDatabase = "0";
                String longitudeFromDatabase = "0";
                String isimFromDatabase = cursor.getString(isimIx);
                String image = cursor.getString(imageIX);

                values.put("name", nameFromDatabase);
                values.put("latitude", latitudeFromDatabase);
                values.put("longitude", longitudeFromDatabase);
                values.put("image", image);
                values.put("isim", isimFromDatabase);
                database.insertOrThrow("my_favourites", null, values);
            }
            cursor.close();
        } else {
            Cursor cursor = database.rawQuery("SELECT * FROM " + tablename + " where isim=?", new String[]{name});
            int nameIx = cursor.getColumnIndex("name");
            int imageIX = cursor.getColumnIndex("image");
            int isimIx = cursor.getColumnIndex("isim");
            while (cursor.moveToNext()) {

                String nameFromDatabase = cursor.getString(nameIx);
                String latitudeFromDatabase = "0";
                String longitudeFromDatabase = "0";
                String image = cursor.getString(imageIX);
                String isimFromDatabase = cursor.getString(isimIx);

                values.put("name", nameFromDatabase);
                values.put("latitude", latitudeFromDatabase);
                values.put("longitude", longitudeFromDatabase);
                values.put("image", image);
                values.put("isim", isimFromDatabase);
                database.insertOrThrow("my_favourites", null, values);
            }
            cursor.close();
        }
        database.close();
    }

    public void unfav(DatabaseHelper databaseHelper, String name) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        if (EnLanguage()) {
            db.delete("my_favourites", "name=?", new String[]{name});
        } else {
            db.delete("my_favourites", "isim=?", new String[]{name});
        }

        db.close();
    }

    public boolean DataExists(DatabaseHelper databaseHelper, String fieldValue) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if (EnLanguage()) {
            Cursor cursor = database.rawQuery("SELECT * FROM my_favourites where name=?", new String[]{fieldValue});

            if (cursor.getCount() <= 0) {
                cursor.close();

                return false;

            }
            cursor.close();

            return true;
        } else {
            Cursor cursor = database.rawQuery("SELECT * FROM my_favourites where isim=?", new String[]{fieldValue});

            if (cursor.getCount() <= 0) {
                cursor.close();

                return false;

            }
            cursor.close();

            return true;
        }
    }

    public boolean MyLocationsDataExists(DatabaseHelper databaseHelper, String fieldValue) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        if (EnLanguage()) {
            Cursor cursor = database.rawQuery("SELECT * FROM my_locations where name=?", new String[]{fieldValue});

            if (cursor.getCount() <= 0) {
                cursor.close();

                return false;

            }
            cursor.close();

            return true;
        } else {
            Cursor cursor = database.rawQuery("SELECT * FROM my_locations where isim=?", new String[]{fieldValue});

            if (cursor.getCount() <= 0) {
                cursor.close();

                return false;

            }
            cursor.close();

            return true;
        }

    }

    //todo

    public void refreshTable(DatabaseHelper databaseHelper, ArrayList<Places> mList) {
        mList.clear();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM my_locations", null);

        int nameIx = app_language();
        int latitudeIx = cursor.getColumnIndex("latitude");
        int longitudeIx = cursor.getColumnIndex("longitude");

        while (cursor.moveToNext()) {

            String nameFromDatabase = cursor.getString(nameIx);
            String latitudeFromDatabase = cursor.getString(latitudeIx);
            String longitudeFromDatabase = cursor.getString(longitudeIx);

            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);

            Places place = new Places(nameFromDatabase, latitude, longitude);


            mList.add(place);

        }
        cursor.close();
    }

    /// ADD DATA
    public void add_religions(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO religions(name,latitude,longitude,image,isim) VALUES ('Suleymaniye Mosque', '41.016177', '28.964153','suleymaniyemin','Süleymaniye Camii')," +
                "('Blue Mosque', '41.005321', '28.976725','sultanahmet','Sultanahmet Camii'),('Eyüp Sultan Mosque', '41.048080','28.933879','eyupsultanmosque','Eyüp Sultan Camii')," +
                "('Corlulu Ali Pasa Medresesi', '41.008986', '28.968498','corlulualipasamedresesi','Corlulu Ali Pasa Medresesi')," +
                "('Mecidiye(Ortaköy) Mosque', '41.047253' ,'29.027009','ortakoymosque','Mecidiye(Ortaköy) Camii'),('The tomb of Hz. Yuşa', '41.162312', '29.084935','hzyusamin','Hz.Yuşa Tepesi')," +
                "('Aya Yorgi Fener Rum Ortodoks Church', '41.029232', '28.951637','ayayorgikilisesi','Aya Yorgi Fener Rum Ortodoks Kilisesi')," +
                "('Saint Antoinie Church', '41.032310', '28.977131','stantoiniechurch','Saint Antoinie Kilisesi'),('Virgin Mary Church' , '41.037039', '28.978541','virginmin','Virgin Mary Kilisesi')," +
                "('Neve Shalom Synagogue' , '41.026877', '28.972623','nevesalommin','Neve Shalom Sinagogu')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM religions Where id Not in (SELECT MIN(id) from religions Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();

    }

    public void add_parks(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO parks_gardens(name,latitude,longitude,image,isim) VALUES ('Yıldız Park', '41.049273', '29.015274','yildizpark','Yıldız Parkı')," +
                "('Emirgan Park', '41.108846', '29.053083','emirgankorusu','Emirgan Parkı')," +
                "('Gulhane Park', '41.013328', '28.981384','gulhanepark','Gülhane Parkı'),('Şile Saklıgöl', '41.1194696', '29.591484', 'sakligol','Şile Saklıgöl')," +
                "('Polonezkoy Nature Park', '41.108598', '29.166018','polonezkoynaturepark','Polonezkoy Nature Park'),('Ozgurluk Park', '40.979074', '29.059408','ozgurlukpark','Ozgurluk Parkı')," +
                "('Mihrabat Korusu', '41.096802', '29.068632','mihrabatpark','Mihrabat Korusu'),('Fethi Paşa Korusu', '41.0967859', '29.0247348','fethipasa','Fethi Paşa Korusu')," +
                "('Çamlıca Tepesi', '41.0277528', '29.06622','camlicatepesi','Çamlıca Tepesi'),('Beykoz Korusu', '41.1331274', '29.0963995','beykoz_korusu','Beykoz Korusu')," +
                "('Belgrad Forest', '41.1871997', '28.965086','belgrad','Belgrad Ormanı'),('Atatürk Arboretum', '41.1766262', '28.9831888','ataturkarboretumu','Atatürk Arboretumu')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM parks_gardens Where id Not in (SELECT MIN(id) from parks_gardens Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();


    }


    public void add_bazaarmarkets(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO bazaar_markets(name,latitude,longitude,image,isim) VALUES ('Spice Bazaar', '41.016536', '28.970594','spicebazaar','Mısır Çarşısı')," +
                "('Eminönü Bazaar', '41.018297', '28.970953','eminonubazaar','Eminönü Çarşısı'),('Grand Bazaar', '41.010673', '28.968063','grandbazaar','Kapalı Çarşı')," +
                "('Bakirkoy Bit Pazarı Ve Sanatkarlar Çarşısı', '40.979889', '28.874933','bakirkoy_bitpazari','Bakirkoy Bit Pazarı Ve Sanatkarlar Çarşısı')," +
                "('Üsküdar Fish Market', '41.024469', '29.015983','uskudarfishmarket','Üsküdar Balık Çarşısı'),('Bakirkoy Underground Bazaar' , '40.980697', '28.873803','bakirkoy_undergroundbazaar','Bakirkoy Yeraltı Çarşısı')," +
                "('Great Sinan Bazaar', '41.023742', '29.015950','mimarsinanbazaar','Mimar Sinan Çarşısı'),('Besiktas Fish Market', '41.043512', '29.004666','besiktasfishmarket','Besiktas Balık Çarşısı')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM bazaar_markets Where id Not in (SELECT MIN(id) from bazaar_markets Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();

    }

    public void add_historicalplaces(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO historical_places (name, latitude, longitude,image,isim) VALUES ('Hagia Sophia Mosque' , '41.008587', '28.980170','ayasofya','Ayasofya Camii')," +
                "('Beylerbeyi Palace', '41.042673', '29.039887','beylerbeyimin','Beylerbeyi Sarayı'),('Galata Tower', '41.025676', '28.974129','galatamin','Galata Kulesi')," +
                "('Hidiv Kasrı', '41.104619', '29.075520','hidivmin','Hidiv Kasrı'),('Dolmabahce Palace', '41.039168', '29.000454','dolmabahcemin','Dolmabahçe Sarayı')," +
                "('The Tomb of Haseki Hurrem Sultan' , '41.008611', '28.941855','thetombof_hurremmin','Haseki Hürrem Sultan Hamamı')," +
                "('Sultanahmet Square' , '41.005752', '28.975445','sultanahmet_square','Sultanahmet Meydanı')," +
                "('The Tomb of Great Sinan', '41.017290', '28.963866','thetombofgreatsinanmin','Mimar Sİnan Türbesi')," +
                "('Maiden’s Tower', '41.021124' ,'29.004111','kizkulesimin','Kız Kulesi'),('Rumeli Fortress', '41.0881692', '29.0406517','rumelihisarimin','Rumeli Hisarı')," +
                "('Anatolian Fortress', '41.0871851' ,'29.0629042','anadolu_hisarmin','Anadolu Hisarı'),('Topkapı Palace', '41.0111792', '28.9808382','topkapimin','Topkapı Sarayı')," +
                "('Çırağan Palace', '41.0434694' ,'29.0134867','ciraganmin','Çırağan Sarayı'),('Adile Sultan Palace', '41.072334', '29.0556518','adilesultanmin','Adile Sultan Sarayı')," +
                "('Haydarpaşa Terminal', '40.9968247' ,'29.0171052','haydarpasamin','Haydarpaşa Terminali'),('Castle of the Seven Towers', '40.9938545' ,'28.9210783','yedikule_zindanlarmin','Yedikule Zindanları')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM historical_places Where id Not in (SELECT MIN(id) from historical_places Group by name)";

        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);

        sqLiteStatement.execute();
        sqLiteStatement1.execute();


    }

    public void add_islandsandbeachs(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO island_beaches (name, latitude, longitude,image,isim) VALUES ('Büyükada', '40.856003', '29.119722','buyukada','Büyükada')," +
                "('Heybeliada' ,'40.873304', '29.089633','heybeliada','Heybeliada'),('Kınalıada', '40.909127', '29.053049','kinaliada','Kınalıada')," +
                "('Sedef Island', '40.850309', '29.146068','sedefisland','Sedef Adası'),('Burgaz Island', '40.879934', '29.068489','burgazada','Burgazada')," +
                "('Kınalıada Public Beach', '40.906846', '29.044361','kinaliada_publicbeach','Kınalıada Halk Plajı')," +
                "('Ağva Beach', '41.138222', '29.8400537','agvap','Ağva Plajı'),('Ağlayan Kayalar', '41.1748855', '29.6274622','aglayankaya','Ağlayan Kayalar')," +
                "('Caddebostan Beach', '40.963336', '29.058661','caddebostanbeach','Caddebostan Plajı'),('Suma Beach', '41.246143', '29.004559','sumabeach','Suma Plajı')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM island_beaches Where id Not in (SELECT MIN(id) from island_beaches Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();

    }

    public void add_museums(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO museums(name,latitude,longitude,image,isim)  VALUES ('Tekfur Palace Museum', '41.033806', '28.940487','tekfurpalacemuseum','Tekfur Sarayı Müzesi')," +
                "('Rahmi Koc Museum', '41.041822', '28.949923','rahmikocmuseum','Rahmi Koç Müzesi'),('Topkapi Palace Museum', '41.011681', '28.983690','topkapimuseum','Topkapi Sarayı Müzesi')," +
                "('Istanbul Modern Art Museum', '41.030076', '28.973875','modern','Istanbul Modern Sanat Müzesi'),('Basilica Cistern', '41.008538', '28.978500','yerebatan_sarnici','Yerebatan Sarnıcı')," +
                "('Tiled Kiosk', '41.012014', '28.981671','tiledkiosk','Çinili Köşk Müzesi'),('Istanbul Toy Museum', '40.975939', '29.071127','istanbul_toy_museum','Istanbul Oyuncak Müzesi')," +
                "('Isbank Museum', '41.016246', '28.973187','isbankmuseum','Türkiye İş Bankası Müzesi'),('The Panorama 1453 Museum', '41.018350', '28.920981','panaroma_1453_museum','Panorama 1453 Müzesi')," +
                "('Sakıp Sabancı Museum', '41.105660', '29.056752','sabancimuseum','Sakıp Sabancı Müzesi'),('Hagia Irene Church', '41.009626', '28.981216','hagiairene','Aya İrini Kilisesi')," +
                "('Miniatürk', '41.0594819', '28.9468834','miniaturk','Miniatürk'),('Istanbul Archaeological Museum', '41.0116855', '28.9791418','arkeoloji','Istanbul Arkeoloji Müzesi')," +
                "('Turkish and Islamic Arts Museum', '41.0062236', '28.9747201','turkislammuseum','Türk ve İslam Eserleri Müzesi'),('Madame Tussauds Istanbul', '41.0345876', '28.9775171','madamt','Madame Tussauds Istanbul')," +
                "('SALT Galata', '41.0239214', '28.964734','saltgalata','SALT Galata'),('Pera Museum', '41.031816', '28.9730116','peramuseum','Pera Müzesi')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM museums Where id Not in (SELECT MIN(id) from museums Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();

    }

    public void add_squares(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO squares (name, latitude, longitude,image,isim) VALUES ('Taksim Square', '41.036991', '28.985081','taksim','Taksim Meydanı')," +
                "('Kadıköy Square', '40.992127', '29.023861','kadikoy_square','Kadıköy Meydanı'),('Sultanahmet Square', '41.006661', '28.976174','sultanahmet_square','Sultanahmet Meydanı')," +
                "('Eminönü Square', '41.017447', '28.970301','eminonu','Eminönü Meydanı')," + "('Beyazıt Square', '41.010470' , '28.963896','beyazitsquare','Beyazıt Meydanı')," +
                "('Yenikapı IDO Square', '41.002250', '28.956403','yenikapi_square','Yenikapı IDO Meydanı'),('Cumhuriyet Square', '40.981070', '28.873726','cumhuriyetsquare','Cumhuriyet Meydanı')," +
                "('Beşiktaş Square', '41.043781', '29.0051786','besiktasmeydan','Beşiktaş Meydanı'),('Üsküdar Square', '41.0255311', '29.004517','uskudar','Üsküdar Meydanı')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM squares Where id Not in (SELECT MIN(id) from squares Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();
    }

    public void add_food(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO foods(name,image,isim)  VALUES ('Fish and Bread','fishandbread','Balık Ekmek'),('Acıbadem Cookies','acibademcookie','Acıbadem Kurabiyesi')," +
                "('Black Sea Pita','blackseapita','Karadeniz Pidesi'),('Kanlıca Yogurt','kanlicayogurt','Kanlıca Yoğurt'),('Coupe Grillée','kupgriye','Kup Griye'),('Stuffed Mussels','stuffedmussels','Midye dolma'),('Ortaköy Baked Potato','ortakoybakedpotato','Ortaköy Kumpir')," +
                "('Wet Burger','wetburger','Islak Hamburger'),('Doner','doner','Döner'),('Tantuni','tantuni','Tantuni'),('Waffle','waffle','Waffle'),('Borek','borek','Börek'),('Ottoman Paste','macun','Osmanlı Macun'),('Turkish Bagel','simit','Simit')," +
                "('Pickle Juice','picklejuice','Turşu Suyu'),('Iskender','iskender','İskender'),('Kokoreç','kokorec','Kokoreç'),('Chicken and Rice','chickenandrice','Tavuk Pilav')," +
                "('Dürüm','durum','Dürüm'),('Halka Tatlısı','halka','Halka Tatlısı'),('Baklava','baklava','Baklava'),('Corn','corn','Mısır'),('Chesnuts','kestane','Kestane')," +
                "('Boza','boza','Boza'),('Köfte Sucuk Ekmek','koftesucukekmek','Köfte Sucuk Ekmek'),('Turkish Breakfast','turkishbreakfast','Türk Kahvaltısı'),('Çiğ Köfte','cigkofte','Çiğ Köfte')," +
                "('Turkish Puddings','pudding','Türk Pudingi'),('Turkish Coffee','turkishcoffee','Türk Kahvesi'),('Turkish Ice Cream','dondurma','Dondurma'),('Turnip Juice','salgam','Şalgam'),('Ayran','ayran','Ayran')," +
                "('Turkish Delight','lokum','Türk Lokumu'),('Rakı','raki','Rakı')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM foods Where id Not in (SELECT MIN(id) from foods Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();


    }

}