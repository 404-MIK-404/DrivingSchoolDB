# Разработка БД для ИС «Автошкола»


Целью курсового проекта является разработка базы данных «Автошкола» для автоматизации работы преподавателей .

**Приложение было разработанно на языке Java с использованием платформы JavaFX**

## Задачи курсового проекта: ##

•	провести системный анализ предметной области «»;

•	провести обзор информационных технологий, подходящих для разработки БД;

•	изучить аналогичные информационные системы данной предметной области;

•	описать требования, предъявляемые к разработке данной базы данных;

•	разработать инфологическую модель базы данных;

•	обосновать выбор модели данных и осуществить логическое проектирование базы данных;

•	нормализовать спроектированную модель и составить схему базы данных;

•	осуществить реализацию БД на выбранной СУБД;


## Структура БД ##

База данных «» содержит таблиц, которые были спроектированы в БД а также 4 триггера.

База данных содержит 10 таблиц:

•	Должность;

•	Персонал;

•	Расписание групп;

•	Группа;

•	Предметы;

•	Ученики;

•	Результаты экзаменов;

•	Экзамены;

•	Тариф;

•	Автомобили;


## Триггеры ##

• Первый триггер происходит в таблице "Экзамены" данный триггер проверяет id персонал, так как на экзамене могут быть автоинструктуры и преподаватели

• Второй триггер работает в таблице "Расписание" триггер работает с id персонала, так как работать с расписанием могут преподователи

• Третий триггер обрабатывает таблицу "Результаты Экзамена" в случай если оценка ученика отсуствует, то автоматически присваивается значение "не явился"

• Четвертый триггер работает в таблице "Тариф" он производит расчёт финальную цены клиента, в случай если скидка у клиента выше 10 процентов, то начисляется доп. скидка в 2 процента .


