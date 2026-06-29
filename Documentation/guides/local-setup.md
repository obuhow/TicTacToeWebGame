# Инструкция по локальному запуску проекта

## Требования
*Обязательно*
- JDK 18

*Опционально*
- Docker и Docker Compose (опционально, если хотите использовать контейнер)
- PostgreSQL 14 (опционально, если используете `prod` профиль без Docker)

## 1. Клонирование репозитория

```bash
git clone https://github.com/obuhow/TicTacToeWebGame.git
cd TicTacToeWebGame
```
## 2. Сборка и запуск

### Вариант А - локально через Gradle ###

```bash
./gradlew bootRun
```

Приложение стартует с профилем dev (использует встроенную H2 БД).
Swagger UI будет доступен по адресу: http://localhost:8080/swagger-ui/index.html

### Вариант Б – через Docker Compose (с PostgreSQL) ###

```bash
docker-compose up -d
```

## 3. Настройка профилей
- Профиль dev – использует H2 in-memory (данные не сохраняются).
- Профиль prod – требует PostgreSQL. Можно задать через переменную SPRING_PROFILES_ACTIVE=prod.