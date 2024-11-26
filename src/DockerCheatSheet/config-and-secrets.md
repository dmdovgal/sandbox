# docker config & docker secret

### Основные команды docker config

    docker config create — Создание новой конфигурации
    docker config ls — Список всех конфигураций
    docker config inspect — Получение информации о конфигурации
    docker config rm — Удаление конфигурации

#### Пример    
    docker config create nginx_config ./nginx.conf
    docker config ls
    docker service create --name nginx --config source=nginx_config,target=/etc/nginx/nginx.conf -p 8080:80 nginx
    docker config rm nginx_config

То есть можно в докере раз создать конфигурацию и ее уже передавать, а не копировать везде файлик по 10 раз

### Основные команды docker secret

    docker secret create — Создание нового секрета
    docker secret ls — Список всех секретов
    docker secret inspect — Получение информации о секрете
    docker secret rm — Удаление секрета

#### Пример

Создаем секрет с паролем из файлика. Файл потом можно грохнуть.

    docker secret create db_password ./db_password.txt
    docker secret ls
    docker service create --name postgres --secret db_password -e
        POSTGRES_PASSWORD_FILE=/run/secrets/ db_password postgres
    docker secret rm db_password