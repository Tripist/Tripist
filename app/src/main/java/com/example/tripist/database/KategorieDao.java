package com.example.tripist.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.example.tripist.models.Places;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;



public class KategorieDao {
    public ArrayList<Places>Mylocations(DatabaseHelper databaseHelper) {
        ArrayList<Places> lstPlaces = new ArrayList<>();
        lstPlaces.clear();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM my_locations",null);

        int nameIx = cursor.getColumnIndex("name");
        int latitudeIx = cursor.getColumnIndex("latitude");
        int longitudeIx = cursor.getColumnIndex("longitude");

        while (cursor.moveToNext()) {

            String nameFromDatabase = cursor.getString(nameIx);
            String latitudeFromDatabase = cursor.getString(latitudeIx);
            String longitudeFromDatabase = cursor.getString(longitudeIx);

            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);

            Places place = new Places(nameFromDatabase,latitude,longitude);


            lstPlaces.add(place);

        }

        cursor.close();
        return lstPlaces;
    }
    public ArrayList<Places>Historical_Places(DatabaseHelper databaseHelper) {

        ArrayList<Places> lstPlaces = new ArrayList<>();
        lstPlaces.clear();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM historical_places",null);

        int nameIx = cursor.getColumnIndex("name");
        int latitudeIx = cursor.getColumnIndex("latitude");
        int longitudeIx = cursor.getColumnIndex("longitude");
        int imageIX = cursor.getColumnIndex("image");

        while (cursor.moveToNext()) {

            String nameFromDatabase = cursor.getString(nameIx);
            String latitudeFromDatabase = cursor.getString(latitudeIx);
            String longitudeFromDatabase = cursor.getString(longitudeIx);
            String image =cursor.getString(imageIX);
            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);

            Places place = new Places(nameFromDatabase,latitude,longitude,image);


            lstPlaces.add(place);

        }

        cursor.close();
        return lstPlaces;
    }


    public void addMylocations(DatabaseHelper databaseHelper,String name, Double latitude , Double longitude){
        SQLiteDatabase database=databaseHelper.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("name",name );
        values.put("latitude",latitude );
        values.put("longitude",longitude );

        database.insertOrThrow("my_locations", null, values);
        database.close();
    }
    public void addMylocationsOthers(DatabaseHelper databaseHelper,String name, Double latitude , Double longitude){
        SQLiteDatabase database=databaseHelper.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("name",name );
        values.put("latitude",latitude );
        values.put("longitude",longitude );

        database.insertOrThrow("my_locations", null, values);
        database.close();
    }

    public void add_marker(DatabaseHelper databaseHelper, GoogleMap mMap) {

        mMap.clear();
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM my_locations", null);

        int nameIX = cursor.getColumnIndex("name");
        int latitudeIX = cursor.getColumnIndex("latitude");
        int longitudeIX = cursor.getColumnIndex("longitude");

        while (cursor.moveToNext()) {
            String nameFromDatabase = cursor.getString(nameIX);
            String latitudeFromDatabase = cursor.getString(latitudeIX);
            String longitudeFromDatabase = cursor.getString(longitudeIX);

            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);
            LatLng latLng = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(latLng).title(nameFromDatabase));
        }
        cursor.close();

    }



    public void deletePlace(DatabaseHelper databaseHelper,String name){
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        db.delete("my_locations", "name=?",new String[] {name} );
        db.close();

    }




    public void updatePlace(DatabaseHelper databaseHelper,String name,String newName){
        String newNamee = newName.substring(0,1).toUpperCase() + newName.substring(1);
        SQLiteDatabase dbx=databaseHelper.getWritableDatabase();

        ContentValues values=new ContentValues();

        values.put("name",newNamee);


        dbx.update("my_locations", values,"name=?",new String[] {name});
        dbx.close();

    }
    public void fav(DatabaseHelper databaseHelper,String name){

        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put("name",name );

        database.insertOrThrow("my_favourites", null, values);
        database.close();

    }
    public void unfav(DatabaseHelper databaseHelper,String name){
        SQLiteDatabase db=databaseHelper.getWritableDatabase();
        db.delete("my_favourites", "name=?",new String[] {name} );
        db.close();

    }
    public  boolean DataExists(DatabaseHelper databaseHelper,String fieldValue) {
        SQLiteDatabase database = databaseHelper.getReadableDatabase();
        String Query = "Select * from my_favourites where name ='" + fieldValue + "'";
        Cursor cursor = database.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();

            return false;

        }
        cursor.close();

        return true;
    }

    public void refreshTable(DatabaseHelper databaseHelper,ArrayList<Places> mList){
        mList.clear();
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM my_locations",null);

        int nameIx = cursor.getColumnIndex("name");
        int latitudeIx = cursor.getColumnIndex("latitude");
        int longitudeIx = cursor.getColumnIndex("longitude");

        while (cursor.moveToNext()) {

            String nameFromDatabase = cursor.getString(nameIx);
            String latitudeFromDatabase = cursor.getString(latitudeIx);
            String longitudeFromDatabase = cursor.getString(longitudeIx);

            Double latitude = Double.parseDouble(latitudeFromDatabase);
            Double longitude = Double.parseDouble(longitudeFromDatabase);

            Places place = new Places(nameFromDatabase,latitude,longitude);


            mList.add(place);

        }
        cursor.close();
    }

    /// ADD DATA
    public  void add_religions(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile =   "INSERT INTO religions(name,latitude,longitude,image) VALUES ('Suleymaniye Mosque', '41.016177', '28.964153','suleymaniyemin'),('Blue Mosque', '41.005321', '28.976725','bluemosque'),('Eyüp Sultan Mosque', '41.048080','28.933879','eyupsultanmosque'),('Corlulu Ali Pasa Medresesi', '41.008986', '28.968498','corlulualipasamedresesi'),('Mecidiye(Ortaköy) Mosque', '41.047253' ,'29.027009','ortakoymosque'),('The tomb of Hz. Yuşa', '41.162312', '29.084935','hzyusamin'),('Aya Yorgi Fener Rum Ortodoks Kilisesi', '41.029232', '28.951637','ayayorgikilisesi'),('Saint Antoinie Church', '41.032310', '28.977131','stantoiniechurch'),('Virgin Mary Church' , '41.037039', '28.978541','virginmin'),('Neve Shalom Synagogue' , '41.026877', '28.972623','nevesalommin')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM religions Where id Not in (SELECT MIN(id) from religions Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();

    }
    public  void add_parks(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO parks_gardens(name,latitude,longitude,image) VALUES ('Yıldız Park', '41.049273', '29.015274','yildizpark')," +
                "('Emirgan Park', '41.108846', '29.053083','emirgankorusu'),('Gulhane Park', '41.013328', '28.981384','gulhanepark'),('Polonezkoy Nature Park', '41.108598', '29.166018','polonezkoynaturepark'),('Ozgurluk Park', '40.979074', '29.059408','ozgurlukpark'),('Mihrabat Korusu', '41.096802', '29.068632','mihrabatpark'),('Fethi Paşa Korusu', '41.0967859', '29.0247348','fethipasa'),('Çamlıca Tepesi', '41.0277528', '29.06622','camlicatepesi'),('Beykoz Korusu', '41.1331274', '29.0963995','beykoz_korusu'),('Belgrad Forest', '41.1871997', '28.965086','belgrad'),('Atatürk Arboretum', '41.1766262', '28.9831888','ataturkarboretumu')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM parks_gardens Where id Not in (SELECT MIN(id) from parks_gardens Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();


    }


    public  void add_bazaarmarkets(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO bazaar_markets(name,latitude,longitude,image) VALUES ('Spice Bazaar', '41.016536', '28.970594','spicebazaar')," +
                "('Eminönü Bazaar', '41.018297', '28.970953','eminonubazaar'),('Grand Bazaar', '41.010673', '28.968063','grandbazaar'),('Bakirkoy Bit Pazarı Ve Sanatkarlar Çarşısı', '40.979889', '28.874933','bakirkoy_bitpazari'),('Üsküdar Fish Market', '41.024469', '29.015983','uskudarfishmarket'),('Bakirkoy Underground Bazaar' , '40.980697', '28.873803','bakirkoy_undergroundbazaar'),('Great Sinan Bazaar', '41.023742', '29.015950','mimarsinanbazaar'),('Besiktas Fish Market', '41.043512', '29.004666','besiktasfishmarket')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM bazaar_markets Where id Not in (SELECT MIN(id) from bazaar_markets Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();

    }
    public  void add_historicalplaces(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO historical_places (name, latitude, longitude,image) VALUES ('Hagia Sophia Mosque' , '41.008587', '28.980170','ayasofya'),('Beylerbeyi Palace', '41.042673', '29.039887','beylerbeyimin'),('Galata Tower', '41.025676', '28.974129','galatamin'),('Hidiv Kasrı', '41.104619', '29.075520','hidivmin'),('Dolmabahce Palace', '41.039168', '29.000454','dolmabahcemin'),('The Tomb of Haseki Hurrem Sultan' , '41.008611', '28.941855','thetombof_hurremmin'),('Sultanahmet Square' , '41.005752', '28.975445','sultanahmet_square'),('The Tomb of Great Sinan', '41.017290', '28.963866','thetombofgreatsinanmin'),('Maiden’s Tower', '41.021124' ,'29.004111','kizkulesimin'),('Rumeli Fortress', '41.0881692', '29.0406517','rumelihisarimin'),('Anatolian Fortress', '41.0871851' ,'29.0629042','anadolu_hisarmin'),('Topkapı Palace', '41.0111792', '28.9808382','topkapimin'),('Çırağan Palace', '41.0434694' ,'29.0134867','ciraganmin'),('Adile Sultan Palace', '41.072334', '29.0556518','adilesultanmin'),('Haydarpaşa Terminal', '40.9968247' ,'29.0171052','haydarpasamin'),('Castle of the Seven Towers', '40.9938545' ,'28.9210783','yedikule_zindanlarmin')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM historical_places Where id Not in (SELECT MIN(id) from historical_places Group by name)";

        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);

        sqLiteStatement.execute();
        sqLiteStatement1.execute();


    }
    public  void add_islandsandbeachs(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO island_beaches (name, latitude, longitude,image) VALUES ('Büyükada', '40.856003', '29.119722','buyukada'),('Heybeliada' ,'40.873304', '29.089633','heybeliada'),('Kınalıada', '40.909127', '29.053049','kinaliada'),('Sedef Island', '40.850309', '29.146068','sedefisland'),('Burgaz Island', '40.879934', '29.068489','burgazada'),('Kınalıada Public Beach', '40.906846', '29.044361','kinaliada_publicbeach'),('Caddebostan Beach', '40.963336', '29.058661','caddebostanbeach'),('Suma Beach', '41.246143', '29.004559','sumabeach')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM island_beaches Where id Not in (SELECT MIN(id) from island_beaches Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();

    }
    public  void add_museums(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile =  "INSERT INTO museums(name,latitude,longitude,image)  VALUES ('Tekfur Palace Museum', '41.033806', '28.940487','tekfurpalacemuseum')," +
                "('Rahmi Koc Museum', '41.041822', '28.949923','rahmikocmuseum'),('Topkapi Palace Museum', '41.011681', '28.983690','topkapimuseum')," +
                "('Istanbul Modern Art Museum', '41.030076', '28.973875','sadfsafda'),('Basilica Cistern', '41.008538', '28.978500','yerebatan_sarnici')," +
                "('Tiled Kiosk', '41.012014', '28.981671','tiledkiosk'),('Istanbul Toy Museum', '40.975939', '29.071127','istanbul_toy_museum')," +
                "('Isbank Museum', '41.016246', '28.973187','isbankmuseum'),('The Panaroma 1453 Museum', '41.018350', '28.920981','panaroma_1453_museum')," +
                "('Sakıp Sabancı Museum', '41.105660', '29.056752','sabancimuseum'),('Hagia Irene Church', '41.009626', '28.981216','hagiairene')," +
                "('Miniatürk', '41.0594819', '28.9468834','miniaturk'),('Istanbul Archaeological Museum', '41.0116855', '28.9791418','fsdfafd')," +
                "('Turkish and Islamic Arts Museum', '41.0062236', '28.9747201','turkislammuseum'),('Madame Tussauds Istanbul', '41.0345876', '28.9775171','madamt')," +
                "('SALT Galata', '41.0239214', '28.964734','saltgalata'),('Pera Museum', '41.031816', '28.9730116','peramuseum')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM museums Where id Not in (SELECT MIN(id) from museums Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();

    }
    public  void add_myfavourites(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
    public  void add_mylocations(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        //String toCompile = "INSERT INTO religions (name, latitude, longitude) VALUES ('ayasofta','45','44') Select '1448523' Where not exists(select * from tablename where code='1448523') ";
        //SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        //sqLiteStatement.execute();

    }
    public  void add_squares(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile = "INSERT INTO squares (name, latitude, longitude,image) VALUES ('Taksim Square', '41.036991', '28.985081','taksim')," +
                "('Kadıköy Square', '40.992127', '29.023861','kadikoy_square'),('Sultanahmet Square', '41.006661', '28.976174','sultanahmet_square')," +
                "('Eminönü Square', '41.017447', '28.970301','eminonu')," + "('Beyazıt Square', '41.010470' , '28.963896','beyazitsquare')," +
                "('Yenikapı IDO Square', '41.002250', '28.956403','yenikapi_square'),('Cumhuriyet Square', '40.981070', '28.873726','cumhuriyetsquare')," +
                "('Beşiktaş Square', '41.043781', '29.0051786','besiktasmeydan'),('Üsküdar Square', '41.0255311', '29.004517','uskudar')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM squares Where id Not in (SELECT MIN(id) from squares Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();
    }
    public  void add_food(DatabaseHelper databaseHelper) {
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        String toCompile =  "INSERT INTO foods(name,image)  VALUES ('Fish and Bread (Balık Ekmek)','fishandbread'),('Acıbadem Cookies (Acıbadem Kurabiyesi)','acibademcookie')," +
                "('Black Sea Pita (Karadeniz Pidesi)','blackseapita'),('Kanlıca Yogurt','kanlicayogurt'),('Kup Griye','kupgriye'),('Stuffed Mussels (Midye Dolma)','stuffedmussels'),('Ortaköy Baked Potato (Kumpir)','ortakoybakedpotato')," +
                "('Wet Burger (Islak Hamburger)','wetburger'),('Doner','doner'),('Tantuni','tantuni'),('Waffle','waffle'),('Borek','borek'),('Osmanlı Macunu','macun'),('Turkish Bagel (Simit)','simit')," +
                "('Pickle Juice (Turşu Suyu)','picklejuice'),('Iskender','iskender'),('Kokoreç','kokorec'),('Chicken and Rice (Pilav üstü tavuk)','chickenandrice')," +
                "('Dürüm','durum'),('Halka Tatlısı','halkatatlisi'),('Baklava','baklava'),('Mısır (Corn)','corn'),('Kestane (Chesnuts)','kestane')," +
                "('Boza','boza'),('Köfte Sucuk Ekmek','koftesucukekmek'),('Turkish Breakfast','turkishbreakfast'),('Çiğ Köfte','cigkofte'),('Kağıt Helva (Paper Candy)','yok')," +
                "('Turkish Puddings','pudding'),('Turkish Coffee','turkishcoffee'),('Turkish Ice Cream','dondurma'),('Şalgam (Turnip Juice)','salgam'),('Ayran','ayran')," +
                "('Turkish Delight','lokum'),('Rakı','raki')";
        SQLiteStatement sqLiteStatement = database.compileStatement(toCompile);
        String sil = "DELETE FROM foods Where id Not in (SELECT MIN(id) from foods Group by name)";
        SQLiteStatement sqLiteStatement1 = database.compileStatement(sil);
        sqLiteStatement.execute();
        sqLiteStatement1.execute();



    }

}
