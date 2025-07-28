import java.util.Scanner;

public class HealthCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Health Calculator!");

        System.out.print("Enter your age: ");
        int age = scanner.nextInt();

        System.out.print("Enter your gender (M/F): ");
        char gender = scanner.next().toUpperCase().charAt(0);

        System.out.print("Choose weight unit (1 for kg, 2 for lbs): ");
        int weightUnit = scanner.nextInt();
        System.out.print("Enter your weight: ");
        double weight = scanner.nextDouble();
        if (weightUnit == 2) {
            weight = weight * 0.453592; // convert lbs to kg
        }

        System.out.print("Choose height unit (1 for cm, 2 for ft-in): ");
        int heightUnit = scanner.nextInt();
        double heightCm;
        if (heightUnit == 1) {
            System.out.print("Enter your height in cm: ");
            heightCm = scanner.nextDouble();
        } else {
            System.out.print("Enter feet: ");
            int feet = scanner.nextInt();
            System.out.print("Enter inches: ");
            int inches = scanner.nextInt();
            heightCm = (feet * 12 + inches) * 2.54;
        }

        double heightM = heightCm / 100.0;

        System.out.println("\n--- Results ---");

        // BMI
        double bmi = weight / (heightM * heightM);
        System.out.printf("BMI: %.2f (%s)\n", bmi, interpretBMI(bmi));

        // Water Intake
        double waterLiters = weight * 0.033;
        System.out.printf("Recommended daily water intake: %.2f liters\n", waterLiters);

        // BMR
        double bmr;
        if (gender == 'M') {
            bmr = 10 * weight + 6.25 * heightCm - 5 * age + 5;
        } else {
            bmr = 10 * weight + 6.25 * heightCm - 5 * age - 161;
        }
        System.out.printf("BMR (Basal Metabolic Rate): %.2f kcal/day\n", bmr);

        // Calorie Needs
        System.out.println("Select your activity level:");
        System.out.println("1. Sedentary");
        System.out.println("2. Lightly active");
        System.out.println("3. Moderately active");
        System.out.println("4. Very active");
        System.out.println("5. Super active");
        int activity = scanner.nextInt();
        double multiplier;
        switch (activity) {
            case 1 -> multiplier = 1.2;
            case 2 -> multiplier = 1.375;
            case 3 -> multiplier = 1.55;
            case 4 -> multiplier = 1.725;
            case 5 -> multiplier = 1.9;
            default -> multiplier = 1.2;
        }
        double dailyCalories = bmr * multiplier;
        System.out.printf("Estimated daily calorie needs: %.2f kcal/day\n", dailyCalories);

        // Ideal Weight
        double idealWeight;
        double heightInInches = heightCm / 2.54;
        if (gender == 'M') {
            idealWeight = 50 + 2.3 * (heightInInches - 60);
        } else {
            idealWeight = 45.5 + 2.3 * (heightInInches - 60);
        }
        System.out.printf("Estimated ideal weight: %.2f kg\n", idealWeight);

        scanner.close();
    }

    private static String interpretBMI(double bmi) {
        if (bmi < 18.5) return "Underweight";
        else if (bmi < 24.9) return "Normal weight";
        else if (bmi < 29.9) return "Overweight";
        else return "Obese";
    }
}  
