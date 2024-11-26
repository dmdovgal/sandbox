#### Создайте том с именем my_data_volume
docker volume create my_data_volume

#### Запустите контейнер с Nginx, смонтировав том my_data_volume в директорию /data контейнера
docker run -d --name nginx_with_volume -v my_data_volume:/data nginx

# Запустите контейнер с Nginx, смонтировав локальную директорию /home/user/website
# в директорию /usr/share/nginx/html контейнера
mkdir /home/user/website
docker run --name nnn -v /home/user/website:/usr/share/nginx/html nginx

# Проверьте, что контейнер запущен и директория смонтирована
docker inspect nnn | grep "Mounts"

#### Проверьте, что том подключен к контейнеру
docker inspect nginx_with_volume | grep "Mounts"

#### Проверьте, что том создан
docker volume ls