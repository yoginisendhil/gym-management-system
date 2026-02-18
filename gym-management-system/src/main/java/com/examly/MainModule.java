package com.examly;

import com.examly.entity.*;
import com.examly.service.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    private static Scanner scanner = new Scanner(System.in);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    private static MemberDAO memberDAO = new MemberDAOImpl();
    private static TrainerDAO trainerDAO = new TrainerDAOImpl();
    private static WorkoutPlanDAO workoutPlanDAO = new WorkoutPlanDAOImpl();
    private static EquipmentDAO equipmentDAO = new EquipmentDAOImpl();
    private static MembershipDAO membershipDAO = new MembershipDAOImpl();
    private static PaymentDAO paymentDAO = new PaymentDAOImpl();
    
    public static void main(String[] args) {
        boolean exit = false;
        
        while (!exit) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1: registerMember(); break;
                case 2: viewAllMembers(); break;
                case 3: addTrainer(); break;
                case 4: viewAllTrainers(); break;
                case 5: createWorkoutPlan(); break;
                case 6: viewWorkoutPlansByTrainer(); break;
                case 7: addEquipment(); break;
                case 8: viewAllEquipment(); break;
                case 9: createMembership(); break;
                case 10: viewMembershipsByMember(); break;
                case 11: makePayment(); break;
                case 12: viewPaymentsByMembership(); break;
                case 13: 
                    exit = true;
                    System.out.println("Exiting Gym Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            if (!exit) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    private static void displayMenu() {
        System.out.println("\n=== Gym Management System ===");
        System.out.println("1. Register Member");
        System.out.println("2. View All Members");
        System.out.println("3. Add Trainer");
        System.out.println("4. View All Trainers");
        System.out.println("5. Create Workout Plan");
        System.out.println("6. View Workout Plans by Trainer");
        System.out.println("7. Add Equipment");
        System.out.println("8. View All Equipment");
        System.out.println("9. Create Membership");
        System.out.println("10. View Memberships by Member");
        System.out.println("11. Make Payment");
        System.out.println("12. View Payments by Membership");
        System.out.println("13. Exit");
        System.out.println("=============================");
    }
    
    private static void registerMember() {
        System.out.println("\n=== Register New Member ===");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        Member existingMember = memberDAO.getMemberByEmail(email);
        if (existingMember != null) {
            System.out.println("Member with this email already exists!");
            return;
        }
        
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        int age = getIntInput("Enter age: ");
        
        System.out.print("Enter gender (Male/Female/Other): ");
        String gender = scanner.nextLine();
        
        Member member = new Member(name, email, phone, password, age, gender);
        
        if (memberDAO.addMember(member)) {
            System.out.println("Member registered successfully!");
        } else {
            System.out.println("Failed to register member.");
        }
    }
    
    private static void viewAllMembers() {
        System.out.println("\n=== All Members ===");
        List<Member> members = memberDAO.getAllMembers();
        
        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            for (Member member : members) {
                System.out.println(member);
            }
        }
    }
    
    private static void addTrainer() {
        System.out.println("\n=== Add New Trainer ===");
        
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter specialization: ");
        String specialization = scanner.nextLine();
        
        System.out.print("Enter contact number: ");
        String contact = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        Trainer trainer = new Trainer(name, specialization, contact, email);
        
        if (trainerDAO.addTrainer(trainer)) {
            System.out.println("Trainer added successfully!");
        } else {
            System.out.println("Failed to add trainer.");
        }
    }
    
    private static void viewAllTrainers() {
        System.out.println("\n=== All Trainers ===");
        List<Trainer> trainers = trainerDAO.getAllTrainers();
        
        if (trainers.isEmpty()) {
            System.out.println("No trainers found.");
        } else {
            for (Trainer trainer : trainers) {
                System.out.println(trainer);
            }
        }
    }
    
    private static void createWorkoutPlan() {
        System.out.println("\n=== Create Workout Plan ===");
        
        List<Trainer> trainers = trainerDAO.getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("No trainers available. Please add a trainer first.");
            return;
        }
        
        System.out.println("Available Trainers:");
        for (Trainer trainer : trainers) {
            System.out.println("ID: " + trainer.getTrainerId() + ", Name: " + trainer.getName());
        }
        
        int trainerId = getIntInput("Enter trainer ID: ");
        
        System.out.print("Enter plan name: ");
        String planName = scanner.nextLine();
        
        int duration = getIntInput("Enter duration in weeks: ");
        
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        
        WorkoutPlan plan = new WorkoutPlan(trainerId, planName, duration, description);
        
        if (workoutPlanDAO.addWorkoutPlan(plan)) {
            System.out.println("Workout plan created successfully!");
        } else {
            System.out.println("Failed to create workout plan.");
        }
    }
    
    private static void viewWorkoutPlansByTrainer() {
        System.out.println("\n=== View Workout Plans by Trainer ===");
        
        List<Trainer> trainers = trainerDAO.getAllTrainers();
        if (trainers.isEmpty()) {
            System.out.println("No trainers found.");
            return;
        }
        
        System.out.println("Available Trainers:");
        for (Trainer trainer : trainers) {
            System.out.println("ID: " + trainer.getTrainerId() + ", Name: " + trainer.getName());
        }
        
        int trainerId = getIntInput("Enter trainer ID to view plans: ");
        
        List<WorkoutPlan> plans = workoutPlanDAO.getPlansByTrainer(trainerId);
        
        if (plans.isEmpty()) {
            System.out.println("No workout plans found for this trainer.");
        } else {
            System.out.println("Workout Plans for Trainer ID " + trainerId + ":");
            for (WorkoutPlan plan : plans) {
                System.out.println(plan);
            }
        }
    }
    
    private static void addEquipment() {
        System.out.println("\n=== Add New Equipment ===");
        
        System.out.print("Enter equipment name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter equipment type: ");
        String type = scanner.nextLine();
        
        int quantity = getIntInput("Enter quantity: ");
        
        System.out.print("Enter maintenance status (Good/Repair): ");
        String status = scanner.nextLine();
        
        Equipment equipment = new Equipment(name, type, quantity, status);
        
        if (equipmentDAO.addEquipment(equipment)) {
            System.out.println("Equipment added successfully!");
        } else {
            System.out.println("Failed to add equipment.");
        }
    }
    
    private static void viewAllEquipment() {
        System.out.println("\n=== All Equipment ===");
        List<Equipment> equipmentList = equipmentDAO.getAllEquipment();
        
        if (equipmentList.isEmpty()) {
            System.out.println("No equipment found.");
        } else {
            for (Equipment eq : equipmentList) {
                System.out.println(eq);
            }
        }
    }
    
    private static void createMembership() {
        System.out.println("\n=== Create Membership ===");
        
        List<Member> members = memberDAO.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members available. Please register a member first.");
            return;
        }
        
        System.out.println("Available Members:");
        for (Member member : members) {
            System.out.println("ID: " + member.getMemberId() + ", Name: " + member.getName());
        }
        
        int memberId = getIntInput("Enter member ID: ");
        
        System.out.print("Enter plan type (Monthly/Quarterly/Yearly): ");
        String planType = scanner.nextLine();
        
        System.out.print("Enter start date (yyyy-MM-dd): ");
        String startDateStr = scanner.nextLine();
        
        System.out.print("Enter end date (yyyy-MM-dd): ");
        String endDateStr = scanner.nextLine();
        
        double totalFee = getDoubleInput("Enter total fee: ");
        
        try {
            Date startDate = dateFormat.parse(startDateStr);
            Date endDate = dateFormat.parse(endDateStr);
            
            Membership membership = new Membership(memberId, planType, startDate, endDate, totalFee);
            
            if (membershipDAO.addMembership(membership)) {
                System.out.println("Membership created successfully!");
            } else {
                System.out.println("Failed to create membership.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
        }
    }
    
    private static void viewMembershipsByMember() {
        System.out.println("\n=== View Memberships by Member ===");
        
        List<Member> members = memberDAO.getAllMembers();
        if (members.isEmpty()) {
            System.out.println("No members found.");
            return;
        }
        
        System.out.println("Available Members:");
        for (Member member : members) {
            System.out.println("ID: " + member.getMemberId() + ", Name: " + member.getName());
        }
        
        int memberId = getIntInput("Enter member ID to view memberships: ");
        
        List<Membership> memberships = membershipDAO.getMembershipsByMember(memberId);
        
        if (memberships.isEmpty()) {
            System.out.println("No memberships found for this member.");
        } else {
            System.out.println("Memberships for Member ID " + memberId + ":");
            for (Membership membership : memberships) {
                System.out.println(membership);
            }
        }
    }
    
    private static void makePayment() {
        System.out.println("\n=== Make Payment ===");
        
        int membershipId = getIntInput("Enter membership ID: ");
        double amount = getDoubleInput("Enter amount paid: ");
        
        System.out.print("Enter payment date (yyyy-MM-dd): ");
        String paymentDateStr = scanner.nextLine();
        
        System.out.print("Enter payment status (Completed/Failed): ");
        String status = scanner.nextLine();
        
        try {
            Date paymentDate = dateFormat.parse(paymentDateStr);
            Payment payment = new Payment(membershipId, amount, paymentDate, status);
            
            if (paymentDAO.addPayment(payment)) {
                System.out.println("Payment recorded successfully!");
            } else {
                System.out.println("Failed to record payment.");
            }
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
        }
    }
    
    private static void viewPaymentsByMembership() {
        System.out.println("\n=== View Payments by Membership ===");
        
        int membershipId = getIntInput("Enter membership ID to view payments: ");
        
        List<Payment> payments = paymentDAO.getPaymentsByMembership(membershipId);
        
        if (payments.isEmpty()) {
            System.out.println("No payments found for this membership.");
        } else {
            System.out.println("Payments for Membership ID " + membershipId + ":");
            for (Payment payment : payments) {
                System.out.println(payment);
            }
        }
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
    
    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }
}