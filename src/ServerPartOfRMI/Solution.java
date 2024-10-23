package ServerPartOfRMI;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/*
RMI-2
*/

public class Solution {
    public static final String DOG_BINDING_NAME = "dog.service";
    public static final String CAT_BINDING_NAME = "cat.service";
    public static Registry registry;

    // Pretend we're starting an RMI client as the CLIENT_THREAD thread
    public static Thread CLIENT_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                for (String bindingName : registry.list()) {
                    Animal service = (Animal) registry.lookup(bindingName);
                    service.printName();
                    service.speak();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            } catch (NotBoundException e) {
                e.printStackTrace();
            }
        }
    });

    // Pretend we're starting an RMI server as the SERVER_THREAD thread
    public static Thread SERVER_THREAD = new Thread(new Runnable() {
        @Override
        public void run() {
            try {
                registry = LocateRegistry.createRegistry(2099);

                final Cat serviceCat = new Cat("Biba");
                final Dog serviceDog = new Dog ("Booba");

                Remote stubCat = UnicastRemoteObject.exportObject(serviceCat, 0);
                registry.bind(CAT_BINDING_NAME, stubCat);
                Remote stubDog = UnicastRemoteObject.exportObject(serviceDog, 0);
                registry.bind(DOG_BINDING_NAME, stubDog);
            } catch (RemoteException | AlreadyBoundException e) {
                throw new RuntimeException(e);
            }
        }
    });

    public static void main(String[] args) throws InterruptedException {
        // Starting an RMI server thread
        SERVER_THREAD.start();
        Thread.sleep(1000);
        // Start the client
        CLIENT_THREAD.start();
    }
}
