package com.example.javatips;

import com.example.javatips.models.Invoice;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@ExtendWith(SpringExtension.class)
public class JavaEightTest {

    List<Invoice> invoices =
            Arrays
                    .asList(
                            new Invoice(1l, "Developer",2400.30),
                            new Invoice(2l, "Architect",6000.40),
                            new Invoice(4l, "Training",50),
                            new Invoice(3l, "Manager",740.00)
                    );

    @Test
    @DisplayName("sort a list of invoices")
    void test01() {

        //old
//        Collections.sort(invoices, new Comparator<Invoice>() {
//            public int compare(Invoice inv1, Invoice inv2) {
//                return Double.compare(inv2.amount(), inv1.amount());
//            }
//        });

        //new
//      invoices.sort(Comparator.comparingDouble(Invoice::amount));
        invoices.sort(Comparator.comparingDouble(Invoice::amount).reversed());

        System.out.println(invoices);
    }

    @Test
    @DisplayName("stream , filter & parallelStream")
    void test02() {
//        List<Long> ids = invoices
//                .stream()
//                .filter(inv -> inv.amount() > 1_000)
//                .map(Invoice::id)
//                .collect(Collectors.toList());

        List<Long> ids = invoices
                .parallelStream()
                .filter(inv -> inv.amount() > 1_000)
                .map(Invoice::id)
                .collect(Collectors.toList());

        System.out.println(ids);
    }

    @Test
    @DisplayName("lambda expressions and streams.")
    void test03() {
        //old
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("Hi");
//            }
//        };
//        new Thread(runnable).start();

        //new
        new Thread(() -> System.out.println("Hi")).start();
    }

    @Test
    @DisplayName("Method references")
    void test04() {
        List<String> strs = Arrays.asList("C", "a", "A", "b");

        //old
//        Collections.sort(strs, new Comparator<String>() {
//            @Override
//            public int compare(String s1, String s2) {
//                return s1.compareToIgnoreCase(s2);
//            }
//        });

        //new
        Collections.sort(strs, String::compareToIgnoreCase);
        System.out.println(strs);
    }

    @Test
    @DisplayName("Streams")
    void test05() {
        //It processes a list of invoices to find the IDs of training-related invoices sorted by the invoice’s amount:

        //old
//        List<Invoice> trainingInvoices = new ArrayList<>();
//        for(Invoice inv: invoices) {
//            if(inv.title().contains("Training")) {
//                trainingInvoices.add(inv);
//            }
//        }
//        Collections.sort(trainingInvoices, new Comparator() {
//            public int compare(Invoice inv1, Invoice inv2) {
//                return inv2.title().compareTo(inv1.title());
//            }
//        });
//        List<Integer> invoiceIds = new ArrayList<>();
//        for(Invoice inv: trainingInvoices) {
//            invoiceIds.add(inv.id());
//        }

        //new
        List<Long> invoiceIds =
                invoices.stream()
                        .filter(inv -> inv.title().contains("Training"))
                        .sorted(Comparator.comparingDouble(Invoice::amount)
                                .reversed())
                        .map(Invoice::id)
                        .collect(Collectors.toList());

        System.out.println(invoiceIds);

    }

    @Test
    @DisplayName("Enhanced Interfaces")
    void test06() {
        // default methods
        // Interfaces can now also have static methods.

    }

    @Test
    @DisplayName("New Date and Time API")
    void test07() {
        // default methods
        // Interfaces can now also have static methods.

        LocalDateTime coffeeBreak = LocalDateTime.now()
                .plusHours(2)
                .plusMinutes(30);

        ZoneId london = ZoneId.of("Europe/London");
        LocalDate july4 = LocalDate.of(2014, Month.JULY, 4);
        LocalTime early = LocalTime.parse("08:45");
        ZonedDateTime flightDeparture = ZonedDateTime.of(july4, early,
                london);
        System.out.println(flightDeparture);
        LocalTime from = LocalTime.from(flightDeparture);
        System.out.println(from);
        ZonedDateTime touchDown
                = ZonedDateTime.of(july4,
                LocalTime.of (11, 35),
                ZoneId.of("Europe/Stockholm"));
        Duration flightLength = Duration.between(flightDeparture, touchDown);
        System.out.println(flightLength);
        // How long have I been in continental Europe?
        ZonedDateTime now = ZonedDateTime.now();
        Duration timeHere = Duration.between(touchDown, now);
        System.out.println(timeHere);

    }

    @Test
    @DisplayName("Optional")
    void test08() {
//        Optional.ofNullable(getEventWithId(5))
//                .flatMap(this::getLocation)
//                .map(this::getCity)
//                .orElse("TBC");
    }

    @Test
    @DisplayName("lambda expressions,")
    void test09() {

    }

}