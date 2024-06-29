import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class Laptop {
    private String brand;
    private int ram; // in GB
    private int hdd; // in GB
    private String os;
    private String color;

    public Laptop(String brand, int ram, int hdd, String os, String color) {
        this.brand = brand;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", ram=" + ram +
                ", hdd=" + hdd +
                ", os='" + os + '\'' +
                ", color='" + color + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();

        laptops.add(new Laptop("Dell", 8, 512, "Windows 10", "Black"));
        laptops.add(new Laptop("HP", 16, 1024, "Windows 10", "Silver"));
        laptops.add(new Laptop("Apple", 8, 256, "MacOS", "Gray"));
        laptops.add(new Laptop("Asus", 32, 2048, "Windows 10", "Black"));
        laptops.add(new Laptop("Lenovo", 4, 500, "Linux", "White"));

        filterLaptops(laptops);
    }

    public static void filterLaptops(Set<Laptop> laptops) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Integer> criteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int criterion = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (criterion) {
            case 1:
                System.out.println("Введите минимальное значение ОЗУ (в ГБ):");
                criteria.put("ram", scanner.nextInt());
                break;
            case 2:
                System.out.println("Введите минимальное значение объема ЖД (в ГБ):");
                criteria.put("hdd", scanner.nextInt());
                break;
            case 3:
                System.out.println("Введите операционную систему:");
                criteria.put("os", scanner.nextLine().hashCode());
                break;
            case 4:
                System.out.println("Введите цвет:");
                criteria.put("color", scanner.nextLine().hashCode());
                break;
            default:
                System.out.println("Неверный критерий");
                return;
        }

        for (Laptop laptop : laptops) {
            boolean matches = true;
            for (Map.Entry<String, Integer> entry : criteria.entrySet()) {
                switch (entry.getKey()) {
                    case "ram":
                        if (laptop.getRam() < entry.getValue()) {
                            matches = false;
                        }
                        break;
                    case "hdd":
                        if (laptop.getHdd() < entry.getValue()) {
                            matches = false;
                        }
                        break;
                    case "os":
                        if (!laptop.getOs().equalsIgnoreCase(new String(entry.getValue().toString()))) {
                            matches = false;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equalsIgnoreCase(new String(entry.getValue().toString()))) {
                            matches = false;
                        }
                        break;
                }
            }
            if (matches) {
                System.out.println(laptop);
            }
        }
    }
}