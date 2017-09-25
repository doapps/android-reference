# Buenas prácticas de desarrollo Android

A continuación encontrarán una serie de buenas prácticas a tomar en cuenta para a la hora de programar aplicaciones móviles
para las plataformas Android.
Como bien sabemos a la hora de desarrollar aplicaciones nativas para Android, usamos el lenguaje de programación Java y en esta
ocación el IDE oficial Android Studio.
Si bien Android Studio nos ofrece de por defecto la estructura general del proyecto, a medida que vayamos profundizando en el desarrollo
de una aplicación o vayamos creando aplicaciones más complejas necesitaremos crear un código entendible por cualquier miembro del equipo,
un código modular que pueda ser escalable fácilmente y por sobre todo un código limpio basándonos en estándares de programación que
solo los buenos desarrolladores siguen.


https://github.com/futurice/android-best-practices
https://github.com/futurice/android-best-practices/tree/master/templates/rx-architecture


## Estructura del proyecto

Esta es la estructura base que nos proporciona Android Studio al crear un proyecto para aplicaciones móviles.

```
├─ app
│  ├─ libs
│  ├─ src
│  │  ├─ androidTest
│  │  │  └─ java
│  │  │     └─ com/domain/project
│  │  └─ main
│  │     ├─ java
│  │     │  └─ com/domain/project
│  │     ├─ res
│  │     └─ AndroidManifest.xml
│  ├─ build.gradle
│  └─ proguard-rules.pro
├─ build.gradle
└─ settings.gradle
```


### Estructura /java

> **Nota:**
>
> - El paquete **java** es un contenedor de clases para diferentes fines dentro de la aplicación.
> - El IDE Android Studio provee por defecto las carpetas bases.
> - Si un paquete contiene sub carpetas solo valdrá la pena crear las carpetas si estas contienen clases.
> - Las carpetas debe estar en minuscula y en singular.

```
├─ java
│  ├─ com.domain.project
│     ├─ activity
│     ├─ fragment
│     ├─ model
│     ├─ adapter
│     ├─ config
│     ├─ dialog
│     ├─ networking
│     ├─ database
│     ├─ session
│     ├─ util
│     └─ widget

```


##### Estructura /java/activity
```
├─ java
│  ├─ com.domain.project
│     └─ activity
│         └─ MainActivity.java
│         └─ SplashActivity.java

** En caso amerite se crearán subcarpetas a discreción del desarrollador para ordenar las clases.
```

### Estructura de un activity
```java

public class ExampleActivity extends AppCompatActivity implements View.OnClickListener , AdapterView.OnItemClickListener{

    @BindView(R.id.global_image)
    ImageView globalImage;

    @BindView(R.id.inicio_text)
    Texview inicioText;

    @BindView(R.id.email_edit)
     EditText emailEdit;

    @BindView(R.id.enviar_button)
    Button enviarButton;

    private int cont;
    private String user;
    private double total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_quota);

        ButterKnife.bind(this);

    }

    /** View Events **/
    @OnClick(R.id.button_opcion1)
    public void getOption1() {
        isEstado(1);
        include_opcion1.setVisibility(View.VISIBLE);
        include_opcion2.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.button_opcion2)
    public void getOption2() {
        isEstado(2);
        include_opcion1.setVisibility(View.INVISIBLE);
        include_opcion2.setVisibility(View.VISIBLE);
    }

    /** Implements Events **/

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    /** Logic Events **/
    public void isEstado(int status) {
        if (status == 1) {
            button_opcion1.setBackground(getResources().getDrawable(R.drawable.borde_in_1));
            button_opcion1.setTextColor(getResources().getColor(R.color.smv_plomo2
            button_opcion2.setBackground(getResources().getDrawable(R.drawable.borde_of_1));
            button_opcion2.setTextColor(getResources().getColor(R.color.smv_blanco));
        } else {
            button_opcion1.setBackground(getResources().getDrawable(R.drawable.borde_of_2));
            button_opcion1.setTextColor(getResources().getColor(R.color.smv_blanco
            button_opcion2.setBackground(getResources().getDrawable(R.drawable.borde_in_2));
            button_opcion2.setTextColor(getResources().getColor(R.color.smv_plomo2));
        }
    }

    /** Lifecycle Events **/
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (!isDisplayAd) {
                displayAds();
                isDisplayAd = true;
            }
            super.onBackPressed();
        }
    }

}
```

