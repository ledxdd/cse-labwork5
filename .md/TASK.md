# 🚀 Лабораторная работа №5: Управление коллекцией Space Marine

## 📋 О проекте
Консольное приложение для управления коллекцией объектов `SpaceMarine` с интерактивным режимом. Реализует полный CRUD функционал с автоматической сортировкой и сохранением данных в XML-формате.

## ✨ Реализованный функционал

### 🎯 Основные возможности
- ✅ **Интерактивный режим** с поддержкой 16 команд
- ✅ **Автоматическая сортировка** элементов по ID (через `Comparable`)
- ✅ **XML сериализация/десериализация** с использованием `BufferedReader/BufferedWriter`
- ✅ **Валидация всех полей** согласно требованиям ТЗ
- ✅ **Обработка ошибок** ввода и файловых операций
- ✅ **Javadoc документация** всех классов

### 📦 Структура проекта
```
cse-labwork5/
├── src/
│   └── cse_labwork5/
│       └── src/
│           └── Main.java          # Основной класс приложения
├── data/                           # Директория для XML файлов
│   └── marines.xml                 # Пример файла с данными
├── scripts/                        # Директория для скриптов
│   └── test.txt                    # Пример скрипта
├── README.md                       
└── .gitignore
```

## 🎮 Реализованные команды

| Команда | Статус | Описание |
|---------|--------|----------|
| `help` | ✅ | Справка по доступным командам |
| `info` | ✅ | Информация о коллекции |
| `show` | ✅ | Показать все элементы |
| `add` | ✅ | Добавить нового десантника |
| `update id` | ✅ | Обновить элемент по ID |
| `remove_by_id id` | ✅ | Удалить элемент по ID |
| `clear` | ✅ | Очистить коллекцию |
| `save` | ✅ | Сохранить в XML-файл |
| `execute_script` | ✅ | Выполнить скрипт |
| `exit` | ✅ | Выход из программы |
| `add_if_max` | ✅ | Добавить если больше макс. |
| `add_if_min` | ✅ | Добавить если меньше мин. |
| `remove_lower` | ✅ | Удалить меньшие элементы |
| `average_of_health` | ✅ | Среднее здоровье |
| `count_less_than_chapter` | ✅ | Подсчет по chapter |
| `print_field_ascending_achievements` | ✅ | Вывод achievements |

## 🛠️ Технические детали

### Ключевые особенности реализации
- **Коллекция**: `TreeSet<SpaceMarine>` с сортировкой по ID
- **Сериализация**: XML через ручной парсинг (BufferedReader/BufferedWriter)
- **Валидация**: Все поля проверяются через сеттеры с исключениями
- **Ввод данных**: Построчный с приглашениями и повторными попытками
- **Null значения**: Вводятся пустой строкой

### Пример работы с программой

```bash
# Запуск программы
> java cse_labwork5.src.Client data/marines.xml
Enter 'help' to see available commands:

> help
info : вывести информацию о коллекции
show : вывести все элементы коллекции
...

> add
*******| New marine creation |*******
Enter the marine name: 
> Артас
Enter marine's health: 
> 100.5
...
✅ Marine created! ID: 42

> show
[SpaceMarine{id=42, name='Артас', health=100.5, ...}]

> average_of_health
Среднее здоровье: 85.3

> save
Коллекция сохранена в файл data/marines.xml

> exit
```

## ⚙️ Требования к окружению

- **Java**: JDK 11 или выше
- **ОС**: Windows/Linux/macOS (поддерживаются все)

## 🚀 Быстрый старт

```bash
# 1. Клонировать репозиторий
git clone <url-репозитория>
cd cse-labwork5

# 2. Скомпилировать проект
javac cse_labwork5/src/Client.java

# 3. Запустить с указанием файла данных
java cse_labwork5.src.Client data/marines.xml
```

## 📝 Пример XML файла

```xml
<SpaceMarines>
    <SpaceMarine>
        <id>1</id>
        <name>Captain Titus</name>
        <coordinates>
            <x>15.5</x>
            <y>3.2</y>
        </coordinates>
        <health>100.0</health>
        <achievements>Hero of Macragge</achievements>
        <category>TACTICAL</category>
        <weaponType>BOLT_RIFLE</weaponType>
        <chapter>
            <name>Ultramarines</name>
            <marinesCount>150</marinesCount>
            <world>Macragge</world>
        </chapter>
    </SpaceMarine>
</SpaceMarines>
```

## 🧪 Тестирование

Программа протестирована на следующих сценариях:
- ✅ Корректный ввод всех типов данных
- ✅ Обработка неверных значений (строки вместо чисел и т.д.)
- ✅ Работа с null значениями (пустая строка)
- ✅ Выполнение скриптов с командами
- ✅ Отсутствие файла/прав доступа
- ✅ Некорректный XML формат

## 📚 Документация

Javadoc сгенерирован для всех классов и методов. Для просмотра:
```bash
javadoc -d docs cse_labwork5/src/*.java
```

## 👨‍💻 Автор

Студент: [Ваше имя]
Группа: [Ваша группа]
Вариант: [Номер варианта]

## 📅 Дата выполнения

Февраль 2026

---
*Лабораторная работа по программированию на Java*