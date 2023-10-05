# Introduccion
En esta primera etapa el Servidor Central solamente se comunicará con el
Componente Estación de Trabajo que brinda una interfaz en Swing que los
administradores usarán para interactuar con el sistema. En esta tarea se espera que la
distribución de los componentes del sistema sea consistente con el siguiente
diagrama.

En esta etapa el sistema consistirá de un solo nodo llamado Servidor Central que
ejecutará una sola máquina virtual Java. Hay que considerar que en las próximas
iteraciones el Servidor Central se comunicará con el Servidor Web y finalmente con el
Dispositivo Móvil.
En cuanto a la lógica que maneja la primera iteración, el Servidor Central
implementará una versión inicial de la lógica del sistema final. Se manejarán los
aspectos que están relacionados a los usuarios, actividades deportivas, dictado de
clases y cuponeras.

## Visión
La plataforma entrenamos.uy ofrece un servicio de gestión social de actividades
deportivas a través de Internet. Para utilizar las funcionalidades que brinda la
plataforma, los usuarios deberán registrarse utilizando sus datos personales.

De cada usuario interesa saber su nickname (único), su nombre, su apellido, su correo
electrónico (único) y su fecha de nacimiento. Si es profesor/a interesa saber a qué
institución pertenece, así como tener una descripción general, una breve biografía
(opcional) y un link a su sitio web (opcional). Los/as profesores/as dictan clases de
actividades deportivas en la institución deportiva a la que pertenecen.
En la plataforma se registran actividades deportivas, de las cuales se indica la
institución deportiva que las ofrece, nombre (único dentro de la plataforma),
descripción, duración en minutos, costo y fecha de registro en la plataforma.
De las clases se indica el nombre de la clase (única), la actividad deportiva asociada,
la fecha de la clase, el/la profesor/a que la dicta, hora de inicio, la URL para acceder a
la clase, y su fecha de registro en la plataforma.
Los/as socios/as pueden registrarse para clases de actividades deportivas,
conociéndose la fecha en que se realiza la inscripción. El/la socio/a podrá registrarse
a las diferentes clases quedando almacenada la fecha de dicho registro.


## Actores
En esta etapa se contará con un único actor que es el Administrador del Sistema

## Casos de uso (Entrega 1)

Caso de uso Alta de Usuario

Descripción El caso de uso comienza cuando el administrador desea dar de alta a
un nuevo usuario en el sistema. Para ello indica si es un/a socio/a o un
profesor/a y sus datos básicos: nickname (único), nombre, apellido,
correo electrónico (único) y fecha de nacimiento. Si el usuario es un
profesor/a se ingresan además otros datos básicos: la institución a la
cual pertenece, una descripción general, una breve biografía (opcional)
y un link a su sitio web (opcional).
Si el nickname o el correo electrónico se encuentran en uso por algún
otro usuario, el sistema avisa al administrador, pudiendo éste corregir
la información o cancelar el alta. Finalmente, el sistema da de alta al
usuario.

------
Caso de uso | Consulta de Usuario

Descripción | El caso de uso comienza cuando el administrador desea consultar el
perfil de un usuario. Para ello el sistema muestra la lista de todos los
usuarios y el administrador elige uno. Luego, el sistema muestra todos
los datos básicos del usuario. Si es profesor/a se muestra también la
información básica de las clases que dicta y actividades deportivas
asociadas. Si es socio/a se muestra también la información de las
clases a las que se registró.
Si el administrador selecciona una actividad deportiva o una clase de
una actividad deportiva, se muestra la información detallada, tal como
se indica en los casos de uso Consulta de Actividad Deportiva y
Consulta de Dictado de Clases, respectivamente.

------
Caso de uso | Consulta de Usuario

Descripción | El caso de uso comienza cuando el administrador desea consultar el
perfil de un usuario. Para ello el sistema muestra la lista de todos los
usuarios y el administrador elige uno. Luego, el sistema muestra todos
los datos básicos del usuario. Si es profesor/a se muestra también la
información básica de las clases que dicta y actividades deportivas
asociadas. Si es socio/a se muestra también la información de las
clases a las que se registró.
Si el administrador selecciona una actividad deportiva o una clase de
una actividad deportiva, se muestra la información detallada, tal como
se indica en los casos de uso Consulta de Actividad Deportiva y
Consulta de Dictado de Clases, respectivamente.

------
Caso de uso | Alta de Actividad Deportiva

Descripción | El caso de uso comienza cuando el administrador desea dar de alta una
actividad deportiva. En primer lugar, el administrador indica la
institución que la ofrece y los datos básicos de la misma: nombre
(único), descripción, duración, costo, fecha de alta. En caso de que
exista una actividad deportiva con dicho nombre, el administrador
puede modificar los datos o cancelar el caso de uso. Finalmente, el
sistema da de alta la actividad deportiva

------
Caso de uso | Consulta de Actividad Deportiva

Descripción | El caso de uso comienza cuando el administrador desea consultar una
actividad deportiva. En primer lugar, el administrador indica la
institución que ofrece la actividad deportiva y el sistema lista las
actividades deportivas asociadas a la misma. El administrador elige una
de ellas y el sistema devuelve todos los datos de la actividad,
incluyendo la lista de clases asociadas. Si el administrador selecciona
una clase asociada a esa actividad deportiva, se muestra la información
detallada, tal como se indica en el caso de uso Consulta de Dictado de
Clase.

