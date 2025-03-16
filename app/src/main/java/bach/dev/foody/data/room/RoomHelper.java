package bach.dev.foody.data.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import bach.dev.foody.data.dao.ProductDao;
import bach.dev.foody.data.model.ProductModel;

@Database(entities = {ProductModel.class}, version = 1)
public abstract class RoomHelper extends RoomDatabase {
    public abstract ProductDao productDao();
    private static volatile RoomHelper INSTANCE;
    public static RoomHelper getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    RoomHelper.class, "db.sqlite")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
