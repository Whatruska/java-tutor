package offtop;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Currencies {

    private static class Transaction {
        private String from;
        private String to;
        private String currency;
        private int amount;

        public Transaction(String from, String to, String currency, int amount) {
            this.from = from;
            this.to = to;
            this.currency = currency;
            this.amount = amount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }

    private static class CurrencyInfo {
        private String currency;
        private int uniAmount;

        public CurrencyInfo(String currency, int uniAmount) {
            this.currency = currency;
            this.uniAmount = uniAmount;
        }

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public int getUniAmount() {
            return uniAmount;
        }

        public void setUniAmount(int uniAmount) {
            this.uniAmount = uniAmount;
        }
    }

    public static void main(String[] args) {
        List<Transaction> transactions = new ArrayList<>();
        List<CurrencyInfo> currencies = new ArrayList<>();
        Map<String, Integer> totalMap = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        String cmd = scanner.nextLine();
        List<String> lines = new ArrayList<>();
        do {
            lines.add(cmd);
            cmd = scanner.nextLine();
        } while (!cmd.equals("$"));

        transactions = lines.stream().filter(str -> str.startsWith("t")).map(str -> {
            String[] parts = str.split("\\s+");
            return new Transaction(parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
        }).collect(Collectors.toList());

        currencies = lines.stream().filter(str -> str.startsWith("c")).map(str -> {
            String[] parts = str.split(" ");
            return new CurrencyInfo(parts[1], Integer.parseInt(parts[2]));
        }).collect(Collectors.toList());

        List<CurrencyInfo> finalCurrencies = currencies;
        totalMap = transactions.stream()
                .flatMap(t -> {
                    int uniVal = finalCurrencies.stream().filter(curr -> curr.getCurrency().equals(t.getCurrency())).findFirst().orElse(new CurrencyInfo("", 0)).getUniAmount();
                    return Stream.of(
                                    new AbstractMap.SimpleEntry<String,Integer>(t.getFrom(), -t.getAmount() * uniVal),
                                    new AbstractMap.SimpleEntry<String,Integer>(t.getTo(), t.getAmount() * uniVal)
                    );
                }).collect(
                        Collectors.toMap(
                                AbstractMap.SimpleEntry::getKey,
                                AbstractMap.SimpleEntry::getValue,
                                Integer::sum
                        )
                );
        Map<String, Integer> finalTotalMap = totalMap;
        totalMap.keySet().stream().sorted().forEach(name -> {
            System.out.println(name + " " + finalTotalMap.get(name));
        });
    }
}
