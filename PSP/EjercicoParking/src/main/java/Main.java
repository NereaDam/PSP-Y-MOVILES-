
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;



    void main() {

        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        final int[] plazasLibres = {3};

        Runnable tareaCoche = () -> {

            if (lock.tryLock()) {

                while (plazasLibres[0] == 0) {
                    try {
                        condition.await();
                        System.out.println("Esperando aparcamiento");
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                }
                System.out.println("consigo plaza");
                plazasLibres[0]--;
                lock.unlock();
                System.out.println("aparcado");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                lock.lock();
                plazasLibres[0]++;
                condition.signal();
                System.out.println("Me voy");

                lock.unlock();


            }
        };

        for (int i = 0; i < 5; i++) {
            Thread coche = new Thread(tareaCoche);
            coche.start();

        }


    }


