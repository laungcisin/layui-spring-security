public class Test {
    public static void main(String[] args) {
        double arr[] = {569.97, 308.16, 308.16, 317.57};
        int days = 30;

        for (int i = 1; i <= days; i++) {
            for (int j = 0; j < 4; j++) {
                arr[j] *= 1.0225;
            }


            for (int k = 0; k < 3; k++) {
                if (arr[k] > 311.0) {
                    arr[k] -= 11.0;
                    arr[4] += 10;
                } else {
                    arr[k] *= 1.0225;
                }
            }
        }

        System.out.println(arr);
    }
}
