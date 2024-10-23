Реализуй логику метода run в CLIENT_THREAD. В нем будет имитироваться клиентская часть, которая коннектится к серверу.
1) Из registry получи сервис с именем UNIC_BINDING_NAME.
2) Вызови метод у полученного сервиса, передай любой не пустой аргумент.
3) Выведи в консоль результат вызова метода.
4) Обработай специфические исключения.
   Метод main не участвует в тестировании.

Требования:
* В методе run() необходимо из registry получить сервис с именем UNIC_BINDING_NAME.
* В методе run() необходимо вызвать метод doubleString (String) у полученного сервиса.
* В методе run() необходимо вывести в консоль результат вызова метода doubleString (String).
* В методе run() должен быть перехват исключения RemoteException.
* В методе run() должен быть перехват исключения NotBoundException.