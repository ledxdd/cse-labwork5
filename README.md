# 🚀 Лабораторная работа №5: Управление коллекцией Space Marine

## 📋 Описание проекта
Консольное приложение для управления коллекцией объектов `SpaceMarine` с интерактивным режимом. Данные хранятся в XML-файле, коллекция автоматически сортируется при добавлении элементов.

## ⚙️ Требования к программе

### 🎯 Основные требования
- Класс `SpaceMarine` реализует сортировку по умолчанию (`Comparable`)
- Хранение элементов в коллекции `java.util.TreeSet`
- Автоматическое заполнение коллекции из файла при запуске
- Имя файла передается через аргумент командной строки
- Формат хранения данных: **XML**
- Чтение данных: `java.io.BufferedReader`
- Запись данных: `java.io.BufferedWriter`
- Полное документирование классов в формате **javadoc**
- Корректная обработка ошибок (неверный ввод, отсутствие прав доступа и т.д.)

### 📦 Описание хранимых классов

#### SpaceMarine
```java
public class SpaceMarine {
    private Long id;                    // Не null, >0, уникальный, авто-генерация
    private String name;                 // Не null, не пустой
    private Coordinates coordinates;     // Не null
    private java.time.ZonedDateTime creationDate; // Не null, авто-генерация
    private Double health;                // Может быть null, >0
    private String achievements;          // Может быть null
    private AstartesCategory category;    // Может быть null
    private Weapon weaponType;            // Может быть null
    private Chapter chapter;              // Не null
}
```

#### Coordinates
```java
public class Coordinates {
    private Double x;    // Не null
    private float y;
}
```

#### Chapter
```java
public class Chapter {
    private String name;          // Не null, не пустой
    private int marinesCount;      // 1-1000
    private String world;          // Может быть null
}
```

#### Enums
```java
public enum AstartesCategory { SCOUT, AGGRESSOR, TACTICAL, APOTHECARY }
public enum Weapon { MELTAGUN, BOLT_RIFLE, PLASMA_GUN, GRENADE_LAUNCHER }
```

## 🎮 Интерактивные команды

| Команда | Описание |
|---------|----------|
| `help` | Справка по доступным командам |
| `info` | Информация о коллекции (тип, дата создания, размер) |
| `show` | Все элементы коллекции |
| `add {element}` | Добавить новый элемент |
| `update id {element}` | Обновить элемент по ID |
| `remove_by_id id` | Удалить элемент по ID |
| `clear` | Очистить коллекцию |
| `save` | Сохранить коллекцию в файл |
| `execute_script file_name` | Выполнить скрипт из файла |
| `exit` | Завершить программу (без сохранения) |
| `add_if_max {element}` | Добавить, если элемент больше максимального |
| `add_if_min {element}` | Добавить, если элемент меньше минимального |
| `remove_lower {element}` | Удалить все элементы меньше заданного |
| `average_of_health` | Среднее значение health |
| `count_less_than_chapter chapter` | Количество элементов с chapter < заданного |
| `print_field_ascending_achievements` | Вывести achievements по возрастанию |

## 📝 Формат ввода команд

### 🔹 Простые типы данных
Вводятся в той же строке, что и команда:
```bash
> remove_by_id 42
> update 15
```

### 🔹 Составные типы данных
Вводятся построчно с приглашениями:
```bash
> add
Введите имя:
> Артас
Введите координату X:
> 15.5
...
```

### 🔹 Особые правила
- **Enum'ы**: Сначала выводится список констант, затем ввод имени
- **Null значения**: Вводятся пустой строкой
- **Авто-генерируемые поля**: Не запрашиваются у пользователя
- **Обработка ошибок**: При неверном вводе - сообщение и повтор ввода

## 🛠️ Обработка ошибок
Программа корректно обрабатывает:
- ❌ Неверный пользовательский ввод
- ❌ Отсутствие прав доступа к файлу
- ❌ Неверный формат данных в файле
- ❌ Ошибки при выполнении скриптов

## 📚 Документация
Все классы документированы в формате **javadoc** с описанием полей, методов и логики работы.

## 🚀 Запуск программы
```bash
java cse_labwork5.src.Main <имя_файла.xml>
```

---
📅 *Лабораторная работа по программированию на Java*
