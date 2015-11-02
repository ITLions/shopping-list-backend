package models
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.PostgresDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema: profile.SchemaDescription = Array(Categories.schema, ListItem.schema, PlayEvolutions.schema, ProductLists.schema, Products.schema, Units.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Categories
   *  @param id Database column id SqlType(uuid), PrimaryKey
   *  @param createdDate Database column created_date SqlType(timestamp)
   *  @param updatedDate Database column updated_date SqlType(timestamp)
   *  @param name Database column name SqlType(varchar), Length(100,true)
   *  @param description Database column description SqlType(varchar), Length(255,true), Default(None)
   *  @param icon Database column icon SqlType(varchar), Length(255,true) */
  case class CategoriesRow(id: java.util.UUID, createdDate: java.sql.Timestamp, updatedDate: java.sql.Timestamp, name: String, description: Option[String] = None, icon: String)
  /** GetResult implicit for fetching CategoriesRow objects using plain SQL queries */
  implicit def GetResultCategoriesRow(implicit e0: GR[java.util.UUID], e1: GR[java.sql.Timestamp], e2: GR[String], e3: GR[Option[String]]): GR[CategoriesRow] = GR{
    prs => import prs._
    CategoriesRow.tupled((<<[java.util.UUID], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[String], <<?[String], <<[String]))
  }
  /** Table description of table categories. Objects of this class serve as prototypes for rows in queries. */
  class Categories(_tableTag: Tag) extends Table[CategoriesRow](_tableTag, "categories") {
    def * = (id, createdDate, updatedDate, name, description, icon) <> (CategoriesRow.tupled, CategoriesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(createdDate), Rep.Some(updatedDate), Rep.Some(name), description, Rep.Some(icon)).shaped.<>({r=>import r._; _1.map(_=> CategoriesRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(uuid), PrimaryKey */
    val id: Rep[java.util.UUID] = column[java.util.UUID]("id", O.PrimaryKey)
    /** Database column created_date SqlType(timestamp) */
    val createdDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_date")
    /** Database column updated_date SqlType(timestamp) */
    val updatedDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_date")
    /** Database column name SqlType(varchar), Length(100,true) */
    val name: Rep[String] = column[String]("name", O.Length(100,varying=true))
    /** Database column description SqlType(varchar), Length(255,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(255,varying=true), O.Default(None))
    /** Database column icon SqlType(varchar), Length(255,true) */
    val icon: Rep[String] = column[String]("icon", O.Length(255,varying=true))

    /** Uniqueness Index over (icon) (database name categories_icon_key) */
    val index1 = index("categories_icon_key", icon, unique=true)
    /** Uniqueness Index over (name) (database name categories_name_key) */
    val index2 = index("categories_name_key", name, unique=true)
  }
  /** Collection-like TableQuery object for table Categories */
  lazy val Categories = new TableQuery(tag => new Categories(tag))

  /** Entity class storing rows of table ListItem
   *  @param id Database column id SqlType(uuid), PrimaryKey
   *  @param createdDate Database column created_date SqlType(timestamp)
   *  @param updatedDate Database column updated_date SqlType(timestamp)
   *  @param checked Database column checked SqlType(bool), Default(false)
   *  @param productId Database column product_id SqlType(uuid)
   *  @param productListId Database column product_list_id SqlType(uuid)
   *  @param amount Database column amount SqlType(int4) */
  case class ListItemRow(id: java.util.UUID, createdDate: java.sql.Timestamp, updatedDate: java.sql.Timestamp, checked: Boolean = false, productId: java.util.UUID, productListId: java.util.UUID, amount: Int)
  /** GetResult implicit for fetching ListItemRow objects using plain SQL queries */
  implicit def GetResultListItemRow(implicit e0: GR[java.util.UUID], e1: GR[java.sql.Timestamp], e2: GR[Boolean], e3: GR[Int]): GR[ListItemRow] = GR{
    prs => import prs._
    ListItemRow.tupled((<<[java.util.UUID], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[Boolean], <<[java.util.UUID], <<[java.util.UUID], <<[Int]))
  }
  /** Table description of table list_item. Objects of this class serve as prototypes for rows in queries. */
  class ListItem(_tableTag: Tag) extends Table[ListItemRow](_tableTag, "list_item") {
    def * = (id, createdDate, updatedDate, checked, productId, productListId, amount) <> (ListItemRow.tupled, ListItemRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(createdDate), Rep.Some(updatedDate), Rep.Some(checked), Rep.Some(productId), Rep.Some(productListId), Rep.Some(amount)).shaped.<>({r=>import r._; _1.map(_=> ListItemRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get, _6.get, _7.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(uuid), PrimaryKey */
    val id: Rep[java.util.UUID] = column[java.util.UUID]("id", O.PrimaryKey)
    /** Database column created_date SqlType(timestamp) */
    val createdDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_date")
    /** Database column updated_date SqlType(timestamp) */
    val updatedDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_date")
    /** Database column checked SqlType(bool), Default(false) */
    val checked: Rep[Boolean] = column[Boolean]("checked", O.Default(false))
    /** Database column product_id SqlType(uuid) */
    val productId: Rep[java.util.UUID] = column[java.util.UUID]("product_id")
    /** Database column product_list_id SqlType(uuid) */
    val productListId: Rep[java.util.UUID] = column[java.util.UUID]("product_list_id")
    /** Database column amount SqlType(int4) */
    val amount: Rep[Int] = column[Int]("amount")

    /** Foreign key referencing ProductLists (database name list_item_product_list_id_fkey) */
    lazy val productListsFk = foreignKey("list_item_product_list_id_fkey", productListId, ProductLists)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Products (database name list_item_product_id_fkey) */
    lazy val productsFk = foreignKey("list_item_product_id_fkey", productId, Products)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ListItem */
  lazy val ListItem = new TableQuery(tag => new ListItem(tag))

  /** Entity class storing rows of table PlayEvolutions
   *  @param id Database column id SqlType(int4), PrimaryKey
   *  @param hash Database column hash SqlType(varchar), Length(255,true)
   *  @param appliedAt Database column applied_at SqlType(timestamp)
   *  @param applyScript Database column apply_script SqlType(text), Default(None)
   *  @param revertScript Database column revert_script SqlType(text), Default(None)
   *  @param state Database column state SqlType(varchar), Length(255,true), Default(None)
   *  @param lastProblem Database column last_problem SqlType(text), Default(None) */
  case class PlayEvolutionsRow(id: Int, hash: String, appliedAt: java.sql.Timestamp, applyScript: Option[String] = None, revertScript: Option[String] = None, state: Option[String] = None, lastProblem: Option[String] = None)
  /** GetResult implicit for fetching PlayEvolutionsRow objects using plain SQL queries */
  implicit def GetResultPlayEvolutionsRow(implicit e0: GR[Int], e1: GR[String], e2: GR[java.sql.Timestamp], e3: GR[Option[String]]): GR[PlayEvolutionsRow] = GR{
    prs => import prs._
    PlayEvolutionsRow.tupled((<<[Int], <<[String], <<[java.sql.Timestamp], <<?[String], <<?[String], <<?[String], <<?[String]))
  }
  /** Table description of table play_evolutions. Objects of this class serve as prototypes for rows in queries. */
  class PlayEvolutions(_tableTag: Tag) extends Table[PlayEvolutionsRow](_tableTag, "play_evolutions") {
    def * = (id, hash, appliedAt, applyScript, revertScript, state, lastProblem) <> (PlayEvolutionsRow.tupled, PlayEvolutionsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(hash), Rep.Some(appliedAt), applyScript, revertScript, state, lastProblem).shaped.<>({r=>import r._; _1.map(_=> PlayEvolutionsRow.tupled((_1.get, _2.get, _3.get, _4, _5, _6, _7)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(int4), PrimaryKey */
    val id: Rep[Int] = column[Int]("id", O.PrimaryKey)
    /** Database column hash SqlType(varchar), Length(255,true) */
    val hash: Rep[String] = column[String]("hash", O.Length(255,varying=true))
    /** Database column applied_at SqlType(timestamp) */
    val appliedAt: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("applied_at")
    /** Database column apply_script SqlType(text), Default(None) */
    val applyScript: Rep[Option[String]] = column[Option[String]]("apply_script", O.Default(None))
    /** Database column revert_script SqlType(text), Default(None) */
    val revertScript: Rep[Option[String]] = column[Option[String]]("revert_script", O.Default(None))
    /** Database column state SqlType(varchar), Length(255,true), Default(None) */
    val state: Rep[Option[String]] = column[Option[String]]("state", O.Length(255,varying=true), O.Default(None))
    /** Database column last_problem SqlType(text), Default(None) */
    val lastProblem: Rep[Option[String]] = column[Option[String]]("last_problem", O.Default(None))
  }
  /** Collection-like TableQuery object for table PlayEvolutions */
  lazy val PlayEvolutions = new TableQuery(tag => new PlayEvolutions(tag))

  /** Entity class storing rows of table ProductLists
   *  @param id Database column id SqlType(uuid), PrimaryKey
   *  @param createdDate Database column created_date SqlType(timestamp)
   *  @param updatedDate Database column updated_date SqlType(timestamp)
   *  @param name Database column name SqlType(varchar), Length(100,true) */
  case class ProductListsRow(id: java.util.UUID, createdDate: java.sql.Timestamp, updatedDate: java.sql.Timestamp, name: String)
  /** GetResult implicit for fetching ProductListsRow objects using plain SQL queries */
  implicit def GetResultProductListsRow(implicit e0: GR[java.util.UUID], e1: GR[java.sql.Timestamp], e2: GR[String]): GR[ProductListsRow] = GR{
    prs => import prs._
    ProductListsRow.tupled((<<[java.util.UUID], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[String]))
  }
  /** Table description of table product_lists. Objects of this class serve as prototypes for rows in queries. */
  class ProductLists(_tableTag: Tag) extends Table[ProductListsRow](_tableTag, "product_lists") {
    def * = (id, createdDate, updatedDate, name) <> (ProductListsRow.tupled, ProductListsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(createdDate), Rep.Some(updatedDate), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> ProductListsRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(uuid), PrimaryKey */
    val id: Rep[java.util.UUID] = column[java.util.UUID]("id", O.PrimaryKey)
    /** Database column created_date SqlType(timestamp) */
    val createdDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_date")
    /** Database column updated_date SqlType(timestamp) */
    val updatedDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_date")
    /** Database column name SqlType(varchar), Length(100,true) */
    val name: Rep[String] = column[String]("name", O.Length(100,varying=true))
  }
  /** Collection-like TableQuery object for table ProductLists */
  lazy val ProductLists = new TableQuery(tag => new ProductLists(tag))

  /** Entity class storing rows of table Products
   *  @param id Database column id SqlType(uuid), PrimaryKey
   *  @param createdDate Database column created_date SqlType(timestamp)
   *  @param updatedDate Database column updated_date SqlType(timestamp)
   *  @param name Database column name SqlType(varchar), Length(100,true)
   *  @param description Database column description SqlType(varchar), Length(255,true), Default(None)
   *  @param icon Database column icon SqlType(varchar), Length(255,true)
   *  @param categoryId Database column category_id SqlType(uuid)
   *  @param unitId Database column unit_id SqlType(uuid) */
  case class ProductsRow(id: java.util.UUID, createdDate: java.sql.Timestamp, updatedDate: java.sql.Timestamp, name: String, description: Option[String] = None, icon: String, categoryId: java.util.UUID, unitId: java.util.UUID)
  /** GetResult implicit for fetching ProductsRow objects using plain SQL queries */
  implicit def GetResultProductsRow(implicit e0: GR[java.util.UUID], e1: GR[java.sql.Timestamp], e2: GR[String], e3: GR[Option[String]]): GR[ProductsRow] = GR{
    prs => import prs._
    ProductsRow.tupled((<<[java.util.UUID], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[String], <<?[String], <<[String], <<[java.util.UUID], <<[java.util.UUID]))
  }
  /** Table description of table products. Objects of this class serve as prototypes for rows in queries. */
  class Products(_tableTag: Tag) extends Table[ProductsRow](_tableTag, "products") {
    def * = (id, createdDate, updatedDate, name, description, icon, categoryId, unitId) <> (ProductsRow.tupled, ProductsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(createdDate), Rep.Some(updatedDate), Rep.Some(name), description, Rep.Some(icon), Rep.Some(categoryId), Rep.Some(unitId)).shaped.<>({r=>import r._; _1.map(_=> ProductsRow.tupled((_1.get, _2.get, _3.get, _4.get, _5, _6.get, _7.get, _8.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(uuid), PrimaryKey */
    val id: Rep[java.util.UUID] = column[java.util.UUID]("id", O.PrimaryKey)
    /** Database column created_date SqlType(timestamp) */
    val createdDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_date")
    /** Database column updated_date SqlType(timestamp) */
    val updatedDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_date")
    /** Database column name SqlType(varchar), Length(100,true) */
    val name: Rep[String] = column[String]("name", O.Length(100,varying=true))
    /** Database column description SqlType(varchar), Length(255,true), Default(None) */
    val description: Rep[Option[String]] = column[Option[String]]("description", O.Length(255,varying=true), O.Default(None))
    /** Database column icon SqlType(varchar), Length(255,true) */
    val icon: Rep[String] = column[String]("icon", O.Length(255,varying=true))
    /** Database column category_id SqlType(uuid) */
    val categoryId: Rep[java.util.UUID] = column[java.util.UUID]("category_id")
    /** Database column unit_id SqlType(uuid) */
    val unitId: Rep[java.util.UUID] = column[java.util.UUID]("unit_id")

    /** Foreign key referencing Categories (database name products_category_id_fkey) */
    lazy val categoriesFk = foreignKey("products_category_id_fkey", categoryId, Categories)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Units (database name products_unit_id_fkey) */
    lazy val unitsFk = foreignKey("products_unit_id_fkey", unitId, Units)(r => r.id, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)

    /** Uniqueness Index over (icon) (database name products_icon_key) */
    val index1 = index("products_icon_key", icon, unique=true)
    /** Uniqueness Index over (name) (database name products_name_key) */
    val index2 = index("products_name_key", name, unique=true)
  }
  /** Collection-like TableQuery object for table Products */
  lazy val Products = new TableQuery(tag => new Products(tag))

  /** Entity class storing rows of table Units
   *  @param id Database column id SqlType(uuid), PrimaryKey
   *  @param createdDate Database column created_date SqlType(timestamp)
   *  @param updatedDate Database column updated_date SqlType(timestamp)
   *  @param name Database column name SqlType(varchar), Length(50,true) */
  case class UnitsRow(id: java.util.UUID, createdDate: java.sql.Timestamp, updatedDate: java.sql.Timestamp, name: String)
  /** GetResult implicit for fetching UnitsRow objects using plain SQL queries */
  implicit def GetResultUnitsRow(implicit e0: GR[java.util.UUID], e1: GR[java.sql.Timestamp], e2: GR[String]): GR[UnitsRow] = GR{
    prs => import prs._
    UnitsRow.tupled((<<[java.util.UUID], <<[java.sql.Timestamp], <<[java.sql.Timestamp], <<[String]))
  }
  /** Table description of table units. Objects of this class serve as prototypes for rows in queries. */
  class Units(_tableTag: Tag) extends Table[UnitsRow](_tableTag, "units") {
    def * = (id, createdDate, updatedDate, name) <> (UnitsRow.tupled, UnitsRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(id), Rep.Some(createdDate), Rep.Some(updatedDate), Rep.Some(name)).shaped.<>({r=>import r._; _1.map(_=> UnitsRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id SqlType(uuid), PrimaryKey */
    val id: Rep[java.util.UUID] = column[java.util.UUID]("id", O.PrimaryKey)
    /** Database column created_date SqlType(timestamp) */
    val createdDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("created_date")
    /** Database column updated_date SqlType(timestamp) */
    val updatedDate: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("updated_date")
    /** Database column name SqlType(varchar), Length(50,true) */
    val name: Rep[String] = column[String]("name", O.Length(50,varying=true))

    /** Uniqueness Index over (name) (database name units_name_key) */
    val index1 = index("units_name_key", name, unique=true)
  }
  /** Collection-like TableQuery object for table Units */
  lazy val Units = new TableQuery(tag => new Units(tag))
}
