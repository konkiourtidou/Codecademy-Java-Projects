// Import statement:
import java.util.ArrayList;

public class PrimeDirective {

    // Add your methods here:

    public boolean isPrime(int number){
        if (number < 2){
            return false;
        } else if(number == 2){
            return true;
        }
        boolean flag = true;
        int i = 2;
        while (flag == true && i < number){
            if (number % i == 0){
                flag = false;
            }
            i += 1;
        }
        return flag;
    }

    public ArrayList<Integer> onlyPrimes(int[] numbers) {
        ArrayList<Integer> primes = new ArrayList<Integer>();

        for (int number : numbers){
            if (isPrime(number)){
                primes.add(number);
            }
        }

        return primes;
    }

    public static void main(String[] args) {

        PrimeDirective pd = new PrimeDirective();
        int[] numbers = {6, 29, 28, 33, 11, 100, 101, 43, 89};
    /*
    System.out.println(pd.isPrime(7));
    System.out.println(pd.isPrime(28));
    System.out.println(pd.isPrime(2));
    System.out.println(pd.isPrime(0));
    System.out.println(pd.isPrime(6));
    */
        System.out.println(pd.onlyPrimes(numbers));

    }

}
