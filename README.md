# Monitor Sensors - CRUD Application

## Описание проекта

Monitor Sensors - это CRUD приложение, предназначенное для управления данными сенсоров, включая их создание, чтение, обновление и удаление. Приложение разработано с использованием следующих технологий:

- Java 11-21
- Spring Boot
- Spring Security
- Hibernate
- Реляционная база данных (выбор за разработчиком)

## Сущность датчиков

Сущность датчиков включает следующие поля:

- **Название датчика (name)**: текстовое поле, обязательное, валидация - не пустое поле, количество символов от 3 до 30.
- **Модель (model)**: текстовое поле, обязательное, валидация - не пустое поле, количество символов не превышает 15.
- **Тип (type)**: список выбора, предустановленные данные из БД. Значения - Pressure, Voltage, Temperature, Humidity.
- **Радиус работы (range)**: объект с полями:
  - **from**: положительное целое число, минимальное значение диапазона, обязательное.
  - **to**: положительное целое число, максимальное значение диапазона, обязательное, должно быть больше `from`.
- **Единица измерения (unit)**: список выбора, предустановленные данные из БД. Значения - bar, voltage, °С, %.
- **Местоположение (location)**: текстовое поле, валидация не больше 40 символов.
- **Описание (description)**: текстовое поле, валидация не больше 200 символов.

### Пример JSON-модели запроса

```json
{
  "name": "Barometer", 
  "model": "ac-23", 
  "range": {
    "from": 22, 
    "to": 45
  },
  "type": "Pressure",
  "unit": "bar", 
  "location": "kitchen", 
  "description": "description"
}
