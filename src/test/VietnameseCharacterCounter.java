package test;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VietnameseCharacterCounter {
    private static final Map<String, String> telexToVietnameseMap = createTelexToVietnameseMap();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập chuỗi chữ cái Latin: ");
        String input = scanner.nextLine();

        int vietnameseCharCount = countVietnameseCharacters(input);
        System.out.println("Số lượng chữ cái Tiếng Việt có thể tạo thành: " + vietnameseCharCount);
    }

    private static Map<String, String> createTelexToVietnameseMap() {
        Map<String, String> map = new HashMap<>();
        // Định nghĩa một danh sách các cặp chữ cái Telex và Tiếng Việt tương ứng: ă=aw, â=aa, đ=dd, ê=ee, ô=oo, ơ=ow, ư=w.
        map.put("aw", "ă");
        map.put("aa", "â");
        map.put("dd", "đ");
        map.put("ee", "ê");
        map.put("oo", "ô");
        map.put("ow", "ơ");
        map.put("w", "ư");
        return map;
    }

    public static int countVietnameseCharacters(String input) {
        int count = 0;

        for (int i = 0; i < input.length(); i++) {
            String telexPair = input.substring(i, i + 1);
            if (telexToVietnameseMap.containsKey(telexPair)) {
                count++;
                i++; // Bỏ qua ký tự kế tiếp
            } else if (i < input.length() - 1) {
                String telexDoublePair = input.substring(i, i + 2);
                if (telexToVietnameseMap.containsKey(telexDoublePair)) {
                    count++;
                    i += 2; // Bỏ qua cả cặp ký tự
                }
            }
        }
        return count;
    }
}
