package bach.dev.foody.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products")
public class ProductModel {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "description")
    public String description;
    @ColumnInfo(name = "thumbnail")
    public String thumbnail;
    @ColumnInfo(name = "price")
    public double price;
    @ColumnInfo(name = "quantity")
    public int quantity;
    @ColumnInfo(name = "category_id")
    public int categoryId;

    public ProductModel(String name, String description, String thumbnail, double price, int quantity, int categoryId) {
        this.name = name;
        this.description = description;
        this.thumbnail = thumbnail;
        this.price = price;
        this.quantity = quantity;
        this.categoryId = categoryId;
    }
}
