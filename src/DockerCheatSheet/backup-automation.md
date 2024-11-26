## Пример 1: Автоматизация бэкапов с помощью скриптов

Создайте скрипт для автоматического создания бэкапов и их хранения.
Terminal

    #!/bin/bash

#### Создание бэкапа тома
    docker run --rm -v my_volume:/data -v /path/to/backup:/backup busybox tar czf /backup/my_volume_backup_$(date +%F).tar.gz -C /data .

#### Очистка старых бэкапов (старше 7 дней)
    find /path/to/backup -type f -name "*.tar.gz" -mtime +7 -exec rm {} \;

## Пример 2: Восстановление данных после сбоя

Используйте бэкапы для восстановления данных после сбоя.
Terminal

#### Остановка контейнера
    docker stop my_container

#### Удаление старых данных
    docker volume rm my_volume

#### Восстановление данных из бэкапа
    docker run --rm -v my_volume:/data -v /path/to/backup:/backup busybox tar xzf /backup/my_volume_backup_latest.tar.gz -C /data

#### Перезапуск контейнера
    docker start my_container