### Estructura /java/fragment
```
├─ java
│  ├─ com.domain.project
│     └─ fragment
│         └─ MainFragment.java

** En caso amerite se crearán subcarpetas a discreción del desarrollador para ordenar las clases.
```

### Estructura de un fragment

```java
public class ExampleFragment extends Fragment {
    @BindView(R.id.categories_recycler)
    RecyclerView categoriesRecycler;

    private CategoriesAdapter categoriesAdapter;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            categoriesRecycler.setLayoutManager(linearLayoutManager);

            categoriesAdapter = new CategoriesAdapter(categories(), getActivity());

            categoriesRecycler.setAdapter(categoriesAdapter);
    }

    private List<Categories> categories() {
        List<Categories> categoriesList = new ArrayList<>();

        categoriesList.add(new Categories("Categoria 1", R.drawable.img_01));
        categoriesList.add(new Categories("Categoria 2", R.drawable.img_02));
        categoriesList.add(new Categories("Categoria 3", R.drawable.img_03));

        return categoriesList;
    }
}

```

### Estructura /java/model
```
├─ java
│  ├─ com.domain.project
│     └─ model
│         └─ Product.java

```

#### Estructura de un model

```java

   public class Producto {
       private int price;
       private String name;

       public Producto(int price, String name) {
           this.price = price;
           this.name = name;
       }

       public int getPrice() {
           return price;
       }

       public void setPrice(int price) {
           this.price = price;
       }

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }
   }

```

#### Estructura /java/adapter
```
├─ java
│  ├─ com.domain.project
│     └─ adapter
│         └─ ProductListAdapter.java
│         └─ ProductGridAdapter.java

** En el caso que el model utilize dos adapter se especificara el adapter con : nombre + caso de listado + adpater
** Ejemplo :
** ProductCountAdapter,ProductGridAdapter,ProductListAdapter
```

### Estructura de un Adapter

Caso de un Spinner

```java
 public class ClothingPicturesAdapter extends BaseAdapter {
    LayoutInflater inflater;
    private Activity activity;
    private ArrayList<ClothingPictures> list;

    public ClothingPicturesAdapter(Activity activity, ArrayList<ClothingPictures> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position).getId();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TextView nameText;

        inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View itemSpinner = inflater.inflate(R.layout.item_spinner, parent, false);
        nameText = (TextView) itemSpinner.findViewById(R.id.name_text);

        nameText.setText(list.get(position).getTitle());

        return itemSpinner;
    }
}
```

Caso de un RecyclerView

```java
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterHolder>{
    private Activity activity;
    private ArrayList<Product> listProduct;

    public CartAdapter(Activity activity, ArrayList<Product> listProduct) {
        this.activity = activity;
        this.listProduct = listProduct;
    }

    @Override
    public ProductAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_product,parent,false);

        return new ProductAdapterHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductAdapterHolder holder, int position) {

        Picasso.with(activity).load(listProduct.get(position).getPicture()).into(holder.image);
        holder.name.setText("" + listProduct.get(position).getName());
        holder.price.setText("" + listProduct.get(position).getPrice());
        holder.count.setText(""+ listProduct.get(position).getCount());
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class ProductAdapterHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name;
        private TextView price;
        private TextView count;

        public ProductAdapterHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name_text);
            price = (TextView) itemView.findViewById(R.id.price_text);
            count = (TextView) itemView.findViewById(R.id.count_text);

        }
    }
}

```

#### Estructura /java/config
```
├─ java
│  ├─ com.domain.project
│     └─ config
│         └─ Setting.java

** Almacenar variables staticas y finales que puedan ser reutilizables como mensajes dinámicos, log errores, etc.
```
### Estructura de un Setting

```java
public class Setting {

    public static String NAME_TEXT = "name";
    public static String DISTRICT_TEXT = "district";
    public static String LOCATION_TEXT = "location";
    public static String COMPANY_TEXT = "company";
    public static String TEXT_TYPE = "type";
    public static String ATTACH_FILES = "Adjuntar archivos";
    public static String DETAIL_SCHEDULE = "Detalle de agenda";
    public static String DIRECTION_REGISTRY = "Registro de dirección";
    public static String CONTINUE = "Continuar";
    public static String QUESTIONNAIRE = "Cuestionario";
    public static String POSITION = "posicion";

    // Cuando Se usa los permisos de android
     REQUEST_LOCATION = 200
     REQUEST_IMAGE_GET = 100
}
```

