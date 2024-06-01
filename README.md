# Система онлайн платежей для компаний

Добро пожаловать в систему онлайн платежей для компаний! Этот проект создан для обеспечения удобного и безопасного способа для компаний принимать онлайн платежи от своих клиентов. Наша система предоставляет простой и эффективный способ для компаний автоматизировать процесс приема платежей, что позволяет сэкономить время и упростить учет финансов.
![image (5)](https://github.com/irrdscnt/PaySystem/assets/116700521/91478926-d722-4d2b-bc45-cf9594d458d5)

## Возможности
- Принимайте платежи онлайн от клиентов без лишних усилий.
- Обрабатывайте транзакции безопасно и надежно.
- Предоставляйте различные способы оплаты для удобства клиентов.
- Получайте уведомления и отчеты о платежах для лучшего управления финансами.
![image (6)](https://github.com/irrdscnt/PaySystem/assets/116700521/119ae0b1-7de8-462a-a2d7-3a6d37a33605)

# Установка
## 1.Клонируйте репозиторий
```bash
https://github.com/irrdscnt/PaySystem
cd PaySystem
```
### Если вы не используете Git, то вы можете просто скачать исходный код репозитория в ZIP-архиве и распаковать его на свой компьютер.

## 2.Подключение вашей базы данных 
```bash
spring.application.name=paysystem 
server.port = 8081 //ваш порт//

spring.datasource.url=jdbc:postgresql://localhost:5432/paysystem //ваша база данных//
spring.datasource.username=postgres //ваш юзернейм//
spring.datasource.password= //ваш пароль //

spring.thymeleaf.prefix=classpath:/templates/

spring.resources.static-locations=classpath:/static/

spring.datasource.driver-class-name=org.postgresql.Driver
```
### Далее можете запустить приложение 

## 3.После запуска проекта вам нужно перейти на Swagger нашего веб-сайта http://localhost:8081/swagger-ui/index.html 
![image](https://github.com/irrdscnt/PaySystem/assets/116700521/8c0410d9-af0a-4791-8af0-52fea203d853)

# Cтруктура проекта

## 1. Авторизация/Pегистрация пользователей с возможностью выбора роли
   ![2024-06-01_10-06-39](https://github.com/irrdscnt/PaySystem/assets/116700521/c20b31dc-530b-497e-b150-d9370f3cd3a9)
   ![2024-06-01_10-09-12](https://github.com/irrdscnt/PaySystem/assets/116700521/07211bc5-b3f4-4af2-b3c8-701ce2cade42)
## 2.Страница пользователя с подробной информацией о номере счёта, баланс, apikey ,  и action
<img width="1400" alt="Снимок экрана 2024-06-01 в 10 11 25" src="https://github.com/irrdscnt/PaySystem/assets/116700521/bd0ba718-b19c-4757-9f26-cc2b85209a2f">

## 3.Админ панель для администратора с возможностью отслеживать транзакции, фильтровать их, а так же контролировать статусы клиентской оплаты 
(голуби щевелятся)
![image (10)](https://github.com/irrdscnt/PaySystem/assets/116700521/836e11a6-9350-445f-b912-cdb42ecd7db7) 
### транцакзии с возможностью фильтрации
![image (11)](https://github.com/irrdscnt/PaySystem/assets/116700521/d01f7b8a-9c5d-4610-83a3-5817457fd380)
![image (12)](https://github.com/irrdscnt/PaySystem/assets/116700521/4b77eaf4-992a-4091-a02d-5c63a9544842)
### клиенты 
![image](https://github.com/irrdscnt/PaySystem/assets/116700521/a3619122-bacb-42e9-9c7e-f2ba69acf889)

## 4.Счёт со стороны компании и оплата со стороны пользователя 
![image (13)](https://github.com/irrdscnt/PaySystem/assets/116700521/eefe636d-ba7c-4987-80f3-b9254d66755f)

## 5.Основной лэндинг
![image (14)](https://github.com/irrdscnt/PaySystem/assets/116700521/1dc4a488-d009-4869-8a5c-6cf81f838667)
![image (15)](https://github.com/irrdscnt/PaySystem/assets/116700521/85755b5c-c10e-441a-9a18-0f5a6a14bc3d)
![image (16)](https://github.com/irrdscnt/PaySystem/assets/116700521/0f1a6702-8353-439f-ac07-a6db1060451a)
![image (17)](https://github.com/irrdscnt/PaySystem/assets/116700521/8ed467ef-139a-4bd7-aab9-fd78ac1d72f3)
![image (18)](https://github.com/irrdscnt/PaySystem/assets/116700521/e7969139-8121-4fe7-9852-da2ba8f15f44)
![image (19)](https://github.com/irrdscnt/PaySystem/assets/116700521/6e2964f3-d795-4e5a-8d4f-a3ec3053724c)
![image (20)](https://github.com/irrdscnt/PaySystem/assets/116700521/c847cb9e-07f1-43d5-9fdc-e327705530a5)

## 6.Дополнительные страницы для разделов O НАС , КОНТАКТЫ 
(рука шавелится)
![photo1717212248](https://github.com/irrdscnt/PaySystem/assets/116700521/f479df8f-dd48-445c-82f0-ae576270f192)
![photo1717212248 (1)](https://github.com/irrdscnt/PaySystem/assets/116700521/d1d534e5-72d4-408f-a852-f472e46118e1)

### контакты 
![image](https://github.com/irrdscnt/PaySystem/assets/116700521/c57bd164-ec02-4bed-ac63-c70e8d19016b)

# Теперь вы ознакомлены с нашим проектом!
## проект всё ещё находится в разработке , но уже представляет собой перспективный аналог PayPal и многих других платёжных онлайн-систем 








   



  
