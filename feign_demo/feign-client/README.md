# client-feign_demo

# Описание
Учебный сервис для демонстрации работы http REST клиента Feign
используется совместно с сервисом server-feign_demo  
Представлен:  
- пример работы с калсическим rest template  
- пример работы с с Feign при условии самостоятельной реализации REST интерфейса  
- пример настройки работы Feign через https  
- пример работы Feign с генератором кода

# Предустановленное 
jdk, postman или другое приложение для отправки запросов
 
# Как запустить: 
кликнуть на кнопку старт в IDE
 
# Примечания
при необходимости поменяйте порт приложения в application.yaml
если вы меняли порты в проекте server-feign_demo, то не забудьте поменять в этом проекте урлы в секции demo-service.url файла application.yaml

# Как потестировать:
имеется 4 эндпоинта, каждый принимает 2 параметра типа Integer  
GET /generated - взаимодействие с server-feign_demo с использованием генератора кода
GET /secured - взаимодействие с server-feign_demo по https
GET /classic - взаимодействие с server-feign_demo с использованием самостоятельно реализованных классов и интерфейсов
GET /rest-template - взаимодействие с server-feign_demo с использованием restTemplate

# Ссылки на полезные статьи
[Презентация по проекту](https://docs.google.com/presentation/d/1klo5xvWM3Dp6ScZruI2Va3B7D4swduQ6U24AfGzXVxs/edit?usp=sharing)