#### Estructura /java/dialog
```
├─ java
│  ├─ com.domain.project
│     └─ dialog
│         └─ DemoDialog.java

```

** Se reutilizara los dialogs siempre y cuando la estructura sea la misma.

### Ejemplo de implementación de dialogo personalizado :

Metodo en xml:

```xml

<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="vertical" android:layout_width="match_parent"
    android:layout_height="match_parent">

    <TextView
        android:id="@+id/title_text"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="TextView"
        android:textAlignment="center" />

    <Button
        android:id="@+id/accept_button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button" />

    <Button
        android:id="@+id/exit_button"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:text="Button" />

</LinearLayout>

```

Metodo en Java:

```java

public class DemoDialog extends AlertDialog {

    private String title;
    private SelectOption selectOption;

    public DemoDialog(Context context, String title) {
        super(context);
        this.title = title;
        init();
    }

    protected DemoDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        init();
    }

    protected DemoDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init();
    }


    private void init() {
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.dialog_demo, null);

        TextView tituloText = (TextView) view.findViewById(R.id.title_text);
        Button aceptarButton = (Button) view.findViewById(R.id.accept_button);
        Button exitButton = (Button) view.findViewById(R.id.exit_button);

        titleText.setText(title);
        aceptarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption.accept();
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectOption.exit();
            }
        });

        setView(view);
        setCancelable(false);
    }

    public interface SelectOption {

        void accept();

        void exit();
    }

    public void setOnSelectOption(SelectOption selectOption) {
        this.selectOption = selectOption;
    }

```

#### Estructura /java/networking
```
├─ java
│  ├─ com.domain.project
│     └─ networking
│         └─ connection
│             └─ RestApi.java
│             └─ GoogleApi.java
│         └─ fcm
│             └─ MessagingService.java
│         └─ background
│             └─ TrackerService.java
│             └─ ExampleAsynctask.java

** connection Almacenar las clases que involucran a Retrofit
** El restapi se refiere a los servicios que ofrecemos
** En caso fuera un cliente es el nombre del proyecto con la palabra api
** Por ejemplo :
** Ozonoapi
** fcm las clases firebase
** background otras clases en segundo plano.
```
### Estructura de un ResApi

```java
public interface RestApi {
    public String BASE_URL = "base url del service";

    OkHttpClient okHttpClient = new OkHttpClient
            .Builder().readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build();

    @Headers("Content-Type: application/json")
    @POST("session/login")
    Call<User> getUserApi(@Body String body);

    public static final Retrofit retrofitLogin = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build();
```

### Estructura de un MessagingService

```java

public class MessagingService extends FirebaseMessagingService {

   private Preference preference;

   @Override
   public void onMessageReceived(RemoteMessage remoteMessage) {
       super.onMessageReceived(remoteMessage);

       if (remoteMessage.getData().size() > 0) {

           getDesingNotification(remoteMessage.getData().get("title")
                   , remoteMessage.getData().get("conten"));
       }
   }

   private void getDesingNotification(String title, String descrip) {

       Intent intent = new Intent(this, SplashActivity.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

       Uri songUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

       NotificationCompat.Builder notification = new NotificationCompat.Builder(this)
               .setContentTitle(title)
               .setContentText(descrip)
               .setAutoCancel(true)
               .setSmallIcon(R.drawable.ic_notificacionbar)
               .setColor(ContextCompat.getColor(this, R.color.blue))
               .setSound(songUri)
               .setContentIntent(pendingIntent);

       NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       notificationManager.notify(0, notification.build());
   }
}

```

### Estructura de un Asynctask

```java
public class ExampleAsynctask extends AsyncTask<String, Integer, Boolean> {
    public PersonProcess personProcess;

    public ExampleAsynctask(Context context, ProgressBar progressBar, TextView textView) {
        personProcess = new PersonProcess(context);
        this.progressBar = progressBar;
        this.textView = textView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
        textView.setText(values[0] + "%");
        seconds.TimeSeconds(values[0] * 100 / 500);
    }

    @Override
    protected Boolean doInBackground(String... params) {
        for (int i = 0; i < 500; i++) {
            personProcess.insert(new Person(nameRandom, lastRandom, ageRandom, hobbyRandom, careerRandom, cityRandom));
            publishProgress((int) (((i + 1) / (float) (500)) * 100));
        }
        return null;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

    }
   }
```

