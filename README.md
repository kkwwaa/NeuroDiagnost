
# 🚀 Запуск проекта

```bash
./gradlew bootRun
```

Приложение поднимается на:

- **http://localhost:8080**

## 🔐 Доступ (Spring Security сейчас отключен в классе `SecurityConfig`)

При первом запуске Spring Boot генерирует временный пароль.

В логах будет строка вида:

```
Using generated security password: <PASSWORD>
```

Использовать для входа:

- **username:** `user`
- **password:** смотри лог запуска


## 📘 Swagger / OpenAPI

Swagger UI доступен по адресу:

- **http://localhost:8080/swagger-ui.html**

OpenAPI спецификация:

- **http://localhost:8080/v3/api-docs**

Если ручек нет — Swagger покажет *“No operations defined in spec”*.

## 🧱 База данных

Используется **PostgreSQL**.

- Имя БД: `neuropsychology`
- Подключение настраивается в `application.yaml`

## 🧩 Liquibase

Liquibase автоматически применяет миграции при запуске.

Главный changelog:

```
src/main/resources/db/changelog/db.changelog-master.yaml
```

Первая миграция со схемой:

```
src/main/resources/db/changelog/changes/001-init-schema.sql
```

При успешном запуске в логах будет:

```
Database is up to date, no changesets to execute
```
