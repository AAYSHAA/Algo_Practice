public class Practice4 {
    public static void main(String[] args){
        int numbers[] = {1,9,8,5,7,3,2,6,7,4,5};
        for (int i = 0; i < numbers.length; i++) {
            int count = 0;
            for (int j = 0; j < numbers.length; j++) {
                if (numbers[i] == numbers[j]) {
                    count++;
                    //System.out.println("Triplicate indexes: "+ i+" and "+j);
                    if (count == 3) {
                        // return i;
                        System.out.println("Triplicate indexes : "+ i);
                    }

                }

            }

        }
       // return -1; // Failure case
    }
}