#### Estructura /java/database
```
├─ java
│  ├─ com.domain.project
│     └─ database
│         └─ SQLiteConnection.java
│         └─ TableContent.java
│         └─ table
│              └─ EntityTable.java
│         └─ process
│              └─ EntityProcessor.java
│         └─ listener
│               └─ EntityInterface.java
```

### Estructura de un SQLiteConnection

```java
 public SQLiteConnection(Context context) {
        super(context, "db.example", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TableContent.sqlCreatePerson);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TableContent.sqlCreatePerson);
        db.execSQL(TableContent.sqlDropTablePerson);
    }
```

### Estructura de un TableContent

```java
public class TableContent {
    public static String sqlCreatePerson = "CREATE TABLE " +
            PersonTable.TABLE_NAME + " ( " +
            PersonTable.COD + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            PersonTable.NAME + " TEXT, " +
            PersonTable.LAST_NAME + " TEXT, " +
            PersonTable.AGE + " INTEGER, " +
            PersonTable.HOBBY + " TEXT, " +
            PersonTable.CAREER + " TEXT, " +
            PersonTable.CITY + " TEXT " + " ) ";

    public final static String sqlDropTablePerson = "DROP TABLE IF EXISTS " + PersonTable.TABLE_NAME;
}

```

### Estructura de un EntityTable

```java
public class EntityTable {
    public static final String TABLE_NAME = "PERSON";

    public static final String COD = "cod";
    public static final String NAME = "name";
    public static final String LAST_NAME = "last_name";
    public static final String AGE = "age";
    public static final String HOBBY = "hobby";
    public static final String CAREER = "career";
    public static final String CITY = "city";
}
```

### Estructura de un EntityProcessor

```java
public class EntityProcess implements EntityInterface<Person> {
    private Context context;
    private SQLiteConnection connection;
    private SQLiteDatabase db;

    public PersonProcess(Context context) {
        connection = new SQLiteConnection(context);
        db = connection.getWritableDatabase();
        this.context = context;
    }

    @Override
    public void insert(Person person) {
        ContentValues values = new ContentValues();

        values.put(PersonTable.NAME, person.getName());
        values.put(PersonTable.LAST_NAME, person.getLastName());
        values.put(PersonTable.AGE, person.getAge());
        values.put(PersonTable.HOBBY, person.getHobby());
        values.put(PersonTable.CAREER, person.getCareer());
        values.put(PersonTable.CITY, person.getCity());

        db.insert(PersonTable.TABLE_NAME, null, values);
    }

    @Override
    public Person find(String id) {
        return null;
    }

    @Override
    public List<Person> findAll() {
        List<Person> personList = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM " + PersonTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                personList.add(new Person(c.getString(1), c.getString(2), c.getInt(3), c.getString(4),
                        c.getString(5), c.getString(6)));
            } while (c.moveToNext());
        }
        return personList;
    }
}

```

### Estructura de un EntityInterface

```java
public interface EntityInterface<P> {
    void insert(P p);
    P find(String id);
    List<P> findAll();
}
```

** ProductTable.java define el nombre de la tabla y las columnas.
** SQLiteConnection.java hereda de SQLiteOpenHelper y define la creación o actualización de la base de datos.
** TableContent.java contiene el script de las tablas, que serán llamadas desde la clase SQLiteConnection.java

#### Estructura /java/session
```
├─ java
│  ├─ com.domain.project
│     └─ session
│         └─ Preference.java

** Almacenar las clases de tipo preferens asociados al usuario
```

#### Modelo de un Preference :

```java

public class Preference {

    private static final String PREFERENCE_NAME = "demo";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static Preference preference;

    private static final String EMAIL = "email";
    private static final String LOGIN = "login";

    private boolean logout;

    public static Preference getIntance(Context context) {
        if (preference == null) {
            preference = new Preference(context);
        }
        return preference;
    }

    public Preference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setEmail(String email) {
        editor.putString(EMAIL, email).commit();
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL, null);
    }

    public void setLogin(boolean login) {
        editor.putBoolean(LOGIN, login).commit();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }
}

```

