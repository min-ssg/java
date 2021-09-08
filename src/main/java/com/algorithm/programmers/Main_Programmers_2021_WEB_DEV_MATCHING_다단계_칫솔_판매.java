package com.algorithm.programmers;

import com.algorithm.entity.Seller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main_Programmers_2021_WEB_DEV_MATCHING_다단계_칫솔_판매 {
    public static void main(String[] args) {

        String[] enroll = {"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String[] referral  = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};
        String[] seller = {"young", "john", "tod", "emily", "mary"};
        int[] amount = {12, 4, 2, 5, 10};

        Map<String, Seller> sellerMap = new HashMap<>();
        sellerMap.put("minho", new Seller("minho", null));

        for (int i = 0; i < enroll.length; i++) {
            Seller parent = "-".equals(referral[i]) ? sellerMap.get("minho") : sellerMap.get(referral[i]);
            sellerMap.put(enroll[i], new Seller(enroll[i], parent));
        }
        Arrays.stream(enroll).forEach(e -> System.out.println(String.format("%s=%s", e, sellerMap.get(e))));

        for (int i = 0; i < seller.length; i++) {
            Seller varSeller = sellerMap.get(seller[i]);
            int sell = amount[i] * 100;
            varSeller.addMoney(sell);
            System.out.println(String.format("-----------START[%s]-----------", seller[i]));
            while(varSeller.hasParent()) {
                varSeller = varSeller.getParent();
                System.out.println("parent incentive = " + (sell * 10 / 100));
                varSeller.addMoney(sell * 10 / 100);
                System.out.println("Parent = " + varSeller.getName() + ", money = " + varSeller.getMoney());
                sell = sell * 10 / 100;
            }
            System.out.println(String.format("-----------END[%s]-----------", seller[i]));
        }

        sellerMap.entrySet().stream().forEach(e -> System.out.println(String.format("%s=%d", e.getKey(), e.getValue().getMoney())));
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        return answer;
    }
}
