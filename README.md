# Веб-игра Крестики-нолики (Spring Boot + REST API)

## 📖 О проекте

Веб-приложение **«Крестики-нолики»** с поддержкой:

- **Одиночного режима** (игрок против компьютера)
- **Многопользовательского режима** (два игрока по очереди через API)
- **Регистрации и аутентификации** пользователей (Basic Auth)
- **Хранения данных** в PostgreSQL
- **Интерактивной документации API** (Swagger UI)

Проект создавался как учебный для практики навыков:
- Архитектурного проектирования
- Разработки: REST API, PostgreSQL, Spring Security
- Тестирования API и UI, manual и auto
- Настройки CI/CD с применением Docker и GitHub Actions
- Ведения релизной документации

---

## Актуальная версия
- **Текущий релиз:** 0.1 (см. [release-notes](./Documentation/release-0.1/release-notes.md))
- **Следующий релиз:** 1.0 (план – [здесь](./Documentation/release-1.0/release-plan.md))

---

## Документация

- **[Релизы](./Documentation/)** – планы и отчеты по каждому релизу
- **[Инструкции](./Documentation/guides/)** – для локального запуска, тестирования
- **[Postman-коллекция](./Documentation/test-collections/)** – тест коллекция Postman для каждого релиза
- **Swagger UI:** http://176.109.101.94:8080/swagger-ui/index.html

---

## Технологический стек

| Компонент       | Технология                                         |
|-----------------|----------------------------------------------------|
| **Язык**        | Java 18                                            |
| **Фреймворк**   | Spring Boot 3.1.5                                  |
| **Безопасность**| Spring Security (Basic Auth, кастом.фильтр)              |
| **БД**          | PostgreSQL 14                                      |
| **ORM**         | Spring Data JPA (Hibernate)                        |
| **Документация**| Springdoc OpenAPI (Swagger UI)                     |
| **Сборка**             | Gradle (Gradle Wrapper)                              |
| **Контейнеризация**    | Docker                                               |
| **Реестр образов**     | GitHub Container Registry (GHCR)                     |
| **CI/CD**              | GitHub Actions (автоматическая сборка и деплой)      |
| **Облачный хостинг**   | Cloud.ru (Evolution Free Tier)                       |
| **Управление секретами**| `.env` + GitHub Secrets (для CI)                    |                  |
| **Тесты**       | JUnit 5, MockMvc (на доработке после рефакторинга проекта) |

---

## Архитектура проекта

```
src/main/java/com/faraldma/tictactoe/
├── datasource/                 # Работа с БД 
│   ├── model/                  # JPA-сущности (GameEntity, User)
│   ├── repository/             # Интерфейсы и реализации репозиториев
│   ├── mapper/                 # Маппинг Entity <-> Domain
│   └── converter/              # Конвертор типов (int[][] <-> JSON)
├── domain/                     # Бизнес-логика и модели 
│   ├── model/                  # Доменные модели (Game, GameBoard, Move)
│   ├── service/                # Интерфейсы и реализации сервисов
│   └── exceptions/             # Кастомные исключения
├── web/                        # Web-слой 
│   ├── controller/             # REST-контроллеры (Single, Multiplayer, Auth + GlobalExceptionHandler)
│   ├── model/                  # DTO (запросы/ответы)
│   └── mapper/                 # Маппинг Domain <-> DTO
├── security/                   # Авторизация и аутентификация
│   ├── AuthFilter.java         # Кастомный фильтр Basic Auth
│   ├── AuthService.java        # Сервис аутентификации
│   └── SecurityConfig.java     # Конфигурация Spring Security
├── di/                         # Конфигурация зависимостей (AppConfig)
└── util/                       # Вспомогательные классы (ArrayCopyUtil)
```
## Планы по развитию

- ✅ Реализовать алгоритм минимакс для одиночной игры
- ✅ Добавить поддержку многопользовательской игры
- ✅ Настроить авторизацию и регистрацию
- ✅ Выложить на облачный сервер с документацией
- ✅ Опубликовать тестовые коллекции в Postman для онлайн-тестирования API
- ✅ Настроить CI/CD (GitHub Actions) для автоматического деплоя
- ⬜ Разработать фронтенд (Thymeleaf либо Vue.js)
- ⬜ Написать автотесты (JUnit, MockMvc, Testcontainers) 