#### Estructura /java/util
```
├─ java
│  ├─ com.domain.project
│     └─ util
│         └─ ColorUtil.java

** Almacenar las clases de tipo utilería
```

### Estructura de un util

```java

public class ColorUtil {

    public static void setColorByHour(Context context, TextView view, int hour) {
        switch (hour) {
            case 0:
            case 1:
            case 2:
                view.setTextColor(context.getResources().getColor(R.color.red));
                break;
            case 3:
            case 4:
                view.setTextColor(context.getResources().getColor(R.color.yellow));
                break;
            case 5:
                view.setTextColor(context.getResources().getColor(R.color.green));
                break;
        }
    }
}

```

#### Estructura /java/widget
```
├─ java
│  ├─ com.domain.project
│     └─ widget
│         └─ EditTextRound.java
│         └─ RoundButton.java

** Almacena componentes visuales customizados
```

### Estructura widget EditTextRound

```java

public class EditTextRound extends android.support.v7.widget.AppCompatEditText {

    public EditTextRound(Context context) {
        super(context);
        init(context);
    }

    public EditTextRound(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EditTextRound(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }


    private void init(Context context){
        setTextSize(TypedValue.COMPLEX_UNIT_PX,getResources().getDimension(R.dimen.textsp16));
        setBackgroundResource(R.drawable.round_edge);
        setTypeface(FontUtil.getDinProRegular(context));
    }
}

```

### Estructura widget RoundButton

```java

public class RoundButton extends android.support.v7.widget.AppCompatButton {

    public RoundButton(Context context) {
        super(context);
        init(context, false);
    }

    public RoundButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomButton);
        boolean disable = a.getBoolean(R.styleable.CustomButton_disable, false);
        init(context, disable);
    }

    public RoundButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, false);
    }

    private void init(Context context, boolean disable) {
        setTextColor(getResources().getColor(R.color.white));
        setTypeface(FontUtil.getDinProMedium(context));
        setBackgroundResource(disable ? R.drawable.gray_round_button : R.drawable.blue_round_button);
    }

    public void setDisable(boolean disable) {
        setBackgroundResource(disable ? R.drawable.gray_round_button : R.drawable.blue_round_button);
    }
}

```

### Estructura JAVA

#### Nomenclatura de paquetes
Los paquetes se nombran en singular y con letras minúsculas.
```
activity
fragment
adapter
model
util
```

#### Nomenclatura de clases
Las clases java se nombran utilizando el estándar “UpperCamelCase”, es decir utilizando
una combinación de palabras que empiezan con mayúscula como “ClasePrincipal”.
```
MainActivity
LoginActivity
RegisterFragment
DetailFragment
```

### Estructura de un layout
```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.constraint.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.example.leo.componet.dummy.MainActivity">

        <ImageView
            android:id="@+id/global_image"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            app:srcCompat="@mipmap/ic_launcher"
            android:layout_marginRight="8dp"
            app:layout_constraintRight_toRightOf="parent"
            android:layout_marginLeft="8dp"
            app:layout_constraintLeft_toLeftOf="parent"
            app:layout_constraintTop_toTopOf="parent"
            android:layout_marginTop="199dp" />

        <TextView
            android:id="@+id/inicio_text"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="TextView"
            android:textAppearance="@style/TextAppearance.AppCompat.Body2"
            android:textSize="24sp"
            android:layout_marginRight="8dp"
            app:layout_constraintRight_toRightOf="parent"
            android:layout_marginLeft="8dp"
            app:layout_constraintLeft_toLeftOf="parent"
            android:layout_marginTop="8dp"
            app:layout_constraintTop_toBottomOf="@+id/imageView"
            app:layout_constraintBottom_toBottomOf="parent"
            app:layout_constraintHorizontal_bias="0.501"
            android:layout_marginBottom="8dp"
            app:layout_constraintVertical_bias="0.0" />

</android.support.constraint.ConstraintLayout>
```

### Comentar una clases
```java
/**
  * Initializing GUI
  **/
private void init() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    TextView text = (TextView) findViewById(R.id.text);
}
```

### Estructura RES

#### Nomenclatura de paquetes
Al igual que en la sección Java, también se nombran los paquetes en minúsculas pero
no siempre en singular, habrá casos en los que usemos plurales como la carpeta “values”
y otros casos donde se usan guiones como en el caso de crear carpetas para para soporte de
de versiones android como “values-v21”.
```
drawable
layout
manu
values
values-v21
```

