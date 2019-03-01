package com.someshop.sneakershop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}

/*
        announcementRepo.deleteAllInBatch();
        sneakerRepo.deleteAllInBatch();

        Sneaker sneaker1 = new Sneaker("Adibas", "Iniki", "blue-red");
        Announcement announcement1 = new Announcement(100.3f, 0);

        sneaker1.setAnnouncement(announcement1);
        announcement1.setSneaker(sneaker1);

        sneakerRepo.save(sneaker1);
        announcementRepo.save(announcement1);
* */


