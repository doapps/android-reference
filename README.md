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

### Estructura base
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

### Comentar una clases
```java
/**Initializing GUI **/
private void init(){
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    TextView text = (TextView) findViewById(R.id.text);
}
```

### Definir formato de Colors.xml

```xml
<resources>

    <!-- grayscale -->
    <color name="white"     >#FFFFFF</color>
    <color name="gray_light">#DBDBDB</color>
    <color name="gray"      >#939393</color>
    <color name="gray_dark" >#5F5F5F</color>
    <color name="black"     >#323232</color>

    <!-- basic colors -->
    <color name="green">#27D34D</color>
    <color name="blue">#2A91BD</color>
    <color name="orange">#FF9D2F</color>
    <color name="red">#FF432F</color>

</resources>
```