### Definir formato de Colors.xml

```xml
<resources>

        <color name="colorPrimary">@color/red</color>
        <color name="colorPrimaryDark">@color/blue</color>
        <color name="colorAccent">@color/blue50</color>

        <!-- blue -->
        <color name="blue">#310004ff</color>
        <color name="blue50">#310004ff</color>

        <!-- red -->
        <color name="red">#ff0000</color>
        <color name="red50">#711616</color>

</resources>
```

### Definir formato de dimens.xml

```xml
<resources>

    <!-- text -->
    <dimen name="text1">1sp</dimen>
    <dimen name="text2">2sp</dimen>
    <dimen name="text3">3sp</dimen>
    <dimen name="text4">4sp</dimen>
    <dimen name="text5">5sp</dimen>

    <!-- size -->
    <dimen name="size1">1dp</dimen>
    <dimen name="size2">2dp</dimen>
    <dimen name="size3">3dp</dimen>
    <dimen name="size4">4dp</dimen>
    <dimen name="size5">5dp</dimen>

    <!-- spacing -->
    <dimen name="spacing1">1dp</dimen>
    <dimen name="spacing2">2dp</dimen>
    <dimen name="spacing3">3dp</dimen>
    <dimen name="spacing4">4dp</dimen>
    <dimen name="spacing5">5dp</dimen>

</resources>
```

### Definir cadenas estáticas por pantalla en strings.xml

```xml
<resources>

    <!-- activity login -->
    <string name="login_title">Titulo del Activity</string>
    <string name="login_text_descripcion">Descripcion</string>

    <!-- Error -->
    <string name="error_usuario">Error el usuario no esta registrado</string>

</resources>
```

#### Nomenclatura de un include

Al crear un includ se nombrará de la siguiente forma
Cuando la vista contenga un Toolbar - > `app_bar_main.xml` , `app_bar_contact.xml`

```xml
  <include
        layout="@layout/app_bar_main"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

Cuando la vista forma parte de la ventana -> `custom_contact.xml , custom_about.xml`

```xml
  <include
        android:id="@+id/custom_contact"
        layout="@layout/custom_contact"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />
```

Cuando la vista sea el contenedor de los fragments- > `content_menu.xml`

#### Uso de las carpetas “mipmap“ y “drawable”
Android Studio nos provee por defecto de ambas carpetas para almacenar los recursos gráficos del proyecto.

La carpeta `mipmap`, sirve exclusivamente para almacenar el icon launcher de la aplicación, es decir que solo
almacenará los archivos `ic_launcher.png` y su distribución será así:
```
├─ mipmap-hdpi
│  ├─ ic_launcher.png
│  |
├─ mipmap-mdpi
│  ├─ ic_launcher.png
│  |
├─ mipmap-xhdpi
│  ├─ ic_launcher.png
│  |
├─ mipmap-xxhdpi
│  ├─ ic_launcher.png
│  |
├─ mipmap-xxxhdpi
│  ├─ ic_launcher.png
│  |
```

La carpeta `drawable`, sirve para almacenar recursos .xml tales como los selector, shape y derivados.
La carpeta `drawable-hdpi`, `drawable-xhdpi`y demás sirven para almcenar los recursos gráficos de la aplicación, como íconos, logos, imágenes de fondo, etc.

#### Utilizar fuentes de texto
Si bien Androi Studio no nos provee de una carpeta por defecto para almacenar archivos .ttf .otf o derivados, podemos optar por crear nuestra propia
paquetería para almacenar otros archivos que no sea recursos gráficos.

Crearemos una carpeta llamanda `assets` y dentro crearemos la carpeta `font`, aquí podremos guardar nuestros archivos .ttf o derivados.

```
─ main
   ├─ assets
   │  └─ font
   ├─ java
   │  └─ com/domain/project
   ├─ res
   └─ AndroidManifest.xml
```

### Librerías a utilizar

    Butter Knife -> https://github.com/JakeWharton/butterknife#butter-knife
    picasso -> http://square.github.io/picasso/
    Retrofit -> http://square.github.io/retrofit/
    mpandroidchart -> https://github.com/PhilJay/MPAndroidChart
    firebase -> https://firebase.google.com/