------
Caso de uso | Alta de dictado de Clase

Descripción | El caso de uso comienza cuando el administrador desea dar de alta una
clase para una actividad deportiva. En primer lugar, el administrador
indica una institución deportiva y el sistema lista las actividades
deportivas asociadas a la misma. El administrador elige una de ellas e
ingresa los datos básicos de la clase: nombre de la clase (único), fecha
y hora de inicio, profesor/a que la dicta, URL asociada y la fecha de alta.
En caso de que exista una clase con el nombre ingresado, el
administrador puede modificar los datos o cancelar el caso de uso.
Finalmente, el sistema da de alta el dictado de la clase.

------
Caso de uso | Registro a dictado de Clase

Descripción | El caso de uso comienza cuando el administrador desea realizar un
registro de un/a socio/a a una clase de una actividad deportiva. En
primer lugar, el administrador indica la institución a través de la cual se
ofrece la actividad deportiva y el sistema muestra las actividades
asociadas a la misma. El administrador elige una y el sistema muestra
los datos básicos de las clases, si existen. Luego, el sistema muestra la
lista de todos los/as socios/as y el administrador selecciona el/la
socio/a que quiere registrar, la clase a la que lo quiere registrar, la
fecha de registro.
En caso de que ya exista un registro de el/la socio/a la clase deportiva
el administrador podrá: cambiar la clase seleccionada, cambiar el/la
socio/a seleccionado/a o cancelar el caso de uso. Finalmente, el
sistema realiza el registro de el/la socio/a a la clase en dicha fecha y
con el costo del registro.

------
Caso de uso | Alta de Institución Deportiva

Descripción | El caso de uso comienza cuando el administrador desea dar de alta una
institución. Para ello, indica el nombre de la institución (único), una
descripción y una URL. Si ya existe una institución con igual nombre, el
administrador puede reingresar los datos o cancelar el caso de uso.
Finalmente, el sistema da de alta la institución deportiva.

------
Caso de uso | Modificar Datos de Usuario

Descripción | El caso de uso comienza cuando el administrador desea modificar el
perfil de un usuario. Para ello el sistema muestra la lista de todos los
usuarios y el administrador elige uno. Luego, el sistema muestra todos
los datos básicos del usuario. El administrador puede editar todos los
datos básicos, menos el nickname y el correo electrónico. Cuando
termina la edición, el sistema actualiza los datos del usuario.

------
Caso de uso Consulta de dictado de Clase

Actores Administrador del Sistema
Descripción El caso de uso comienza cuando el administrador desea consultar una
clase de una actividad deportiva. En primer lugar, el administrador
indica la institución que ofrece la actividad y el sistema lista las
actividades deportivas asociadas a la misma. El administrador elige una
y el sistema lista todas las clases asociadas. El administrador elige una
de ellas y el sistema devuelve todos los datos de la clase seleccionada

------
Caso de uso | Consulta de dictado de Clase

Descripción | El caso de uso comienza cuando el administrador desea consultar una
clase de una actividad deportiva. En primer lugar, el administrador
indica la institución que ofrece la actividad y el sistema lista las
actividades deportivas asociadas a la misma. El administrador elige una
y el sistema lista todas las clases asociadas. El administrador elige una
de ellas y el sistema devuelve todos los datos de la clase seleccionada

------
Caso de uso | Modificar Actividad Deportiva

Descripción | El caso de uso comienza cuando el administrador desea modificar una
actividad deportiva. Para ello el sistema muestra la lista de todas las
actividades deportivas y el administrador elige una. Luego, el sistema
muestra todos los datos básicos de la actividad deportiva. El
administrador puede editar todos los datos básicos, menos el nombre y
la fecha de registro. Cuando termina la edición, el sistema actualiza los
datos de la actividad deportiva.

------
Caso de uso | Modificar Institución Deportiva

Descripción | El caso de uso comienza cuando el administrador desea modificar una
institución deportiva. Para ello el sistema muestra la lista de todas las
instituciones deportivas y el administrador elige una. Luego, el sistema
muestra todos los datos básicos de la institución deportiva. El
administrador puede editar todos los datos básicos, menos el nombre
Cuando termina la edición, el sistema actualiza los datos de la
institución deportiva.

------
Caso de uso | Ranking de dictados de clases

Descripción | El caso de uso comienza cuando el administrador desea ver el Ranking
de dictado de clases. El sistema lista las clases ordenadas de mayor a menor considerando la cantidad de socios registrados. De cada clase se
lista el nombre, fecha de clase y url.

------
Caso de uso | Ranking de Actividades Deportivas

Descripción | El caso de uso comienza cuando el administrador desea ver el Ranking
de actividades deportivas. El sistema lista las clases ordenadas de
mayor a menor considerando la cantidad de clases asociadas que
tiene. De cada actividad deportiva se lista el nombre, costo y
descripción.

## Persistencia de datos
Se espera que todos los datos que impliquen los requerimientos mínimos estén
persistidos utilizando Hibernate cómo se trabaja durante el curso.