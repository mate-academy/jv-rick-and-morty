# Rick And Morty requirements

### Business requirements

- Task: Create API. It should contain two methods:

1. The request randomly generates a wiki about one character in the universe the animated series Rick & Morty.
   Запит випадковим чином генерує вікі про одного персонажа всесвіту мультсеріалу «Рік і Морті».
   Response example:

    ```json
    {
      "id": 1,
      "externalId": "1",
      "name": "Rick Sanchez",
      "status": "Alive",
      "gender": "Male"
    }
    ```
    
    NOTE: `externalId` field should save the original character ID received from the external API. `id` field should
    represent the identifier of entire `Character` entity, that is associated with internal DB.
   ПРИМІТКА: поле `externalId` має зберігати вихідний ідентифікатор символу, отриманий із зовнішнього API. Поле `id`
   має представляти ідентифікатор усієї сутності `Character`, яка пов'язана з внутрішньою БД.

2. The request takes a string as an argument, and returns a list of all characters whose name contains the search
   string.
   Запит приймає рядок як аргумент і повертає список усіх символів, ім’я яких містить пошуковий рядок.
    
   During the application start, the web application downloads data from a third-party service to the internal database.
   Під час запуску програми веб-програма завантажує дані зі стороннього сервісу у внутрішню базу даних.

   Implemented API requests must work with a local database (i.e. fetch data from a database).
   Реалізовані запити API мають працювати з локальною базою даних (тобто отримувати дані з бази даних).

- What to use:

1. You must use [public API](https://rickandmortyapi.com/documentation/#rest) (you should use REST API).
2. All data from the public API should be fetched once, and only once, when the Application context is created
   Усі дані з загальнодоступного API слід отримати один раз і лише один раз під час створення контексту програми

### Tech Requirements

- Use MySQL DB in your app.
- Use H2 DB in your test configuration (It is already configured in the `src/test/resources/application.properties`
  file).
- Keep identical set of params in the `src/main/resources/application.properties`
  and `src/test/resources/application.properties` files. In other case you may face a problem with Application Context
  creation during the `mvn test` phase.
- Requests must be documented using Swagger.
- Використовуйте базу даних MySQL у своїй програмі. 
- Використовуйте H2 DB у своїй тестовій конфігурації (її вже налаштовано у файлі`src/test/resources/application.properties`). 
- Зберігайте ідентичний набір параметрів у файлах `src/main/resources/application.properties` 
- і `src/test/resources/application.properties`. В іншому випадку ви можете зіткнутися з проблемою 
- створення контексту програми під час фази `mvn test`. 
- Запити мають бути задокументовані за допомогою Swagger.