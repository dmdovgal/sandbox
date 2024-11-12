# Tips & Trick on MySQL 8 installation and getting ready for use

There are lots of manual how to install a MySQL Server on localhost and access it. Here some tips on how to make it running 
on a virtual machine and getting accessed remotely.

Since life is changing every day, let fix our versions of the document.
The document is actual on:
* Q4 2024

## Steps to be done
1. One
2. Two
3. Three

## How to set & recover root password in MySQL

### 1.Set root password
```
root@ubuntu ~]# mysql -u root

mysql> Alter user 'root'@'localhost' identified WITH mysql_native_password by 'KAvghytfJA{3ab';

mysql> flush privileges;

mysql> exit;
```

### 2. Recover root password if you even did not know it

1. Stop Mysql Service.
```
root@ubuntu ~]# systemctl stop mysql
```

2. Check if mysqld dir exists, if not then create it and set the owner
```
root@ubuntu ~]# mkdir /var/run/mysqld

root@ubuntu ~]# chown mysql /var/run/mysqld
```

3. Start MySQL with in "safe mode" --skip grant-tables&
```
root@ubuntu ~]# mysqld_safe --skip-grant-tables &
```

When the --skip-grant-tables option is used, anyone can connect to the database server without a password and with all privileges granted.

4. Login without a password
```
root@ubuntu ~]# mysql -u root
```
5. Set a new root password
```
mysql> UPDATE mysql.user SET authentication_string=null WHERE User='root';
mysql> flush privileges;
mysql> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'your_password_here';
mysql> flush privileges;
```

6. Kill all MYSQL processes before starting the service again.
```
root@ubuntu ~]# killall -u mysql
```

7. Start Mysql
```
root@ubuntu ~]# systemctl start mysql
```